## 동작 파라미터화(behavior-parameterization)
동작 파라미터화란 아직은 어떻게 실행할 것인지 결정하지 않은 코드 블록을 의미한다. 이 코드 블록은 나중에 프로그램에서 호출한다. 즉, 코드 블록의 실행은 나중으로 미뤄진다. 예를 들어 나중에 실행될 메서드의 인수로 코드 블록을 전달할 수 있다.

예를 들어 컬렉션을 처리할 때 다음과 같은 메서드를 구현한다고 가정하자.

- 리스트의 모든 요소에 대해서 `어떤 동작`을 수행할 수 있음
- 리스트 관련 작업을 끝낸 다음에 `어떤 다른 동작`을 수행할 수 있음
- 에러가 발생하면 `정해진 어떤 다른 동작`을 수행할 수 있음

동작 파라미터화를 추가하려면 쓸데없는 코드가 늘어난다. 자바 8은 람다 표현식으로 이 문제를 해결한다.

---

**하나의 예제를 선정한 다음 코드를 점차 개선하면서 유연한 코드를 만드는 사례를 보자.**

아래는 농장 재고 목록 애플리케이션의 리스트에서 녹색 사과만 필터링하는 기능을 추가한다고 가정한다.

### 2.2.1. 첫 번째 시도 : 녹색 사과 필터링
```java
enum color { RED, GREEN }
```
```java
public static List<Apple> filterGreenApples(List<Apple> inventory) {
  List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
  for (Apple apple : inventory) {
    if (GREEN.eqauls(apple.getColor()) { // 녹색 사과만 선택
      result.add(apple);
    }
  }
  return result;
}
```
그런데 갑자기 농부가 변심하여 녹색사과 말고 빨간 사과도 필터링하고 싶어졌다. 어떻게 고쳐야 할까? 크게 고민하지 않은 사람이라면 메서드를 복사해서 `fileterRedApples`라는 새로운 메서드를 만들고 if 문의 조건을 빨간 사과로 바꾸려 할 것이다.

하지만 나중에 다양한 색(노란색, 푸른색, 어두운 빨간색)으로 필터링하는 변화에는 적절하게 대응할 수 없다. 

**거의 비슷한 코드가 반복 존재한다면 그 코드를 추상화한다.**
<br/><br/>
### 2.1.2 두 번째 시도 : 색을 파라미터화
색을 파라미터화 할 수 있도록 메서드에 파라미터를 추가하면 변화하는 요구사항에 좀 더 유연하게 대응하는 코드를 만들 수 있다.
```java
public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
  List<Apple> result = new ArrayList<>();
  for (Apple apple : inventory) {
    if (apple.getColor().eqauls(color)) {
      result.add(apple)
    }
  }
  return result;
}
```

**이제 농부도 만족할 것이다. 다음 처럼 구현한 메서드를 호출할 수 있다.**
```java
List<Apple> greenApples = filterApplesByColor(inventory, GREEN);
List<Apple> redApples = filterApplesByColor(inventory, RED);
```

<br/>

하지만 다양한 요구사항을 듣다보면 색과 마찬가지로 앞으로 무게의 기준도 얼마든지 바뀔 수 있다는 사실을 눈치 챘을것이다. 그래서 다음 메서드에 무게 정보 파라미터도 추가했다.
```java
public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
  List<Apple> result = new ArrayList<>();
  for (Apple apple : inventory) {
    if (apple.getWeight() > weight) {
      result.add(apple)
    }
  }
  return result;
}
```
위 코드도 좋은 해결책이라 할 수 있다. 하지만 구현 코드를 자세히 보면 목록을 검색하고, 각 사과에 필터링 조건을 적용하는 부분의 코드가 색 필터링 코드와 대부분 `중복`된다.
이는 소프트웨어 공학의 `DRY(don't repeat yourself, 같은 것을 반복하지 말 것)` 원칙을 어기는 것이다.

탐색 과정을 고쳐서 성능을 개선하려면 한줄이 아니라 메서드 전체 구현을 고쳐야 한다. 즉 , 엔지니어링적으로 `비싼 대가`를 치러야 한다.

<br/>

### 2.1.3 세 번째 시도 : 가능한 모든 속성으로 필터링
다음에는 만류에도 불구하고 모든 속성을 메서드 파라미터로 추가한 모습이다.
```java
public static List<Apple> filterApple(List<Apple> inventory, Color color, int weight, boolean flag) {
  List<Apple> result = new ArrayList<>();
  for (Apple apple : inventory) {
    if ((flag && apple.getColor().eqauls(color)) || (!flag && apple.getWeight() > weight)) { // 색이나 무게를 선택하는 방법이 마음에 들지 않는다.
      result.add(apple)
    }
  }
  return result;
}
```

**다음처럼 위 메서드를 사용할 수 있다.(정말 마음에 들지 않는 코드다.)**
```java
List<Apple> greenApples = filterApples(inventory, GREEN, 0, true);
List<Apple> heavyApples = filterApples(inventory, null, 150, false);
```






