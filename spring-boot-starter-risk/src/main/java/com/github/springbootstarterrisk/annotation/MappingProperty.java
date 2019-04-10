package com.github.springbootstarterrisk.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 创建时间为 17:30-2019-03-19
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MappingProperty {


    /**
     * Property name.
     *
     * @return name
     */
    String name();

    /**
     * Property value
     *
     * @return value
     */
    String value();


}
