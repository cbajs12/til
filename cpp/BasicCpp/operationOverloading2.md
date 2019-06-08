### Operation Overloading 2

#### 대입연산자 오버로딩
- 대입연산자를 정의하지않으면 디폴트 대입 연산자 삽입
- 디폴트 대입 연산자는 얕은 복사를 한다.
- 연산자내에 동적할당이나 깊은 복사 필요하면 직접 정의 해야한다.
```cpp
Person& operator=(const Person& ref){
   delete []name;
   int len=strlen(ref.name)+1;
   name = new char[len];
   strcpy(name, ref.name);
   age=ref.age;
   return *this;
}
```
- 부모 클래스에 대입연산자가 정의 되있고, 자식 클래스의 대입 연산자에 정의 되어있지 않다면, 부모클래스의 대입연산자가 호출되지 않는다.
- 상속시 부모 클래스 또한 대입 연산자를 통한 복사를 하기 위해서는 명시적인 부모 클래스 대입 연산자를 호출하여야 한다.

#### 배열 인덱스 연산자 오버로딩
```cpp
arrObject.operator[](size);
```
- c, cpp 기본배열은 경계검사 하지 않으므로, 이것을 구현할수 있다.
```cpp
clas BoundCheck{
   private:
      int * arr;
      int arrlen;
      BoundCheck(const BoundCheck& arr){ }
      BoundChcek& operator=(const BoundCheck& arr){}
}
```
- 배열접근의 안정성을 위해, 빈상태의 복사생성자와 대입 연산자를 private멤버로 둠으로써 복사와 대입을 원천적으로 막는것도 좋은 선택
- 매개변수형이 const일 경우 const 함수 오버로딩으로 선언해야 컴파일 에러를 피할수있다.
- `arr[0]=new Point(3);`과 같이 객체의 주소갑을 저장할 경우, 깊은복사/얕은복사의 문제를 신경쓰지 않아도 된다.

#### new & delete 오버로딩
- new는 기본적으로 메모리 할당, 생성자 호출, 할당하고자하는 자료형에 맞게 반환된 주소 값의 형 변환을 한다.
- new의 오버로딩은 메모리 공간의 할당만 할수있다. ` void * operator new(size_t size){...}` (size_t => typedef unsigned int size_t;)
- delete의 오버로딩은 메모리 공간의 소멸을 책임진다. `void operator delete(void* adr){ delete []adr;}`
- new와 delete함수는 멤버함수로 선언해도 static으로 간주한다.

#### 포인터 연산자 오버로딩
- `->`, `*` 단항연산자의 형태로 오버로딩 된다.
```cpp
Number* operator->(){
   return this;
}
Number& operator*(){
   return *this;
}
```
#### 스마트포인터
- 스마트 포인터는 포인터의 역할을 하는 객체이다.
```cpp
class SmartPtr{
   private:
      Point* posptr;
   public:
      SmartPtr(Point* ptr): posptr(ptr){}
      Point& operator*() const{
         return *posptr;
      }
      Point* operator->() const{
         return posptr;
      }
      ~SmartPtr(){
         delete posptr;
      }
}
```

#### ()연산자 오버로딩
- 객체를 함수처럼 사용할수 있게 한다.
- Functor라고도 한다.
```cpp
class Adder{
   public:
      int operator()(const int& n1, const int& n2){
         return n1+n2;
      }
      Point operator()(const Point& pos1, const Point& pos2){
         return pos1+pos2;
      }
}
```
- 함수 또는 객체의 동작방식에 유연함을 제공할 떄 주로 사용
```cpp
class SortRule{
   public:
      virtual bool operator()(int num1, int num2) const = 0;
}
class AsendingSort : public SortRule{ // 오름차순
   public:
      bool operator()(int num1, int num2) const{
         if(num1>num2)
            return true;
         else
            return false;
      }
}
```

#### 형변환 연산자 오버로딩
- cpp는 동일 자료형의 객체간 대입연산이 가능하다.
```cpp
Number num1(100);
Number num2(0);
num2=num1;
```
- A형 객체가 와야 할 위치에 B형 데이터 또는 객체가 왔을 경우, B형 데이터를 인자로 전달받는 A형 클래스의 생성자 호출을 통해서 A형 임시객체를 생성
```cpp
class Number{
   private:
      int num;
   public:
      Number(int n=0) : num(n)
      Number& operator=(const Number& ref){
         num=ref.num;
         return *this;
      }
}
int main(void){}
   Number num;
   num=30;
}
```
