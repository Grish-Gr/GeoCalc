package maks.ter.geocalc.web;

import maks.ter.geocalc.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping({"", "/home"})
    public String home() {
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
    public String service() {
        return "service";
    }
}