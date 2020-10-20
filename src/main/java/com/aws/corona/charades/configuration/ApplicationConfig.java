//package com.aws.corona.charades.configuration;
//
//import com.aws.corona.charades.controller.GamePlayController;
//import com.aws.corona.charades.domain.CategoryMap;
//import com.aws.corona.charades.repositories.GameRepository;
//import com.aws.corona.charades.repositories.UuidGeneratorImpl;
//import com.aws.corona.charades.service.GameService;
//import com.aws.corona.charades.service.GamePlayService;
//import com.aws.corona.charades.service.GameSetUpService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import com.aws.corona.charades.controller.GameSetUpController;
//
//import java.util.HashMap;
//import java.util.Random;
//
//@Configuration
//@ComponentScan({ "com.aws.corona.charades" })
//@PropertySource("classpath:application.properties")
//public class ApplicationConfig {
//
////    @Bean
////    public GameSetUpController createGameSetUpController() {
////        return new GameSetUpController(createGameSetUpService(), createGameService());
////    }
////
////    @Bean
////    public GamePlayController createGamePlayController() {
////        return new GamePlayController(createGamePlayService(), createGameService());
////    }
////
////    @Bean
////    public GamePlayService createGamePlayService() {
////        return new GamePlayService(createGameService());
////    }
////
////    @Bean
////    public GameSetUpService createGameSetUpService(){
////        return new GameSetUpService(new CategoryMap(new HashMap<>()), createGameService());
////    }
////
////    @Bean
////    public GameService createGameService(){
////        return new GameService(new GameRepository(new HashMap<>(), new UuidGeneratorImpl()));
////    }
//
//}
