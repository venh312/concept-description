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
true 와 false는 뭘 의미하는 걸까? 게다가 앞으로 요구사항이 바뀌었을때 유연하게 대응할 수도 없다.

<br/>

한 걸음 물러서서 전체를 보자. 우리의 선택 조건을 다음처럼 결정할 수 있다. 사과의 어떤 속성에 기초해서 불리언값을 반환(예를 들어 사과가 녹색인가? 150그램 이상인가?)하는 방법이 있다. 참 또는 거짓을 반환하는 함수를 `프레디케이트`라고 한다. `선택 조건을 결정하는 인터페이스`를 정의하자.
```java
public interface ApplePredicate {
  boolean test(Apple apple);
}
```
<br/>
다음 예제처럼 다양한 선택 조건을 대표하는 여러 버전의 ApplePredicate를 정의할 수 있다.

```java
// 무거운 사과만 선택
public class AppleHeavyWeightPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return apple.getWeight() > 150;
  }
}
```

<br/>

```java
// 녹색 사과만 선택
public class AppleGreenColorPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return GREEN.equals(apple.getColor());
  }
}
```
ApplePredicate는 사과 선택 전략을 캡슐화했다. 조건에 따라 filter 메서드가 다르게 동작할 것이라고 예상할 수 있다. 이를 `전략 디자인 패턴(strategy design pattern)` 이라고 부른다. 

<br/>

### 전략 디자인 패턴(strategy design pattern)
각 알고리즘(전략이라 불리는)을 캡슐화하는 알고리즘 패밀리를 정의해둔 다음에 런타임에 알고리즘을 선택하는 기법이다. 위 예제에서는 **ApplePredicate가 알고리즘 패밀리**고 **AppleHeavyWeightPredicate와 AppleGreenColorPredicate가 전략**이다.

그런데 ApplePredicate는 어떻게 다양한 동작을 수행할 수 있을까? filterApples에서 ApplePredicate 객체를 받아 애플의 조건을 검사하도록 메서드를 수정해야 한다. 이렇게 **동작 파라미터화**, 즉 메서드가 다양한 동작(또는 전략)을 받아서 내부적으로 다양한 동작을 수행할 수 있다.

이제 filter 메서드가 ApplePredicate 객체를 인수로 받도록 고치자. 이렇게 하면 filterApples 메서드 내부에서 컬렉션을 반복하는 로직과 컬렉션의 각 요소에 적용할 동작(우리 예제에서는 프레디케이트)을 분리할수 있다는 점에서 소프트웨어 엔지니어링적으로 큰 이득을 얻는다.

### 2.2.1 네 번째 시도 : 추상적 조건으로 필터링
```java
public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
  List<Apple> result = new ArrayList<>();
  for (Apple apple : inventory) {
    if (p.test(apple)) {
      result.add(apple);
    }
  }
}
```
첫 번째 코드에 비해 더 유연한 코드를 얻었으며 동시에 가독성도 좋아졌을 뿐 아니라 사용하기도 쉬워졌다. 이제 필요한 대로 다양한 ApplePredicate를 만들어서 filterApples 메서드로 전달할 수 있다.

만약 150그램이 넘는 빨간 사과를 검색해달라고 부탁하면 ApplePredicate를 적절하게 구현하는 클래스만 만들만 된다.

```java
public class AppleRedAndHeavyPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return RED.eqauls(apple.getColor()) && apple.getWeight() > 150;
  }
}
```
```java
List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
```

하지만 filterApples 메서드의 새로운 동작을 정의하는 것이 test 메서드다. 안타깝게도 메서드는 객체만 인수로 받으므로 test 메서드를 ApplePredicate 객체로 감싸서 전달해야 한다. test 메서드를 구현하는 객체를 이용해서 불리언 표현식 등을 전달할 수 있으므로 이는 `코드를 전달`할 수 있는 것이나 다름없다.

다음 2.3절에서는 람다를 이용해서 여러개의 ApplePredicate 클래스를 정의하지 않고도 "red.eqauls(apple.getColor()) && apple.getWeight() > 150 같은 표현식을 filterApples 메서드로 전달하는 방법을 설명한다.

### 2.3 복잡한 과정 간소화
#### 예제 2-1 동작 파라미터화 : 프레디케이트로 사과 필터링
```java
// 무거운 사과 선택
public class AppleHeavyWeightPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return apple.getWeight() > 150;
  }
}

// 녹색 사과 선택
public class AppleGreenColorPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return GREEN.equals(apple.getColor());
  }
}

public class FilteringApples {
  public static void main(String...args) {
    List<Apple> inventory = Arrays.asList(new Apple(80, GREEN),
                                          new Apple(155, GREEN),
                                          new Apple(120, RED));
    // 결과: 155그램의 사과 한개를 포함한다.
    List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
    // 결과: 녹색 사과 두 개를 포함한다.
    List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
  }

  public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }
}
```
로직과 관련 없는 코드가 많이 추가 되었다. 자바는 클래스의 선언과 인스턴스화를 동시에 수행할 수 있도록 `익명 클래스(anonymous class)`라는 기법을 제공한다.
(익명클래스를 자바8에서 추가된 람다 표현식으로 더 가독성 있는 코드를 구현할 수 있다.)

