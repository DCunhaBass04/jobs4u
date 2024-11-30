package jobs4u.core.jobapplication.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import jobs4u.core.candidate.domain.Candidate;
import jobs4u.core.candidate.domain.PhoneNumber;
import jobs4u.core.customer.domain.Customer;
import jobs4u.core.customer.domain.CustomerCode;
import jobs4u.core.jobopening.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JobApplicationTest {

    private static final Candidate CANDIDATE = new Candidate(EmailAddress.valueOf("teste@isep.ipp.pt"), Name.valueOf("Candidate", "JobApplication"), new PhoneNumber("987654321"));
    private static final String PATH_TO_APPLICATION_FILES = "src/test/java/jobs4u/core/jobapplication/domain/textfiles";
    private static final ApplicationState APPLICATION_STATE = new ApplicationState(ApplicationStateIndicator.PENDING, null);
    private static final String REQUIREMENT_ANSWERS = "path/to/answers.txt";
    private static final Interview INTERVIEW = new Interview(LocalDateTime.of(2024, 5,16,10,15));
    private static final String PASSED = "Passed", FAILED = "Failed", NON_VALID = "null";
    @Test
    void ensureMustHaveJobOpening() {
        assertThrows(IllegalStateException.class, () -> new JobApplicationBuilder().ofCandidate(CANDIDATE).build());
    }

    private JobOpening getJobOpening(){
        CustomerCode customerCode = new CustomerCode("ABC123");
        Name name = Name.valueOf("ACME", "Corp");
        EmailAddress customerManagerEmail = EmailAddress.valueOf("manager@acme.com");
        Customer customer = new Customer(customerCode, name, EmailAddress.valueOf("abc123@email.com"), customerManagerEmail);
        JobOpeningFunction jobOpeningFunction = JobOpeningFunction.valueOf("Software Engineer");
        JobOpeningMode jobOpeningMode = JobOpeningMode.ONSITE;
        JobOpeningAddress jobOpeningAddress = JobOpeningAddress.valueOf("123 Main St, Anytown, USA");
        Vacancies numberOfVacancies = Vacancies.valueOf(5);
        LocalDateTime activeSince = LocalDateTime.now();
        return new JobOpening(ContractType.FULL_TIME, jobOpeningFunction, jobOpeningMode, jobOpeningAddress, customer, numberOfVacancies, activeSince, 1L);
    }

    @Test
    void ensureCanCreateJobApplicationWithJobOpening() {
        JobOpening jobOpening = getJobOpening();
        // Act
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(jobOpening)
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .build();

        // Assert
        assertNotNull(jobApplication);
        assertEquals(jobOpening, jobApplication.getJobOpening());
        assertEquals(CANDIDATE, jobApplication.getCandidate());
        // Add more assertions as needed
    }

    @Test
    void ensureCanAddInterviewToJobApplication() {
        JobOpening jobOpening = getJobOpening();

        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(jobOpening)
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .build();
        Interview interview = new Interview(/* provide necessary arguments for Interview */);

        // Act
        jobApplication.addInterview(interview);

        // Assert
        assertEquals(interview, jobApplication.getInterview());
    }

    @Test
    void ensureCanChangeApplicationState() {
        // Arrange
        JobOpening jobOpening = getJobOpening();
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(jobOpening)
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .build();
        ApplicationState newState = new ApplicationState();

        jobApplication.changeStateTo(newState);

        assertEquals(newState, jobApplication.getApplicationState());
    }
    //-------------------------------------------------------------------------------
    @Test
    void ensureInformationDisplayedIsntNull(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .build();
        assertNotNull(jobApplication.details());
    }
    @Test
    void ensureAllDataIsDisplayed(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .build();
        String details = jobApplication.details();
        assertTrue(details.contains("Opening") && details.contains("candidate") && details.contains("State")
                    && details.contains("cv") && details.contains("candidate-data"));
    }
    @Test
    void ensureNonFilledFieldsAreNotDisplayed(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .build();
        String details = jobApplication.details();
        assertFalse(details.contains("Requirements") || details.contains("Interview"));
    }
    @Test
    void ensureFilledFieldsAreDisplayed(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        String details = jobApplication.details();
        assertTrue(details.contains("Requirements") && details.contains("Interview"));
    }
    //-------------------------------------------------------------------------------
    @Test
    void assertListOfWordsAreInDescendingOrder() throws InterruptedException {
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        List<Map.Entry<String, Map.Entry<List<String>, Integer>>> list = jobApplication.createListOfWords(20);
        for(int i = 1; i < list.size(); i++)
            if(list.get(i).getValue().getValue() > list.get(i-1).getValue().getValue())
                fail();
    }
    @Test
    void assertEachEntryHasListOfFileAppearances() throws InterruptedException {
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        List<Map.Entry<String, Map.Entry<List<String>, Integer>>> list = jobApplication.createListOfWords(20);
        for(Map.Entry<String, Map.Entry<List<String>, Integer>> entry : list)
            if (entry.getValue().getKey().isEmpty())
                fail();
    }
    @Test
    void assertNumOccurrencesIsCorrect() throws InterruptedException {
        //This is specifically for files from job application nº6 without the 6-big-file1.txt
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        List<String> words = Arrays.stream("and to a the with jane of i software her in she development 1 company xyz skills am team has".split(" ")).toList();
        List<Integer> occurrences = Arrays.asList(20, 19, 11, 11, 8, 8, 8, 7, 7, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 4);
        List<Map.Entry<String, Map.Entry<List<String>, Integer>>> list = jobApplication.createListOfWords(20);
        for(int i = 0; i < list.size(); i++){
            if(!list.get(i).getKey().equals(words.get(i)) || !list.get(i).getValue().getValue().equals(occurrences.get(i)))
                fail();
        }
    }
    @Test
    void assertStateIsUpdatedToPassed(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        jobApplication.updateStatus(PASSED);
        assertEquals(jobApplication.getApplicationState().getState(), ApplicationStateIndicator.ACCEPTED);
    }
    @Test
    void assertStateIsUpdatedToFailed(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        jobApplication.updateStatus(FAILED);
        assertEquals(jobApplication.getApplicationState().getState(), ApplicationStateIndicator.REJECTED);
    }
    @Test
    void assertStateIsNotUpdated(){
        JobApplication jobApplication = new JobApplicationBuilder()
                .withId(6)
                .withState(APPLICATION_STATE)
                .forJobOpening(getJobOpening())
                .withFiles(PATH_TO_APPLICATION_FILES)
                .ofCandidate(CANDIDATE)
                .withAddonInterview(INTERVIEW)
                .withAddonAnswers(REQUIREMENT_ANSWERS)
                .build();
        ApplicationStateIndicator oldState = jobApplication.getApplicationState().getState();
        jobApplication.updateStatus(NON_VALID);
        assertEquals(jobApplication.getApplicationState().getState(), oldState);
    }
}