package cn.wenqi.oauth2.server.controller;

import cn.wenqi.oauth2.entity.User;
import cn.wenqi.oauth2.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author wenqi
 * @since v1.1
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/list")
    public String list(Model model){
        List<User> userList=userRepository.findAll();
        model.addAttribute("users",userList);
        return "admin/user_list";
    }
}
