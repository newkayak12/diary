# Spring_BASE

- spring 2.6.7
- spring config server client 3.1.3
- hibernate 5.6.8.FINAL
- querydsl 5.0.0
- modelmapper 3.1.0
- reflections 0.10.2
- spring-security-crypto 5.6.3
- jjwt 0.9.1 / jjwt-api 0.11.2
- [swagger3](http://localhost:8080/swagger-ui/index.html#/) (springfox-boot-starter 3.0.0 / springfox-swagger-ui 3.0.0)


### Jwts 관련 처리 토큰 

>   <br/> @Authorization : (@RequestHeader Object name) :: Method에 표시
>   <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -> AOP에서 자동으로 토큰을 dto로 변경하는데 이를 위해 하는 표시
>   <br/>
>   <br/> @AuthorizeDto : 인증 DTO :: ElementType으로 class에 표시
>   <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -> AOP에서 대상 dto로 선정하는 기준
>   <br/>
>   <br/> @IgnoreEncrypt :  Jwt body 변환 무시
>   <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -> 주로 변환 대상이 되는 dto의 필드에 선언
>   <br/>
>   <br/>


### Junit Test
```java
  class BaseTest{
//    아래의 Object는 request를 보내는 파라미터를 보내는 부분
    
      get(String url, Object object);
//      RestTemplate GetMapping
    
      post(String url, Object object);
//      RestTemplate PostMapping
    
      patch(String url, Object object);
//      RestTemplate PatchMapping
      
      delete(String url, Object object);
//      RestTemplate DeleteMapping
  }
    
```
