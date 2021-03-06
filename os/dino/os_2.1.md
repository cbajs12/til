## 시스템 구조

### User Operating-System Interface
- CLI: 사용자가 지정한 명령을 가져와서 실행하는 것
- CLI의 구현 방법들: CLI가 명령을 실행할 코드를 갖고 있다. 이 경우에는 커널에 CLI가 포함되어 있다. 또 다른 방법은 시스템 프로그램에 의해 대부분의 명령을 구현하는 것, 메모리에 적재되어 실행될 파일을 식별하기 위해 명령을 사용한다.(rm -rf a.txt)
- GUI: 마우스를 기반으로 하는 윈도우 메뉴 시스템

### System Calls
- OS가 제공하는 서비스에 대한 인터페이스를 제공
- OS의 특정 저수준 작업에 대한 것이라도 시스템 호출은 고급 언어로 작성된 루틴 형태로 제공
- 시스템 콜의 예: 파일 존재 안할시 콘솔에 에러 메시지 출력, 비정상적인 프로그램 종료, 입력파일 읽기 등
- API: 각 함수에 전달되어야 할 매개변수들과 프로그래머가 기대할 수 있는 반환 값을 포함하여 응용 프로그래머가 사용 가능한 함수의 집합을 명시
- API에 따라 프로그램을 만드는 프로그래머는 만들어진 프로그램이 같은 API를 지원하는 어느 시스템에서건 컴파일 되고 실행된다는 것을 기대할 수 있다.
- 프로그램 언어를 위한 라이브러리 함수들은 OS가 제공하는 시스템 호출에 대한 연결로서 동작하는 시스템 호출 인터페이스를 제공한다.
- 시스템 호출 인터페이스는 API 함수의 호출을 가로채어 필요한 OS 시스템 호출을 부른다.
- 통상 각 시스템 호출에는 번호가 할당되고 시스템 호출 인터페이스는 이 번호에 따라 인덱스되는 테이블을 유지한다.
- OS 인터페이스에 대한 대부분의 자세한 내용은 API에 의해 프로그래머로부터 숨겨지고 실행시간 지원 라이브러리 의해 관리된다.

#### OS에 매개변수를 전달하기 위한 3가지 방법
- 매개변수를 레지스터 내에 전달
- 메모리 내의 블록이나 테이블에 매개변수 저장 후, 블록의 주소가 레지스터 내에 매개변수로 전달
- 프로그램에 의해 스택에 넣어질 수 있고, OS에 의해 꺼내진다.

### 시스템 호출 유형
- 프로세스 제어, 파일 조작, 장치 조작, 정보 유지 보수, 통신과 보호로 나눌수 있다.


#### 프로세스 제어
- 실행 중인 프로그램은 실행을 정상적 또는 비정상적으로 종료할 수 있어야 한다.
- 정상적, 비정상적 상황에서 OS는 CLI로 제어를 전달해야 한다.
- 끝내기, 중지, 적재, 실행, 프로세스 생성/ 종료, 메모리 할당등이 포함된다. (fork(), exit(), wait())

#### 파일 관리
- 파일 생성, 삭제등의 작동을 한다.
- Open(), read(), write(), close()등이 포함된다.

#### 장치관리
- 장치 속성 획득, 장치 요구, 장치 읽기/쓰기/위치 변경등의 작동을 한다.
- ioctl(), read(), write()등이 포함된다.

#### 정보 유지
- 시간과 날짜 설정, 획득, 프로세스/파일/장치 속성의 획득등의 작동을 한다.
- getpid(), alarm(), sleep()등이 포함된다.

#### 통신
- 통신 연결의 생성, 제거, 메시지 송수신등의 작동을 한다.
- pipe(), shmget(), mmap()등이 포함된다.
- 메시지 전달 모델: 통신 모델중 하나, 통신하는 두 프로세스가 정보를 교환하기 위하여 서로 메시지를 주고받는다. 서로 메시지를 주고 받을때는 연결이 먼저 열려야하며, 상대 통신자에 대한 정보를 알고 있어야 한다.
- 공유 메모리 모델: 프로세스는 다른 프로세스가 소유한 메모리 영역을 생성하고 접근하기 위해 시스템 호출을 사용. 공유한 영역에 데이터를 읽고 씀으로써 통신을 한다.

#### 보호
- 컴퓨터 시스템이 제공하는 자원에 대한 접근을 제어하기 위한 기법을 지원
- chmod(), umask(), chown()등이 포함된다.

### 시스템 프로그램
- 논리적인 컴퓨터 계층: 하드웨어 - OS - 시스템 프로그램 - 애플리케이션
- 시스템 유틸리티로 알려진 프로그램 개발과 실행을 위해 편리한 환경을 제공한다.
- 대부분의 사용자가 보는 OS는 운영체제의 관점은 시스템 호출에 의해서라기 보다는 시스템 프로그램과 애플리케이션에 의해서 정의된다.

#### 파일 관리
- 파일과 디렉터리를 생성, 삭제등을 하는 프로그램들이 있다.

#### 상태 정보
- 시스템에 날짜, 시간, 사용 가능 메모리와 디스크 공간의 양, 로깅 및 디버깅 정보등의 상태 정보를 묻는 프로그램들이 있다.

#### 파일 변경
- text editor등과 같은 프로그램들이 있다.

#### 프로그래밍 언어 지원
- 프로그래밍에 대한 컴파일러, 어셈블러, 인터프리터를 지원하는 프로그램들이 있다.

#### 프로그램 적재와 실행
- absolute loader, relocatable loader, linkage editor, overlay loader, 디버깅등을 제공하는 프로그램들이 있다.

#### 통신
- 프로세스, 사용자, 다른 컴퓨터 사이의 접속을 이루기 위한 기법을 제공하는 프로그램들이 있다.

### 운영체제 설계 및 구현
- OS는 설게, 구현, 유지 보수가 쉬워야 하며, 적응성, 신뢰성, 무오류, 효율성을 가져야 한다.
- 메커니즘과 정책을 분리하는 해야 한다. 이것은 융통성을 위해 아주 중요하다.
- 매커니즘: 어떤 일을 어떻게 할 것인가를 결정하는 것
- 정책: 무엇을 할 것인가를 결정
- 정책 결정은 모든 자원 할당 문제에 있어 중요하다. 자원의 할당 여부를 결정할 필요가 있을 때마다 정책을 결정해야 한다.
- OS를 구현하기 위해 고급 언어나 최소한 시스템 구현 언어를 사용함으로써 생기는 장점은 코드를 빨리 작성할 수 있고, 간결하고, 이해하기 쉽고, 다른 하드웨어로 이식하기 쉽고, 디버그하기 쉽다는 것이다.
- OS의 주요 성능 향상은 우수한 어셈블리어 코드보다는 좋은 자료구조와 알고리즘의 결과일 가능성이 크다.