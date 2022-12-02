package ma.enset.patient_mvc.sec.dao;

import ma.enset.patient_mvc.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleDao extends JpaRepository<AppRole,Long> {

    AppRole findByRoleName(String roleName);
}
