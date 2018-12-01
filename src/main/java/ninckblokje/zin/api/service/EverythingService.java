package ninckblokje.zin.api.service;

import ninckblokje.zin.api.entity.Company;
import ninckblokje.zin.api.model.AssignmentDTO;
import ninckblokje.zin.api.model.CompanyDTO;
import ninckblokje.zin.api.model.RatingDTO;
import ninckblokje.zin.api.repository.AssignmentRepository;
import ninckblokje.zin.api.repository.CompanyRepository;
import ninckblokje.zin.api.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EverythingService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RatingRepository ratingRepository;

    public CompanyDTO getEverythingForCompany(Long companyId) {
        CompanyDTO companyDTO = new CompanyDTO();

        Company company = companyRepository.findById(companyId).orElse(null);
        modelMapper.map(company, companyDTO);

        ratingRepository.getAllByCompany(company).forEach(rating -> {
            RatingDTO ratingDTO = new RatingDTO();
            companyDTO.getRatings().add(ratingDTO);
            modelMapper.map(rating, ratingDTO);
        });

        assignmentRepository.getAllByCompany(company).forEach(assignment -> {
            AssignmentDTO assignmentDTO = new AssignmentDTO();
            companyDTO.getAssignments().add(assignmentDTO);
            modelMapper.map(assignment, assignmentDTO);

            ratingRepository.getAllByAssignment(assignment).forEach(rating -> {
                RatingDTO ratingDTO = new RatingDTO();
                assignmentDTO.getRatings().add(ratingDTO);
                modelMapper.map(rating, ratingDTO);
            });
        });

        return companyDTO;
    }
}
