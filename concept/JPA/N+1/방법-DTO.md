DTO(Data Transfer Object)를 사용하여 연관된 엔티티를 조회할 때는 JPQL(Querydsl을 포함)을 사용하여 해당 쿼리를 작성합니다. DTO는 엔티티와는 다르게 일부 속성만을 포함하도록 설계된 클래스입니다. 이를 활용하면 실제 필요한 데이터만을 조회하여 반환하므로, 불필요한 데이터 로딩을 피하고 성능을 개선할 수 있습니다.

아래는 DTO를 사용한 예시를 보여드리겠습니다. 가정하에 다음과 같은 엔티티와 DTO가 있다고 가정해보겠습니다:

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

public class ParentDTO {
    private Long id;
    private String name;
    private List<ChildDTO> childDTOs;
    
    public ParentDTO(Long id, String name, List<ChildDTO> childDTOs) {
        this.id = id;
        this.name = name;
        this.childDTOs = childDTOs;
    }
    
    // getters, setters...
}

public class ChildDTO {
    private Long id;
    private String name;

    public ChildDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // getters, setters...
}
```

이제 JPQL을 사용하여 DTO를 반환하는 쿼리를 작성할 수 있습니다:

```java
public List<ParentDTO> getParentDTOsWithChildren() {
    String jpql = "SELECT NEW com.example.ParentDTO(p.id, p.name, c.id, c.name) " +
                  "FROM ParentEntity p " +
                  "JOIN p.childEntities c";
    
    TypedQuery<ParentDTO> query = entityManager.createQuery(jpql, ParentDTO.class);
    return query.getResultList();
}
```

위의 코드는 `ParentDTO` 객체를 생성하는데 필요한 데이터를 조회하여 반환합니다. 이렇게 DTO를 사용하면 필요한 데이터만을 로딩하여 반환하기 때문에 불필요한 데이터를 로딩하는 경우에 비해 성능이 향상될 수 있습니다.
