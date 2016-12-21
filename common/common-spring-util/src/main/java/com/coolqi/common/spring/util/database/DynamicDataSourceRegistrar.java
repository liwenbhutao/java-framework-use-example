package com.coolqi.common.spring.util.database;

import com.coolqi.common.spring.util.druid.DruidDataSourceFactory;
import com.coolqi.common.spring.util.druid.DruidProperties;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 9:49 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class DynamicDataSourceRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Environment environment;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerBeanDefinitions(final AnnotationMetadata importingClassMetadata,
                                        final BeanDefinitionRegistry registry) {
        final ConfigurationProperties annotation =
                AnnotationUtils.findAnnotation(DynamicDataSourceProperties.class, ConfigurationProperties.class);
        final String prefix = StringUtils.hasText(annotation.value()) ? annotation.value() : annotation.prefix();

        final DynamicDataSourceProperties dynamicDataSourceProperties = new DynamicDataSourceProperties();
        bindProperties(dynamicDataSourceProperties, prefix, this.environment);

        if (dynamicDataSourceProperties.hasNames()) {
            for (final String name : dynamicDataSourceProperties.getNames()) {
                final DruidProperties druidProperties = new DruidProperties();
                bindProperties(druidProperties, prefix + "." + name, this.environment);
                final BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(DruidDataSourceFactory.class)
                        .addConstructorArgValue(druidProperties);
                final AbstractBeanDefinition beanDefinition = bdb.getBeanDefinition();
                beanDefinition.addQualifier(new AutowireCandidateQualifier(Qualifier.class, name));
                registry.registerBeanDefinition(name, beanDefinition);
            }
        }
    }

    private <T> T bindProperties(final T t, final String prefix, final Environment environment) {
        final PropertiesConfigurationFactory<T> factory = new PropertiesConfigurationFactory<>(t);
        factory.setConversionService(new DefaultConversionService());
        factory.setTargetName(prefix);

        if (environment instanceof ConfigurableEnvironment) {
            final ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            final MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            factory.setPropertySources(propertySources);
        }

        try {
            factory.bindPropertiesToTarget();
        } catch (final BindException e) {
            throw new BeanCreationException("DynamicDataSourceRegistrar", "Could not bind properties to "
                    + t.getClass() + " (" + prefix + ")", e);
        }
        return t;
    }
}
