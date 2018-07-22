package cn.wenqi.oauth2.service.repository;

import cn.wenqi.oauth2.entity.IResources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wenqi
 * @since v
 */
public interface IResourcesRepository extends JpaRepository<IResources,Integer>,JpaSpecificationExecutor<IResources> {

}
