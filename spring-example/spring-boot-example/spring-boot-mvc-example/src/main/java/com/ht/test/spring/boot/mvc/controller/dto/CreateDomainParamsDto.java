package com.ht.test.spring.boot.mvc.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 2016/12/4
 * @time 下午4:48
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Data
@ApiModel
public class CreateDomainParamsDto {
    @ApiParam(value = "id字段描述信息")
    private long id;
    /**
     * 距离中心的最大距离
     * 距离中心的最大距离不正确
     * 单位:米
     */
    @ApiParam(value = "距离中心的最大距离,单位为米", required = true)
    @Range(min = 1, max = 2000, message = "{SearchBikeParamsDto.maxDistance.Range}")
    private long maxDistance;
    /**
     * 返回结果数目
     */
    @ApiParam(value = "返回结果数目", required = true)
    @Range(min = 1, max = 100, message = "返回结果数目不正确")
    private int maxResultNum;
    /**
     * 经度
     */
    @ApiParam(value = "经度", required = true)
    @Range(min = -180, max = 180, message = "经度不正确")
    private float centerLongitude;
    /**
     * 纬度
     */
    @ApiParam(value = "纬度", required = true)
    @Range(min = -90, max = 90, message = "纬度不正确")
    private float centerLatitude;
    /**
     * 省
     */
    @ApiParam(value = "省", required = true)
    @NotEmpty(message = "省参数不能为空")
    private String province;
    /**
     * 市
     */
    @ApiParam(value = "市", required = true)
    @NotEmpty(message = "市参数不能为空")
    private String city;
    /**
     * 县／城区
     */
    @ApiParam(value = "县／城区")
    private String countyOrDistrict;
    @ApiParam(value = "自行车类型")
    private int type;
}
