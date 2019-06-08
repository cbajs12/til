## closer function
- 클로저는 독립적인 (자유) 변수를 가리키는 함수이다. 또는, 클로저 안에 정의된 함수는 만들어진 환경을 `기억한다`.
- 예제
```javascript
function makeFunc() {
  var name = "모질라";
  function displayName() {
    alert(name);
  }
  return displayName;
}

var myFunc = makeFunc();
myFunc();
```

- 클로저는 두 개의 것(함수, 그 함수가 만들어진 환경)으로 이루어진 특별한 객체의 한 종류이다. 환경이라 함은 클로저가 생성될 때 그 범위 안에 있던 여러 지역 변수들로 이루어진다. 이 경우에 myFunc는 displayName 함수와 "모질라" 문자열을 포함하는 클로저이다.
- 결론적으로 오직 하나의 메소드를 가지고 있는 오브젝트를 사용하는 곳에 일반적으로 클로저를 사용할 수 있다.

```javascript
function makeSizer(size) {
  return function() {
    document.body.style.fontSize = size + 'px';
  };
}

var size12 = makeSizer(12);
var size14 = makeSizer(14);
var size16 = makeSizer(16);
```

### reference
- https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Closures