### 2.3.1 익명 클래스
익명클래스는 자바의 지역 클래스와 비슷한 개념이다. 말 그대로 이름이 없는 클래스로 클래스 선언과 인스턴스화를 동시에 할 수 있다. 즉, 즉석에서 필요한 구현을 만들어서 사용할 수 있다.

### 2.3.2 다섯 번째 시도 : 익명 클래스 사용
다음은 익명 클래스를 이용해서 ApplePreicate를 구현하는 객체를 만드는 방법으로 필터링 예제를 다시 구현한 코드다.
```java
List<Apple> redApples = filterApple(inventory, new ApplePredicate() { // filterApples 메서드의 동작을 직접 파라미터화
  public boolean test(Apple apple) {
    return RED.eqauls(apple.getColor());
  }
});
```

익명 클래스로도 아직 부족한 점이 있다. 첫째, 아래 표현한 부분에서 알 수 있는것처럼 익명 클래스는 여전히 많은 공간을 차지한다.
```java
List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
  public boolean test(Apple a) {
    ...
  }
}
```

둘째, 많은 프로그래머가 익명 클래스의 사용에 익숙하지 않다. 예를 들어 퀴즈2-2는 많은 프로그래머를 곤경에 빠뜨리는 고전 자바 문제다.
```java
public class MeaningOfThis {
  public final int value = 4;
  public void doIt() {
    int value = 6;
    Runnable r = new Runnable() {
      public final int value = 5;
      public void run() {
        int value = 10;
        System.out.println(this.value);
      }
    }
  }
}
```
정답 : 5 (코드에서 this는 MeaningOfThis가 아니라 Runnable을 참조하므로..)

### 2.3.3 여섯 번째 시도 : 람다 표현식 사용
자바 8의 람다 표현식을 이용하여 위 예제 코드를 다음처럼 간단하게 재구현할 수 있다.
```java
List<Apple> result = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
```
이전 보다 훨씬 간단해지고 더 잘 설명하는 코드가 되었다.

### 2.3.4 일곱 번째 시도 : 리스트 형식으로 추상화
```java
public interface Predicate<T> {
  boolean test(T t);
}
```

```java
public static <T> List<T> filter(List<T> list, Predicate<T> p) { // 형식 파라미터 등장
  List<T> result = new ArrayList<>();
  for (T e : list) {
    if (p.test(e)) {
      result.add(e);
    }
  }
}
```

이제 바나나, 오렌지, 정수, 문자열 등의 리스트에 필터 메서드를 사용할 수 있다. 다음은 람다 표현식을 사용한 예제다.
```java
List<Apple> redApples = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));

List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
```
훨씬 멋진 코드다 되었다. 유연성과 간결함이라는 두 마리 토끼를 모두 잡을 수 있었다. 자바 8이 아니면 불가능한 일이다.


### 2.4 실전 예제
지금까지 동작 파라미터화가 변화하는 요구사항에 쉽게 적응하는 유용한 패턴임을 확인했다. 동작 파라미터화 패턴은 동작을 (한 조각의 코드로) 캡슐화한 다음에 메서드로 전달해서 메서드의 동작을 파라미터화 한다.

### 2.4.1 Comparator로 정렬하기
컬렉션 정렬은 반복되는 프로그래밍 작업이다. 예를 들어 처음에는 농부가 무게를 기준으로 목록에서 사과를 정렬하고 싶다고 말할 것이다. 하지만 마음을 바꿔 색을 기준으로 사과를 정렬하고 싶어질수 있다. 자바 8의 List에는 sort 메서드가 포함되어 있다. (물론 Collections.sort도 존재한다.) 다음과 같은 인터페이스를 갖는 `java.util.Comparator` 객체를 이용해서 sort의 동작을 파라미터화 할 수 있다.

```java
public interface Comparator<T> {
  int compare(T o1, T o2);
}
```

Comparator를 구현해서 sort 메서드의 동작을 다양화할 수 있다. 예를 들어 익명클래스를 이용해서 무게가 적은 순서로 목록에서 사과를 정렬할 수 있다.
```java
inventory.sort(new Comparator<Apple>() {
  public int compare(Apple a1, Apple a2) {
    return a1.getWeight().compareTo(a2.getWeight());
  }
}
```
농부의 요구사항이 바뀌면 새로운 요구사항에 맞는 Comparator를 만들어 sort 메서드에 전달할 수 있다.

#### 람다 표현식을 이용하면 더 간단하게 코드를 구현할 수 있다.
```java
inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
```









