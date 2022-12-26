package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
    @RequestMapping("/security/login/form")
    public String loginForm(Model model){
        model.addAttribute("message","Please login");
        return "security/login";
    }
    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model){
        model.addAttribute("message","Login success");
        return "redirect:/product/list";
    }
    @RequestMapping("/security/login/error")
    public String loginError(Model model){
        model.addAttribute("message","Invalid username or password");
        return "security/login";
    }
    @RequestMapping("/security/unauthorized")
    public String unauthorized(Model model){
        model.addAttribute("message","Has no rights to access");
        return "security/login";
    }
    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model){
        model.addAttribute("message","Logout success");
        return "security/login";
    }
}
