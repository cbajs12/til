### Function & Multiple Inheritance
#### 객체 함수
- 객체가 생성되면 멤버변수는 객체내에 존재하지만, 멤버함수는 메모리의 한공간에 별도로 위치하고, 정의된 클래스의 모든 객체가 이를 공유한다.

#### 가상함수
- 한개 이상의 가상함수를 포함하는 클래스는 컴파일러가 가상함수 테이블을 만들어 관리한다.
- 이 테이블은 실제 호출되어야할 함수의 위치정보를 담고 있다.
- 오버라이딩 된 가상함수의 주소정보는 자식 클래스의 가상함수 테이블에 포함되지 않는다. 그래서 오버라이딩된 가상함수를 호출하면, 무조건 가장 마지막에 오버라이딩을 한 자식 클래스의 멤버함수가 호출된다.
- 클래스에 가상함수가 포함되면, 테이블을 생성하고 호출을 정의하기 때문에 실행속도가 감소한다. (그러나 속도 차이는 미미하다.)

#### 다중상속
```cpp
class C : public A, public B{
   ...
   public:
      A::show();
      B::show();
}
```
- 다중 상속시 같은 부모 클래스들이 같이 이름의 멤버함수를 가질경우, 클래스 명을 명시해줘야한다.

#### 가상상속
```cpp
class Base{
   public:
      show();
}

class A : public Base{
   ...
}
class B : public Base{
   ...
}

class C : public A, public B{
   ...
   public:
      A::show();
      B::show();
}
```
- Base함수 `show()`에 대한 호출이 모호하기 때문에 가상 상속을 사용한다.
```cpp
class A : virtual public Base{...};
Class B : virtual public Base{...};
```
- 가상 상속을 하게 되면 `class C`는 `class Base`를 한번만 상속하게 되는 효과를 가진다.
