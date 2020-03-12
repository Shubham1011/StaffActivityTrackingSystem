package staffactivitytrackingsystem.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import staffactivitytrackingsystem.app.model.Staff;

@Repository("staffrepo")
public interface StaffRepository  extends MongoRepository<Staff,Long> {


}
