package cn.wenqi.oauth2.service.repository;

import cn.wenqi.oauth2.entity.Clicker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wenqi
 * @since v
 */
public interface ClickerRepository extends JpaRepository<Clicker,Integer>,JpaSpecificationExecutor<Clicker> {

}
