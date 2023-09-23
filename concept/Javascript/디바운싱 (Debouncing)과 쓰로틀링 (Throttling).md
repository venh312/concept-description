자바스크립트에서 버튼을 빠른 시간에 연속적으로 누르지 못하게 하는 방법은 "디바운싱 (Debouncing)"과 "쓰로틀링 (Throttling)" 두 가지 기술을 사용하여 구현할 수 있습니다. 두 기술은 사용자 입력이나 이벤트를 제어하고 부드럽게 처리하기 위해 사용됩니다.

1. 디바운싱 (Debouncing):
디바운싱은 사용자의 연속된 입력에 반응하지 않고, 일정 시간 동안 마지막 입력만 반영하는 기술입니다. 이를 통해 빠른 연타로 인한 중복 처리를 방지할 수 있습니다.

```javascript
function debounce(func, delay) {
  let timerId;
  return function (...args) {
    clearTimeout(timerId);
    timerId = setTimeout(() => {
      func.apply(this, args);
    }, delay);
  };
}

// 사용 예시
function handleClick() {
  console.log("버튼 클릭 이벤트가 발생했습니다.");
}

const debouncedClickHandler = debounce(handleClick, 1000); // 1초 디바운스

// 버튼에 이벤트 핸들러 등록
document.getElementById("myButton").addEventListener("click", debouncedClickHandler);
```

위의 코드에서 `debounce` 함수는 디바운싱을 구현한 함수를 반환합니다. `debounce` 함수에 이벤트 핸들러 함수와 디바운싱을 적용할 시간 간격을 전달하여 새로운 디바운스된 함수를 생성합니다. 디바운스된 함수는 일정 시간(`delay`) 동안 마지막 입력만을 처리합니다.

2. 쓰로틀링 (Throttling):
쓰로틀링은 일정 시간 간격으로 일정 이벤트만 허용하는 기술입니다. 이를 통해 사용자의 빠른 연타를 제어하여 너무 많은 이벤트 발생을 방지할 수 있습니다.

```javascript
function throttle(func, delay) {
  let isThrottled = false;
  return function (...args) {
    if (!isThrottled) {
      func.apply(this, args);
      isThrottled = true;
      setTimeout(() => {
        isThrottled = false;
      }, delay);
    }
  };
}

// 사용 예시
function handleClick() {
  console.log("버튼 클릭 이벤트가 발생했습니다.");
}

const throttledClickHandler = throttle(handleClick, 1000); // 1초 쓰로틀링

// 버튼에 이벤트 핸들러 등록
document.getElementById("myButton").addEventListener("click", throttledClickHandler);
```

위의 코드에서 `throttle` 함수는 쓰로틀링을 구현한 함수를 반환합니다. `throttle` 함수에 이벤트 핸들러 함수와 쓰로틀링을 적용할 시간 간격을 전달하여 새로운 쓰로틀링 함수를 생성합니다. 쓰로틀링 함수는 일정 시간(`delay`) 간격으로만 이벤트를 처리합니다.

두 기술 중 어떤 것을 사용할지는 상황에 따라 다릅니다. 디바운싱은 버튼 클릭과 같은 이벤트가 자주 발생하지만, 마지막 이벤트만 반영해야 할 때 유용하고, 쓰로틀링은 이벤트 발생을 일정 시간 간격으로 제어해야 할 때 유용합니다.
