## String
### 메소드들
- count(keyword, start, end) : keyword가 몇번이나 포함되어 있는지 알려준다. start, end를 지정하면 슬라이싱 효과가 난다.
- encode(encoding, errors) : 인코딩이 있는 바이너리로 변환 된다.
- endswith(postfix, start, end) : postfix로 문자열이 끝나면 true로 반환한다. start, end 지정하면 슬라이싱 효과가 난다.
- find(keyword, start,end) : 문자열 keyword가 나타나는 첫번째 인덱스를 반환 한다. 찾지 못하면 -1을 반환한다.
- index(keyword, start, end) : find와 같지만 keyword 찾지 못하는 경우 `ValueError` 예외가 발생한다.
- isalnum() : 알파벳과 숫자로 구성되어 있으면 true반환 한다. 
- isalpha() : 알파벳으로만 구성되 있으면 true 반환한다.
- islower() : 모든 알파벳이 소문자이면 true, 알파벳 이외의 다른 문자는 체크하지 않는다.
- isdecimal() : 10진수로 구성되어 있으면 true반환
- isnumeric() : 숫자로 구성되어 있으면 ture반환, 다른 문자가 섞여있으면 False 반환 
- join(sequence) : 순회 가능한 입력인 시퀸스형 변수를 지정된 문자열로 연결해서 반환 한다.
- lstrip(chars) : chars를 지정하지 않으면 공백 문자를 제거, 지정되있으면 chars의 모든 조합을 제거한다.
- translate, maketrans : maketrans는 translate에 쓰일 번역용 맵을 반환한다. maketrans의 인자가 하나인 경우에는 사전을 입력받고, 인자가 두개인 경우에는 길이가 같은 문자열을 입력 받고, 인자가 셋이면 같은 문자열 둘과 마지막 인자로 None으로 대체될 문자열을 입력 받는다. translate는 maketrans을 참고 삼아 문자열을 바꾼다.
- partition(separator) : 문자열을 separator로 나눈다. 앞부분, separaotr, 뒷부분으로 나눈다.
- replace(old, new, count) : old를 new로 대체한 결과를 반환, count를 인자로 준경우 count만큼의 횟수만 대체
- split(separator, maxsplit) : 문자열을 separator로 구분한다. separator가 생략된 경우에는 공백 문자를 구분자로 삼는다. maxsplit을 지정한 경우에는 앞에서 부터 maxsplit만큼 분리한다.
- strip(chars) : 문자열의 양쪽 끝을 잘라낸다. chars를 지정하지 않으면 공백문자를 제거하며, chars를 지정하면 chars의 모든 조합을 제거한다.

### 정규표현식
#### 문법
- `.`는 개행 문자를 제외한 문자 1자를 나타낸다.
- `^`는 문자의 시작을 나타낸다.
- `$`는 문자열의 종료를 나타낸다.
- `[]`는 문자의 집합을 나타낸다.
- `|`는 or을 나타낸다.
- `()`는 괄호 안의 정규식을 그룹으로 만든다.
- `*`는 문자가 0회 이상 반복됨
- `+`는 문자가 1회 이상 반복됨
- `?`는 문자가 0 혹은 1회 반복됨
- `{m}`는 문자가 m회 반복
- `{m, n}`는 문자가 m회부터 n회까지 반복되는 모든 경우
- `{m,}`는 문자가 m회부터 무한 반복되는 모든 경우를 나타냄
- `\w`는 유니코드인 경우 숫자, 밑줄을 포함하는 모든 언어의 표현 가능한 문자이다.
- `\d`는 유니코드인 경우 [0-9]를 포함하는 모든 숫자
- `\D`는 유니코드인 경우 숫자를 제외한 모든 문자
- `\s`는 유니코드인 경우 [, \t, \n, \r, \f, \v를 포함하는 공백 문자
- `b`는 단어의 시작과 끝의 빈 공백
- `\\`는 역슬래시 문자 자체를 의미
- `\[숫자]`는 지정된 숫자만큼 일치하는 문자열을 의미
- `\A`는 문자열의 시작
- `\Z`는 문자열의 끝

#### re 모듈함수
- re.search(pattern, string, flags) : string 전체에 대해 pattern이 존재하는지 검사해 MatchObject를 반환
- re.match(pattern, string, flags) : string이 시작하는 부분부터 pattern이 존재하는지 검사해 MatchObject를 반환
- re.split(pattern, string, maxsplit) : pattern을 구분자로 string을 분리해 리스트로 반환
- re.findall(pattern, string, flags) : string에서 pattern과 매치되는 모든 경우를 찾아 리스트로 반환
- re.finditer(pattern, string, flags) : string에서 pattern과 일치하는 결과에 대한 이터레이터 객체를 반환
- re.sub(pattern, repl, string, count) : string에서 pattern과 일치하는 부분에 대해 repl로 교체해서 결과 문자열을 반환
- re.escape(string) : 영문자, 숫자가 아닌 문자에 대해 백슬래스 문자를 추가한다.
- re.complie(pattern, flags) : pattern을 컴파일해서 정규식 객체를 반환, 이 객체는 동일한 패턴을 연속적으로 검색하는 경우 비효율적인 동작을 피하고자 사용
- 정규식에서 ".*?" 표현은 lzey방식으로 조건을 만족하는 한 가장 짧은 문자열을 선택한다는 의미
- 정규식에서 ".*" 표현은 greedy방식으로 조건은 만족하는 한 가장 긴 문자열을 선택한다는 의미

### Match 객체
- match(), search()의 수행 결과로 생성되며, 검색된 결과를 효율적으로 처리할수 있는 기능 제공
- group([group1, ..]) : 입력받은 인덱스에 해당하는 매칭된 문자열 결과의 부분집합을 반환한다. 인덱스가 0이거나 입력되지 않은 경우 전체 매칭 문자열을 반환
- groups() : 매칭된 결과를 튜플 형태로 반환
- start(group) : 매칭된 결과 문자열의 시작 인덱스를 반환, 인자로 부분 집합의 번호나 명시된 이름이 전달된 경우, 그에 해당하는 시작 인덱스를 반환
- end(group) : 매칭된 결과 문자열의 종료 인덱스를 반환
