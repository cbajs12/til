## 3. array

```c
int alist[5] = {1, 2, 3, 4, 5};     //alist는 0번요소의 주소 (&alist[0])

int alist2[] = {1,2,3};

alist = alist2 // error: 왼쪽 피연산자가 변수가 아니라서 발생
```

- 배열이름은 주소상수이다. 상수는 정보이지 메모리가 아니라서 대입연산의 대상이 될수 없다.
- 문자열에서 '\n'은 0과 같다.

### 선택 정렬
- 각 항을 교환하는 방식으로 정렬을 한다.
- 두 항을 비교한 후, 작은 값이 저장된 요소의 값이 아니라 배열의 인덱스를 따로 저장한다. 그리고 안쪽 반복문이 끝나면 단 한 번만 교환한다.
```c
for (i = 0; i<4; ++i){
    for(j = i+1; j<5 ; ++j){
        if(alist[j] < alist[i]){
            temp = alist[i];
            alist[i] = alist[j];
            alist[j] = temp;
        }
    }
}
```

### 버블 정렬
- 서로 인접한 두 항을 계속해서 비교하는 방식
```c
for(i=4; i > 0 ; --i){
    for(j=0; j<i; ++j){
        if(alist[j] > alist[j+1]){
            temp = alist[j];
            alist[j] = alist[j+1];
            alist[j+1] = temp;
        }
    }
}
```

### lookup 배열
- 정보검색 기능을 제공할 목적으로 사용된 배열
- 인덱스가 정보를 찾는 역할을 하는 배열