package com.github.springbootservice.controller;

import com.github.springbootservice.pojo.RequestDO;
import com.github.springbootstarterrisk.annotation.MappingProperty;
import com.github.springbootstarterrisk.annotation.RiskCommand;
import com.github.springbootstarterrisk.pojo.RiskResultDO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 创建时间为 16:10-2019-03-19
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@RestController
public class RequestController {

    @RiskCommand(
            requestIndex = 0,
            resultIndex = 1,
            mappingProperty = {
                    @MappingProperty(name = "name", value = "userName"),
                    @MappingProperty(name = "pass", value = "userPass")
            }
    )
    @PostMapping(value = "remote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RequestDO getRemote(@RequestBody RequestDO remoteDO, RiskResultDO riskResultDO) {
        System.out.println("请求数据:" + remoteDO.toString());
        System.out.println("风控结果:" + riskResultDO.toString());
        return remoteDO;
    }

}
