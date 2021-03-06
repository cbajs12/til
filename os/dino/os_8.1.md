# 메모리 관리 전략
- 주 메모리와 프로세서 자체에 내장되어 있는 레지스터들은 CPU가 직접 접근할 수 있는 유일한 저장장치이다.
- 기계 명령어들은 메모리 주소만을 인수로 취하고, 디스크의 주소는 인수로 취하지 않는다. 따라서 모든 실행되는 명령어와 데이터들은 CPU가 직접적으로 접근할 수 있는 주 메모리와 레지스터에 있어야 한다. 만약 데이터가 메모리에 없다면 CPU가 그것들을 처리하기 전에 메모리로 이동시켜야한다.
- CPU와 주 메모리 사이에 빠른 속도의 메모리인 `캐시`를 추가하여 속도 차이를 완화시킬 수 있다.
- 각각의 프로세스는 독립된 메모리 공간을 가진다. 이를 위해 특정 프로세스만 접근할 수 있는 메모리 주소 영역을 설정한다. 이러한 방법으로는 `상한`과 `기준` 레지스터를 이용한다. 
- 기준 레지스터는 가장 작은 합법적인 물리 메모리 주소의 값을 저장한다. 상한 레지스터는 주어진 영역의 크기를 저장한다. 프로세스가 사용할수 있는 영역은 `기준 + 상한 레지스터의 값`이 되는 것이다.
- 메모리 공간의 보호는 CPU 하드웨어가 사용자 모드에서 만들어진 모든 주소와 레지스터를 비교함으로써 이루어진다. 사용자 모드에서 실행되는 프로그램에 의해 OS의 메모리 공간이나 다른 사용자 프로그램의 메모리 공간으로의 접근이 일어나면 OS는 치명적인 에러로 간주하고 트랩을 발생시킨다.
- 기준과 상한 레지스터는 여러 가지 특권 명령을 사용하는 OS에 의해서만 적재된다. 이럼으로써 사용자 프로그램이 레지스터의 내용을 변경하는 것을 막는다.
- 프로세스는 디스크에서 주 메모리로 들어오기 위하여 `입력 큐`를 형성한다. 이 큐에서 하나의 프로세스를 선택해서 메모리로 올린 후 실행하고 이 프로세스는 실행하는 동안 메모리에서 명령어와 데이터를 엑세스한다. 언젠가 이프로세스가 종료되면 프로세스가 사용했던 기억 공간이 가용 공간이 되고 다른 프로세스를 위해 사용된다.
- 프로그램에서 주소는 숫자가 아닌 심벌 형태로 표현된다. 컴파일러는 이 심벌 주소를 재배치 가능 주소로 바인딩시키고, 추후에 연결 편집기나 적재기가 이 재배치 가능 주소를 절대 주소로 바인딩 시킨다. 각각의 바인딩 과정은 한 주소 공간에서 다른 주소 공간으로 맵핑하는 것이다.
- 컴파일 시간 바인딩: 만일 프로세스가 메모리 내에 들어갈 위치를 컴파일 시간에 미리 알 수 있으면 컴파일러는 절대 코드를 생성할 수 있다. 그러나 만일 이 위치가 변경되어야 한다면 이 코드는 다시 컴파일되어야 한다.
- 적재 시간 바인딩: 만일 프로세스가 메모리 내 어디로 올라오게 될지를 컴파일 시점에 알지 못하면 컴파일러는 일단 이진 코드를 재배치 가능 코드로 만들어야 한다. 이경우에 심벌과 진짜 번지수와 바인딩은 프로그램이 주 메모리로 실제로 적재되는 시간에 이루어 진다. 이렇게 만들어진 재배치 가능 코드는 시작 주소가 변경되면 아무 때나 사용자 코드를 다시 적재하기만 하면 된다.
- 실행시간 바인딩: 만약 프로세스가 실행하는 중간에 메모리 내의 한 세그먼트로부터 다른 세그먼트로 옮겨질 수 있다면 바인딩이 실행시간 까지 허용되었다고 한다.
- CPU가 생성하는 주소를 일반적으로 `논리 주소`라 하며, 메모리가 취급하게 되는 주소(메모리 주소 레지스터(`MAR`)에 주어지는 주소)는 일반적으로 `물리 주소`라 한다.
- 컴파일 시 바인딩과 적재 시 바인딩 기법의 경우에는 논리, 물리 주소가 같다. 그러나 실행시간 바인딩 기법에서는 논리, 물리 주소가 다르다. 이러한 경우에는 논리 주소를 `가상 주소`라 한다. 프로그램에 의해 생성된 모든 논리 주소 집합을 `논리 주소 공간`이라 하며, 이 논리 주소에 상응하는 모든 물리 주소 집합을 `물리 주소 공간`이라 한다.
- 프로그램 실행 중에는 가상 주소를 물리 주소로 바꿔줘야 하는데 이 매핑작업은 메모리 관리기(`MMU`)에 의해 실행된다.
- 메모리 공간의 이용률을 높이기 위해서는 동적 적재를 해야한다. 동적 적재에서 각 루틴은 실제 호출되기 전까지는 메모리에 올라오지 않고 재배치 가능한 상태로 디스크에서 대기하고 있다. 즉, 각 루틴이 호출될때 메모리에 적재(런타임에)된다.
- 동적 적재의 장점은 사용되지 않는 루틴들의 경우 절대로 미리 적재되지 않는다는 것이다. 
- 동적 적재는 OS로부터 특별한 지원을 필요로 하지 않는다. 사용자 자신이 프로그램의 설계를 책임져야 한다.
- 동적 적재에서는 로딩이 실행 시 까지 미루어지지만 동적 연결에서는 연결이 실행 시기까지 미루어지는 것이다. 동적 연결은 주로 언어 서브루틴 라이브러리와 시스템 라이브러리에서 사용된다.
- 동적 연결에서는 라이브러리를 부르는 곳마다 `스텁`이 생긴다. 이 스텁은 작은 코드 조각으로 메모리에 존재하는 메모리를 찾는 방법 또는 메모리에 없을 경우 라이브러리를 적재하는 방법을 알려준다. 스텁은 라이브러리 루틴의 주소를 알아내고 자신을 그 루틴의 주소로 대체하고 루틴을 실행한다. 다음번에 그 부분이 호출되면 다시 동적 연결을 할 필요 없이 직접 그곳의 라이브러리 루틴을 실행하면 된다.
- 동적 연결은 동적 적재와는 달리 OS의 도움이 필요하다.

