package ninckblokje.zin.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/assignments")
@Secured("ROLE_ZIN_USER")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DomainObjectReader domainObjectReader;
    @Autowired
    private ObjectMapper objectMapper;

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

    @PatchMapping("/{assignmentId}")
    @Transactional
    public void patchAssignment(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId, HttpServletRequest request) throws IOException {
        Company company = new Company();
        company.setId(companyId);

        Assignment assignment = assignmentRepository.getByIdAndCompany(assignmentId, company);
        Assignment patchedAssignment = domainObjectReader.read(request.getInputStream(), assignment, objectMapper);
        patchedAssignment.setId(assignmentId);
        patchedAssignment.setCompany(company);
    }

    @PutMapping("/{assignmentId}")
    @Transactional
    public void  updateAssignment(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId, @RequestBody @Valid Assignment assignment) {
        Company company = companyRepository.findById(companyId).orElse(null);

        assignment.setId(assignmentId);
        assignment.setCompany(company);

        assignmentRepository.save(assignment);
    }
}
