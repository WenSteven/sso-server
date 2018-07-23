package cn.wenqi.oauth2.entity;

import lombok.Data;

import java.util.List;

/**
 * 分页model
 * @author wenqi
 * @since v1.0.1
 */
@Data
public class PageInfo<T> {
    /**
     * 数据项
     */
    private List<T> data;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 总数
     */
    private long totalCount;
}
