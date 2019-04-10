package com.github.springbootstarterrisk.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 创建时间为 15:54-2019-03-19
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
public class RemoteResultDO {

    private String message;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