### 단순한 MMU 기법
- 여기서는 기준 레지스터를 `재배치` 레지스터라고 부른다. 기준 레지스터 속에 들어있는 값은 주소가 메모리로 보내질 때마다 사용자 프로세스에 의해 생성된 모든  논리 주소에 더해진다. 더해진 값은 물리주소가 된다.
- 재배치 레지스터의 값이 계속 유지 되는 것은 적재 시간 바인딩이고 재배치 레지스터의 값이 변동 되는 것은 실행시간(동적 할당) 바인딩이다.

### 사용자 프로그램의 단계별 처리 과정

#### 컴파일 시간
- 소스프로그램 -> 컴파일러/어샘블러 -> 오브젝트 모듈 

#### 적재 시간
- 오브젝트 모듈  -> 연결 편집기 + 다른 오브젝트 모듈들(적재 시간) -> 적재 모듈 -> 적재기 + 시스템 라이브러리

#### 실행시간
- 적재기 ->메모리내의 이진 메모리 이미지 +(동적 연결) 동적 라이브러리

## 스와핑
- 프로세스가 실행되기 위해서는 메모리에 있어야 하지만 필요한 경우 프로세스는 실행 도중에 임시로 보조 메모리로 교체되어 나갔다가 다시 메모리로 되돌아 올 수 있다.
- 한 프로세스가 CPU 할당 시간이 끝나면 주 메모리 고나리자가 이 프로세스를 보조 메모리로 교체하여 내보내고 다른 프로세스를 메모리로 불러들인다. 이것을 `스왑`이라 한다. 이러는 동안 CPU 스케줄러는 메모리 내의 다른 프로세스에게 시간 할당량을 준다.
- CPU 스케줄러는 할당 시간이 만료되어 CPU를 새로 스케줄할 때 마다 메모리 관리기가 이미 메모리 내에 준비시켜 놓은 여러 프로세스들 중 하나를 고르기만 하면 도니다.
- 이상적인 메모리 관리자는 CPU 스케줄러가 CPU의 스케줄링 재조정을 원할 때 다음에 실행할 작업이 메모리에 준비 될 수 있도록 빠른 시간 내에 스왑 작업을 완료해야 한다. 게다가 시간 할당량은 스왑작업을 고려하여 원할한 작업 처리가 이루어질 수 있도록 충분히 길어야 한다.
- 우선순위에 근간을 둔 스와핑 정책이 있을수 있다. 더높은 우선순위를 가진 프로세스가 도착하면 메모리 관리기는 이 작업을 위해서 낮은 순위의 프로세스를 디스크로 스왑시켜 내보낼수 있다. 이러한 변형을 `롤 인 `과 `롤 아웃`이라고 한다.
- 스왑되어 나갔던 프로세스가 다시 스왑되어 들어올 때 가장 간단한 방법은 이전과 동일한 주소로 되돌아오는 방법이다. 
- 바인딩이 어셈블리나 적재 시간에 이루어지는 프로세스라면 스왑되어 나온 프로세스는 전에 있었던 기억 공간 내로 다시 스왑되어야 할 것이다. 바인딩이 어셈블리나 적재 시간에 이루어지는 프로세스라면 일단 실행이 시작된 후에는 다른 위치로 이동될 수 없기 때문이다.
- 실행시간에 바인딩이 이루어지는 프로세스는 프로세스를 스왑하여 들여올 때 메모리 내의 빈 공간 어디라도 올 수 있게 된다.
- 시스템은 실행할 준비가 된 모든 프로세스를 모아 준비완료 큐에 가지고 있어야 한다. CPU 스케줄러는 다음 프로세스를 고를 때 디스패처를 호출한다. 디스패처는 이 큐에 있는 다음 프로세스가 메모리에 올라와 있는지 확인하고, 만약 올라오지 않았다면 디스크에서 불러들여야 한다. 만약 프로세를 위한 공간이 메모리에 없다면 현재 메모리에 있는 프로세스를 내보내고 CPU의 모든 레지스터를 실행해야 할 프로세스의 것으로 다시 적재하고 제어권을 프로세스에게 넘긴다.
- 스와핑 시스템에서는 문맥 교환 시간이 상당히 오래 걸린다. 문맥 교환 시간은 `(전송시간 + 지연시간) *2`이다.
- 스왑 시간을 줄이기 위해서는 실제로 사용하는 부분만을 스왑해야 한다. 이러한 작업이 효과적으로 이루어지기 위해서는 사용자는 메모리 요구사항의 변화가 있을 때마다 시스템에게 이를 알려주어야 한다. 따라서 동적으로 메모리를 요구하는 프로세스는 OS에게 메모리 요구사항의 변화를 알려줄 수 있는 시스템 호출을 호출해야한다.
- 스와핑에는 제약이 따른다. 한 프로세스를 스왑하려면 그 프로세스가 완전히 유휴 상태에 있음을 확인해야 한다. 그 프로세스가 입출력을 기다리는 경우에는 특히 오류를 일으킬 가능성이 있다.
- 많은 UNIX버전에서는 수정된 스와핑을 사용한다. 스와핑은 보통 때는 스왑을 하지 않지만 너무 많은 프로세스가 실행되고 있고, 많은 프로세스들이 메모리 공간의 부족을 느끼게 되면 스와핑이 시작된다.

