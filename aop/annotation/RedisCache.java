package movieSell.aop.annotation;

import java.lang.annotation.*;

/**
 * projectName:
 *
 * @author: Shannon
 * description:Redis缓存策略注解
 * 承载在方法上
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
        //注解参数
    long duration() default  -1;

}
