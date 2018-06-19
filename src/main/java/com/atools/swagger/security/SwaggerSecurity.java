package com.atools.swagger.security;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.List;

import com.atools.swagger.properties.SwaggerSecurityProperties;
import com.atools.swagger.properties.enums.PathType;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 安全相关
 *
 * @author study
 * @since 2018/6/14 0014
 */
public class SwaggerSecurity {

    private final SwaggerSecurityProperties swaggerSecurityProperties;

    public SwaggerSecurity(SwaggerSecurityProperties swaggerSecurityProperties) {
        this.swaggerSecurityProperties = swaggerSecurityProperties;
    }


    public ApiKey apiKey() {
        final SwaggerSecurityProperties.ApiKey apiKey = swaggerSecurityProperties.getApiKey();
        return new ApiKey(apiKey.getName(), apiKey.getKeyName(), apiKey.getParamType());
    }

    /**
     * 采用正则表达式进行 HTTP API 全局鉴权接口配置；默认 any 所有接口都授权
     * 其中 securityReferences 为配置启用的鉴权策略
     *
     * @return SecurityContext
     */
    public SecurityContext securityContext() {
        final SwaggerSecurityProperties.ApiKey apiKey = swaggerSecurityProperties.getApiKey();
        final PathType pathType = apiKey.getPathType();
        return SecurityContext.builder()
                .securityReferences(defaultAuth(apiKey))
                .forPaths(pathType == PathType.REGEX ? PathSelectors.regex(apiKey.getAuthRegex()) :
                        pathType == PathType.ANY ? PathSelectors.any() : PathSelectors.none()).build();
    }

    /**
     * 配置默认的全局鉴权策略； SecurityReference 中 reference 参数需要与 ApiKey.name 保持一致
     *
     * @return List<SecurityReference>
     */
    private List<SecurityReference> defaultAuth(SwaggerSecurityProperties.ApiKey apiKey) {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        return newArrayList(new SecurityReference(apiKey.getName(), new AuthorizationScope[]{authorizationScope}));
    }

}
