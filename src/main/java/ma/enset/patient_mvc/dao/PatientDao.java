package ma.enset.patient_mvc.dao;

import ma.enset.patient_mvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient,Long> {

    Page<Patient> findByNomContains(String kw, Pageable pageable);
}
