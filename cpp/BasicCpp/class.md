###  Class

####  구조체
1) C에서 구조체를 선언하려면 키워드  struct나 typedef가 필요 그러나 C++에서는 필요없이 선언 가능
> C -> struct Car basicCar;
> C++ -> Car basicCar;

2) C++에서는 구조체 안에 함수를 삽입하는것이 가능
```cpp
struct Car{
   int carSpeed;
   
   void setSpeed(int speed){
      carSpeed = speed;
      return;
   }
}
int main(void){
   Car run99={100};
   run99.setSpeed(200);
}
```
3)구조체 안에  enum을 사용하는 것도 가능

4)  구조체 안의 함수를 밖으로 빼낼수 있다.
```cpp
struct Car{
   void show();
}
vodi Car::show(){
   ...
}
```
5) C++에서 구조체 와 클래스 차이
* 선언의 차이
> 클래스는 Car run99{100} 과 같이 선언 불가하다.

* 접근제어자 차이
> 구조체는 기본은 public, 클래스는 private로 설정된다.

#### 클래스 특징
기본 형태
```cpp
using namespace std;
class Hello{
   private:
      int hi;
   public:
      void show(){
         cout<<Hello<<endl;
      }
}
```
1. 클래스의 변수 선언시 초기화까지 하는것을 허용하지 않는다.
2. 두가지의 객체 생성 방법을 가진다.
> ClassName objName; //일반적인 방법
> ClassName *ptrObj = new ClassName; // 동적할당(힙 할당)

3. const 함수 
> const함수 내에서는 멤버변수에 저장된 값을 변경않겠다.
> const가 아닌 함수의 호출이 제한된다. 
```cpp
class Simple{
   private:
      int num;
   public:
      void initNUm(int n){
         num=n;
      }
      int getNum() const{
         return num; //const 함수
      }
      void showNum() const{
         //const함수를 call
         cout<<getNum()<<endl; 
      }
}
```
#### 생성자
1) 생성자를 이용하기 위해서는 SimpleClass sc1( ); 의 형태로 선언하면 안된다.

2) 멤버 이니셜라이저

   - 선언과 동시에 초기화가 이뤄지는 형태로 바이너리 코드 생성 (const 변수도 초기화 가능)
   - 생성자에서 대입연산을 통한 초기화를 진행하면, 초기화 과정을 단순화시켜서, 약간의 성능향상을 기대할수있다.
```cpp
class Rect{
   private:
      Point upLeft;
      Point lowRight;
   ...
}
Rect::Rect(const int &x1, const int &x2)
         :upLeft(x1), lowRight(x2) //멤버이니셜라이저
{
   ...
}
```
3) malloc을 이용한 클래스 호출은 생성자가 호출되지 않는다.

4) private 생성자 - 클래스 내부에서만 객체 생성을 허용하려는 목적으로 사용
```cpp
class AAA{
   private:
      int num;
      AAA(int n) : num(n){ }
}
```
#### 소멸자
1. ~ClassName( ) 형태를 가진다.
2. 대부분 생성자에 할당한 리소스 소멸에 사용
> new를 사용하였다면, delete를 사용해서 소멸시켜야함

#### 객체 및 this
1. 객체 배열
> Simple arr[10];
> Simple *ptrArr = new Simple[10];
* 배열의 선언과정에서는 호출할 생성자를 별도로 명시하지 못하기 때문에 배열이 생성되려면 빈생성자가 필요( Simple( ){ ... } )

2. 객체 포인터 배열
* 객체의 주소 값 저장이 가능한 포인터 변수로 이뤄진 배열

3. this - 객체 자신의 주소 값을 의미
4. Self-Reference - 객체 자신을 참조할 수 있는 참조자
```cpp
class SelfRef{
   private:
      int num;
   public:
      SelfRef(int n) : num(n){ }
      SelfRef& Adder(int n){
         num+=n;
         return *this;
      }
}

int main(void){
   SelfRef obj(3);
   SelfRef &ref = obj.Adder(2); //ref == obj
}
```
