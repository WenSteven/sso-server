package cn.wenqi.oauth2.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wenqi
 */
@RestController
public class UserController {


    @RequestMapping({ "/user", "/me" })
    public Principal user(Principal principal) {
        return principal;
    }
}
