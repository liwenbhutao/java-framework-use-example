package com.ht.common.spring.util.database;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 9:05 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@ConfigurationProperties(prefix = "spring.dynamic.datasource")
@Data
@Getter
public class DynamicDataSourceProperties {
    @Value("${names:}")
    private List<String> names;
    @Value("${default:}")
    private String defaultName;
    @Value("${basePackages:}")
    private List<String> basePackages;

    public boolean hasNames() {
        return this.names != null && !this.names.isEmpty();
    }
}
