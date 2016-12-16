package com.ht.common.util.gis;

import com.github.davidmoten.geo.GeoHash;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/12/1
 * @time 上午11:27
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@UtilityClass
public class GeoHashUtils {
    /**
     * a）在纬度相等的情况下：
     * 经度每隔0.00001度，距离相差约1米；
     * 每隔0.0001度，距离相差约10米；
     * 每隔0.001度，距离相差约100米；
     * 每隔0.01度，距离相差约1000米；
     * 每隔0.1度，距离相差约10000米。
     * <p>
     * b）在经度相等的情况下：
     * 纬度每隔0.00001度，距离相差约1.1米；
     * 每隔0.0001度，距离相差约11米；
     * 每隔0.001度，距离相差约111米；
     * 每隔0.01度，距离相差约1113米；
     * 每隔0.1度，距离相差约11132米。
     * <p>
     * geohash的位数是9位数的时候，大概为附近2米
     *
     * @param latitude
     * @param longitude
     * @param length
     * @return
     */
    public static String getGeoHash(final double latitude,
                                    final double longitude,
                                    final int length) {
        return GeoHash.encodeHash(latitude, longitude, length);
    }

    public static List<String> getNeighboursGeoHashList(final String geoHash) {
        return GeoHash.neighbours(geoHash);
    }
}
