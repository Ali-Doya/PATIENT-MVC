package ma.enset.patient_mvc.sec.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.patient_mvc.sec.dao.AppRoleDao;
import ma.enset.patient_mvc.sec.dao.AppUserDao;
import ma.enset.patient_mvc.sec.entities.AppRole;
import ma.enset.patient_mvc.sec.entities.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AppUserDao appUserDao;
    private AppRoleDao appRoleDao;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Passwords not match");
        String hashedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserDao.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleDao.findByRoleName(roleName);
        if (appRole != null) throw new RuntimeException("Role" + roleName + "Allready exist");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole = appRoleDao.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserDao.findByUsername(username);
        if (appUser == null) throw new RuntimeException("User not found");
        AppRole appRole =appRoleDao.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
        appUserDao.save(appUser);

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserDao.findByUsername(username);
        if (appUser == null) throw new RuntimeException("User not found");
        AppRole appRole =appRoleDao.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
        appUserDao.save(appUser);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserDao.findByUsername(username);
    }
}
