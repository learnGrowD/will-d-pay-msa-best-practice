package com.willd.moneyservice;

import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyServiceApplication {
    public static void  main(String[] args) {
        SpringApplication.run(MoneyServiceApplication.class, args);
    }
}
