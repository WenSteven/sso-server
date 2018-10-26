package cn.wenqi.oauth2.server.service;


import cn.wenqi.oauth2.server.entity.User;
import cn.wenqi.oauth2.server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Kristijan Georgiev
 *
 */
@Service(value = "userDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String input) {
		log.info("读取用户信息：{}",input);
		User user;
		if (input.contains("@"))
			user = userRepository.findByEmail(input);
		else
			user = userRepository.findByUsername(input);

		if (user == null)
			throw new BadCredentialsException("Bad credentials");

		new AccountStatusUserDetailsChecker().check(user);
		return user;
	}

}
