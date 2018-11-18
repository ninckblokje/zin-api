package ninckblokje.zin.api.model;

import ninckblokje.zin.api.entity.Rating;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDTO {

    private Long id;

    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
