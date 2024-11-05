package com.app.cediamback.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/{path:^(?!api).*$}")
public String redirect() {
    return "forward:/index.html";
}

}