## 연속 메모리 할당
- 메모리는 일반적으로 두 개의 부분으로 나누어지는데, 하나는 메모리에 상주하는 OS를 위한 것이며, 다른 하나는 사용자 프로세스를 위한 것이다. 
- 인터럽트 벡터는 흔히 0번지에 위치하기 때문에 OS는 하위 메모리에 위치시키는 것이 보통이다.
- 여러 프로세스가 동시에 메모리에 올라와 있는 것이 보통이기 때문에 입력 큐에서 기다리고 있는 프로세스들에게 메모리를 할당하는 것이 문제이다. 연속 메모리 할당 시스템에서는 각 프로세스는 연속된 메모리 공간을 차지하게 된다.

### 메모리 보호
- 메모리 변환과 보호는 재배치 레지스터와 상한 레지스터에 의해 제공되어진다. 재배치 레지스터는 가장 작은 물리 주소의 값을 저장하고, 상한 레지스터는 논리 주소의 범위 값을 저장 한다.
- 각각의 논리 주소는 상한 레지스터보다 작아야 한다. MMU는 동적으로 논리 주소에 재배치 레지스터의 값을 더함으로써 주소를 변환하는 역할을 한다. 이렇게 변환된 주소는 메모리로 보내진다.
- CPU 스케줄러가 다음으로 실행할 프로세스를 선택할 때 디스패처는 문맥 교환을 일환으로 재배치 레지스터와 상한 레지스터에 정확한 값을 적재한다.
- CPU에 의해서 생성되는 모든 주소들은 이 레지스터들의 값을 참조해서 확인 작업을 거치기 때문에 우리는 OS와 다른 사용자 프로그램을 현재 실행 중인 사용자 프로그램의 접근으로부터 보호할 수 있다.
- 재배치 레지스터를 사용함으로써 OS의 크기는 실행 중이라도 얼마든지 변경될 수 있음을 알 수 있다. 필요에 따라 메모리로 올라오기도하고 지워지기도 하는 부분을 `일시적 OS 코드`라고 한다.

