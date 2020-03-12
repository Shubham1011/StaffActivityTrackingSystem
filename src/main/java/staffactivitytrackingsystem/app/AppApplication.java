package staffactivitytrackingsystem.app;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import staffactivitytrackingsystem.app.controller.MainController;
import staffactivitytrackingsystem.app.service.DataProcessing;

import java.io.*;

@SpringBootApplication
public class AppApplication {



    public static void main(String[] args) throws IOException, ParseException {


        SpringApplication.run(AppApplication.class, args);



    }

}
