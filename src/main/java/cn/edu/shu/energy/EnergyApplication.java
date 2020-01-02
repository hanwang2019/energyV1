package cn.edu.shu.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//项目的启动类
@SpringBootApplication
@EnableScheduling
public class EnergyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyApplication.class, args);
    }
}

//第一个参数告诉Spring哪个是主要组件，第二个参数是运行时输入的其他参数