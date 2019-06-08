## 1. 서론

### 컴퓨터 부팅
- 부트스트랩: 펌웨어라는 읽기전용 메모리에 적재, 장치제어기, 메모리 내용들을 포함한 시스템의 모든면을 초기화한다.
- 부팅 순서: 부트스트랩 시작 -> 커널을 메모리에 적재 -> `init`과 같은 첫 번째 프로세스 실행 -> 이벤트가 발생하기를 기다림

### 인터럽트
- 사건이 발생하면 인터럽트에 의해 신호가 보내진다.
- 하드웨어의 경우: 어느 순간이든 시스템 버스를 통해 CPU로 인터럽트를 발생
- 소프트웨어의 경우: 시스템 호출이라 불리는 특별한 연산을 실행하여 인터럽트 발생
- CPU가 인터럽트 되면, 하던일을 중단하고, 인터럽트를 위한 서비스 루틴이 위치한 시작 주소로 실행을 옯겨서 서비스하고, 완료되면 원래 하던일을 재개한다.
- OS는 `interrupt-driven system`이다.
- 인터럽트의 수가 제한되어 있으므로 테이블을 통해 간접적으로 인터럽트 루틴이 호출될 수 있다.
- 인터럽트 루틴은 현재 상태를 저장하며, 복귀하기 전에 상태를 복원해야 한다.

### 메모리
- CPU는 오직 메모리로부터 명령을 적재할수 있고, 실행하고자 하는 프로그램 모두 메모리에 저장되야 한다.
- 메모리는 워드의 배열을 제공하며, 각 워드는 고유의 주소를 가지고 있다.
- 상호작용은 메모리 주소들을 load 또는 store 명령을 통하여 이루어진다.
- load: 주 메모리로부터 cpu 레지스터로 한 워드를 옮김
- store: 레지스터 내용을 메모리로 옮김
- 폰노이만 구조의 명령-실행 사이클: 메모리로부터 명령을 인출해 그 명령을 명령 레지스터에 저장, 이것을 해독하고, 명령은 메모리로부터 피연산자를 인출해 레지스터에 저장하도록 한다. 피연산자의 명령 실행후, 결과가 메모리에 다시 저장될수 있다.

### 입출력
- 장치제어기는 약간의 로컬 버퍼 저장장치와 특수 목적용 레지스터 집합을 유지 또한, 자신이 제어하는 주변장치와 로컬 버퍼 저장장치 사이의 데이터 전송을 담당
- 디바이스 드라이버: 장치 제어기의 동작을 이해하고 운영체제의 다른 부분들에게 장치에 대한 일관된 인터페이스를 제공
- 입출력 연산 과정: 디바이스 드라이버가 필요값을 장치제어기 레지스터에 적재 -> 레지스터 내용 조사 -> 제어기의 로컬 버퍼로 데이터 전송 -> 데이터 전송 완료후, 완료 인터럽트를 디바이스 드라이버에 보냄 -> 디바이스 드라이버는 제어를 OS에 반환
- DMA(직접 메모리 접근): CPU의 개입없이 메모리로부터 자신의 버퍼 장치로 또는 버퍼로부터 메모리로 데이터 블록전체를 전송, 블록 전송이 완료될 때 마다 인터럽트가 발생