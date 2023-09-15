## 코드 작성 예시
```java
public abstract class BusinessLogicTemplate {
    public void execute() {
        // 공통 로직
        
        // 파라미터에 따른 로직 호출
        doBusinessLogic();
    }
    
    protected abstract void doBusinessLogic();
}

public class ConcreteBusinessLogic extends BusinessLogicTemplate {
    private String param1;
    private String param2;
    
    public ConcreteBusinessLogic(String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }
    
    @Override
    protected void doBusinessLogic() {
        // 실제 비즈니스 로직
    }
}

// 다양한 파라미터 조합으로 호출
new ConcreteBusinessLogic("value1", "value2").execute();
new ConcreteBusinessLogic("value3", "value4").execute();
```
