package cn.wenqi.oauth2.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * @author wenqi
 */
@Controller
public class LoginController {
    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @Autowired
    private ApprovalStore approvalStore;
    @RequestMapping("/")
    public ModelAndView root(Map<String,Object> model, Principal principal){


        List<Approval> approvals=clientDetailsService.listClientDetails().stream()
                .map(clientDetails -> approvalStore.getApprovals(principal.getName(),clientDetails.getClientId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        model.put("approvals",approvals);
        model.put("clientDetails",clientDetailsService.listClientDetails());
        return new ModelAndView("admin/index",model);

    }

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value="/approval/revoke",method= RequestMethod.POST)
    public String revokApproval(@ModelAttribute Approval approval){

        approvalStore.revokeApprovals(Collections.singletonList(approval));
        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
                .forEach(tokenStore::removeAccessToken) ;
        return "redirect:/";
    }




    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";}
}