### 메모리 할당
- 공간 할당의 간단한 방법은 메모리를 똑같은 고정된 크기로 분할하는 것이다. 각 분할마다 한 프로세스를 적재할 수 있고, 이때 분할의 개수가 다중 프로그래밍 정도를 결정한다. 한 분할이 비게 되면 한 프로세스가 입력 큐에서 선택되어 빈 분할에 들어온다.
- 고정 분할기법(MVT)는 주로 배치 환경에서 사용되고, 순수 세그먼테이션을 사용하는 시분할 환경에도 적용된다.
- 가변 분할 기법에서 OS는 메모리의 어떤 부분이 사용되고 있고, 어떤 부분이 사용되지 않고 있는 가를 파악할 수 있는 테이블을 유지한다. 초기에 모든 메모리 공간은 한 개의 큰 사용가능한 블록으로 간주된다. 프로세스가 시스템에 들어오면, OS는 프로세스의 메모리와 가용한 메모리 공간에 따라 공간을 할당한다. 프로세스가 공간을 할당받게 되면, 이후로는 CPU를 할당받기 위해 경쟁한다.
- OS는 항상 가용 공간의 크기들과 입력 큐를 유지해야 한다. OS는 특정 알고리즘을 사용하여 입력 큐의 프로세스들을 일정한 순서로 배치할 수도 있다. 공간을 차례로 할당해 주다가 만일 한 프로세스의 메모리 요구가 만족될 수 없게 되면 공간이 생길 때까지 기다리던가, 좀 더 적은 메모리를 요구하는 다른 프로세스가 있는 가를 알아보기 위해 입력 큐로 되돌아갈 수 도 있다.
- 일반적으로 메모리에는 다양한 크기의 자유 공간이 여기저기 산재하게 된다. 프로세스가 공간을 필요로 할 때 OS는 이 자유 공간들의 집합에서 적절한 것을 찾아내야 한다. 자유 공간이 요청한 것보다 크면 두개로 나누어 한 조각은 프로세스에게 할당하고, 나머지 하나는 자유 공간으로 소속시킨다. 해방된 블록이 자유블록과 인접해 있다면, 이 두 개의 블록을 합쳐서 한 개의 큰 자유 공간 블록으로 만든다. 이러한 기법은 `동적 메모리 할당 문제`의 특별한 예이다.
- 일련의 자유 공간-리스트에서 크기 n-바이트 블록 요청을 어떻게 만족시킬 것인지를 결정하는 문제이다. 이런 문제에 대한 해결책은 `최초 적합`, `최적 적합`, `최악 적합`등이 있다.
- 최초 적합: 요청을 만족시키는 충분히 큰 첫 번째 사용 가능한 가용 공간을 할당한다. 충분히 큰 가용 공간을 찾았을 때 검색을 끝낼 수 있다.
- 최적 적합: 충분히 큰 공간들 중에서 가장 작은 것을 택한다. 리스트가 크기순으로 되어 있지 않다면 리스트 전체를 검색해야만 한다. 이 방법은 아주 작은 잔여 공간을 만들어 낸다.
- 최악 적합: 가장 큰 가용 공간을 택한다. 자유 공간들이 크기순으로 정렬되어 있지 않으면 리스트 전체를 다 검색해야만 한다.

