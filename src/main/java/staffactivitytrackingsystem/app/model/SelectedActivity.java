package staffactivitytrackingsystem.app.model;

public class SelectedActivity implements Comparable {

    private String name;
    private Long start_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    @Override
    public int compareTo(Object o) {
        SelectedActivity selectedActivity=(SelectedActivity) o;
        int t=(int)(this.getStart_time()-selectedActivity.getStart_time());
        System.out.println();
        return t;
    }
}
