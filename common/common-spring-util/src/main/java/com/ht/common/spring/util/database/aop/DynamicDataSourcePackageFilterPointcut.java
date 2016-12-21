package com.ht.common.spring.util.database.aop;

import com.ht.common.spring.util.aop.AnnotationDeepMethodMatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.util.List;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 9:33 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Slf4j
public class DynamicDataSourcePackageFilterPointcut implements Pointcut {
    private final List<String> packages;

    public DynamicDataSourcePackageFilterPointcut(final List<String> packages) {
        this.packages = packages;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            for (final String packageName : this.packages) {
                log.debug("{} | {} : {}", packageName, clazz.getName(), packageName.startsWith(clazz.getName()));
                if (clazz.getName().startsWith(packageName)) {
                    return true;
                }
            }
            return false;
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new AnnotationDeepMethodMatcher(DynamicDataSourceAnnotation.class);
    }

}
