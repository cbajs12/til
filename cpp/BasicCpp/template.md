### Template

#### 함수 Template
- 함수 템플릿은 함수를 만들어 낸다, 함수의 기능은 결정되어 있지만, 자료형은 결정되어 있지 않아서 결정해야한다.
```cpp
template <typename T> // template <class T>와 같다
T Add(T num1, T num2){
   return num1+num2;
}
int main(void){
   Add<int>(15,20);
   Add<double>(2.2,3.2);
}
```
- 함수는 자료형당 하나씩 만들어진다.
- `Add(2.2, 3.2);`와 같은 형태로 쓸수도 있다. 전달되는 인자의 자료형을 참조하여 호출될 함수의 유형을 컴파일러가 결정하기 때문이다.
- 둘이상의 형에 대해서 텀플릿을 선언 ->  `template <typename T1, typename T2>`

#### 템플릿함수
- 함수 템플릿을 기반으로 컴파일러가 만들어 내는 함수들
```cpp
int Add<int>(int num1, int num2){
   return num1+num2;
}
double Add<double>(double num1, double num2){
   return num1+num2;
}
```
- 템플릿이 정의되어도 일반함수를 정의할수 있다,
- 일반함수가 정의되어있다면, 템플릿 함수를 호출하기 위해서 `<int>`, `<double>`의 표시를 통해서 템플릿 함수 호출을 명시 해야한다.

#### 함수 템플릿의 특수화
- 특정 자료형에 대해서 구분이 되는 다른 행동을 보이기 위해 사용
```cpp
template <typename T>
T Max(T a, T b){
   return a > b ? a : b;
}

template<>
char* Max(char* a, char* b){
   return strlen(a) > strlen(b) ? a : b;
}

template<>
const char* Max(const char* a, const char* b){
   return strcmp(a, b) > 0 : a : b;
}
```
- 컴파일러가 특정 함수의 자료형을 필요로 할때, 구현된것이 있다면, 별도의 함수를 만들지 말고 구현된것을 사용한다.

#### 클래스 템플릿
- 클래스 템플릿 기반의 객체생성에는 반드시 자료형 정보를 명시하여야 한다.
```cpp
template <typename T>
class Point{
   ...
}
int main(void){
   Point<int> pos1();
   Point<double> pos2();
   return 0;
}
```
- 클래스 템플릿도 멤버함수를 클래스 외부에 정의하는것이 가능
```cpp
template <typename T>
Point<T>::Point(T x, T y) : xpos(x), ypos(y){ }
```
- 포인터형 자료형
```cpp
BoundCheckArray<Point<int>*> arr(50);

typedef Point<int>* POINT_PTR;
BoundCheckArray<POINT_PTR> arr(50);
```
- 템플릿 클래스의 자료형을 대상으로도 템플릿이 아닌 일반함수의 정의가 가능하고, 클래스 템플릿 내에서 이러한 함수를 대상으로  friend선언도 가능하다.

#### 클래스 템플릿의 특수화
- 특정 자료형을 기반으로 생성된 객체에 대해, 구분이 되는 다른 행동을 적용하기 위해서 사용
- 템플릿을 구성하는 멤버함수의 일부 또는 전부를 다르게 행동하도록 정의 할수 있다.
```cpp
template <typename T>
class Simple{
   private:
      T data;
   public:
      Simple(T data2) : data(data2){}
}

template<>
class Simple<char*>{
   private:
      char* data;
   public:
      Simple(char* data2){
         data2 = new char[strlen(data)+1];
         strcpy(data2, data);
      }
}

int main(){
   Simple<int> sim1(170);
   Simple<char*> sim2("hihi");
   return 0;
}
```
- 부분 특수화
```cpp
template <typename T1, typename T2>
class Simple{...}

template<typename T1>
class Simple<T1, int>{...} // 부분 특수화
```
- 전체 특수화가 부분 특수화보다 우선시 된다.

#### 템플릿 인자
- 템플릿 매개변수의 선언에는 변수의 선언이 올수있다.
```cpp
template <typename T, int len>
class Simple{...}

Simple<int, 5> arr; // 가능
Simple<double, 6> darr; // 가능
Simple<int, 7> iarr; // Simple<int, 5>와는 다른 형이다.
arr = iarr // 에러
```
- 디폴트 값의 지정도 가능하다.
```cpp
template <typename T=int, int len=8>
class Simple{...}

Simple<> arr; // <>는 템플릿 클래스의 객체생성을 의미해서 반드시 있어야한다.
```

#### 템플릿과 static
- 템플릿의 static 지역변수는 템플릿 함수 별로 각각 존재하게 된다.
- 정의 부분에 T가 존재하면 T에 대한 설명을 위해서 `<typename T>`형태로 덧붙이면 되고, T가 없으면, <>형태로 선언한다.
- static 멤버 초기화
```cpp
template <typename T>
T Simple<T>::mem=0; // 모든 템플릿함수의 static 초기화

template<>
long Simple<long>::mem=5; // long형 템플릿 static 초기화
```
