package com.ht.test.spring.boot.mvc.service.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created on 2016/11/11.
 * tb_project
 *
 * @author hutao
 * @version 1.0
 */
@Data
public class Domain {
    /**
     * 主键id
     */
    private long id;
    /**
     * 状态
     * tinyint
     */
    private int status;
    /**
     * 创建时间
     * datetime
     */
    private Date createTime;
    /**
     * 修改时间
     * timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */
    private Date updateTime;
}
