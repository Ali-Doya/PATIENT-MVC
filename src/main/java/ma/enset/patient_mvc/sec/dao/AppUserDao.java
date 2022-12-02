package ma.enset.patient_mvc.sec.dao;

import ma.enset.patient_mvc.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDao extends JpaRepository<AppUser,String> {

    AppUser findByUsername(String username);
}
