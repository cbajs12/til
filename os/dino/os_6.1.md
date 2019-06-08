# 프로세스 동기화
- 협력적 프로세스는 논리 주소 공간(코드와 데이터등)을 직접 공유하거나, 파일 또는 메시지를 통해서만 데이터를 공유할 수 있다.
- 공유 데이터에 대한 동시 접근은 데이터의 비일관성을 낳을 수 있다. 프로세스 동기화는 데이터의 일관성을 유지하기 위한 메커니즘이다.
- 동시에 여러 개의 프로세스가 동일한 자료를 접근하여 조작하고, 그 실행결과가 접근이 발생한 특정 순서에 의존하는 상황을 `경쟁 상황(race condition)`이라 한다. 이러한 경쟁 상황으로부터 보호하기 위해 프로세스들을 동기화할 필요가 있다.

## 임계 영역 문제
- 각 프로세스는 임계 영역이라고 부르는 코드를 포함하고 있고, 그 안에서 프로세스와 공유하는 변수를 변경하거나, 테이블을 갱신하거나 파일을 쓰거나 하는 등의 작업을 실행한다.
- 한 프로세스가 자신의 임계 영역에서 실행하는 동안에는 다른 프로세스는 자신의 임계 영역에 들어갈 수 없다. 즉, 동시에 두 프로세스가 그들의 임계영역 안에서 실행할 수 없다.
- 각 프로세스는 자신의 임계 영역으로 진입하려면 진입허가를 요청해야 한다. 이러한 요청을 구현하는 코드 부분을 `진입 영역`이라고 부른다. 임계 영역뒤에는 `퇴출 영역`이 따라올 수 있다. 코드의 나머지 부분들은 총칭하여 `나머지 영역`이라고 한다.

### 임계 영역 문제에 대한 해결안
- 상호 배제: 프로세스가 자기의 임계 영역에서 실행된다면, 다른 프로세스들은 그들 자신의 임계 영역에서 실행될 수 없다.
- 진행: 자기의 임계영역에서 실행 중인 프로세스가 없고 자신의 임계 영역으로 진입하려고 하는 프로세스들이 있다면, 나머지 영역에서 실행 중이지 않은 프로세스들만 임계 영역으로 진입할 프로세스를 결정하는 데 참여 할 수 있으며, 이 선택은 무기한 연기될 수 없다.
- 한정된 대기: 프로세스가 자기의 임계 영역에 진입하려는 요청을 한 후부터 그 요청이 허용될 때까지 다른 프로세스들이 자신의 임계 영역에 진입하도록 허용되는 횟수는 한계 또는 제한이 있어야 한다.

### OS 내 임계 영역 접근법
- 임의의 한 순간에 많은 커널 모드 프로세스들이 OS안에서 활성화 될 수 있다. 그 결과 OS를 구현하는 코드는 경쟁 조건이 발생하기 쉽다.
- 선점형 커널: 프로세스가 커널 모드에서 실행되는 동안 선점되는 것을 허용한다. 그러하기 때문에, 공유되는 커널 자료구조에서 경쟁 조건이 발생하지 않는다는 것을 보장하도록 설계하여야 한다.
- 비선점형 커널: 커널 모드에서 실행되는 프로세스의 선점을 허용하지 않고 커널 모드 프로세스는 커널을 빠져 나갈 때까지 또는 봉쇄될 때까지 또는 자발적으로 CPU의 제어를 양보할 때까지 계속 실행된다. 비선점형 커널은 한 순간에 커널 안에서 실행 중인 프로세스는 하나 밖에 없기 때문에 커널 자료구조에 대한 경쟁 조건을 염려할 필요는 없다.
- 선점형 커널은 실시간 프로세스가 현재 커널에서 실행 중인 프로세스를 선점할 수 있기 때문에 실시간 프로그래밍에 더 적당하여 선점형 커널이 더 선호된다.

## 피터슨의 해결안
- 임계 영역과 나머지 영역을 번갈아 가며 실행하는 두 개의 프로세스로 한정한다. 두 프로세스가 두 개의 데이터 항목을 공유하도록 하여 해결한다.
- 변수 turn은 임계 영역으로 진입할 순번을 나타낸다. 만일, `turn == i`이면 프로세스 i 가 임계 영역으로 진입할 수 있다.
- 임계영역으로 진입하기 위해서 프로세스1인 flag[1]을 참으로 만들고, turn을 2로 지정한다. 프로세스 2가 임계 영역으로 진입하기를 원한다면 진입 가능하다는 것을 보장한다. 만일 두 프로세스가 동시에 진입하기를 원한다면 turn 둘 중 한 것만 배정된다. turn의 궁극적인 값에 따라 둘 중 누가 먼저 임계 영역으로 진입할 것인가를 결정한다. 프로세스1은 프로세스2가 지난번에 진입했다면 이번에는 자기도 한 번은 들어갈 수 있게 보장된다.
- 이것으로 상호 배제가 제대로 지켜지고, 진행에 대한 요구 조건을 만족하며, 대기 시간이 한없이 길어지지 않아짐을 만족한다.

