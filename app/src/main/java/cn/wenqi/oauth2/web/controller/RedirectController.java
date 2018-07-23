package cn.wenqi.oauth2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
public class RedirectController {

    @RequestMapping("/*")
    public String index(){
        return "index";
    }



    @RequestMapping("/resources/detail/{id}")
    public String detail(@PathVariable("id") Integer id,
                         String ext,
                         String desc,
                         String t,
                         Model model){
        model.addAttribute("ext",ext);
        model.addAttribute("id",id);
        model.addAttribute("desc",desc);
        model.addAttribute("t",t);
        return "detail";
    }
}
