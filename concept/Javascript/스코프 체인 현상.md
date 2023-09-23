JavaScript에서 스코프 체인(Scope Chain)은 변수 및 함수에 접근할 때 변수가 정의된 위치를 찾아가는 메커니즘입니다. 스코프 체인은 변수의 유효 범위(scope)를 결정하고, 변수의 검색을 수행합니다.

스코프 체인은 다음과 같은 방식으로 동작합니다:

1. 함수 스코프(Function Scope):
   - JavaScript에서 변수는 함수 내부에서 선언된 경우 해당 함수의 스코프 내에서만 접근할 수 있습니다.
   - 함수 내부에서 변수를 참조하면, JavaScript 엔진은 현재 스코프에서 변수를 찾습니다. 변수를 찾지 못하면 상위 스코프로 이동하여 검색을 계속합니다. 이 과정을 스코프 체인이라고 합니다.

2. 렉시컬 스코프(Lexical Scope):
   - JavaScript의 스코프 체인은 함수의 정의 위치에 기반합니다. 함수가 정의된 위치에 따라 스코프 체인이 형성됩니다.
   - 변수는 해당 함수가 정의된 스코프와 그 상위 스코프에서 찾을 수 있습니다. 즉, 함수가 선언된 위치에 따라 변수의 유효 범위가 결정됩니다.

아래는 스코프 체인의 동작을 보여주는 예시입니다:

```javascript
function outer() {
  var outerVariable = 'Outer';

  function inner() {
    var innerVariable = 'Inner';
    console.log(innerVariable);  // 'Inner'
    console.log(outerVariable);  // 'Outer'
  }

  inner();
}

outer();
```

위의 예시에서 `inner` 함수 내부에서 `innerVariable`과 `outerVariable`에 접근하고 있습니다. `inner` 함수 내부에서 변수를 검색할 때, 먼저 현재 스코프인 `inner` 함수의 스코프에서 변수를 찾습니다. `innerVariable`을 찾았으므로 해당 값을 출력합니다. 그리고 `outerVariable`은 `inner` 함수의 상위 스코프인 `outer` 함수의 스코프 체인을 통해 검색하여 출력합니다.

스코프 체인은 함수가 중첩된 경우에도 동일한 방식으로 작동합니다. 함수 스코프 내에 또 다른 함수가 있는 경우, 상위 함수의 스코프도 스코프 체인에 포함되어 변수에 접근할 수 있게 됩니다.

스코프 체인은 JavaScript의 중요한 개념 중 하나이며, 변수의 유효 범위를 이해하고 변수를 올바르게 사용하는 데 도움이 됩니다.
