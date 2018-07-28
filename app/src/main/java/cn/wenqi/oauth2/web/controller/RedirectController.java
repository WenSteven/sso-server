package cn.wenqi.oauth2.web.controller;

import cn.wenqi.oauth2.web.conf.UrlSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
public class RedirectController {

    @Autowired
    private RestTemplate restTemplate;

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
        CsrfToken csrfToken=new CookieCsrfTokenRepository().loadToken(request);
        restTemplate.postForEntity(urlSettings.getOauth2()+"/logout?{1}={2}",null,Boolean.class,
                csrfToken.getParameterName(),csrfToken.getToken());
        return "index";
    }
}
