package ninckblokje.zin.api.repository;

import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    List<Assignment> getAllByCompany(Company company);
    Assignment getByIdAndCompany(Long assignmentId, Company company);
}
