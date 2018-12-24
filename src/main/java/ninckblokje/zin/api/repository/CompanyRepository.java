package ninckblokje.zin.api.repository;

import ninckblokje.zin.api.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends org.springframework.data.repository.Repository<Company, Long> {

    Iterable<Company> findAllByUserId(String userId);
    Optional<Company> findByIdAndUserId(Long id, String userId);
    Company save(Company company);
}
