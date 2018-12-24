package ninckblokje.zin.api.service;

import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.model.AssignmentDTO;
import ninckblokje.zin.api.model.CompanyDTO;
import ninckblokje.zin.api.model.RatingDTO;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import ninckblokje.zin.api.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EverythingService {

    private AssignmentRepository assignmentRepository;
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;
    private RatingRepository ratingRepository;
    private UserService userService;

    public EverythingService(AssignmentRepository assignmentRepository, CompanyRepository companyRepository, ModelMapper modelMapper, RatingRepository ratingRepository, UserService userService) {
        this.assignmentRepository = assignmentRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    public CompanyDTO getEverythingForCompany(Long companyId) {
        CompanyDTO companyDTO = new CompanyDTO();

        Company company = companyRepository.findByIdAndUserId(companyId, userService.getUserId()).orElse(null);
        modelMapper.map(company, companyDTO);

        ratingRepository.getAllByCompanyAndUserId(company, userService.getUserId()).forEach(rating -> {
            RatingDTO ratingDTO = new RatingDTO();
            companyDTO.getRatings().add(ratingDTO);
            modelMapper.map(rating, ratingDTO);
        });

        assignmentRepository.getAllByCompanyAndUserId(company, userService.getUserId()).forEach(assignment -> {
            AssignmentDTO assignmentDTO = new AssignmentDTO();
            companyDTO.getAssignments().add(assignmentDTO);
            modelMapper.map(assignment, assignmentDTO);

            ratingRepository.getAllByAssignmentAndUserId(assignment, userService.getUserId()).forEach(rating -> {
                RatingDTO ratingDTO = new RatingDTO();
                assignmentDTO.getRatings().add(ratingDTO);
                modelMapper.map(rating, ratingDTO);
            });
        });

        return companyDTO;
    }
}
