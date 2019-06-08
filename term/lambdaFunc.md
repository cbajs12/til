## lambda function
- 람다식, 또는 람다 함수는 프로그래밍 언어에서 사용되는 개념으로 익명 함수(Anonymous functions)를 지칭하는 용어이다.
- 실무적으로는 코드의 간결함, 지연 연산을 통한 퍼포먼스 향상, 그리고 기존 이터레이션 관련 코드를 구현하는 데 있어 불필요한 부분들을 제거할 수 있다는 점에서 비교적 중요하게 다루어지고 있다.

### 장점
- 코드의 간결성: 효율적인 람다 함수의 사용을 통하여 불필요한 루프문의 삭제가 가능하며, 동일한 함수를 재활용할 수 있는 여지가 커진다.(Java의 경우 Predicate절을 이용하여 조건을 넘기는 방식으로 재활용성을 극대화 할 수 있다.)
- 필요한 정보만을 사용하는 방식을 통한 퍼포먼스 향상: 지연 연산을 지원하는 방식[2]을 통하여 효율적인 퍼포먼스를 기대할 수 있다. 이 경우 메모리 상의 효율성 및 불필요한 연산의 배제가 가능하다는 장점이 있다.

### 단점
- 어떤 방법으로 작성해도 모든 원소를 전부 순회하는 경우는 람다식이 조금 느릴 수 밖에 없다. (어떤 방법으로 만들어도 최종 출력되는 bytecode 나 어셈블리 코드는 단순 while(혹은 for) 문 보다 몇 단계를 더 거치게 된다.)
- 익명함수의 특성상 함수 외부의 캡처를 위해 캡처를 하는 시간제약 논리제약적인 요소도 고려해야한다.
- 람다식을 너무 남발하여 사용하게되면 오히려 코드를 이해하기 어려울 수 도 있다.

### 예제
```java
/// 람다식을 사용한 코드
IntStream.range(0, 10).forEach((int value) -> System.out.println(value));

/// 컴파일러의 추론을 통해 파라미터의 자료형을 생략한 코드.
IntStream.range(0, 10).forEach(value -> System.out.println(value));

/// 메서드 레퍼런스를 사용한 코드
IntStream.range(0, 10).forEach(System.out::println);
```

```cpp
/// C++11부터 지원
std::vector<int> v = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
std::for_each(std::begin(v), std::end(v), [&](const int &i) { std::cout << i; });

/// 컴파일러의 추론을 통해 파라미터의 자료형을 생략한 코드.
std::for_each(v.begin(), v.end(), [](auto n) { std::cout << n; });

/// 이 경우는 람다보다 "Range-based for loop" 가 가독성이 더 좋다.
for (auto n : v) std::cout << n;
```

```javascript
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9].forEach(console.log);
```

```python
# map
list(map(lambda x: print(x), [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]))

# list comprehension
[print(x) for x in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]]

# range()
[print(x) for x in range(0, 10)]

```

### reference
- https://namu.wiki/w/람다식

