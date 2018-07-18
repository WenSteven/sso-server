package cn.wenqi.oauth2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
@RequestMapping("/resources")
public class ResourceController {

    @RequestMapping("/manage")
    public String page(){

        return "resources";
    }
}
