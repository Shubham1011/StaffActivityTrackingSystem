package staffactivitytrackingsystem.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import staffactivitytrackingsystem.app.model.Activity_;
import staffactivitytrackingsystem.app.model.SelectedActivities;
import staffactivitytrackingsystem.app.model.Staff;
import staffactivitytrackingsystem.app.repository.StaffRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessing {

    @Autowired
    public StaffRepository staffRepository;

    List<String> validactivities=new ArrayList<String>(Arrays.asList("login","logout","teabreak","lunchbreak","gamemood","naptime"));

    //Method which reads data form directory
    public void processdata(String directoryname){
        JSONParser jsonParser=new JSONParser();

        //Getting all the files in the ActivitiesToProcess directory in a List
        List<File> filesInFolder = null;
        try {
            filesInFolder = Files.walk(Paths.get(directoryname))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            //iterating through all the files and saving into mongodb
            for (File f : filesInFolder) {

                FileReader fileReader = new FileReader(directoryname+"/" + f.getName());
                Object obj = jsonParser.parse(fileReader);
                ObjectMapper objectMapper = new ObjectMapper();
                Staff staff = objectMapper.readValue(obj.toString(), Staff.class);

               staff.setActivities(validatedata(staff.getActivities()));
                saveToDatabase(staff);

            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private  Staff saveToDatabase(Staff s){

      return staffRepository.save(s);

    }

    public List<Activity_> validatedata(List<Activity_> activities){

        boolean valid=false;
        for (Activity_ activity_:activities)
        {
            if(!validactivities.contains(activity_.getName()))
            {

                System.out.println("Data not add as it contains invalid activity");
                activities.remove(activity_);
                continue;
            }


        }

        return activities;
    }

    public List<SelectedActivities> counter(List<String> strings)
    {
        List<SelectedActivities> selectedActivities=new ArrayList<>();

            for(String validacctivity:validactivities)
            {
                int count=Collections.frequency(strings,validacctivity);
                SelectedActivities selectedActivities1=new SelectedActivities();
                selectedActivities1.setName(validacctivity);
                selectedActivities1.setOccurences(count);
                selectedActivities.add(selectedActivities1);
            }

             Collections.sort(selectedActivities);
            return selectedActivities;




    }

}
