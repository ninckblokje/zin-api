package ninckblokje.zin.api.controller;

import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.entity.Rating;
import ninckblokje.zin.api.repository.CompanyRepository;
import ninckblokje.zin.api.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/ratings")
@Secured("ROLE_ZIN_USER")
public class CompanyRatingController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping
    @Transactional
    public void addRating(@PathVariable("companyId") Long companyId, @RequestBody @Valid Rating rating) {
        Company company = companyRepository.findById(companyId).orElse(null);
        rating.setCompany(company);

        ratingRepository.save(rating);
    }

    @GetMapping
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Rating> getAllRatings(@PathVariable("companyId") Long companyId) {
        return ratingRepository.getAllByCompany(getCompany(companyId));
    }

    Company getCompany(Long companyId) {
        Company company = new Company();

        company.setId(companyId);

        return company;
    }
}
