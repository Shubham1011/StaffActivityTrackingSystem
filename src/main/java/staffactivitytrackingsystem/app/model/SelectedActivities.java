package staffactivitytrackingsystem.app.model;

public class SelectedActivities implements Comparable {

    private String name;
    private int occurences;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccurences() {
        return occurences;
    }

    public void setOccurences(int occurences) {
        this.occurences = occurences;
    }

    @Override
    public int compareTo(Object o) {
        SelectedActivities selectedActivities=(SelectedActivities)o;
        return -(this.getOccurences()-selectedActivities.getOccurences());
    }
}
