package ninckblokje.zin.api.controller;

import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

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

    @PutMapping("/{companyId}")
    @Transactional
    public void updateCompany(@PathVariable("companyId") Long companyId, @RequestBody @Valid Company company) {
        company.setId(companyId);
        repository.save(company);
    }
}