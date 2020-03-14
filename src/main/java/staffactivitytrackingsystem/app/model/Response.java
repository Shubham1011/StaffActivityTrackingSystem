package staffactivitytrackingsystem.app.model;

import java.util.List;

public class Response {

   // private List<Staff> todays_activities;
private List<SelectedActivities> all_staffs_last_7_days_activities;
private List<SelectedStaff> todays_activities;
    public List<SelectedActivities> getAll_staffs_last_7_days_activities() {
        return all_staffs_last_7_days_activities;
    }

    public void setAll_staffs_last_7_days_activities(List<SelectedActivities> all_staffs_last_7_days_activities) {
        this.all_staffs_last_7_days_activities = all_staffs_last_7_days_activities;
    }

    public List<SelectedStaff> getStaff() {
        return todays_activities;
    }

    public void setStaff(List<SelectedStaff> staff) {
        this.todays_activities = staff;
    }
}