## 동기화 하드웨어
- 피터슨의 해결안과 같은 소프트웨어 기반 해결책은 현대의 컴퓨터 구조에서 올바르게 동작한다는 것을 보장하지 않는다.
- 일반적으로 임계 영역에 대한 임의의 해결책은 락이라는 간단한 도구가 필요하다. 경쟁 조건은 임계 영역이 락에 의해 보호되게 함으로써 예방할 수 있다.
- 프로세스는 임계 영역에 진입하기 전에 반드시 락을 획득해야 한다. 임계 영역을 나올 때에는 락을 방출한다.
- 임계 영역 문제는 단일처리기 환경에서는 공유 변수가 변경되는 동안 인터럽트 발생을 허용하지 않음으로써 간단히 해결할 수 있다. 
- 다중처리기 환경에서는 인터럽트가 불능화는 상당한 시간을 소비한다. 인터럽트 불능화는 모든 프로세스에 메시지를 전달하고 또한, 매 임계 영역에 진입하는 것을 지연시켜, 시스템 효율을 떨어뜨린다.
- 현대 기계들은 한 워드의 내용을 검사하고 변경하거나, 두 워드의 내용을 원자적으로 교환할 수 있는, 즉 인터럽트되지 않는 하나의 단위로서, 특별한 하드웨어 명령어들을 제공한다. `TestAndeSet()` 과 `Swap()` 이다.

```cpp
Boolean TestAndSet(boolean *target){
   boolean rv = *target;
   *target = true;
   return rv;
}

do{
   while(TestAndSet(&lock))
      ; // do nothing
      // critical section
   lock = FALSE;
      // remain section
}while(TRUE);
```

- TestAndSet의 중요한 특징으로는 이 명령어가 원자적으로 실행된다는 것이다. 두 개의 TestAndSet명령어가 동시에 실행된다면, 어떤 임의의 순서로 순차적으로 실행될 것이다. 기계가 TestAndSet() 명령을 지원한다면, false로 초기화 되는 lock이라는 Boolean 변수를 선언하여 상호 배제를 구현할 수 있다.

```cpp
void Swap(boolean *a, boolean *b){
   boolean temp = *a;
   *a = *b;
   *b = temp;
}

do{
   key = TRUE;
   while(key == TRUE)
      Swap(&lock, &key)
      // critical section
   lock = FALSE;
      // remain section
}while(TRUE);
```

- Swap 두 개의 워드의 내용에 대해 작동한다. 또한 Swap도 원자적으로 실행된다. 기계가 Swap() 명령을 지원한다면, 전역 Boolean 변수 lock을 선언하고 false로 초기화한다. 그리고 각 프로세스는 지역 Boolean 변수 key를 가지고 있다.

```cpp
boolean waiting[n];
boolean lock;

do{
   waiting[i] = TRUE;
   key = TRUE;
   while(waiting[i] && key)
      key = TestAndSet(&lock)
   waiting[i] = FALSE;
   // critical section
   j = (i+1) % n;
   while((j != i) && !waiting[j])
      j = (j+1) % n;
   
   if(j==i)
      lock = FASLE;
   else
      waiting[j] = FALSE;
   // remainder section
}while(TRUE);
```

- boolean 자료구조들은 모두 false로 초기화 된다.
- waiting[i] == false, key == false일때 임계 영역에 프로세스를 진입시킴으로써 상호 배제 조건을 만족시킨다. key 값은 TestAndSet() 명령어를 실행했을 경우에만 false가 된다. 또 다시 waiting[i]가 false가 되는 것은 다른 프로세스가 임계 영역을 떠날 때 뿐이다. 오직 한 개의 waiting[i]만이 false로 지정되고 따라서 상호 배제가 보장된다.
- 한 프로세스가 임계 영역을 떠날때에는 waiting 배열을 순환하면서 조사하여, waiting[j] == true이면서 배열의 순환순서중 첫번째 프로세서가 임계 영역에 들어가게 된다. 따라서 임계 영역에 들어가고자 하는 프로세스는 최대한 n-1회만 양보하면 들어갈 수 있다.
- 그러나 하드웨어적으로 다중프로세서에서 원자적 TestAndSet() 명령어 구현은 간단한 작업이 아니다.