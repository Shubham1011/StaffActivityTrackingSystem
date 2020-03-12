package staffactivitytrackingsystem.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import staffactivitytrackingsystem.app.model.Activity_;
import staffactivitytrackingsystem.app.model.Response;
import staffactivitytrackingsystem.app.model.SelectedActivities;
import staffactivitytrackingsystem.app.model.Staff;
import staffactivitytrackingsystem.app.repository.StaffRepository;
import staffactivitytrackingsystem.app.service.DataProcessing;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
    public List<SelectedActivities> getresponse(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    Calendar cal = Calendar.getInstance();
// get starting date
    cal.add(Calendar.DAY_OF_YEAR, -7);
    String last7day= sdf.format(cal.getTime()).replaceAll("-","").trim();

    int date7=Integer.parseInt(last7day);
    List<String> selecetedActivities=new ArrayList<>();
    List<Staff> staffList=staffRepository.findAll();

    for (Staff staff: staffList)
    {
        List<Activity_> activity_s=staff.getActivities();
        for (Activity_ activity_: activity_s)
        {
            System.out.println(activity_.getTime()+">"+date7);
            if(activity_.getTime()>=date7)
            {
                selecetedActivities.add(activity_.getName());
            }
        }

    }
    System.out.println(selecetedActivities);


return dataProcessing.counter(selecetedActivities);
}





}
