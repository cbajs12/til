## Linear Algebra
- 파이썬을 이용한 선형대수

### 벡터
- 벡터끼리 더하거나 상수와 곱해지면 새로운 벡터를 생성
- 유한한 차원이 공간에 존재하는 점들
- 가장 간단하게 표현하는 방법은 list를 이용하는것

#### 연산
- 벡터 덧셈은 zip을 사용해서 두벡터를 묶은뒤, 두 배열의 각 성분끼리 더하는 list comprehension을 적용하면 된다.
```python
def vector_add(v,w):
	return [i + j for i, j in zip(v,w)]
```

- 벡터 뺄셈
```python
def vector_subtract(v, w):
	return [i - j for i, j in zip(v,w)]
```

- 모든 벡터의 각 성분들 끼리의 합
```python
def vector_sum(vec):
	return reduce(vector_sum, vec)
```
- 벡터에 스칼라 곱
```python
def scalar_multi(c, v):
	return [c*i for i in v]
```

- 벡터로 구성된 list가 주어졌을때 각 성분별 평균구하기
```python
def vector_mean(vec):
	n = len(vec)
    return scalar_multi(1/n, vector_sum(vec))
```

- 내적구하기(내적은 벡터v가 벡터w방향으로 얼마나 멀리 뻗어나가는지를 나타낸다.)
```python
def dot(v, w):
	return sum(i*j for i, j in zip(v,w))
```

- 각 성분의 제곱의 합
```python
def sum_of_squares(v):
	return dot(v,v)
```

- 벡터의 크기
```python
import math
def magnitude(v):
	return math.sqrt(sum_of_squares(v))
```

- 두 벡터간의 거리
```python
def squared_distance(v, w)
	return sum_of_squares(vertor_subtract(v,w))
def distance(v, w):
	return math.sqrt(squared_distance(v,w))
```
```python
def distance(v, w):
	return magnitude(vector_subtract(v,w))
```

### 행렬
- 2차원으로 구성된 숫자의 집합
- list의 list로 표현가능
- 각 벡터를 행렬의 행으로 나타냄으로써 여러 벡터로 구성된 데이터셋을 행렬로 표현할수 있다.
- K차원의 벡터를 N차원의 벡터로 변환해주는 nXk행렬로 표현할수 있다.
- 이진관계를 나타낼수있다.

#### 연산
- 행렬의 행과 열 가져오기
```python
def shape(A):
	rows = len(A)
    cols = len(A(0)) if A else 0
    return rows, cols
```

- 행렬 만들기
```python
def make_matrix(rows, cols, entry_fn):
	return [[entry_fn(i,j) for j in range(cols)] for i in range(rows)]
```

- 단위행렬 만들기
```python
def is_diagonal(i, j):
	return 1 if i==j else 0
identitiy_matrix = make_matrix(5, 5, is_diagonal)
```

### 참고
- 벡터를 list로 표현하는것은 끔찍한 성능을 보인다. Numpy를 사용하라.
