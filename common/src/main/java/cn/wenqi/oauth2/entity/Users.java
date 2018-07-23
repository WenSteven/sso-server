package cn.wenqi.oauth2.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wenqi
 * @since v
 */
@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @Column(name = "username")
    private String userName;

    private String password;

    private Integer enable=1;
}
