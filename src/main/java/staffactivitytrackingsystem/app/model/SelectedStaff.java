package staffactivitytrackingsystem.app.model;

import java.util.List;

public class SelectedStaff implements Comparable {

    private Long staff_id;
    private List<SelectedActivity> activities;

    public Long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Long staff_id) {
        this.staff_id = staff_id;
    }

    public List<SelectedActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<SelectedActivity> activities) {
        this.activities = activities;
    }

    @Override
    public int compareTo(Object o) {
        SelectedStaff selectedStaff = (SelectedStaff)o;
        return (int)(selectedStaff.getStaff_id()-this.getStaff_id());
    }
}
