## Math
- sum(iterable[, start]) : 이터러블 객체의 총 합계 반환, 시작값이 주어지면 그 값부터 진행
- max(iterable) : 최댓값
- min(iteralbe) : 최솟값
- abs(x) : 절댓값
- pow(x,y[, z]) :  x의 y 제곱 값을 반환한다. z가 입력된 경우 x의 y 제곱 결과를 z로 나눈 나머지를 반환
- round(x[, n]) : 인자 x의 반올린 결과 반환, 지정된 자리수까지 반올림 결과를 나타냄
- divmod(a,b) : a/b의 몫과 나머지를 튜플로 반환

### math module
- math.pi : pi
- math.e : e(자연 상수)
- math.ceil(x) : `N>=x`를 만족하는 가장 작은 정수 N 반환
- math.floor(x) : `N<=x`를 만족하는 가장 큰 정수 N 반환
- math.trunc(x) : x의 정수 부분만을 반환
- math.copysign(x,y) : y의 부호만 x에 복사해서 반환
- math.fabs(x) : x의 절대값 반환
- math.factorial(x) : x의 factorial 값을 반환
- math.fmod(x,y) : C의 fmod()를 호출
- math.modf(x) : 입력받은 x의 순수 소수부분과 정수부분으로 분리해 튜플로 반환, 분리된 두 부분 모두 부호가 할당
- math.sqrt(x) : x의 제곱근 결과 반환
- math.exp(x) : e의 x승의 결과 반환
- math.log(x[, base]) : 밑을 base로 하는 log X의 결과를 반환, base가 입력되지 않으면, 자연로그로 연산
- math.degrees(x) : 라디안으로 표현된 각도를 60분법으로 변환
- math.radians(x) : 60분법을 라디안으로 변환
- math.sin(x), math.cos(x), .... (삼각함수들)

### fraction module
- fractions.Fraction(분자, 분모) / (Franction 객체) / (문자열)
- from_float(flt) : float을 받아 Fraction 객체 생성
- from_decimal(dec) : 10진수 받아 Fraction 객체 생성
- fractions.gcd(a, b) : 두 정수 사이의 최대 공약수를 반환

### decimal module
- decimal.Decemal([value, context])
- Infinity : 무한대
- -Infinity : 음의 무한대
- NaN : Not a Number
- In() : Decimal의 자연로그 결과를 반환
- compare(other) : 두 Decimal객체를 비교해 그결과를 Decimal 객체로 반환 (같으면 0, 크면 1, 작으면 -1)
- Decimal 객체의 연산결과는 기본적으로 소수 28번째 자리까지 출력 (getcontext()로 객체 환경설정 확인 가능. setcontext() 혹은 직접 변수호출로 변경가능)

### random module
- random.seed([x]) : 임의 숫자 생성기 초기화 작업, 인자가 없으면, 시스템 시간 값 사용
- random.random() : `0.0<=F<1.0`사이의 임의 float숫자를 반환
- random.uniform(a,b) : 인자로 받은 두 값 사이의 임의의 float숫자를 반환
- random.gauss(m, sb) : 가우스 분포의 난수 반환
- random.randint(a,b) : `a<=N<=b` 임의 정수 N 반환
- random.choice(seq) : 시퀸스 객체의 임의의 아이템을 반환, 시퀸스가 비었을 경우 IndexError발생
- random.shuffle(x[, random]) : 입력 받은 시퀸스 객체를 섞는다. random인자는 0.0과 1.0사이의 임의 값을 반환하는 함수여야 한다.
- random.sample(population, k) : 두 번째 인자 k만큼의 아이템을 첫번째 인자인 시퀸스나 set 객체로부터 임의로 중복없이 추출한다. 원본 변경 없음
