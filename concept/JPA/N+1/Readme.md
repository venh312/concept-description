## N+1 문제
연관 관계에서 발생하는 이슈로 연관 관계가 설정된 엔티티를 조회할 경우에 조회된 데이터 갯수(n) 만큼 조회 쿼리가 추가로 발생하는 현상

## 현상 재현, OneToMany 조인 시 발생

Fetch 모드를 EAGER(즉시 로딩)으로 한 경우 (EAGER는 디폴트이다.)
```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
```
```java
@Entity
public class Team {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
```
### 조회
```
teamRepository.findAll();
System.out.println("============== N+1 ===================");
```
### N + 1 문제 발생
```
Hibernate: select team0_.id as id1_0_, team0_.name as name2_0_ from team team0_
Hibernate: select users0_.team_id as team_id1_1_0_, users0_.users_id as users_id2_1_0_, user1_.id as id1_2_1_, user1_.first_name as first_na2_2_1_, user1_.last_name as last_nam3_2_1_, user1_.team_id as team_id4_2_1_ from team_users users0_ inner join user user1_ on users0_.users_id=user1_.id where users0_.team_id=?
Hibernate: select users0_.team_id as team_id1_1_0_, users0_.users_id as users_id2_1_0_, user1_.id as id1_2_1_, user1_.first_name as first_na2_2_1_, user1_.last_name as last_nam3_2_1_, user1_.team_id as team_id4_2_1_ from team_users users0_ inner join user user1_ on users0_.users_id=user1_.id where users0_.team_id=?
Hibernate: select users0_.team_id as team_id1_1_0_, users0_.users_id as users_id2_1_0_, user1_.id as id1_2_1_, user1_.first_name as first_na2_2_1_, user1_.last_name as last_nam3_2_1_, user1_.team_id as team_id4_2_1_ from team_users users0_ inner join user user1_ on users0_.users_id=user1_.id where users0_.team_id=?
============== N+1 ===================
```
### Fetch 모드를 LAZY(지연 로딩)으로 변경 후 조회
```
teamRepository.findAll();
```
이번에는 N + 1 문제가 발생하지 않는 것처럼 보인다.
```
Hibernate: select team0_.id as id1_0_, team0_.name as name2_0_ from team team0_
```
하지만 아래와 같이 users를 사용하려고 하면 N+1 문제 발생   
즉, 지연로딩은 최초에 연관관계 데이터를 사용하지 않으면 불러오지 않지만, 나중에라도 연관관계 데이터를 사용하려는 경우 N번의 쿼리 발생   

```java
List<Team> list = teamRepository.findAll();
System.out.println("============== N+1 ===================");
list.stream().forEach(team -> {
    team.getUsers().size();
});
```
```
Hibernate: select team0_.id as id1_0_, team0_.name as name2_0_ from team team0_
============== N+1 ===================
Hibernate: select users0_.team_id as team_id1_1_0_, users0_.users_id as users_id2_1_0_, user1_.id as id1_2_1_, user1_.first_name as first_na2_2_1_, user1_.last_name as last_nam3_2_1_, user1_.team_id as team_id4_2_1_ from team_users users0_ inner join user user1_ on users0_.users_id=user1_.id where users0_.team_id=?
Hibernate: select users0_.team_id as team_id1_1_0_, users0_.users_id as users_id2_1_0_, user1_.id as id1_2_1_, user1_.first_name as first_na2_2_1_, user1_.last_name as last_nam3_2_1_, user1_.team_id as team_id4_2_1_ from team_users users0_ inner join user user1_ on users0_.users_id=user1_.id where users0_.team_id=?
```

### N+1 문제를 해결하기 위해 다음과 같은 방법들이 사용됩니다:

- Eager 로딩 대신 Lazy 로딩 사용: 기본적으로 JPA는 @ManyToOne, @OneToOne 등의 관계에서 Eager 로딩을 사용합니다. 이 경우 N+1 문제가 발생할 가능성이 높습니다. 따라서 FetchType.LAZY 옵션을 사용하여 Lazy 로딩을 활용하면, 실제로 필요한 시점에 자식 엔티티를 로딩하게 할 수 있습니다.
- [패치 조인 (Fetch Join)](https://github.com/conf312/concept-description/blob/master/concept/JPA/N%2B1/%EB%B0%A9%EB%B2%95-FETCH%20JOIN.md): JPA의 페치 조인은 JPQL에서 JOIN FETCH 구문을 사용하여 연관된 엔티티를 함께 로딩하는 방법입니다. 이를 사용하면 부모 엔티티와 함께 자식 엔티티도 한 번의 쿼리로 로딩할 수 있습니다.
- [DTO 사용](https://github.com/conf312/concept-description/blob/master/concept/JPA/N%2B1/%EB%B0%A9%EB%B2%95-DTO.md): 필요한 데이터만 조회하여 DTO(Data Transfer Object)로 변환하는 방법도 있습니다. DTO는 엔티티의 일부 속성만 포함하므로 불필요한 데이터 조회를 줄일 수 있습니다.
- Batch 사이즈 설정: Hibernate에서는 @BatchSize 어노테이션을 사용하여 일괄 처리를 할 수 있습니다. 이를 통해 부모 엔티티의 리스트를 한 번에 조회하고, 지정한 크기만큼의 자식 엔티티를 로딩할 수 있습니다.

N+1 문제는 성능 저하의 주요 원인 중 하나이며, 효율적인 데이터 로딩 전략을 선택하여 해결하는 것이 중요합니다.
