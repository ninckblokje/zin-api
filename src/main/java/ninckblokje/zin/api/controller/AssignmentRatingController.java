package ninckblokje.zin.api.controller;

import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.entity.Rating;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import ninckblokje.zin.api.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/assignments/{assignmentId}/ratings")
@Secured("ROLE_ZIN_USER")
public class AssignmentRatingController {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping
    @Transactional
    public void addRating(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId, @RequestBody @Valid Rating rating) {
        Company company = companyRepository.findById(companyId).orElse(null);
        Assignment assignment = assignmentRepository.getByIdAndCompany(assignmentId, company);

        assignment.setCompany(company);
        rating.setAssignment(assignment);

        ratingRepository.save(rating);
    }

    @GetMapping
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Rating> getAllRatings(@PathVariable("assignmentId") Long assignmentId) {
        return ratingRepository.getAllByAssignment(getAssigment(assignmentId));
    }

    Assignment getAssigment(Long assignmentId) {
        Assignment assignment = new Assignment();

        assignment.setId(assignmentId);

        return assignment;
    }
}
