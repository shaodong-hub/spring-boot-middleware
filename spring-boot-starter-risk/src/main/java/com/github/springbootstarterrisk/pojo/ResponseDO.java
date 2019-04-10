package com.github.springbootstarterrisk.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 创建时间为 14:49-2019-03-20
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
public class ResponseDO {

    private Integer score;

    private String desc;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
