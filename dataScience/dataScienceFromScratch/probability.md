## Probability
- 어떤 사건의 공간에서 특정 사건이 선택될때 발생하는 불확실성을 수치적으로 나타내는 것

### 종속, 독립사건
- 한 사건의 결과가 다른사건에 영향을 줄경우 `종속사건`이라한다. 그렇지 않다면 `독립사건`이다.
- 수학적으로 두 사건이 동시에 발생할 확률이 각각 사건이 발생할 확률의 곱과 같다면 두 사건은 독립이다.

> p(E, F) = p(E)p(F)

### 조건부 확률
- 두 사건이 반드시 독립 사건이라는 보장이 없고, 사건 F의 확률이 0이 아닌경우 사건 E가 발생할 확률

> p(E|F) = p(E,F)/p(F)   
> p(E,F) = p(E|F)/p(F) 

- 사건 E와 F가 독립사건이라면, `p(E|F) = p(E)` (사건 F가 발생해도 사건 E의 발생 여부에 대한 추가적인 정보를 알수 없다.)

### 베이즈의 정리
- 사건 F가 발생했다는 가정하에 사건 E가 발생할 확률이 필요하지만, 사건 E가 발생했다는 가정하에 사건 F가 발생할 확률만 주어졌을때 사용

> p(E|F) = p(E,F)/p(F) = p(F|E)p(E)/p(F)
> p(F) = p(F,E) + p(F,~E)
> p(E|F) = p(F|E)p(E) / (p(F|E)p(E) + p(F|~E)p(~E))

### 확률 변수
- 특정 확률분포와 연관되어 있는 변수
- `확률분포`는 각 변수의 값에 해당되는 확률 값을 계산해 준다.
- 모든 확률변수의 확률을 해당 확률변수의 값으로 가중 평균한 값을 `확률변수의 기댓값`이라 한다.
- 확률 변수 또한 보통 사건처럼 조건부 확률을 구할수 있다.

### 연속분포
- `균등분포`는 0과 1사이의 모든 값에 동등한 비중을 준 분포이다.
- 밀도함수를 특정 구간에서 적분한 값으로 확률을 나타내는것을 `확률밀도함수`라한다.
- 확률변수의 값이 특정 값보다 작거나 클 확률을 나타내는 것을 `누적분포함수`라 한다.

### 정규분포
- 평균(mu)과 표준편차(sigma)의 두 파라미터로 정의되는 종형 곡선 모양의 분포이다.
- 평균은 종의 중심이 어디인지를 나타내고, 표준편차는 종의 폭이 얼마나 넓은지를 나타낸다.

```python
# 정규분포의 밀도함수
def normal_pdf(x, mu=0, sigma=1):
   sqrt_two_pi = math.sqrt(2*math.pi)
   return (math.exp(-(x-mu)**2 / 2 sigma ** 2) / (sqrt_two_pi * sigma))
```

- 표준정규분포는 평균이 0이고 표준편차가 1인 정규분포를 의미한다. 
- 만약 Z가 표준정규분포의 확률변수를 나타낸다면, X도 평균이 mu이고 표준편차가 sigma인 정규분포로 표현할수 있다. `X=sigma*Z+mu`
- X가 평균이 mu이고 표준편차가 sigma인 정규분포의 확률변수를 나타낸다면, 선형변환을 통해 표준정규분포로 표현할수 있다. `Z = (X-mu)/sigma`

```python
# 정규분포의 누적분포함수 구현하기
def normal_cdf(x, mu=0, sigma=1):
   return (1+math.erf((x-mu) / math.sqrt(2) / sigma)) /2
```

- 가끔식 특정 확률을 갖는 확률변수의 값을 찾기 위해, normal_cdf의 역함수가 필요할수도 있다.

### 중심극한정리
- 동일한 분포에 대한 독립적인 확률변수의 평균을 나타내는 확률변수가 대략적으로 정규분포를 따른다는 정의
- `이항확률변수`는 단순히 n개의 독립적인 베르누의 확률변수를 더한것이고 각 베르누이 확률변수의 값은 p의 확률로 1, 1-p의 확률로 0이 된다.
- 베르누이 확률변수의 평균은 p이며, 표준편차는 `math.sqrt(p*1-p)`이다.