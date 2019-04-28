package com.github.springbootstarterrisk.service;

import com.alibaba.fastjson.JSON;

import com.github.springbootstarterrisk.pojo.MsgProperties;
import com.github.springbootstarterrisk.pojo.ResponseDO;
import com.github.springbootstarterrisk.pojo.ReturnDO;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * <p>
 * 创建时间为 14:45-2019-03-20
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Component
@ConditionalOnMissingBean
public class RemoteService {

    private RestTemplate restTemplate;

    private MsgProperties msgProperties;

    public RemoteService(RestTemplate restTemplate, MsgProperties msgProperties) {
        this.restTemplate = restTemplate;
        this.msgProperties = msgProperties;
    }

    @SneakyThrows(URISyntaxException.class)
    public ReturnDO<ResponseDO> getRiskResult(Map<String, String> requestData) {
        RequestEntity requestEntity = RequestEntity.post(new URI(msgProperties.getUrl()))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .body(JSON.toJSONString(requestData));
        ParameterizedTypeReference<ReturnDO<ResponseDO>> myBean = new ParameterizedTypeReference<ReturnDO<ResponseDO>>() {
        };
        ResponseEntity<ReturnDO<ResponseDO>> response = restTemplate.exchange(requestEntity, myBean);
        return response.getBody();
    }

}
