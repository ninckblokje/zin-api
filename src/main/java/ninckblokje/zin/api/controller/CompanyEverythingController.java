package ninckblokje.zin.api.controller;

import ninckblokje.zin.api.model.CompanyDTO;
import ninckblokje.zin.api.service.EverythingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/everything")
@Secured("ROLE_ZIN_USER")
public class CompanyEverythingController {

    @Autowired
    private EverythingService everythingService;

    @GetMapping
    @ResponseBody
    public CompanyDTO getEverything(@PathVariable("companyId") Long companyId) {
        return everythingService.getEverythingForCompany(companyId);
    }
}
