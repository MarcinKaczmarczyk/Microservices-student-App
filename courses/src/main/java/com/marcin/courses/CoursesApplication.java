package com.marcin.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursesApplication.class, args);
    }


//    int[] someArray = {0, 1, 2, 3, 9, 8, 7, 5, 13};
//    int[] g = {0, 1, 2, 4, 5, 6, 7, 8, 9,13};
//
//    public List<Integer> findMissing(int[] arr) {
//        int[] sorted = Arrays.stream(arr).sorted().toArray();
//        List<Integer> result = new ArrayList<>();
//        int hold=0;
//        for (int i = 0; i < sorted.length; i++) {
//            if (sorted[i] != i){
//                result.add(i);
//            hold++;
//        }
//            hold++;
//    }
//        return result;
//
//}
//
//    @PostConstruct
//    public void go() {
//        System.out.println("hathaethaethaerthaehtaeheat");
//        System.out.println(findMissing(someArray));
//    }


    @PostConstruct
    public void enu(){

    }
}
