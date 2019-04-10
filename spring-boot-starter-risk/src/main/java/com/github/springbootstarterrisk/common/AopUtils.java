package com.github.springbootstarterrisk.common;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * <p>
 * 创建时间为 17:19-2019-03-19
 * 项目名称 SpringBootMiddleware
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


public class AopUtils {

    /**
     * Gets a {@link Method} object from target object (not proxy class).
     *
     * @param joinPoint the {@link JoinPoint}
     * @return a {@link Method} object or null if method doesn't exist or if the signature at a join point
     * isn't sub-type of {@link MethodSignature}
     */
    public static Method getMethodFromTarget(JoinPoint joinPoint) {
        Method method = null;
        if (joinPoint.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            method = getDeclaredMethod(joinPoint.getTarget().getClass(), signature.getName(), getParameterTypes(joinPoint));
        }
        return method;
    }


    /**
     * Gets parameter types of the join point.
     *
     * @param joinPoint the join point
     * @return the parameter types for the method this object
     * represents
     */
    private static Class[] getParameterTypes(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getParameterTypes();
    }


    /**
     * Gets declared method from specified type by mame and parameters types.
     *
     * @param type           the type
     * @param methodName     the name of the method
     * @param parameterTypes the parameter array
     * @return a {@link Method} object or null if method doesn't exist
     */

    @SneakyThrows(NoSuchMethodException.class)
    private static Method getDeclaredMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
        return type.getDeclaredMethod(methodName, parameterTypes);
    }


}
