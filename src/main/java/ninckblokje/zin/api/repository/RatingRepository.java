package ninckblokje.zin.api.repository;

import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

    List<Rating> getAllByAssignmentAndUserId(Assignment assignment, String userId);
    List<Rating> getAllByCompanyAndUserId(Company company, String userId);
}
