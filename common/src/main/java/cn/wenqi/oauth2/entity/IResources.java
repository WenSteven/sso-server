package cn.wenqi.oauth2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wenqi
 * @since v
 */
@Entity
@Table(name = "resources")
@Setter
@Getter
public class IResources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;
    /**
     * 文件名 文件的全路径
     */
    @NotNull
    private String name;
    /**
     * 后缀名
     */
    @NotNull
    private String ext;
    /**
     * 创建时间
     */
    private Date createTime;
}
