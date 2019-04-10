package com.github.springbootstarterrisk.aspect;


import com.github.springbootstarterrisk.annotation.MappingProperty;
import com.github.springbootstarterrisk.annotation.RiskCommand;
import com.github.springbootstarterrisk.common.AopUtils;
import com.github.springbootstarterrisk.pojo.RemoteResultDO;
import com.github.springbootstarterrisk.pojo.ResponseDO;
import com.github.springbootstarterrisk.pojo.ReturnDO;
import com.github.springbootstarterrisk.service.RemoteService;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joor.Reflect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>
 * 创建时间为 15:58-2019-03-19
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Aspect
@Component
public class AspectRemote {

    @Resource
    private RemoteService remoteService;

    @Pointcut("@annotation(com.github.springbootstarterrisk.annotation.RiskCommand)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    @SneakyThrows(Throwable.class)
    public Object logAround(ProceedingJoinPoint pdj) {
        Map<String, String> requestData = getRequestMap(pdj);
        ReturnDO<ResponseDO> returnDO = remoteService.getRiskResult(requestData);
        setResult(pdj, returnDO);
        return pdj.proceed();
    }


    private void setResult(ProceedingJoinPoint pdj, ReturnDO<ResponseDO> returnDO) {
        Object[] objects = pdj.getArgs();
        RemoteResultDO remoteResultDO = (RemoteResultDO) objects[1];
        remoteResultDO.setMessage("success:" + returnDO.getData());
    }


    private Map<String, String> getRequestMap(ProceedingJoinPoint pdj) {
        Method method = AopUtils.getMethodFromTarget(pdj);
        RiskCommand remote = method.getAnnotation(RiskCommand.class);
        int requestIndex = remote.requestIndex();
        int resultIndex = remote.resultIndex();





        Object[] objects = pdj.getArgs();


        MappingProperty[] properties = remote.mappingProperty();
        Map<String, String> map = Maps.newHashMap();
        for (MappingProperty property : properties) {
            String value = Reflect.on(objects[requestIndex]).get(property.name());
            map.put(property.value(), value);
        }
        return map;
    }


}
