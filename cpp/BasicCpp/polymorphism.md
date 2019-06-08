### Inheritance & Polymorphism

#### 객체 포인터
- 객체의 주소값을 저장하는 포인터 변수
- 포인터 변수는 현재의 객체 뿐만 아니라 자식 클래스의 객체도 가르킬수있다. 즉, 직접 혹인 간접으로 상속하는 모든 객체를 가르킬수 있다.
```cpp
class Student : public Person{
   ...
}
class Worker : public Student{
   ...
}

Person *ptr = new Student();
Person *ptr = new Worker();
```
> is-a 관계로 설명가능 -> 노동자는 학생이다. 노동자는 사람이다. 학생은 사람이다.

- 포인터 형에 해당하는 클래스에 정의된 멤버에만 접근이 가능하다.
```cpp
class First{
   void first(){}
   void show(){}
}
class Second: public First{
   void second(){}
   void show(){}
}
class Third: public Second{
   void third(){}
   void show(){}
}

Third *tptr = new Third();
Second *sptr=tptr;
First *fptr=sptr;

tptr->first(); //ok
tptr->second(); //ok
tptr->third(); //ok

sptr->first(); //ok
sptr->second(); //ok
sptr->third(); //fail

fptr->first(); //ok
fptr->second(); //fail
fptr->third(); //fail
```
> Fisrt *fptr = new Second();
> Second *sptr=fptr; 일때,  컴파일러는 fptr이 가르키는게 Second객체라는 사실을 기억하지 않고 포인터형만 보고 판단을 하여 에러를 일으킨다.
``` cpp
fptr->show();   // First class
sptr->show();   // Second class
tptr->show();   // Third class
```
>-  fptr의 경우 : fptr이 First이니 First의 함수를 무조건 호출
> - sptr의 경우 : sptr이 Second이고 first함수와 second가 오버라이딩이라 second함수 호출
> - tptr의 경우 :  tptr이 Third이고 first, second, third가 오버라이딩 가지므로 third호출


#### 오버라이딩
- 부모 클래스의 함수를 오버라이딩 하게 되면, 오버라이딩을 한 자식클래스의 함수에 가려지게 된다.
- 클래스의 이름을 통하여 부모클래스의 오버라이딩된 함수를 호출할수 있다.
> Student::work();
> worker.Student::work();

- 오버라이딩시 주의점 : 자식 클래스에서 오버라이딩한 클래스 함수를 호출 했을경우, 그 안에서 호출하는 변수나 함수는 자식 클래스에 존재해야한다.

#### 가상함수
 - virtual 키워드로 선언
 - 이 함수를 오버라이딩 하는 함수도 가상함수가 된다.
 - 가상함수를 선언하면 포인터 자료형 기반이 아닌, 포인터 변수가 실제로 가리키는 객체 참조하여 호출한다.
``` cpp
fptr->show();   // Third class
sptr->show();   // Third class
tptr->show();   // Third class
```
- 순수 가상함수랑 함수의 몸체가 정의되지 않은 함수, = 0 의 표시는 명시적으로 몸체를 정의하지 않았음을 컴파일러에 알리는 것
> ` virtual int getPay() const = 0`

- 하나 이상의 멤버함수를 순수 가상함수로 선언한 클래스를 추상 클래스라고 한다.
- 소멸자에도 virtual 선언이 올수 있다. 소멸시에 포인터 변수의 자료형에 상관없이 모든 소멸자가 호출되야하므로 가상 소멸자를 사용

#### 참조자의 참조
- 참조자의 특성은 객체 포인터의 특징을 가지고 있다. 또한 가상함수의 개념도 똑같이 적용된다.
```cpp
   Third obj;
   Second &sref=obj;
   First &fref=sref;
```
