package staffactivitytrackingsystem.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import staffactivitytrackingsystem.app.model.*;
import staffactivitytrackingsystem.app.repository.StaffRepository;
import staffactivitytrackingsystem.app.service.DataProcessing;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private StaffRepository staffRepository;
@Autowired
private DataProcessing dataProcessing;
    @GetMapping("/getdatafrom")
    public void test(){
    dataProcessing.processdata("ActivitiesToProcess");

}

@GetMapping("/getresult")
    public Response getresponse(){

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    Calendar cal = Calendar.getInstance();
/** get starting date
 and modify it
 */
    cal.add(Calendar.DAY_OF_YEAR, -7);
    String last7day= sdf.format(cal.getTime()).replaceAll("-","").trim();

    int date7=Integer.parseInt(last7day);
    List<String> selecetedActivities=new ArrayList<>();
    List<Staff> staffList=staffRepository.findAll();

    /**
     Filter out the activities performed 7 days ago and add
     them to another list
     */
    for (Staff staff: staffList)
    {
        List<Activity_> activity_s=staff.getActivities();

        for (Activity_ activity_: activity_s)
        {

      //      System.out.println(activity_.getTime()/10000+">="+date7);
            /**
             Dividing by 1000 to remove the time from the timestamp
             and only get the date to compare the last 7 days
             */
            if(activity_.getTime()/10000>=date7)
            {
                selecetedActivities.add(activity_.getName());
            }
        }

    }
    System.out.println(selecetedActivities);
/**
 Prepare the response to be sent
 */
    Response response=new Response();

    /**
     count the occurences of the activities
     */
    response.setAll_staffs_last_7_days_activities(dataProcessing.counter(selecetedActivities));

    List<SelectedStaff> todaystaff=new ArrayList<>();
    /**
     * get todays date
     */
    String today=sdf.format(Calendar.getInstance().getTime()).replaceAll("-","").trim();
    int todayint=Integer.parseInt(today);

    /**
     * compare with every staff that has performed an activity today
     */
    for(Staff staff1:staffList)
    {
        SelectedStaff selectedStaff=new SelectedStaff();
        List<SelectedActivity> selectedActivities=new ArrayList<>();
        selectedStaff.setStaff_id(staff1.getId());
        for (Activity_ activity_:staff1.getActivities())
        {
            System.out.println(activity_.getTime()/10000+"=="+todayint);
            /**
             * If the activity has been performed today
             * add it to a list
             */
            if(activity_.getTime()/10000==todayint)
            {


                SelectedActivity selectedActivity=new SelectedActivity();
                selectedActivity.setName(activity_.getName());
                selectedActivity.setStart_time(activity_.getTime()%10000);
                selectedActivities.add(selectedActivity);
            }
        }
        /**
         * Sort the selected activities according to
         * the time using Comparable Interface and Collections
         */
        Collections.sort(selectedActivities);

        selectedStaff.setActivities(selectedActivities);
        todaystaff.add(selectedStaff);

    }
    /**
     * Sort the staff id in ascending order using
     * comparable interface and collections
     */
    Collections.sort(todaystaff);
    response.setStaff(todaystaff);
/**
 * returning the entire response
 */
    return response;
}





}
