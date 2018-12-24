package ninckblokje.zin.api.controller;

import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.entity.Rating;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import ninckblokje.zin.api.repository.RatingRepository;
import ninckblokje.zin.api.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/assignments/{assignmentId}/ratings")
@Secured("ROLE_ZIN_USER")
public class AssignmentRatingController {

    private AssignmentRepository assignmentRepository;
    private CompanyRepository companyRepository;
    private RatingRepository ratingRepository;
    private UserService userService;

    public AssignmentRatingController(AssignmentRepository assignmentRepository, CompanyRepository companyRepository, RatingRepository ratingRepository, UserService userService) {
        this.assignmentRepository = assignmentRepository;
        this.companyRepository = companyRepository;
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public void addRating(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId, @RequestBody @Valid Rating rating) {
        Company company = companyRepository.findByIdAndUserId(companyId, userService.getUserId()).orElse(null);
        Assignment assignment = assignmentRepository.getByIdAndCompanyAndUserId(assignmentId, company, userService.getUserId());

        assignment.setCompany(company);
        rating.setAssignment(assignment);

        ratingRepository.save(rating);
    }

    @GetMapping
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Rating> getAllRatings(@PathVariable("assignmentId") Long assignmentId) {
        return ratingRepository.getAllByAssignmentAndUserId(getAssigment(assignmentId), userService.getUserId());
    }

    Assignment getAssigment(Long assignmentId) {
        Assignment assignment = new Assignment();

        assignment.setId(assignmentId);

        return assignment;
    }
}
