package nus.iss.day25.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.day25.models.User;
import nus.iss.day25.repositories.LoginRepository;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private LoginRepository loginRepo;
    
    @GetMapping("")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @PostMapping("/authenticate")
    public ModelAndView postLogin(@RequestBody MultiValueMap<String, String> form, HttpSession session){
        String username = form.getFirst("username");
        String password = form.getFirst("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ModelAndView mnv = new ModelAndView();
        mnv.setStatus(HttpStatus.UNAUTHORIZED);
        mnv.setViewName("invalid");
        
        if(loginRepo.verifyUser(user)){
            // mnv.setStatus(HttpStatus.OK);
            // mnv.addObject("name",username);
            session.setAttribute("name", username);
            mnv = new ModelAndView("redirect:/protected/success");
        }
        return mnv;
    }
}
