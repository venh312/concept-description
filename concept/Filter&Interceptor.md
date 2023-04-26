### Filter
- Servlet Specification에 따라 동작하는 기능
- DispatcherServlet 이전에 위치하며, 요청이 Controller에 도달하기 전에 가로챈다.
- HttpServletRequest, HttpServletResponse 객체를 인자로 받아 처리할 수 있다.
- 여러 개의 Filter를 등록하여 체인 형태로 동작할 수 있으며, 순서대로 실행된다.
- 전역적으로 적용되며, 모든 URL 패턴에 대해 동작한다.
  
### Interceptor
- Spring MVC Framework에서 지원하는 기능
- DispatcherServlet에서 Controller로 가는 요청, Controller에서 DispatcherServlet으로 가는 응답, 그리고 Exception 발생시 동작한다.
- HandlerInterceptor 인터페이스를 구현하여 사용하며, preHandle(), postHandle(), afterCompletion() 메서드를 정의하여 처리한다.
- DispatcherServlet과 Controller 사이에서 동작하므로, Controller에서 처리하는 모든 RequestMapping에 적용된다.
- HandlerInterceptor를 구현한 클래스는 스프링 빈으로 등록하여 사용한다.

따라서 Filter는 모든 요청에 대해 동작하고, HttpServletRequest와 HttpServletResponse 객체를 이용하여 요청과 응답을 처리하는 반면, Interceptor는 DispatcherServlet과 Controller 사이에서 동작하고, RequestMapping 처리 전, 후, 예외 발생시에 처리가 가능하다는 차이가 있습니다. 또한 Interceptor는 Filter와 달리 스프링 빈으로 등록하여 사용하며, 다양한 AOP와 결합하여 사용할 수 있습니다.
