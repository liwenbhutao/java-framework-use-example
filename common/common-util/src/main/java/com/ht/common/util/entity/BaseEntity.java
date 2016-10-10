package com.ht.common.util.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by ht on 5/15/15.
 * 数据库对象的基类
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    protected long id;
    /**
     * 状态
     * tinyint
     */
    protected int status;
    /**
     * 创建时间
     * datetime
     */
    protected Date createTime;
    /**
     * 修改时间
     * timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */
    protected Date updateTime;
}
