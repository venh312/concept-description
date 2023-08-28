패치 조인은 JPQL을 사용하여 연관된 엔티티를 한 번의 쿼리로 함께 로딩하는 방법입니다. 패치 조인을 사용하면 N+1 문제를 해결할 수 있습니다. 패치 조인은 `JOIN FETCH` 구문을 사용하여 작성됩니다. 아래는 패치 조인을 사용한 예시를 보여드리겠습니다.

가정하에 다음과 같은 엔티티가 있다고 가정해보겠습니다:

```java
@Entity
public class ParentEntity {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<ChildEntity> childEntities;
    
    // getters, setters, constructors...
}

@Entity
public class ChildEntity {
    @Id
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;
    
    // getters, setters, constructors...
}
```

패치 조인을 사용하여 연관된 엔티티를 함께 로딩하는 JPQL 쿼리는 다음과 같습니다:

```java
public List<ParentEntity> getParentEntitiesWithChildren() {
    String jpql = "SELECT p " +
                  "FROM ParentEntity p " +
                  "JOIN FETCH p.childEntities";
    
    TypedQuery<ParentEntity> query = entityManager.createQuery(jpql, ParentEntity.class);
    return query.getResultList();
}
```

위의 코드에서 `JOIN FETCH` 구문은 `ParentEntity`와 `childEntities` 사이의 연관관계를 패치 조인으로 설정하고 있습니다. 이렇게 하면 부모 엔티티와 함께 자식 엔티티도 한 번의 쿼리로 로딩됩니다.

패치 조인을 사용하여 연관된 엔티티를 함께 로딩하면 N+1 문제를 피하고, 성능을 향상시킬 수 있습니다. 하지만 패치 조인은 데이터베이스 쿼리의 결과에 영향을 주므로 조심스럽게 사용해야 합니다. 필요한 경우에만 패치 조인을 사용하여 불필요한 데이터 로딩을 방지할 수 있습니다.
