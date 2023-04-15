## WebFlux의 등장

WebFlux는 논블로킹으로 동작하는 웹 스택의 필요성 때문에 등장하게 되었다. 기존 SpringMVC의 Servlet API는 v3.1부터 논블로킹을 위한 API를 제공했었다. 하지만 이외의 동기적으로 처리하는 모듈(Filter, Servlet)과 블로킹 방식의 API(getParameter, getPart)들이 있기에 완벽한 논블로킹 환경의 개발을 할 수 없었으며, 비동기 논블로킹 환경의 서버로 Netty가 부상하고 있었으며 이 Netty와의 연동을 위해 Spring은 새로운 API가 필요했다.

## Spring WebFlux는 아래와 같은 사용에 추천
- 비동기, non-blocking reactive 개발
- 효율적으로 동작하는 고성능 웹어플리케이션 개발
- 서비스간 호출이 많은 마이크로서비스 아키텍처
- 일반적으로 blocking 코드 작성에는 익숙하기 때문에 생산성이 높을 것이나 non-blocking 코드에는 익숙하지 않고 확실히 이해하고 코딩하지 않으면 알 수 없는 오류도 발생하기 쉽고 디버깅도 어렵기 때문에 학습이 필요하다. 그리고 non-blocking과 blocking 코드를 같이 사용하게 되면 비동기 코드가 무의미해지고 성능적인 이점도 볼 수 없기 때문에 고려해야할 부분도 많다.

## Spring MVC vs WebFlux

### Spring MVC와 WebFlux의 공통점
@Controller, Reactive클라이언트이다. 둘 모두 Tomcat, Jetty, Undertow와 같은 서버에서 실행할 수 있다. (Spring PSA)    

### Spring MVC, 명령형 논리, JBDC, JPA
Spring MVC는 하나의 요청에 대해 하나의 스레드가 사용된다(thread-per-request). 그래서 다량의 요청을 대비해 미리 스레드 풀을 생성해놓으며, 각 요청마다 스레드를 할당하여 처리한다.

### Spring WebFlux
에서는 기능적 엔트 포인트, 이벤트 루프, 동시성 모델을 가질 수 있다.