### 단편화
- 메모리 할당에서 언급한 방법에서는 `외부 단편화`가 발생한다. 이것은 유휴 공간들을 모두 합치면 충분한 공간이 되지만 그것들이 너무 작은 조각들로 단편화되어 있는 것이다. 외부 단편화의 최악의 경우는 모든 프로세스 사이마다 못 쓰게 된 자유 공간을 가질 수 있다.
- 일반적으로 메모리를 먼저 아주 작은 공간들로 분할하고 프로세스가 요청하면 할당을 항상 이 분할된 크기의 정수 배로만 해주는 것이 보통인데, 이 경우 할당된 공간은 요구된 공간보다 약간 더 클 수 있다. 이들 두 크기 사이의 차이가 바로 `내부 단편화`이다. 내부 단편화는 분할 내부에 존재하고 있으며, 현재 사용되고 있지 않은 메모리를 말한다.
- 외부 단편화를 해결하는 한 가지 방법은 `압축`이다. 이 방법은 메모리의 모든 내용들을 한군데로 몰고 모든 자유 공간들을 다른 한 군데로 몰아서 큰 블록으로 만드는 것이다. 그러나 압축이 항상 가능한 것은 아니다. 재배치가 어셈블리 또는 적재 시에 정적으로 행해진다면 압축은 실행될수 없다. 즉, 압축은 프로세스들의 재배치가 실행시간에 동적으로 이루어지는 경우에만 가능하다.
- 또 다른 해결 방안은 한 프로세스의 논리 주소 공간을 여러 개의 비연속적인 공간으로 나누어 필요한 크기의 공간이 가용해지는 경우 물리 메모리를 프로세스에게 할당하는 방법이다. 이 방법은 `페이징`과 `세그먼테이션`이 있고 둘다 혼용되어 사용될 수도 있다.

## 페이징
- 페이징은 논리 주소 공간이 한 연속적인 공간에 다 모여 있어야 한다는 제약을 없앤다. 페이징은 외부 단편화를 방지하고 압축 작업이 필요 없게 된다.
- 페이징은 하드웨어에 의존적이다. 그러나 최근 디자인에서는 OS와 하드웨어의 긴밀한 연계를 통해서 페이징을 구현하고 있다.

