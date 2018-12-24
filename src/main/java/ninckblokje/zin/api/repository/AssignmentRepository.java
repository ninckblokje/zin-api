package ninckblokje.zin.api.repository;

import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends org.springframework.data.repository.Repository<Assignment, Long> {

    List<Assignment> getAllByCompanyAndUserId(Company company, String userId);
    Assignment getByIdAndCompanyAndUserId(Long assignmentId, Company company, String userId);
    Assignment save(Assignment assignment);
}
