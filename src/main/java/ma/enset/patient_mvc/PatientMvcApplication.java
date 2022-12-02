package ma.enset.patient_mvc;

import ma.enset.patient_mvc.dao.PatientDao;
import ma.enset.patient_mvc.entities.Patient;
import ma.enset.patient_mvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    // Quand je mets cette annotation en Com le code enbas ne va pas s'execute
    // @Bean
    CommandLineRunner commandLineRunner(PatientDao patientdao) {
        return args -> {
            patientdao.save(new Patient(null, "Hassan", new Date(), true, 12));
            patientdao.save(new Patient(null, "Mohammed", new Date(), false, 321));
            patientdao.save(new Patient(null, "Yasmine", new Date(), true, 65));
            patientdao.save(new Patient(null, "Hanane", new Date(), false, 32));

            patientdao.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });
        };
    }

   // @Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("Mohamed", "1234", "1234");
            securityService.saveNewUser("Yasmine", "1234", "1234");
            securityService.saveNewUser("Hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Mohamed","USER");
            securityService.addRoleToUser("Mohamed","ADMIN");
            securityService.addRoleToUser("Yasmine","USER");
            securityService.addRoleToUser("Hassan","USER");

        };
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
