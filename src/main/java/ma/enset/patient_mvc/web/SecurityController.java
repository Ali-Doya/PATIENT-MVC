package ma.enset.patient_mvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/403")
    public String notAuthorized(){
return "403";
    }
}
