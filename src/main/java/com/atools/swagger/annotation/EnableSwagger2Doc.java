package com.atools.swagger.annotation;
import org.springframework.context.annotation.Import;
import com.atools.swagger.SwaggerAutoConfiguration;
import java.lang.annotation.*;

/**
 * 发现Swagger中有个EnableSwagger2 故而此处改成EnableSwagger2Doc
 * TODO 想法是废弃掉,依赖 swagger-spring-boot-starter 后自动装配;
 * @author study
 */
@Deprecated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSwagger2Doc {


}