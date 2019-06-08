## Exception
### 구문에러
- 구문에러가 발생하는 경우에는 인터프리터가 지적하는 부분의 앞뒤를 모두 확인해야 한다.

### 예외
- exceptions 모듈에 예외들이 미리 정의되어 있다.
- 내장 이름공간으로 이미 포함되어 있어서 import할 필요없다.

### 예외 처리
```python
try:
   ...
except <exception>:
   ...
else:
   ... #  예외가 발생하지 않은 경우, 수행
finally:
   ...  
```

- 다중  except를 사용할때, 좁은 범위에서 넓은 범위로 확장해야 한다.

###   raise
- 프로그래머가 의도적으로 예외를 발생시켜야 하는 경우
```python
raise <exception>
raise <exception(data)> # 예외발생시 관련 데이터 전달
raise # 발생된 예외를 상위로 전달
```

### 사용자정의 예외
- 내장 예외인  Exception 클래스나 그 하위 클래스를 상속받아 구현해야 한다.
- 전달해야할 인자가 있는 경우 생성자에서 클래스 멤버 변수를 이용해 저장할 수 있다.
```python
class SomeError(Exception):
   def __init__(self, value):
      self.value = value
```

### assert
- 인자로 받은 조건식이 거짓인 경우 `AssertionError`가 발생

> assert <조건식>, <관련 데이터>

- 관련 데이터는 예외가 발생할 경우 `AssertionError`의 인자로 전달되며, 생략 가능
- 내부분수인 `__ debug__`가 True인 경우에만 assert구문이 활성화 된다.

```python
def foo(x):
   assert type(x)==int, "Input value must be Integer"
   return x*10
```
