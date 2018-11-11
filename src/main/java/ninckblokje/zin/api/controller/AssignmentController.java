package ninckblokje.zin.api.controller;

import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    @Transactional
    public Long createAssignment(@PathVariable("companyId") Long companyId, @RequestBody @Valid Assignment assignment) {
        Company company = companyRepository.findById(companyId).orElse(null);
        assignment.setCompany(company);

        assignmentRepository.save(assignment);
        return assignment.getId();
    }

    @GetMapping
    @Transactional(readOnly = true)
    @ResponseBody
    public List<Assignment> getAllAssignments(@PathVariable("companyId") Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);

        return assignmentRepository.getAllByCompany(company);
    }

    @GetMapping("/{assignmentId}")
    @Transactional(readOnly = true)
    @ResponseBody
    public Assignment getAssignment(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId) {
        Company company = companyRepository.findById(companyId).orElse(null);

        return assignmentRepository.getByIdAndCompany(assignmentId, company);
    }
}
