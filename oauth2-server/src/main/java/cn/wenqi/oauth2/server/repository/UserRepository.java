package cn.wenqi.oauth2.server.repository;

import cn.wenqi.oauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Kristijan Georgiev
 * 
 *         UserRepository with custom methods for finding a User by username or
 *         email
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

}
