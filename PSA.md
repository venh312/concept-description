## PSA(Portable Service Abstraction)란?
서비스의 기능에 접근하는 방식을 일관되게 유지하면서 기술 자체를 유연하게 사용할 수 있도록 하는 것을 PSA(일관된 서비스 추상화)라고 한다.
즉, PSA가 적용된 코드는 나의 코드가 바뀌지 않고, 다른 기술로 바꿀 수 있도록 확장성이 좋고, 어떤 기술에 특화되어 있지 않는 코드를 의미한다.

### 대표적인 예로 Spring의 MVC, Transactional, Cacheable가 있다.

## Transactional
@Transactional을 예로 들면, 어떤 데이터베이스를 선택하더라도 트랜잭션 기능을 사용할 수 있는것처럼, 이러한 기능을 가능하게 해주는 개념이 PSA(일관된 서비스 추상화)라고 한다.

이게 가능한 이유는 PlatformTransactionManager이라는 최상위 Manager를 사용하고, 각각 사용자의 선언에 따라서 JPATransactionManger, DatasourceTransactionManager, HibernateTransactionManger 등을 상황에 맞게 의존성 주입을 받아 사용하게 만들어졌기 때문이다.

## Spring MVC
기본적으로 Servlet 기반의 Application을 활용하지만, 개발자는 Spring framework를 사용하면 Servlet을 직접적으로 코딩할 일이 거의 없다. 

@Controller, @GET(url) 등의 어노테이션만 활용하게 되면 보이지 않는 곳에서 Spring이 처리해 준다. 

즉, 개발자는 HttpServlet을 구현하여 모든 Mapping에 대해 직접적으로 구현할 일이 없게 Service Abstraction을 통해 편리하게 사용할 수 있는것이다.
