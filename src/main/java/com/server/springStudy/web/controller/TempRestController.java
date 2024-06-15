package com.server.springStudy.web.controller;

import com.server.springStudy.apiPayload.ApiResponse;
import com.server.springStudy.converter.TempConverter;
import com.server.springStudy.service.tempService.TempQueryService;
import com.server.springStudy.web.dto.temp.TempResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 0. @ResponseBody

 [데이터 처리 모델]
 동기식 처리 모델 / 비동기식 처리 모델

 [동기 synchronous]
 사용자가 데이터를 서버에게 요청하면, 그 서버가 데이터 요청에 따른 응답을 사용자에게 다시 리턴해주기 전까지 사용자는 다른 활동 못하고 기다려야 함

 [비동기 Asynchronous]
 서버에게 데이터를 요청한 후, 요청에 따른 응답을 계속 기다리지 않고 다른 외부 활동 수행해도 되고 서버에게 다른 요청을 보내도 됨

 [@RequestBody, @ResponseBody]
 웹에서 화면 전환 없이 이루어지는 동작들은 대부분 비동기 통신으로 이루어짐. 비동기 통신을 하기 위해선 (클라->서버 요청 메시지의 Body에, 서버->클라 응답 메시지의 Body에) 데이터를 담아서 보내야 함
 @RequestBody : 비동기 통신에서 쓰이는 Body 안의 데이터 (JSON 객체) -> 자바 객체 로 변환
 @ResponseBody : 자바 객체 -> JSON 객체 변환

 1. @RestController

 [@Controller / @RestController]
 Controller 를 지정해주기 위한 어노테이션. Response Body 가 생성되는 방식에서 차이가 있음

 [@Controller]
 전통적인 SpringMVC의 Controller. 주로 "View" 를 반환하기 위해 사용

 [@RestController]
 RESTful 웹 서비스의 Controller. @Controller + @ResponseBody 어노테이션이라고 볼 수 있음. 반환하려는 주류는 JSON 형태의 객체 데이터
 Spring MVC 의 컨트롤러 (@Controller) 를 사용할 때 "데이터" 를 반환해야 하는 경우가 있는데, 이때 자바 객체를 JSON 객체로 변환하기 위해 @ResponseBody 사용. @RestController = @Controller + @ResponseBody
 */
@RestController
@RequestMapping("/temp")

/*
 0. Spring 프레임워크의 의존성 주입 방식
 : 필드 주입 / setter 주입 / 생성자 주입

 1. 생성자 주입
 : 객체 생성 시점에 필요한 의존성을 생성자 파라미터로 전달받아 주입하는 방식

 2. Lombok의 @RequiredArgsConstructor

 [Lombok]
 불필요한 코드와 작업을 줄여주는 라이브러리. 다양한 어노테이션 제공
 @AllArgsConstructor / @RequiredArgsConstructor / @NoArgsConstructor / @Getter / @Builder 등

 [Lombok 사용 이점]
 스프링에서는 @Autowired 를 사용해서 생성자 주입 방식으로 의존성 주입을 하는데,
 Lombok 이 제공하는 생성자 주입 관련 어노테이션을 사용하면, Spring 이 자동으로 생성자를 통해 의존성을 주입하므로 @Autowired 없이도 의존성 주입을 쉽게 할 수 있음

 [@RequiredArgsConstructor]
 Lombok 의 생성자 주입 관련 어노테이션. final 이나 @NonNull 로 선언된 필드만을 파라미터로 받는 생성자를 자동으로 생성. 따라서 의존성 주입을 더욱 간편하게 할 수 있음
 즉, 스프링에서 Lombok 으로 의존성 주입 하는 방법 중에, 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
 새로운 필드를 추가할 때마다 생성자를 수정해야하는 번거로움을 없애줌
 */
@RequiredArgsConstructor
public class TempRestController {

    // ** 구현체 아닌 인터페이스 작성 (컨트롤러는 인터페이스를 의존하며 실제 인터페이스에 대한 구체화 클래스는 Springboot의 의존성 주입 이용)
    private final TempQueryService tempQueryService;

    @GetMapping("/")
    // 현재는 <String> 으로 뒀지만 onSuccess 타입을 제네릭스로 했으므로 DTO나 다른 클래스도 가능
    public ApiResponse<String> stringTest() {

        return ApiResponse.onSuccess("성공!");
    }

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> test() {

        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){

        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}
