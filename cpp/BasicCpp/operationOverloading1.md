### Operation Overloading 1
```cpp
class Point{
	private:
		int x, y;
	public:
		Point operator+(const Point &ref){
			return ref.x + x;
		}
}
```
- 멤버함수에 의한 연산자 오버로딩
> pos1.operate+(pos2); // pos1+pos2

- 전역함수에 의한 연산자 오버로딩
> operator+(pos1, pos2); // pos1+pos2

- 멤버함수와 전역함수 모두로 연산자 오버로딩을 한다면 멤버함수 연산자 오버로딩이 우선시 되어 호출된다.
- 연산자를 오버로딩한 함수도 const로 선언가능
- 연산자 오버로드 함수를 friend를 선언하면 클래스의 private영역에 접근할수 있다.
- `.` , `.*`, `::`, `?:`, `sizeof`, `typeid`, `static_cast`, `dynamic_cast`, `const_cast`, `reinterpret_cast`의 연산자는 오버로딩을 할수 없다.
- `=`, `()`, `[]`, `->`는 오버로딩 할수 있다.

#### 연산자 오버로딩 주의사항
- 본래의 연산자의 사용법과 다른 오버로딩은 삼가라
- 연산자의 우선순위와 결합성은 바뀌지 않는다.
- 매겨변수의 디폴트 값 설정이 불가

#### 단항 연산자의 오버로딩
```cpp
class Point{
	private:
		int x, y;
	public:
		Point& operator++(){
			x+=1;
			y+=1;
			return *this;
		}
		friend Point& operator—(Point &ref);
}
Point& operator—(Point &ref){
	ref.x-=1;
	ref.y-=1;
	return ref;
}
```
- 멤버함수에 의한 연산자 오버로딩
> pos.operator++( ); // ++pos;
> pos.operator++(int); //pos++; & int는 데이터형이 아니라 후위연산 구분목적용 기호이다.

- 전역함수에 의한 연산자 오버로딩
> operator++(pos); // ++pos;

- 반환형이 const로 선언된다면, const객체를 대상으로 참조자를 선언할 때에는 참조자도 const로 선언해야한다.

- 오버로딩은 자료형이 다른 두 피연산자를 대상으로 하는 연산에 예외를 둘수있다.

#### 교환법칙 문제
```cpp
cpy = pos * 3;
cpy = 3 * pos; // Error
```
- 멤버함수의 형태로 오버로딩하면 3.operator*(pos)가 되기때문이다.
- 전역함수의 형태로 오버로딩이 필요하다.
> Point operator*(int times, Point &ref){…}
