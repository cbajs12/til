## memoization
- 컴퓨터 프로그램이 동일한 계산을 반복해야 할 때, 이전에 계산한 값을 메모리에 저장함으로써 동일한 계산의 반복 수행을 제거하여 프로그램 실행 속도를 빠르게 하는 기술이다. 동적 계획법의 핵심이 되는 기술이다.

### 예제 - 피보나치
```
fib(n) {
   if n is 1 or 2, return 1;
   return fib(n-1) + fib(n-2);
} 
```

```
allocate array for memo, setting all entries to zero;
initialize memo[1] and memo[2] to 1;

fib(n) {
   if memo[n] is zero:
       memo[n] = fib(n-1) + fib(n-2);
   return memo[n];
}
```

### reference
- https://ko.wikipedia.org/wiki/메모이제이션
- http://www.onjava.com/pub/a/onjava/2003/08/20/memoization.html
- http://code.activestate.com/recipes/52201/