### 기본 방법
- 물리 메모리는 `프레임`이라 불리는 고정 크기의 블록으로 나누어져 있고, 논리 메모리는 `페이지`라 불리는 프레임과 같은 크기의 블록으로 나누어 진다.
- CPU에서 나오는 모든 주소는 `페이지 번호(p)`와 `페이지 오프셋(d)` 두 개의 부분으로 나누어진다. 페이지 번호는 `페이지 테이블`을 엑세스할 때 사용되며, 페이지 테이블은 주 메모리에 존재하는 페이지의 기준 주소를 갖고 있다. 이 페이지의 주소에 페이지 오프셋을 더하면 메모리 장치로 전송될 물리 주소가 된다.
- 프레임 크기와 페이지 크기도 하드웨어에 의해 정의된다. 만약 논리 주소 공간의 크기가 2^m 이고 페이지가 2^n 크기라면 논리 주소의 상위 m-n 비트는 페이지 번호를 나타내고, 하위 n 비트는 페이지 오프셋을 나타낸다.
- 페이징은 동적 재배치의 한 형태이다. 모든 논리 주소는 페이징 하드웨어에 의해 물리 주소로 매핑된다. 페이징 기법을 사용하면 외부 단편화가 발생하지 않는다. 그러나 내부 단편화가 발생된다. 할당은 항상 프레임의 정수 배로 할당되기 때문이다.
- 내부 단편화를 생각하면 작은 페이지가 바람직 하지만 페이지 크기가 작아지면 그에 반비례하여 페이지 테이블의 크기가 커지게 되고, 이 테이블이 차지하는 공간은 낭비된다. 디스크의 입장에서는 페이지의 크기가 클수록 효율적이다.
- 프로세스가 실행되기 위해 도착하면 그 프로세스의 크기가 페이지 몇 개분에 해당하는가를 조사한다. 그후 프로세스가 필요한 페이지만큼의 메모리에서 이용할 수 있는 프레임이 있다면 프로세스가 할당된다.
- 페이징의 가장 중요한 특징은 메모리에 대한 사용자 인식과 실제 물리 메모리를 명확하게 분리한다는 사실이다. 사용자 프로그램은 메모리가 하나의 연속적인 공간이며, 메모리에는 이 프로그램만 있다고 생각한다. 그러나 실제로는 프로그램은 물리 메모리 여러 곳에 프레임 단위로 산재되어 있고, 많은 다른 프로그램이 올라와있다. 사용자가 인식하고 있는 메모리와 물리 메모리의 차이는 주소 변호나 하드웨어에 의해 조정된다. 사용자 프로그램이 만들어 내는 논리 주소는 물리 주소로 변환된다. 이 매핑은 사용자에게는 안보이고 OS에 의해 조정된다.
- OS는 메모리를 관리하기 때문에 물리 메모리의 자세한 할당에 대해 파악하고 있고 있어야 한다. 이러한 정보는 `프레임 테이블`이라는 자료구조에 있다. 프레임 테이블은 각 프레임 당 하나의 항목을 가지고 있으며, 프레임이 가용한지, 할당되었는지, 할당되었다면 어느 프로세스의 어느 페이지에게 할당되었는지를 나타낸다.
- OS는 각 프로세스에 대해 페이지 테이블의 복사본을 유지해 이 복사본은 OS가 논리 주소를 수작업으로 물리 주소로 변환할 때마다 사용된다. 프로세스가 CPU를 할당받았을 때 하드웨어 페이지 테이블을 설정하는데 CPU 디스패치가 사용된다. 따라서 페이징은 문맥 교환 시간을 증가시킨다.

### 하드웨어 지원
- 대부분의 OS는 각 프로세스마다 하나의 페이지 테이블을 할당한다. 페이지 테이블을 가리키는 포인터는 PCB에 저장된다. 디스패처가 어떤 프로세스를 시작시킬 때, 이 레지스터들을 다시 적재하면 페이지 테이블도 함께 사용할 수 있게 된다.
- 페이지 테이블을 하드웨어로 구현하는 간단한 방법은 전용 레지스터의 집합으로서 구현하는 것이다. 이들 레지스터는 페이징 주소 변환을 효율적으로 하기 위하여 고속 논리 회로로 설계된다. 메모리의 모든 액세스는 페이징 맵을 통해야 하므로, 맵핑의 효율은 매우 중요하다. 디스패처는 다른 레지스터를 적재하는 것처럼 이들 레지스터를 재적재해야 한다. 페이지 테이블 레지스터를 적재하거나 변경하는 일은 특수한 명령이기 때문에 OS만이 그 일을 할수 있다.
- 페이지 테이블로 레지스터를 사용하는 것은 페이지 테이블이 작은 경우에 적합하다. 그러므로 대부분의 컴퓨터는 페이지 테이블을 주 메모리에 저장하고 `페이지 테이블 기준 레지스터(PTBR)`로 하여금 페이지 테이블을 가리키도록 한다. 다른 페이지 테이블을 사용하려면 단지 이 레지스터만 교체하면 되고, 문맥 교환 시간을 많이 줄일 수 있다.
- 이 방식의 문제점은 사용자의 메모리 접근 시간이다. 특정 주소에 접근을 하기 위해서는 2번의 메모리 접근이 필요하다. 그렇기 때문에 메모리 접근이 두배로 느려진다.
- 이 문제를 해결하는 표준방법은 `TLB`라고 불리는 특수한 소형 하드웨어 캐시가 사용된다. TLB는 매우 빠른 연관 메모리로 구성된다. TLB 내의 각 항목은 키와 값의 두 부분으로 구성된다. TLB에 페이지를 찾아 달라고 요청이 들어오면 이 찾고자 하는 페이지를 동시에 여러 개의 내부 키(페이지 번호)와 비교한다. 페이지 번호가 같은 것이 발견되면 그에 대응하는 프레임 번호를 알려준다. 이 탐색의 속도는 매우 빠르지만 하드웨어가 매우 비싸다.
- CPU가 생성한 논리 주소의 페이지 번호가 TLB에 주어진다. 페이지 번호가 발견되면 프레임 번호를 바로 알 수 있고 메모리를 접근하는데 사용된다. 이러한 모든 작업은 매핑되지 않은 메모리가 참조될 때에 비해 10% 정도 시간이 더 소요된다.
- 만약 페이지 번호가 연관 레지스터 TLB에서 찾아지지 않으면, 주 메모리에 있는 페이지 테이블에서 찾아야 한다. 프레임 번호가 얻어지면, 그것을 메모리 엑세스를 위해 사용한다. 페이지 번호와 프레임 번호는 TLB에 추가되어 다음 참조시 매우 빨리 처리할 수 있게 된다. 
- 만약 TLB 항목이 가득 차 있으면, OS는 새 항목을 넣기 위해 헌 항목을 빼어내는 교체 작업을 해야 한다. 교체 정책은 LRU부터 무작위 교체까지 다양한 정책이 사용된다.
- 어떤 TLB는 각 항목에 ASIDs를 저장하기도 한다. ASID는 그 TLB 항목이 어느 프로세스에게 속한 것인지를 알려주며, 그 프로세스의 주소 공간을 보호하기 위해 사용된다. TLB가 가상 페이지 번호를 변환할 때 현재 실행 중인 프로세스의 ASDI가 TLB entry에 있는 ASID와 동일한지 여부를 검사한다. ASID가 맞지 않으면 TLB miss로 처리한다. ASID 지원이 있으면 한 TLB안에 여러 프로세스들의 정보를 동시에 함께 보관할 수 있다.
- 페이지 번호가 TLB에서 발견되는 비율을 적중률이라고 부른다.

