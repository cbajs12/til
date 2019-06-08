### Copy Constructor

####특징
1. 객체간 복사시에 작동하는 생성자
> Simple sim2 = sim1; == Simple sim2(sim1);

2. 객체를 인자로 받을 수있는 생성자를 호출함으로 객체 복사를 완료
```cpp
Simple(Simple &copy){
	...
}
```
3. explicit 키워드를 통하여 묵시적 복사 생성자를 호출 안하도록 할수있다.
```cpp
explict Simple(const Simple &copy){
	...
}
```
4. 복사생성자 매개변수에 참조형 & 을 써주어야 한다. 그렇지 않으면 복사 생성자의 호출은 무한루프에 빠진다.

#### 깊은복사 & 얕은복사
1. 디폴트 복사 생성자는 얕은복사를 사용한다. 즉, 복사자와 피복사자가 같은 객체 메모리를 가르키고있다.
2. 깊은복사 예제
```cpp
Person(const Person &copy) : age(copy.age){
	name = new char[strlen(copy.name)+1];
	strcpy(name, copy.name);
}
```

#### 복사 생성자의 호출
* 함수가 값을 반환하면, 별도의 메모리 공간이 할당되고, 이 공간에 반환 값이 저장된다. 
```cpp
Simple simpleObj(Simple obj){
	...
	return obj;
}
int main(void){
	Simple obj;
	simpleObj(obj); //obj가 리턴하는 결과로 복사생성자를 초기화
}
```

* 함수의 반환이 객체이면, 임시객체가 생성되고 이것이 전달된다. 임시객체는 참조자에 의해 참조되면 소멸되지 않는다.
