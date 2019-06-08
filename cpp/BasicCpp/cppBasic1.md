#CPP Basic 1

### 입출력
#### 1. iostream
```cpp
#include <iostream>
```
* 입출력을 담당하는 헤더파일

#### 2. cout & cin
```cpp
std::cout<<"Hello world"<<std::endl;
```
* std::endl; 는 개행을 뜻함

```cpp
std::cin>>val;
```
* val변수에 키보드의 값을 입력 받는다, 자료형은 변수선언에 따라 변함

### 함수
#### 1. 오버로딩
같은 함수명이라도 매개변수의 갯수 혹은 자료형에 따라 다른 함수로 구분한다. (함수의 반환형은 제외)

#### 2. 매개변수
* 매개변수에 디폴트 값을 설정하는것이 가능
* 함수 선언 부분에만 표현하고 함수 정의 부분에는 선언하지않아도 인식한다.
```cpp
int add(int num1=1, int num2=2);
...
int add(int num1, int num2){
	return 1;
}
```
* 부분적 디폴트 값 설정 가능 (오른쪽부터 채워져 있어야 한다.)
```cpp
int myfunc(int num1, int num=2, int num=3) ///possible
int myfunc(char a='a', char b, char c) ///impossible
```

#### 3. 인라인 함수
* 매크로 함수와 비슷하지만 일반함수처럼 정의를 한 함수
* 매크로 함수의 장점인 '일반 함수 보다 빠른 실행속도의 이점'을 가진다.
* 매크로 함수는 자료형에 의존하지 않지만 인라인 함수는 자료형에 의존적이다.
```cpp
#define square(x) ((x)*(x))
vs
inline int square(int x){ return x*x;}
```
* 템플릿을 사용하면 자료형에 의존을 해결할수 있다.
```cpp
template <typename T>
inline T square(T x){
	return x*x;
}
```
### 이름공간 (name spaece)
* 특정 영역에 이름을 붙여주기 위한 문법적 요소
```cpp
namespace A{
	void func(void)
	{
		std::cout<<'helloworld'<<std::endl;
	}
}
namespace B{
	void func(void)
	{
		std::cout<<'helloworld wow'<<std::endl;
	}
}

int main(void){
	a::func();
	b::func();
	return 0;
}
```

> :: 는 범위 지정 연산자이며, 이름공간을 지정할때 사용하는 연산자이다.

* 동일한 이름공간에 정의된 함수를 호출할 때에는 이름공간을 명시할 필요가 없다.
* 이름공간은 다른 이름공간 안에 삽입될 수 있다.

#### using 문법
> using A::B;

* B를 이름공간 A에서 찾으라라는 선언

> using std::cin;
> using std::cout;
> using std::endl;
> 
> cout<<"hello"<<endl;
> cin>>val;

* std 또한 이름공간이므로 using을 통해 생략 가능

> using namespace std;

* 이름공간 std에 선언된 모든것에 대해 이름공간 지정의 생략을 명령 가능

#### 기타
별칭 부여
> namespace ABC=AAA::BBB::CCC;

예제
```cpp
using namespace std;

namespace AAA{
	namespace BBB{
		namespace CCC{
			int num;
		}
	}
}

int main(void){
	AAA::BBB::CCC::num=20;

	namespace ABC=AAA::BBB::CCC;
	cout<<ABC::num<<endl;
	return 0;
}
```

범위지정 연산자( :: )의 또 다른 기능
* 지역변수에 가려지는 전역변수를 접근 가능하다.

```cpp
int val=100;

int fun(void){
	int val=30;
		val+=3; // local val = 33
	::val+=7; // val = 107;
}
```

