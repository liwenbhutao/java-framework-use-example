package com.ht.common.util.gis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/12/2
 * @time 下午5:45
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class LatitudeLongitudePair {
    private final double longitude;
    private final double latitude;
}
