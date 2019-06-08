### Data Type Cast
#### dynamic_cast
- `dynamic_cast<T>(expr)` 
- <>사이에 변환하고자 하는 자료형의 이름을 두되, 객체의 포인터 또는 참조형이 와야하고, ()사이에는 변환의 대상이 와야한다.
- 형 변환이 적절치 않으면 컴파일 에러 발생
- 상속관계에 있는 두 클래스 사이에서 자식 클래스의 포인터 및 참조형 데이터를 부모 클래스의 포인터 및 참조형 데이터로 형 변환 가능
```cpp
class Car{...};
class Truck : public Car{...};

int main(void){
	Truck *p = new Truck(70,105);
	Car *p1 = dynamic_cast<Car*>(p);

	Car *p2 = new Truck(80, 30);
	Truck *p3 = dynamic_cast<Truck*>(p2); // error
}
```
- 부모 클래스가 Polymorphic클래스라면, dynamic_cast도 부모 클래스의 포인터 및 참조형 데이터를 자식 클래스의 포인터 및 참조형으로 형변환을 허용한다.
- 실행시간에 안전성을 검사하도록 컴파일러가 바이너리 코드를 생성하여 안정적인 형변환을 보장

#### static_cast
- `static_cast<T>(expr)`
- 자식 클래스의 포인터 및 참조형 데이터를 부모 클래스의 포인터 및 참조형 데이터 뿐만 아니라 부모 클래스의 포인터 및 참조형 데이터도 자식 클래스의 포인터 및 참조형 데이터로 형 변환 가능
- 보다 많은 형변환을 허용하므로, 신중한 사용이 필요
```cpp
class Car{…};
class Truck : public Car{…};

int main(void){
	Car *p = new Truck(70,105);
	Truck *p1 = static_cast<Truck*>(p);

	Car *p2 = new Car(80);
	Truck *p3 = static_cast<Truck*>(p2); // 컴파일 가능, 그러나 해서는 안됨
}
```
- 기본 자료형 데이터간의 형 변환에도 사용
```cpp
double result = static_cast(double)(20)/3;
```
- 컴파일러는 무조건 형변환 되도록 바이너리 코드를 생성하여 결과를 책임지지 못한다, 안전성 검사를 하지 않으므로 실행속도는 빠르다.

#### const_cast
- `const_cast<T>(expr)`
- 포인터와 참조자의 const성향을 제거하는 형 변환
- 함수의 인자전달시 const선언으로 인한 type 불일치가 발생할경우 사용
- 컴파일러 최적화를 제한하는 목적으로 선언되는  volatile의 성향을 제거하는데 사용가능

#### reinterpret_cast
- `reinterpret_cast<T>(expr)`
- 전혀 상관이 없는 자료형으로의 형 변환에 사용이 된다.
- 포인터를 대상으로 하는, 그리고 포인터와 관련이 있는 모든 유형의 형 변환을 허용
```cpp
Simple *sim = new Simple;
Test *test = reinterpret_cast<Test*>(sim);

int num=72;
int *ptr=&num;
int adr = reinterpret_cast<int>(ptr);
```

#### polymorphic 클래스
- 하나 이상의 가상 함수를 지니는 클래스
```cpp
class Simple{
	public:
		virtual void show(){…}
};
class Complex: public Simple{
	public:
		void show(){…}// 가상함수
}
int main(void){
	Simple * sim = new Simple;
	Complex * com = dynamic_cast<Complex*>(sim); // 잘못된 형변환이지만 에러대신 null을 반환한다.
}
```
