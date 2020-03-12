package staffactivitytrackingsystem.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import staffactivitytrackingsystem.app.model.Activity_;

public interface ActivityRepositry  extends MongoRepository<Activity_,String> {
}
