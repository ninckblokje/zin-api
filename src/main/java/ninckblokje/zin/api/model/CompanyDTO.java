package ninckblokje.zin.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompanyDTO {

    private Long id;

    private List<AssignmentDTO> assignments = new ArrayList<>();
    private LocalDate endDate;
    private String name;
    private List<RatingDTO> ratings = new ArrayList<>();
    private LocalDate startDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AssignmentDTO> getAssignments() {
        return assignments;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
