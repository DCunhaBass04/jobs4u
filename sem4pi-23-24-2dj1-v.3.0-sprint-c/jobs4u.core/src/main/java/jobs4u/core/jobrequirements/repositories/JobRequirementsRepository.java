package jobs4u.core.jobrequirements.repositories;

import jobs4u.core.jobrequirements.domain.JobRequirements;
import jobs4u.core.jobrequirements.domain.JobRequirementsDesignation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRequirementsRepository extends CrudRepository<JobRequirements, JobRequirementsDesignation> {

    JobRequirements findJobRequirementsByJobRequirementsDesignation(JobRequirementsDesignation jobRequirementsDesignation);

    @Override
    List<JobRequirements> findAll();

}
