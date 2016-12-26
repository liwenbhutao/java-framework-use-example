package com.ht.common.spring.util.druid;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.*;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/26/16
 * @time 4:17 PM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Import({EnableDruidWebServlet.DruidWebServletConfigurerImportSelector.class})
public @interface EnableDruidWebServlet {

    class DruidWebServletConfigurerImportSelector implements ImportSelector {

        @Override
        public String[] selectImports(final AnnotationMetadata importingClassMetadata) {
            return new String[]{DruidServletConfiguration.class.getName()};
        }

    }
}
