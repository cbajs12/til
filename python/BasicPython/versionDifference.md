### Version Difference(2.x -> 3.x)

#### print - 함수 형태로 변경
- `print "hello" // 2.x`
- `print("hello") // 3.x`
- 3.x에서는 구분자(sep), 끝라인(end), 출력(file)지정 가능
```python
print("Hello", "you", sep="*", end="!", file=sys.stderr)
Hello*you!
```

#### long형이 int로 통일되었다.

#### int/int의 결과는 float으로 처리됨
- 2.x에서는 int/int는 int결과이다.

#### String, Unicode 체계가 바뀜
- 2.x는 일반 문자열이 인코딩이 있는 문자열이었고 유니코드가 따로 있었다.
- 3.x는 일반 문자열이 유니코드이고, 인코딩이 있는 문자열은 bytes로 표현