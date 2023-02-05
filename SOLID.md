## SOLID
SOLID란 **로버트 마틴**이 2000년대 초반에 명명한 객체 지향 프로그래밍 및 설계의 다섯 가지 기본 원칙을 **마이클 페더스가 두문자어 기억술로 소개한 것**

## 단일 책임 원칙 SRP (Single Responsibility Principle)
모든 클래스는 각각 하나의 책임을 가져야 한다. 클래스의 역할과 책임을 너무 많이 주어서는 안된다.

### 왜?
그럴경우 

## 지키지 못한 예
```java
public class Bike {
	private final static int TWO_WHEEL = 2;
	private final static int THREE_WHEEL = 3;
	
	public void run(int wheel) {
		if (wheel == TWO_WHEEL) {
			// 두 바퀴로 가는 자전거
		} else if(wheel == THREE_WHEEL) {
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

public class TwoWheelBike extends Bike {
	@Override
	public void run(int wheel) {
		// 네 바퀴로 가는 자전거
	}
}
```




## 개방-폐쇄 원칙 OCP (Open-Closed Principle)
기능을 추가할 때에는 기존의 코드를 변경하지 않고도 추가할 수 있어야 한다.

## 지키지 못한 예
```java
class 강아지 {
  final static Boolean 수컷  = true;
  final static Boolean 암컷  = false;
  Boolean 성별;
  
  void 소변보다() {
      if (this.성별 == 수컷) {
        // 한쪽 다리를 들고 소변을 본다.
      } else {
        // 뒷다리 두 개로 앉은 자세로 소변을 본다.
      }
  }
}
```

## 좋은 예
```java
abstract class 강아지 {
    abstract void 소변보다();
}

class 수컷강아지 extends 강아지 {
    void 소변보다() {
        // 한쪽 다리를 들고 소변을 본다.
    }
}

class 암컷강아지 extends 강아지 {
    void 소변보다() {
        // 뒷다리 두 개로 앉은 자세로 소변을 본다.
    }
}
```

## 리스코프 치환 원칙 LSP (Liskov Substitution Principle)
자식클래스는 언제나 부모클래스를 대체할 수 있도록 상속 되어야 한다.


## 인터페이스 분리 원칙 ISP (Interface Segregation Principle)
하나의 일반적인 인터페이스보다는 여러개의 구체적인 인터페이스를 사용하는 좋다



## 의존 역전 원칙 DIP (Dependency Inversion Principle)
의존 관계를 맺을떄 좀 더 추상화 된것에 의존하는게 좋다.