### 보호
- 페이지화된 환경에서 메모리의 보호는 각 프레임과 연관된 보호비트에 의해 구현된다.
- 이 비트들은 보통 페이지 테이블에 속해 있다. 한 비트는 이 페이지가 읽고-쓰기 또는 읽기 전용임을 정의할 수 있다. 
- 메모리에 대한 모든 접근은 페이지 테이블을 거치므로 주소 변환과 함께 읽기 전용 페이지에 쓰기 연산이 실행되지 않도록 검사하는 작업도 실행된다. 읽기 전용 페이지에 대해 쓰기를 시도하면 OS에게 하드웨어 트랩이 전달된다.
- 페이지 테이블의 각 엔트리에는 유효/무효라는 하나의 비트가 더 있다. 이 비트가 유효로 설정되면 관련된 페이지가 프로세스의 합법적인 페이지임을 나타내며, 이 비트가 무효로 설정되면 그 페이지는 프로세스의 논리 주소 공간에 속하지 않는다는 것을 나타낸다. 불법적인 주소는 유효-무효 비트를 사용하여 잡아낼 수 있다. OS는 이 비트를 이용해서 그 페이지에 대한 접근을 허용 또는 비허용 할 수 있다.

### 공유 페이지
- 페이징의 장점 중의 하나는 공통 코드를 공유할 수 있다는 점이다.
- 재진입 가능 코드는 실행하는 동안 절대로 변하지 않는다. 따라서 두 개나 그 이상의 프로세서들이 동시에 같은 코드를 실행할 수 있다.
- 코드 부분은 서로 공유하더라도 각각의 프로세서들은 레지스터들의 복사 값과 프로세서가 실행되는 동안 필요한 데이터들을 저장하는 데이터 저장소를 따로 가지고 있다. 이렇게 하면 단 하나의 코드 사본만 물리 메모리에 존재하면 된다. 각각의 사용자 페이지 테이블은 같은 코드를 페이지 테이블에 매핑한다. 그러나 데이터는 서로 다른 페이지 프레임에 매핑한다.
- 공유가 가능하려면 코드는 반드시 재진입이 가능해야한다. 공유 코드의 읽기 전용 속성을 보장하는 의무가 코드의 정확성에만 맡겨져서는 안되고, OS가 이를 강제해야 한다.
