package movieSell;

import org.bouncycastle.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
@SpringBootApplication
@MapperScan("movieSell.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
