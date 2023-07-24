Visitor 패턴은 소프트웨어 디자인 패턴 중 하나로, 객체 지향 프로그래밍에서 사용되는 패턴입니다. 이 패턴은 다양한 객체들의 구조와 동작을 분리하여 쉽게 확장 가능한 코드를 작성할 수 있도록 도와줍니다. 주로 객체 구조를 변경하지 않고 새로운 동작(메서드)을 추가해야 하는 상황에서 유용하게 사용됩니다.

Visitor 패턴은 다음과 같은 상황에서 적합하게 사용될 수 있습니다:
1. 여러 객체들의 구조에 동일한 작업을 수행해야 할 때.
2. 새로운 동작을 추가하면서 객체 구조를 수정하고 싶지 않을 때.
3. 객체들의 구조와 동작을 분리하여 코드의 유연성과 확장성을 강화하고 싶을 때.

Visitor 패턴은 크게 두 가지 주요 구성 요소로 이루어집니다

1. Visitor: 새로운 동작을 추가하는 클래스를 나타냅니다. Visitor 클래스는 일반적으로 각 객체 타입에 대해 visit 메서드를 오버로드하며, 해당 객체 타입에서 수행할 특정 작업을 정의합니다.

2. Element: Visitor에 의해 방문되는 클래스를 나타냅니다. Element 클래스는 accept 메서드를 가지고 있으며, 이 메서드는 자신을 방문하고자 하는 Visitor를 인자로 받습니다. Element 클래스는 해당 Visitor에게 자신을 방문하도록 요청합니다.

간단한 예시로, 다양한 형태의 도형을 Visitor 패턴으로 처리하는 방법을 살펴보겠습니다.

```java
// Element 인터페이스
interface Shape {
    void accept(ShapeVisitor visitor);
}

// ConcreteElement 클래스들
class Circle implements Shape {
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }
}

class Rectangle implements Shape {
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }
}

// Visitor 인터페이스
interface ShapeVisitor {
    void visitCircle(Circle circle);
    void visitRectangle(Rectangle rectangle);
}

// ConcreteVisitor 클래스
class AreaCalculator implements ShapeVisitor {
    private double totalArea = 0.0;

    @Override
    public void visitCircle(Circle circle) {
        double area = Math.PI * Math.pow(circle.getRadius(), 2);
        totalArea += area;
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        totalArea += area;
    }

    public double getTotalArea() {
        return totalArea;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(), new Rectangle()};

        AreaCalculator areaCalculator = new AreaCalculator();
        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }

        System.out.println("Total area: " + areaCalculator.getTotalArea());
    }
}
```

이 예제에서, Visitor 패턴을 사용하여 여러 형태의 도형(Circle, Rectangle)에 대해 새로운 작업(넓이 계산)을 추가했습니다. 각 도형은 accept 메서드를 통해 Visitor를 받고, Visitor는 visit 메서드를 통해 도형의 특정 작업(넓이 계산)을 수행합니다. 이렇게 하면 도형 클래스를 수정하지 않고도 새로운 작업을 추가할 수 있으며, 코드의 유연성과 확장성이 향상됩니다.
