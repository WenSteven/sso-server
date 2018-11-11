package cn.wenqi.oauth2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 点击量
 *
 * @author wenqi
 * @since v1.0.1
 */
@Entity
@Table(name = "res_clicker")
@Setter
@Getter
public class Clicker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "rid")
    private IResources iResources;

    @OneToOne
    @JoinColumn(name = "uid")
    private User users;
    /**
     * 点击时间
     */
    private Date clickTime;
    /**
     * 观看时长
     */
    private Integer watchime;
}
