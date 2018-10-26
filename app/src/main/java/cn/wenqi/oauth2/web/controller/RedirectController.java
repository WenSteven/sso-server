package cn.wenqi.oauth2.web.controller;

import cn.wenqi.oauth2.web.conf.UrlSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
public class RedirectController {

    @Autowired
    private UrlSettings urlSettings;

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

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        return "index";
    }
}
