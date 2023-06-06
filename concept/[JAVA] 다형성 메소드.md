자바에서 대표적인 다형성 메소드 중 하나는 오버라이딩(Overriding)입니다. 오버라이딩은 상위 클래스에 정의된 메소드를 하위 클래스에서 동일한 시그니처(메소드 이름, 매개변수 타입 및 개수)를 가지고 재정의하는 것을 말합니다. 이를 통해 하위 클래스에서 상위 클래스의 메소드를 재정의하여 다양한 동작을 수행할 수 있습니다.

다른 대표적인 다형성 메소드로는 오버로딩(Overloading)이 있습니다. 오버로딩은 같은 이름을 가진 메소드를 여러 개 정의하고, 매개변수의 타입, 개수, 순서를 다르게하여 다양한 매개변수 조합에 대해 동작할 수 있도록 하는 것입니다. 이를 통해 메소드의 사용자는 동일한 이름의 메소드를 호출하더라도 다양한 매개변수로 메소드를 사용할 수 있습니다.

아래는 간단한 예시 코드입니다.

```java
class Animal {
    public void makeSound() {
        System.out.println("동물이 소리를 낸다.");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("멍멍!");
    }
    
    public void makeSound(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("멍멍!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.makeSound(); // "동물이 소리를 낸다."
        
        Dog dog = new Dog();
        dog.makeSound(); // "멍멍!"
        dog.makeSound(3); // "멍멍! 멍멍! 멍멍!"
        
        Animal anotherDog = new Dog();
        anotherDog.makeSound(); // "멍멍!" (다형성)
    }
}
```

위 예시에서 `Animal` 클래스와 `Dog` 클래스는 오버라이딩과 오버로딩을 보여줍니다. `Animal` 클래스의 `makeSound()` 메소드를 `Dog` 클래스에서 재정의하여 다른 동작을 수행합니다. 또한 `Dog` 클래스에는 `makeSound(int count)`라는 오버로딩된 메소드가 추가되어 있습니다. 이렇게 오버로딩된 메소드는 매개변수의 타입과 개수에 따라 다른 동작을 수행합니다.

마지막으로, `Animal anotherDog = new Dog();`라는 코드는 다형성을 보여줍니다. `anotherDog` 변수는 `Animal` 타입으로 선언되었지만, 실제로는 `Dog` 클래스의 인스턴스를 참조하고 있습니다. 이 경우, 컴파일 타임에는 `Animal` 클래스의 `makeSound()` 메소드가 호출될 것으로 예상되지만, 런타임에는 실제로 `Dog` 클래스의 `makeSound()` 메소드가 호출되어 다형성이 발생합니다.
