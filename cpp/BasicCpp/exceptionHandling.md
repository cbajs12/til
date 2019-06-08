### Exception Handling
- try/catch
> - try블록에 여러개의 catch블록이 올수 있다.
 
- throw `throw expo;`
> - expn은 예외상황에 대한 정보를 담은 변수, 상수 그리고 객체 등 표현 가능한 모든 데이터가 될수 있다.
> - throw에 의해 던져진 예외 데이터는 try블록에 감지되어 catch블록에 의해 처리된다.
```cpp
try{
	if(exception)
		throw expo;
}catch(type eon){…}
```
- 예외가 발생한 지점 이후의 나머지 try영역은 건너뛰고 catch를 실행한다.
- throw절에 의해 던져진 예외 데이터의 자료형과 catch 매개변수의 자료형은 일치해야한다.
- 자료형이 일치하지 않으면 예외처리를 위하여, 상위 단계의 영역으로 넘어간다.
- try/catch블록이 없는 throw의 예외처리는 함수를 호출한 영역으로 넘어간다.
- 함수 내에서 함수를 호출한 영역으로 예외를 전달하면, 해당 함수는 더이상 실행되지 않고 종료된다.
- 예외 데이터가 main함수에 도달했는데 처리가 되지않는다면, terminate함수가 호출되면서 프로그램이 종료된다.
- 함수내에서 전달될 수 있는 예외의 종류(예외 데이터의 자료형)을 명시해주는것이 좋다
> - 다른 자료형의 예외 데이터가 전달될경우 terminate함수 호출로 프로그램이 종료된다.
```cpp
int ThrowFunc(int num) throw (int, char){…}// 예외발생으로 int, char형의 예외 데이터 전달될수 있다.
```

#### 예외 객체
- 예외발생을 알리는데 사용되는 객체
```cpp
class TempException{
	private:
		int money;
	public:
		TempException(int num) : money(num){}
		void ShowException(){ 
			cout<<"예외"<<money<<"발생"<<endl;
		} 
};

try{
	…
}catch(TempException &expn) {expn.ShowException();}
```

#### 상속 관계의 예외 클래스
```cpp
class AccountException{
	public:
		virtual void ShowException()=0;
};
class TempException : public AccountException{
	private:
		int money;
	public:
		TempException(int num) : money(num){}
		void ShowException(){ 
			cout<<"예외"<<money<<"발생"<<endl;
		} 
}

try{
	…
}catch(AccoutException &expn){
	expn.ShowException();
}
```

#### 기타
- 하나의 예외가 둘 이상의 catch블록에 의해서 처리
```cpp
void Divide(int num1, int num2){
	try{
		…
	}catch(int expo){
		throw; // 예외가 소멸되지 않고 다시 던져진다.
	}
}
int main(void){
	try{
		Divide(2,4);
	}catch(int expo){…}
}	
```
- `catch(…)`은 전달되는 모든 예외를 받아주겠다는 것
