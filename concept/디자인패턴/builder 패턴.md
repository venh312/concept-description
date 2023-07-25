빌더 패턴(Builder Pattern)은 객체 생성에 관련된 디자인 패턴 중 하나로, 복잡한 객체의 생성 과정을 단계별로 분리하여 구현하는 방법을 제공합니다. 주로 복합 객체(Composite Object)를 생성하는데 사용되며, 객체의 구성 요소가 서로 다를 수 있고, 생성 과정이 복잡하거나 다양한 표현 방법이 필요한 경우에 유용합니다.

빌더 패턴은 다음과 같은 주요 요소로 이루어집니다:

1. Director (Director):
   - 빌더 패턴의 사용을 주도하는 역할입니다.
   - 클라이언트가 생성할 객체의 종류를 결정하고, 빌더 객체를 통해 객체를 생성합니다.

2. 빌더 (Builder):
   - 객체의 각 부분을 생성하기 위한 추상 인터페이스를 정의합니다.
   - 객체의 각 부분 생성 메서드를 선언하고, 기본 구현을 제공합니다.

3. 구체적인 빌더 (Concrete Builder):
   - 빌더 인터페이스를 구현하여 객체의 각 부분을 생성하는 역할을 합니다.
   - 빌더 패턴은 구체적인 빌더가 여러 개일 수 있으며, 각각 다른 종류의 객체를 생성합니다.

4. 제품 (Product):
   - 생성할 객체의 표현 방법을 정의하는 클래스입니다.
   - 빌더 패턴을 사용하여 생성된 객체는 이 클래스를 통해 생성됩니다.

빌더 패턴의 사용 예시로는 다양한 종류의 문서 생성기를 들 수 있습니다. 각각의 문서 종류에 따라 다양한 부분(제목, 내용, 저자 등)으로 구성된 문서를 생성해야 할 때, 빌더 패턴을 사용하여 문서의 생성 과정을 단계별로 추상화하여 구현할 수 있습니다.

이점:
- 객체 생성 과정을 유연하게 조정할 수 있습니다.
- 생성 코드를 클라이언트 코드로부터 분리하여 가독성과 유지보수성을 향상시킵니다.
- 다양한 표현 방법으로 객체를 생성할 수 있습니다.

단점:
- 객체의 각 부분을 생성하는 별도의 빌더 클래스를 구현해야 하므로 클래스의 개수가 증가할 수 있습니다.
- 조금 더 많은 코드가 필요할 수 있습니다.

빌더 패턴은 객체 생성과정이 복잡하고 다양한 표현 방법이 필요한 경우에 유용하며, 객체 생성과정에 있어서 유연성과 확장성을 제공하는 디자인 패턴 중 하나입니다.

빌더 패턴을 자바 코드로 보여드리겠습니다. 같은 예제인 텍스트 문서를 생성하는 빌더 패턴을 자바로 구현해보겠습니다.

### 샘플 코드
1. 문서 객체 클래스 (Product):
```java
public class Document {
    private String title;
    private String author;
    private String content;

    public Document() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nContent: " + content;
    }
}
```

2. 빌더 인터페이스 (Builder):

```java
public interface Builder {
    void setTitle(String title);
    void setAuthor(String author);
    void setContent(String content);
    Document getDocument();
}
```

3. 구체적인 빌더 클래스 (Concrete Builder):

```java
public class TextDocumentBuilder implements Builder {
    private Document document;

    public TextDocumentBuilder() {
        this.document = new Document();
    }

    @Override
    public void setTitle(String title) {
        document.setTitle(title);
    }

    @Override
    public void setAuthor(String author) {
        document.setAuthor(author);
    }

    @Override
    public void setContent(String content) {
        document.setContent(content);
    }

    @Override
    public Document getDocument() {
        return document;
    }
}
```

4. Director 클래스 (Director):

```java
public class DocumentCreator {
    private Builder builder;

    public DocumentCreator(Builder builder) {
        this.builder = builder;
    }

    public void createDocument(String title, String author, String content) {
        builder.setTitle(title);
        builder.setAuthor(author);
        builder.setContent(content);
    }

    public Document getDocument() {
        return builder.getDocument();
    }
}
```

5. 클라이언트 코드:

```java
public class Main {
    public static void main(String[] args) {
        Builder builder = new TextDocumentBuilder();
        DocumentCreator documentCreator = new DocumentCreator(builder);

        documentCreator.createDocument("Sample Document", "John Doe", "This is a sample document.");
        Document textDocument = documentCreator.getDocument();
        System.out.println(textDocument);
    }
}
```
