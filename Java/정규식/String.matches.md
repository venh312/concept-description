### A 또는 B인 문자 체크
```java
String name = "B";
String pattern = "[A|B]";
if (name.matches(pattern)) {
    System.out.println("A또는 B입니다.");
}
```

### A,B가 아닌 문자 체크
```java
String name = "KIM";
String pattern = "^(?!A$|B$).*$";
if (name.matches(pattern)) {
    System.out.println("A또는 B가 아닙니다.");
}
```

### 숫자 체크
```java
String name = "4561237597546453123";
String pattern = "\\d+";
if (name.matches(pattern)) {
    System.out.println("숫자입니다.");
}
```
