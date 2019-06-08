### CPP Basic 2

#### C 복습
1. const - 상수화
```cpp
const int num=10; // 변수 num을 상수화
const int *ptr1=&val1; // 포인터 ptr1을 이용하여 val1의 값을 변경할수 없음
int* const ptr2=&val2; // 포인터 ptr2가 상수화됨
const int * const ptr3=&val3; // 포인터 ptr3가 상수화 되었으며, ptr3를 이용해서 val3의 값을 변경할수없음
```
2. 데이터, 스택, 힙, malloc&free
> 데이터 : 전역변수 저장되는 영역
> 스택 : 지역변수 및 매개변수 저장되는 영역
> 힙 : 동적 할당이 이루어지는 영역
> malloc & free : malloc으로 메모리 할당 free로 해제

3. call-by-value vs call-by-reference
```cpp
void swap(int num1, int num2){
	int temp=num1;
	num1=num2;
	num2=temp;
}	// call-by-value

void swap(int* ptr1, int* ptr2){
	int temp=*ptr1;
	*ptr1=*ptr2;
	*ptr2=temp;
}	// call-by-reference
```

#### 참조자(reference)
자신이 참조하는 변수를 대신할 수 있는 또 하나의 이름(별칭)
#####&연산자
1. 선언된 변수의 앞에 &연산자가 오면 주소 값의 반환을 명령
> int* ptr=&num1;

2. 새로 선언되는 변수의 이름앞에 등장하면, 참조자를 선언
> int &num2=num1;

#####특징
1. 참조자의 수에는 제한이 없고, 참조자를 대상으로 참조자 선언 가능
2. 참조자는 무조건 선언과 동시에 변수를 참조하여야 한다.

##### 참조자 이용한 call-by-reference
* call-by-reference
주소 값을 전달받아서, 함수 외부에 선언된 변수에 접근하는 형태의 함수호출
```cpp
void swap(int &ref1, int &ref2){
	int temp = ref1;
	ref1=ref2;
	ref2=temp;
}
```

##### 기타
1. 반환형이 참조형일때, 반환값을 무엇으로 저장하느냐에 따라서 결과에 차이가 있다.
```cpp
int& ref(int &ref){
	ref++;
	return ref;
}
int num2 = ref(num1);
int &num2 = ref(num1);
```
2. 상수화된 변수에 대한 참조자 선언
> const int num=20;
> const int &ref=num;

#### new & delete
메모리의 동적할당 및 해제

#### 기타
C라이브러리를 CPP에도 사용하기
* 헤더파일의 확장자인 .h를 생략하고 앞에 c를붙이면 된다.

> #include stdio.h -> cstudio
> #include stdlib.h -> cstdlib
