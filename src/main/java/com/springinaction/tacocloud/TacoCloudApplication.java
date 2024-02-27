package com.springinaction.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 은  아래의 어노테이션이 결합된 형태이다.
 * @SpringBootConfiguration : 현재 클래스를 구성 클래스로 지정한다
 * @EnableAutoConfiguration : 스프링 부트 자동-구성을 활성화한다.
 * @ComponentScan : @Component, @Controller, @Service 등의 어노테이션과 함께 클래스를 선언할 수 있게 해준다.
 */
@SpringBootApplication  // 스프링 부트 애플리케이션
public class TacoCloudApplication {

    /**
     * main() 메서드는 JAR 파일이 실행될  때 호출되어 실행되는 메서드
     * 실제로 애플리케이션을 시작시키고 스프링 애플리케이션 켄텍스트를 생성 하는 SpringApplication 클래스의
     * run() 메서드를 호출한다.
     * 두개의 매개변수는 구성 클래스와 Command-line 인자다.
     */
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args); // 애플리케이션을 실행한다
    }

}
