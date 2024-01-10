### 출처
https://gmoon92.github.io/spring/aop/2019/04/20/jdk-dynamic-proxy-and-cglib.html


사실은 JDK Dynamic Proxy 때문입니다. interface에서만 위빙되는 특징 때문에 인터페이스 생성이 강제였던거구요. CGLIB이 등장하면서 더 이상 인터페이스로 구현할 필요가 없어진거죠..

 

여기까지 JDK Dynamic Proxy와 CGLib에 대해 알아보았습니다.

 

정리를 해보자면, JDK Dynamic Proxy는 인터페이스를 구현하여 Proxy를 생성해주고, Spring은 인터페이스가 아닌 클래스를 가지고 Proxy를 생성해주기 위해 CGLib 방식을 지원하고 있습니다.

 

CGLib은 클래스를 상속받아 Proxy를 생성해준다는 점, 마지막으로 CGLib이 가지고 있었던 3 가지 한계점이 모두 개선되어 Spring Boot에선 기본 Proxy 생성 방법으로 사용하고 있다는 부분을 인지하셨으면 좋겠습니다.

 

또한, JDK Dynamic Proxy는 Spring AOP의 AOP 기술의 근간이 되는 방식이기 때문에 Spring에서 사용되는 AOP의 기술들은 Proxy 메커니즘을 따르고 있습니다. 즉 CGLib이든 JDK Dynamic Proxy든 Proxy 메커니즘을 따른다는 점을 인지하셔야 됩니다.

 

프록시 서버는 클라이언트가 자신을 통해서 다른 네트워크 서비스에 간접적으로 접속할 수 있게 해 주는 컴퓨터 시스템이나 응용 프로그램을 가리킨다

 

서버와 클라이언트 사이에 중계기로서 대리로 통신을 수행하는 것을 가리켜 '프록시', 그 중계 기능을 하는 것을 프록시 서버라고 부른다.

 

우선 CGlib의 의존성을 추가하여 개발해야 된다는 점은 Spring 3.2 버전부터 CGLib을 Spring Core 패키지에 포함시켜 더이상 의존성을 추가하지 않아도 개발할 수 있게 되엇습니다.

그 다음 4 버전에선 Objensis 라이브러리의 도움을 받아 [2] default 생성자 없이도 Proxy를 생성할 수 있게 되었고, [3] 생성자가 2 번 호출되던 상황도 같이 개선이 되었습니다.

결과적으로 기존의 CGLib이 가지고 있던 대부분의 한계들이 개선이 되어, Spring에선 성능이 좋은 CGLib으로 Proxy를 생성하게 되었습니다.
