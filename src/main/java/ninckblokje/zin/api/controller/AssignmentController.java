package ninckblokje.zin.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ninckblokje.zin.api.entity.Assignment;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import ninckblokje.zin.api.service.UserService;
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

    private AssignmentRepository assignmentRepository;
    private CompanyRepository companyRepository;
    private DomainObjectReader domainObjectReader;
    private ObjectMapper objectMapper;
    private UserService userService;

    public AssignmentController(AssignmentRepository assignmentRepository, CompanyRepository companyRepository, DomainObjectReader domainObjectReader, ObjectMapper objectMapper, UserService userService) {
        this.assignmentRepository = assignmentRepository;
        this.companyRepository = companyRepository;
        this.domainObjectReader = domainObjectReader;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public Long createAssignment(@PathVariable("companyId") Long companyId, @RequestBody @Valid Assignment assignment) {
        Company company = companyRepository.findByIdAndUserId(companyId, userService.getUserId()).orElse(null);
        assignment.setCompany(company);

        assignmentRepository.save(assignment);
        return assignment.getId();
    }

    @GetMapping
    @Transactional(readOnly = true)
    @ResponseBody
    public List<Assignment> getAllAssignments(@PathVariable("companyId") Long companyId) {
        Company company = companyRepository.findByIdAndUserId(companyId, userService.getUserId()).orElse(null);

        return assignmentRepository.getAllByCompanyAndUserId(company, userService.getUserId());
    }

    @GetMapping("/{assignmentId}")
    @Transactional(readOnly = true)
    @ResponseBody
    public Assignment getAssignment(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId) {
        Company company = companyRepository.findByIdAndUserId(companyId, userService.getUserId()).orElse(null);

        return assignmentRepository.getByIdAndCompanyAndUserId(assignmentId, company, userService.getUserId());
    }

    @PatchMapping("/{assignmentId}")
    @Transactional
    public void patchAssignment(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId, HttpServletRequest request) throws IOException {
        Company company = new Company();
        company.setId(companyId);

        Assignment assignment = assignmentRepository.getByIdAndCompanyAndUserId(assignmentId, company, userService.getUserId());
        Assignment patchedAssignment = domainObjectReader.read(request.getInputStream(), assignment, objectMapper);
        patchedAssignment.setId(assignmentId);
        patchedAssignment.setCompany(company);
    }

    @PutMapping("/{assignmentId}")
    @Transactional
    public void  updateAssignment(@PathVariable("companyId") Long companyId, @PathVariable("assignmentId") Long assignmentId, @RequestBody @Valid Assignment assignment) {
        Company company = companyRepository.findByIdAndUserId(companyId, userService.getUserId()).orElse(null);

        assignment.setId(assignmentId);
        assignment.setCompany(company);

        assignmentRepository.save(assignment);
    }
}
