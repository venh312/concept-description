
### fetch join
JPA(Java Persistence API)에서 fetch join은 엔티티 그래프를 로드할 때 사용되는 개념입니다. 엔티티 그래프는 엔티티와 그와 연관된 엔티티들 간의 관계를 시각적으로 표현한 것입니다.

fetch join은 연관된 엔티티들을 함께 로드하여 즉시 데이터베이스에서 모든 연관 엔티티들을 가져오는 것을 의미합니다. 이는 lazy loading이나 추가적인 쿼리 없이도 연관된 엔티티들을 한 번에 가져오는 것을 가능하게 합니다.

지연로딩이 설정되어 있어도 fetch join을 하면 한번에 데이터를 조회합니다.

### 예제
#### Member, Team Entity
```java
@Entity
public class Member {
    @Id
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    // other fields, getters and setters
}

@Entity
public class Team {
    @Id
    @Column(name="team_id")
    private Long id;

    private String name;

    // other fields, getters and setters
}
```

#### JPQL
```java
String jpql = "SELECT m FROM Member m JOIN FETCH m.team";

List<Order> orders = entityManager.createQuery(jpql, Order.class).getResultList();
```

위의 JPQL 쿼리는 Member 엔티티와 Team 엔티티를 함께 로드합니다. 이때 JOIN FETCH 구문을 사용하여 fetch join을 수행하고 있습니다. 이렇게 함으로써 추가적인 쿼리 없이 연관된 엔티티를 함께 가져올 수 있습니다.


#### 실제 실행되는 SQL
```
SELECT m.*, t.* FROM Member m INNER JOIN Team t
```

fetch join을 사용하여 연관 참조 필드를 조회하면 묵시적으로 inner join(내부조인)을 수행합니다. 외부 조인(outer)은 명시적으로만 수행됩니다.
따라서 묵시적 조인은 유지보수상에 피해를 줄 수 있기 때문에 되도록 명시적 조인을 사용하는것을 권장합니다.
