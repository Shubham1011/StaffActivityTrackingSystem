package staffactivitytrackingsystem.app.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "staff")
public class Staff {

    @MongoId
    private Long staff_id;
    private List<Activity_> activities;

    public Long getId() {
        return staff_id;
    }

    public void setId(Long id) {
        this.staff_id = id;
    }

    public List<Activity_> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity_> activities) {
        this.activities = activities;
    }
}
