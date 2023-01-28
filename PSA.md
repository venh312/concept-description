## PSA(Portable Service Abstraction)란?
서비스의 기능을 접근하는 방식 자체를 일관되게 유지하면서 기술 자체를 유연하게 사용할 수 있도록 하는 것을 PSA(일관된 서비스 추상화)라고 한다.

## PSA가 필요한 이유
애플리케이션에서 사용하는 기술이 변경되더라도 최소한의 변경만으로 접근 방식을 일관된 방식으로 유지함으로써 변경된 요구 사항을 반영하기 위함

추상화 계층을 사용하여 어떤 기술을 내부에 숨기고 개발자에게 편의성을 제공해주는 것이 서비스 추상화(Service Abstraction)이고, 하나의 추상화로 여러 서비스를 묶어둔 것을 PSA(Portable Service Abstraction)이라고 한다.

### 대표적인 예시로 @MVC, @Transactional, @Cacheable가 있다.
@Transactional을 예로 들면, 어떤 데이터베이스를 선택하더라도 트랜잭션 기능을 사용할 수 있는것처럼, 이 걸 가능하게 해주는 개념이 PSA(일관된 서비스 추상화)라고 한다.

추상화 된 상위 클래스를 일관되게 바라보며 하위 클래스의 기능을 사용하는 것이 바로 일관된 서비스 추상화(PSA)의 기본 개념이다.
서비스의 기능을 접근하는 방식 자체를 일관되게 유지하면서 기술 자체를 유연하게 사용할 수 있도록 하는 것을 PSA(일관된 서비스 추상화)라고 한다.

기본적으로 Spring에서는 PlatformTransactionManager이라는 최상위 Manager를 사용하고, 각각 사용자의 선언에 따라서 JPATransactionManger, DatasourceTransactionManager, HibernateTransactionManger 등을 상황에 맞게 의존성 주입을 받아 사용하게 된다.

따라서 개발자는 내부적으로 어떠한 Database Mapping전략을 사용하던지 관계없이 @Transactional이라는 어노테이션을 활용하면 Transaction 처리를 할 수 있게 된다.

PlatformTransactionManager이라는 최상위 Manager를 사용하고, 각각 사용자의 선언에 따라서 JPATransactionManger, DatasourceTransactionManager, HibernateTransactionManger 등을 상황에 맞게 의존성 주입을 받아 사용하게 됩니다.

따라서 개발자는 내부적으로 어떠한 Database Mapping전략을 사용하던지 관계없이 @Transactional이라는 어노테이션을 활용하면 Transaction 처리를 할 수 있게 됩니다.

또 다른 예로는 기본적으로 SpringMVC를 살펴보면 확인할 수 있습니다.

저희는 기본적으로 Servlet기반 Application을 활용하지만, 개발자는 Spring framework를 사용하면 Servlet을 직접적으로 코딩할 일이 거의 없습니다. @Controller, @GET(url) 등의 어노테이션만 활용하게 되면 보이지 않는 곳에서 Spring이 저희가 원하는 기능을 편리하게 처리해 줍니다. 즉 개발자는 HttpServlet을 구현하여 모든 Mapping에 대해 직접적으로 구현할 일이 없게 Service Abstraction을 하여 도와주는 겁니다.

