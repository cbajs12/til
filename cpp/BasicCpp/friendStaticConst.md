### Friend & Static & Const

#### Const 객체
const 객체의 데이터 변경을 허용하지 않겠다는 선언
- const 멤버함수의 호출만 허용
- 멤버변수에 저장된 값을 수정하지 않는 함수는 가급적 const로 선언
- const로 선언된 함수는  const로 선언되지않은 객체에서도 호출할수 있다.
- const 선언은 함수 오버로딩의 조건이 될수 있다.
```cpp
void Simple(){..}
void Simple() const {..}
```

#### Friend
- private 멤버의 접근을 허용하는 선언
- 클래스, 전역함수와 클래스의 멤버함수를 대상으로 선언가능

예시
- A 클래스가 B클래스를 friend선언하면, B는 A의 private를 직접 접근 가능
- 단, A클래스도 B에 접근하려면, B가 A에 대해 friend 선언해야한다.
```cpp
class A{
   private:
      friend class B;
      friend A C::add(int a);
      friend void show();
}
```
> 정보은닉을 무너트릴수 있으므로 주의해서 사용해야함

#### Static
기본
- 전역변수에 선언된 static -> 선언된 파일 내에서만 참조 허용
- 함수 내에 선언된 static -> 한번만 초기화 되고, 함수를 빠져나가도 소멸되지 않는다.

Static 멤버변수
- 클래스당 하나씩만 생성
- 객체를 여러개 생성해도 같은 static변수를 공유
- 미리 공간에 할당이 이루어지는 변수
> int Simple::staticNum = 0; // static 멤버변수 초기화 문법

- private로 설정되면 class 객체간만 접근가능, public이면 어디서든 접근 가능
> public으로 접근시에는 클래스 이름으로 접근이 좋다.
> ex ) Simple::staticNum
- const static 멤버변수는 `const static int num = 10;` 과 같이 선언과 동시에 초기화가 가능 

Static 멤버함수
- 함수내에서는 static으로 선언되지 않는 멤버변수 접근, 멤버함수의 호출도 불가능

#### mutable
- const 함수 내에서의 값의 변경을 예외적으로 허용한다.
- 가급적 사용을 낮춰야한다.
