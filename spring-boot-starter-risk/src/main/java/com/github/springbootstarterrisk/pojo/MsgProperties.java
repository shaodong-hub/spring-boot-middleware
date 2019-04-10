package com.github.springbootstarterrisk.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 创建时间为 15:51-2019-03-19
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "com.github.remote")
public class MsgProperties {

    private String url;

    private Integer maxSize;

    private Integer maxRetry;

    private Boolean retryEnable;

    private Integer timeToLive;

    private TimeUnit timeUnit;

}
