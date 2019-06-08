#Pointer

### * 연산자

> type * ptr;

* type형 변수의 주소 값을 저장하는 포인터 변수 ptr 선언
* 포인터가 가르키는 메모리 공간에 접근할 때 사용하는 연산자

### & 연산자

> & ptr;

* 피연산자의 주소 값을 변환하는 연산자

예제1
```cpp
int main(void){
	int num = 10;
	int * pnum = &num;
	*pnum=20;
	printf("%d", *pnum);
}
```

예제2
```cpp
int main(void){
	int num1=100, num2=100;
	int * pnum;
	pnum=&num1;
	(*pnum)+=30;
	pnum=&num2;
	(*pnum)-=30;

	///num1:130, num2:70
	printf("num1:%d, num2:%d \n", num1, num2);
	return 0;
}
```

예제3
```cpp
void swap(int* ptr1, int*ptr2){
	int temp = *ptr1;
	*ptr1 = *ptr2;
	*ptr2 = temp;
}

int main(void){
	int num1=20, num=30;
	swap(&num1, &num2); ///num1=30, num2=20
	std::cout<<num1<<' '<<num2<<std::endl;
	
}
```
