## 마이크로 서비스 아키텍쳐

### 모노리틱 아키텍처
- 하나의 애플리케이션 내에 모든 로직이  들어가 있는 구조
- 웹서버에 돌아가는 war파일안에 모든 서비스 컴포넌트들과 UX가 들어가있는 구조
- 각 컴포넌트는 상호 호출을 함수를 이용한 call-by-ref 구조를 취한다.

#### 장점
- 규모가 작을 경우 배포가 쉽고, 규모가 크더라도 참조에 의한 호출에 의해 컴포넌트 간 호출시 성능의 제약이 덜하고 관리가 쉽다.
- 트랜잭션 관리가 쉽다

#### 문제점
- 규모가 큰 애플리케이션의 경우, 크기 때문에 빌드 및 배포 시간, 서버 기동시간이 오래 걸린다.
-  call-by-ref로 컴포넌트들이 타이트하게 연결되어 있기 때문에 특정 컴포넌트의 장애는 다른 컴포넌트에도 영향을 준다.
- 특정 컴포넌트를 수정할고 재배포시에 전체 애플리케이션을 재컴파일해서 전체를 다시 재배포한다.

### 마이크로 서비스 아키텍쳐
- SOA(service oriented architecture) 사상에 근간을 두고, 대용량 웹 서비스 개발에 맞는 구조로 사상이 경량화되고 대규모 개발팀의 조직 구조에 맞도록 변형된 아키텍쳐
- 각 컴포넌트를 서비스라는 개념으로 정의, 상호 컴포넌트간의 의존성 없이 개발되며 REST API와 같은 표준 인터페이스로 서로 통신
- 각 서비스는 독립된 서버로 타 컴포넌트와의 의존성 없이 독립적으로 배포
- 확장을 위해서 서비스가 배치된 웹서버 인스턴스는 횡적으로 스케일링하며, 앞에는 로드 밸런서를 배치하여 서비스간의 로드를 분산시킨다.
- 각 서비스마다 데이터베이스를 가진다. 그러나 다른 컴포넌트의 데이터를 API통신으로만 가져와야 해서 성능상 문제를 야기할수 있고, 이 기종 데이터베이스 간의 트랜잭션을 묶을 수 없다는 문제점이 존재
- 각 서비스가 물리적으로 분리되어 있기 때문에 서비스 부분만 배포가 가능하다.
- 부하가 많은 특정 서비스에 대해서만 스케일링을 할수 있다. 모노리틱의 경우 특정 서비스의 부하가 많으면 전체 서버의 수를 늘리지만, 마이크로는 부하가 많은 서비스만 스케일링 해주면 된다.
- 컨웨이 법칙: 소프트웨어의 구조는 그 소프트웨어를 만드는 조직의 구조와 일치한다.

#### API Gateway
- API Gateway: 모든 API들 앞에서 모든 API에 대한 엔드 포인트를 통합하고 몇가지 추가적인 기능을 제공하는 미들웨어
- 각 서버의 URL이 다른것을 관리하기 위하여 서비스 버스와 같은 역할을 하게된다. 그럼으로써 서비스간 호출을 단순화 할수 있다.
- Orchestration: 여러 개의 서비스를 묶어서 하나의 새로운 서비스를 만드는 개념으로 API Gateway에서 구현할 수 있다.
- API Gateway에서는 오케스트레이션이 부담되는 일이 될수 있다.
- 로깅과 인증같은 공통 기능에 대한 처리를 API Gateway에서 처리할 수 있다.
- 포맷을 변경하는 메시지 변환기능이나 프로토콜을 변환하는 기능, 서비스간의 메시지를 라우팅하는 기능등을 제공할수 있다.

#### 문제점
- API 통신을 통하여 각 서비스가 연결되기 때문에 네트워크 속도문제, 데이터 변환문제등이 성능을 떨어트린다.
- 중복되는 모듈에 대해서 메모리 사용량이 늘어난다. 예를 들을 각각의 서비스를 위한 웹서버 운용 메모리등이 있다.
- 각 서비스가 분리되어 있기때문에 테스팅의 복잡도가 올라간다.
- API 기반의 여러 서비스를 하나의 트랜잭션으로 묶는 것은 불가능하다. 그래서 분산 트랜잭션을 없애거나, 에러 처리 로직을 구현하거나, 트랜잭션을 지원하는 네이티브 프로토콜을 구현하는 방법등이 존재한다.

### 분산형 거버넌스 모델
- Governance: 시스템을 개발하는 조직의 구조나 프로세스를 정의한 것
- 중앙 집중형 거버넌스: 중앙 집중화된 조직에서 표준 프로세스와 가이드를 기반으로 전체 팀을 운용하는 모델
- 분산형 거버넌스: 각 팀에 독립적인 프로세스와 기술 선택 권한을 주는 모델