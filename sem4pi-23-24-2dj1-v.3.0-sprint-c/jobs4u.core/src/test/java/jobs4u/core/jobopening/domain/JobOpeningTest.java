package jobs4u.core.jobopening.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.domain.PhoneNumber;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.jobopening.repositories.JobOpeningRepository;
import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.core.jobrequirements.domain.JobRequirementsDesignation;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class JobOpeningTest {

    private static Customer CUSTOMER = new Customer(CustomerCode.valueOf("CUSTO"), Name.valueOf("Customer", "One"), EmailAddress.valueOf("customer@jobs4u.com"), EmailAddress.valueOf("customermanager@jobs4u.com"));
    private static ContractType CONTRACT_TYPE = ContractType.PART_TIME;
    private static JobOpeningMode JOB_OPENING_MODE = JobOpeningMode.ONSITE;
    private static JobOpeningFunction JOB_OPENING_FUNCTION = JobOpeningFunction.valueOf("Front-end Programmer");
    private static JobOpeningAddress JOB_OPENING_ADDRESS = JobOpeningAddress.valueOf("Rua de trás");
    private static LocalDateTime ACTIVE_SINCE = LocalDateTime.of(2024,4,25,12,0);
    private static Vacancies VACANCIES = Vacancies.valueOf(50);
    private static JobRequirements REQUIREMENTS = new JobRequirements(new JobRequirementsDesignation("REQB"), "REQ2", "path/to/jarfile");

    @Test
    void ensureJobOpeningCanBeCreated(){
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        assertNotNull(jobOpening);
    }
    @Test
    void ensureJobReferenceIsAsIntended(){
        JobOpening jobOpening1 = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        JobOpening jobOpening2 = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 2L);
        assertNotNull(jobOpening1);
        assertEquals(jobOpening1.getJobReference().toString(), "CUSTO-1");
        assertNotNull(jobOpening2);
        assertEquals(jobOpening2.getJobReference().toString(), "CUSTO-2");
    }
    @Test void ensureRequirementsAreAdded(){
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        jobOpening.setJobRequirements(REQUIREMENTS);
        assertNotNull(jobOpening.getJobRequirements());
    }
    @Test void ensureJobOpeningCantBeCreatedWOContractType(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(null, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L));}
    @Test void ensureJobOpeningCantBeCreatedWOFunction(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(CONTRACT_TYPE, null, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L));}
    @Test void ensureJobOpeningCantBeCreatedWOMode(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, null, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L));}
    @Test void ensureJobOpeningCantBeCreatedWOAddress(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, null, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L));}
    @Test void ensureJobOpeningCantBeCreatedWOCustomer(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, null, VACANCIES, ACTIVE_SINCE, 1L));}
    @Test void ensureJobOpeningCantBeCreatedWOVacancies(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, null, ACTIVE_SINCE, 1L));}
    @Test void ensureJobOpeningCantBeCreatedWOActiveSince(){assertThrows(IllegalArgumentException.class, () -> new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, null, 1L));}

    /*@Test void ensure

    Possivel
    Testar impossivel registar a seguir a screening phase

     */

    @Test void ensureRankListGetsSavedInJobOpening(){
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        int id1 = 0, id2 = 1, id3 = 2, position = 0;
        List<Rank> rankList = new ArrayList<>();
        Rank rank1 = new Rank(position,id1);
        rankList.add(rank1);
        position++;
        Rank rank2 = new Rank(position,id2);
        rankList.add(rank2);
        position++;
        Rank rank3 = new Rank(position,id3);
        rankList.add(rank3);
        jobOpening.setRankList(rankList);

        assertEquals(rankList, jobOpening.getRankList());
    }

    @Test void assertCannotExecuteRequirementsWhileNotInScreening(){
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        RecruitmentProcess process = new RecruitmentProcess(Arrays.asList(
                new RecruitmentPhase(RecruitmentPhaseDesignation.APPLICATION, LocalDate.of(2024, 4,4), LocalDate.of(2024,4,5)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING, LocalDate.of(2024, 4,6), LocalDate.of(2024,4,7)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.ANALYSIS, LocalDate.of(2024, 4,8), LocalDate.of(2024,4,9)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT, LocalDate.of(2024, 4,10), LocalDate.of(2024,4,11))));

        jobOpening.setRecruitmentProcess(process); //Currently in Application phase
        assertThrows(IllegalStateException.class, () -> jobOpening.executeRequirements(new ArrayList<>()));
    }
    @Test
    void testVerifyIfIsInIterviewPhaseTimeframeWithinRange() {
        JobOpening jobOpening = new JobOpening();
        LocalDate startDate = LocalDate.of(2024, 4, 4);
        LocalDate closeDate = LocalDate.of(2024, 4, 10);
        LocalDate localDate = LocalDate.of(2024, 4, 6);
        assertDoesNotThrow(() -> jobOpening.verifyIfIsInIterviewPhaseTimeframe(localDate, startDate, closeDate));
    }

    @Test
    void testVerifyIfIsInIterviewPhaseTimeframeBeforeRange() {
        JobOpening jobOpening = new JobOpening();
        LocalDate startDate = LocalDate.of(2024, 4, 4);
        LocalDate closeDate = LocalDate.of(2024, 4, 10);
        LocalDate localDate = LocalDate.of(2024, 4, 2);
        assertThrows(DateTimeException.class, () -> jobOpening.verifyIfIsInIterviewPhaseTimeframe(localDate, startDate, closeDate));
    }

    @Test
    void testVerifyIfIsInIterviewPhaseTimeframeAfterRange() {
        JobOpening jobOpening = new JobOpening();
        LocalDate startDate = LocalDate.of(2024, 4, 4);
        LocalDate closeDate = LocalDate.of(2024, 4, 10);
        LocalDate localDate = LocalDate.of(2024, 4, 12);
        assertThrows(DateTimeException.class, () -> jobOpening.verifyIfIsInIterviewPhaseTimeframe(localDate, startDate, closeDate));
    }

    @Test
    void ensureAtrribuesAreEditedBeforeApplicationPhase() {
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        assertDoesNotThrow(jobOpening::isOnCorrectPhases);
    }

    @Test
    void ensureAtribuesAreEditeOnApplicationPhase(){}{
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);
        RecruitmentProcess process = new RecruitmentProcess(Arrays.asList(
                new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING, LocalDate.of(2024, 4,6), LocalDate.of(2024,4,7)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.ANALYSIS, LocalDate.of(2024, 4,8), LocalDate.of(2024,4,9)),
                new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT, LocalDate.of(2024, 4,10), LocalDate.of(2024,4,11))));

        process.setCurrentPhase(new RecruitmentPhase(RecruitmentPhaseDesignation.APPLICATION));
        jobOpening.setRecruitmentProcess(process); //Currently in Application phase
        assertDoesNotThrow(jobOpening::isOnCorrectPhases);

    }

    @Test
    void ensureAtribuesAreEditeOnScreeningPhase(){}{
        JobOpening jobOpening = new JobOpening(CONTRACT_TYPE, JOB_OPENING_FUNCTION, JOB_OPENING_MODE, JOB_OPENING_ADDRESS, CUSTOMER, VACANCIES, ACTIVE_SINCE, 1L);

        RecruitmentProcess process = new RecruitmentProcess(Arrays.asList(
                    new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING, LocalDate.of(2024, 4,6), LocalDate.of(2024,4,7)),
                    new RecruitmentPhase(RecruitmentPhaseDesignation.ANALYSIS, LocalDate.of(2024, 4,8), LocalDate.of(2024,4,9)),
                    new RecruitmentPhase(RecruitmentPhaseDesignation.RESULT, LocalDate.of(2024, 4,10), LocalDate.of(2024,4,11))));

        process.setCurrentPhase(new RecruitmentPhase(RecruitmentPhaseDesignation.SCREENING));
        jobOpening.setRecruitmentProcess(process); //Currently in Screening  Phase
        assertThrows(RuntimeException.class, jobOpening::isOnCorrectPhases);
    }
}
