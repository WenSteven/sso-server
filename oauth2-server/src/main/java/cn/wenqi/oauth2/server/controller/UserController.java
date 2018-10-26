package cn.wenqi.oauth2.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wenqi
 */
@RestController
public class UserController {


    @GetMapping("/userInfo")
    public Principal user(Principal principal) {
        return principal;
    }
}
