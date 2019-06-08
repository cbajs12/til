## String
### String class의 디자인
- 문자열을 인자로 전달받는 생성자의 정의 `string str = "hello";`
- 생성자, 소멸자, 복사 생성자 그리고 대입연산자 정의 (문자열의 길이가 일정하지 않으므로 동적할당사용)
- +연산자를 오버로딩하여 결합된 문자열로 객체를 반환
- +=연산자를 오버로딩하여 문자열의 덧붙인다.
- ==연산자를 오버로딩하여 내용비교를 한다.
- `<<`, `>>`를 오버로딩하여 콘솔 입출력이 가능하게 한다.

### find_first_not_of(token)
- string의 첫번째가 token과 같지 않은find first of() 과 같으나 주어진 문자가 아닌 첫 문자가 처음으로 나타나는 위치를 찾는다.
- 찾지 못하면 string::npos리턴

#### example
```cpp
for(int i=0; i<words.size(); ++i){
   if(words[i].find_first_not_of (' ') != words[i].npos)
      count++;
}
```

### reference
- https://wiki.kldp.org/HOWTO/html/C++Programming-HOWTO/standard-string.html

