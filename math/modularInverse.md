## Modular multiplicative inverse
- 정수 a를 m으로 나눈 나머지 연산의 곱셈 역원은 `a x a^-1 = 1 (mod m)`을 만족하는 a^-1을 말한다.
- `a^-1 = x (mod m)`을 만족하는 x를 의미
- 역원은 a와 m이 서로소인 경우에만 존재

### Solution 1 : Fermat’s Little Theorem
- a이 소수 p로 나눠떨어지지 않을 경우 항상 `a^p−1%p=1`을 만족 (단, a와 p는 서로소)
- 양변에 a^−1을 곱하면, `(1∗a^−1)%p=(a^p−1∗a^−1)%p` -> `a^−1%p=a^p−2%p`
- 이를 이용하여, 나머지 연산에 대한 나눗셈은 아래와 같은 형태로 전개 가능
- `∴(a/b)%M=((a%M)∗(b^−1%M))=((a%M)∗(b^M−2%M))`

### Solution 2 : Extended Euclidean Algorithm
- gcd(a,b)를 구하는 Euclidean Algorithm의 확장된 개념으로, 모든 정수에 대해서 아래 식을 만족하는 수가 존재하고, 이 때 x와 y를 구할 수 있다는 것이 알고리즘의 핵심
- `gcd(a,b)=ax+by`

### references
- https://www.acmicpc.net/blog/view/29
- http://zetacode.com/math/2016/04/22/modular-multiplicative-inverse.html
