package cn.wenqi.oauth2.entity;

import lombok.Data;

/**
 *
 * @author wenqi
 * @since v1.3
 */
@Data
public class Goods {

    private Integer goodsId;

    private String goodsName;
    /**
     * 单位 分
     */
    private int price;
}
