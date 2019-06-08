### logging
1. commons-logging
>  아파치가 만들었으며 spring이 이걸로 로그를 찍도록 만들었다. 

2. log4j
> 현재 실무에서 가장 많이 쓰이고 있다.

3. logback
> log4j보다 더 빠르며, 리소스를 덜 잡는다.

#### 특징
> system.out.println 한줄 남기는데 io 리소스를 너무 많이 먹어서 시스템이 느려진다.

> log4j는 메모리 관리를 따로해서 io쪽에서 메모리를 많이 안먹고 빠르다. 그러나 log4j를 많이 사용하다 보니 system.out.println 만큼 리소스를 먹게 된다.

> log4j의 단점을 보안하기 위해 logback이 개발 되었다.

#### logback
Logback은 log4j 프로젝트의 후계자이다.

현재 logback은 3가지의 모듈로 나눠져있는데, logback-core, logback-classic 그리고 logback-access 이다.

core 모듈은 두가지 모듈의 베이스이다. classic 모듈은 log4j의  획기적으로 높인 성능과 맞먹는 성능을 가진다.
