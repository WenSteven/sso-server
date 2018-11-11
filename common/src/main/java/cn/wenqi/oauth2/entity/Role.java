package cn.wenqi.oauth2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 角色 比如管理员角色等，角色下还有不同的权限 {@link Permission}
 * @author wenqi
 */

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permission_role", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private List<Permission> permissions;

}
