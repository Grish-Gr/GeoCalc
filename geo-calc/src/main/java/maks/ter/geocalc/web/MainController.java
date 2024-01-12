package maks.ter.geocalc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping({"", "/home"})
    public String home(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("completed_questionnaire", "false");
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plain");
        return "/main";
    }

    @GetMapping({"/account"})
    public String account() {
        return "account";
    }

    @GetMapping({"/analytics"})
    public String analytics() {
        return "analytics";
    }

    @GetMapping({"/data"})
    public String data() {
        return "data";
    }

    @GetMapping({"/data_base"})
    public String data_base() {
        return "data_base";
    }

    @GetMapping({"/events"})
    public String events() {
        return "events";
    }

    @GetMapping({"/info_project"})
    public String info_project() {
        return "info_project";
    }

    @GetMapping({"/news"})
    public String news() {
        return "news";
    }

    @GetMapping({"/researches"})
    public String researches() {
        return "researches";
    }

    @GetMapping({"/service"})
    public String service() { return "service"; }

    @GetMapping({"/questionnaire"})
    public String questionnaire(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("completed_questionnaire", "false");
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plain");
        return "questionnaire"; }

}