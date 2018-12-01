package ninckblokje.zin.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@Secured("ROLE_ZIN_USER")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private DomainObjectReader domainObjectReader;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    @Transactional
    public Long createCompany(@RequestBody @Valid Company company) {
        repository.save(company);
        return company.getId();
    }

    @GetMapping
    @ResponseBody
    @Transactional(readOnly = true)
    public Iterable<Company> getAllCompanies() {
        return repository.findAll();
    }

    @GetMapping("/{companyId}")
    @ResponseBody
    @Transactional(readOnly = true)
    public Optional<Company> getCompany(@PathVariable("companyId") Long companyId) {
        return repository.findById(companyId);
    }

    @PatchMapping("/{companyId}")
    @Transactional
    public void patchCompany(@PathVariable Long companyId, HttpServletRequest request) throws IOException {
        Company company = repository.findById(companyId).orElse(null);
        Company patchedCompany = domainObjectReader.read(request.getInputStream(), company, objectMapper);
    }

    @PutMapping("/{companyId}")
    @Transactional
    public void updateCompany(@PathVariable("companyId") Long companyId, @RequestBody @Valid Company company) {
        company.setId(companyId);
        repository.save(company);
    }
}
