package cn.wenqi.oauth2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * 
 * <h2>Permission</h2>
 * 
 * @author Kristijan Georgiev
 * 
 *         Permission entity
 *
 */

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	private String name;

}
