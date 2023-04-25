## SOLID
SOLID란 **로버트 마틴**이 2000년대 초반에 명명한 객체 지향 프로그래밍 및 설계의 다섯 가지 기본 원칙을 **마이클 페더스가 두문자어 기억술로 소개한 것**

## 단일 책임 원칙 SRP (Single Responsibility Principle)
모든 클래스는 각각 하나의 책임을 가져야 한다. 클래스의 역할과 책임을 너무 많이 주어서는 안된다.

### 어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다. - 로버트 C.마틴

### 왜?
그럴경우 클래스 하나에 결합도가 높아져 확장이 힘들어지고, 재사용이 힘들기 때문이다.

## 지키지 못한 예
```java
public class Bike {
	private final static int TWO_WHEEL = 2;
	private final static int THREE_WHEEL = 3;
	
	public void run(int wheel) {
		if (wheel == TWO_WHEEL) {
			// 두 바퀴로 가는 자전거
		} else if (wheel == THREE_WHEEL) {
			// 세 바퀴로 가는 자전거
		} else {
			// 바퀴가 없는 자전거
		}
	}
}
```

## 좋은 예
```java
abstract class Bike {
	public abstract void run(int wheel);
}

public class TwoWheelBike extends Bike {
	@Override
	public void run(int wheel) {
	// 두 바퀴로 가는 자전거
	}
}

public class ThreeWheelBike extends Bike {
	@Override
	public void run(int wheel) {
	// 네 바퀴로 가는 자전거
	}
}
```

## 개방-폐쇄 원칙 OCP (Open-Closed Principle)
기능을 추가할 때에는 기존의 코드를 변경하지 않고도 추가할 수 있어야 한다.

### 소프트웨어 엔티티는 확장에 대해서는 열려있어야 하지만 변경에 대해서는 닫혀있어야 한다. - 로버트 C.마틴

## 지키지 못한 예
```java
public class 마티즈 {
	void 창문조작() {
	// 수동 창문조작
	}
    
	void 기어조작() {
	// 수동 기어조작
	}
}
```

```java
public class 쏘나타 {
	void 창문조작() {
	// 자동 창문조작
	}
    
	void 기어조작() {
	// 자동 기어조작
	}
}
```

## 좋은 예
```java
public interface 자동차 {
	void 창문조작();
	void 기어조작();
}
```

```java
public class 마티즈 implements 자동차 {
	void 창문조작() {
	// 수동 창문조작
	}
    
	void 기어조작() {
	// 수동 기어조작	
	}
}
```

```java
public class 쏘나타 implements 자동차 {
	void 창문조작() {
	// 자동 창문조작
	}
    
	void 기어조작() {
	// 자동 기어조작
	}
}
```

## 리스코프 치환 원칙 LSP (Liskov Substitution Principle)
자식클래스는 언제나 부모클래스를 대체할 수 있도록 상속 되어야 한다.

### 리스코프 치환 원칙이란 하위 클래스의 인스턴스는 상위형 객체 참조 변수에 대입해 상위 클래스의 인스턴스 역할을 하는데 문제가 없어야 한다는 것이다.

## 인터페이스 분리 원칙 ISP (Interface Segregation Principle)
하나의 일반적인 인터페이스보다는 여러개의 구체적인 인터페이스를 사용하는 좋다. 작을 수록 좋다.

### 인터페이스 분리 원칙은 사용자의 상황과 관련이 있는 메소드만 제공하는 제공하라는 뜻이다.
- 결론적으로는 단일 책임 원칙(SRP)과 인터페이스 분할 원칙(ISP)은 같은 문제에 대한 두 가지 다른 해결책이라고 볼 수 있다.
- 하지만 특별한 경우가 아니라면 단일 책임 원칙을 적용하는 것이 더 좋은 해결책이라고 할 수 있다.

```java
public interface 요리 {
	void cook();
}
```

```java
public interface 운동 {
	void exercise();
}
```

```java
// 요리와 운동을 하는 철수 구현
public class 철수 implements 요리, 운동 {
	@Override
	public void cook() {
	// ...
	}
    
	@Override
	public void exercise() {
	// ...
	}
}
```

```java
// 요리를 하는 영희 구현
public class 영희 implements 요리 {
	@Override
	public void cook() {
	// ...
	}
}
```

```java
public class Main {
	public static void main(String[] args) {
		// 운동하는 철수가 필요할 때
		운동 cs = new 철수();
		cs.exercise();
		
		// 요리하는 영희가 필요할 때
		요리 yh = new 영희();
		yh.cook();
	}
}
```

## 의존 역전 원칙 DIP (Dependency Inversion Principle)
의존 관계를 맺을떄 좀 더 하위개념 보다는 상위 개념인 추상화 된것에 의존하는게 좋다.




