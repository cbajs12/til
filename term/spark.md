## Spark
- 범용적이면서도 빠른 속도로 작업을 수행할 수 있도록 설계한 클러스터용 연산 플랫폼
- 다양한 연산 모델을 효과적으로 지원하는, 맵리듀스 모델을 대화형 명령어 쿼리나 스트리밍 처리등이 가능하도록 확장하였다.
- 스파크가 속도를 높이기 위하여 제공하는 중요한 기능중 하나는 연산을 메모리에서 수행하는 기능이지만, 설령 복잡한 프로그램을 메모리가 아닌 디스크에서 돌리더라도 맵리듀스보다는 더욱 뛰어난 성능을 보여준다.
- 범용적이라는 면에서 스파크는 기존에 각각 분리된 분산 시스템에서 돌아가던 배치 어플리케이션, 반복 알고리즘, 대화형 쿼리, 스트리밍 같은 다양한 작업 타입을 동시에 커버할 수 있게 설계되었다.
- 실제의 데이터 분석 파이프라인에서도 서로 다른 형태의 작업을 쉬고 저비용으로 연계할 수 있게 해준다.

### 통합된 구성
- 스파크 프로젝트는 밀접하게 연동된 여러 개의 컴포넌트로 구성되어 있다.
- 핵심인 스파크는 다수의 작업 머신이나 클러스터 위에서 돌아가는 많은 연산 작업 프로그램을 스케줄링하고 분배하고 감시하는 역할을 한다. SQL이나 머신 러닝같은 다양한 워크로드에 특화된 여러 고수준 컴포넌트를 실행할 수 있게 해준다.
- 밀접하게 컴포넌트를 연동하는 설계 원칙에는 몇가지 장점이 있다. 성능 향상에 의해 직접적으로 이익을 볼 수 있다. 서로 다른 데이터 처리 모델을 깔끔하게 합쳐서 하나의 어플리케이션을 만들수 있다는 것이다.

#### 스파크 코어
- 작업 스케쥴링, 메모리 관리, 장애 복구, 저장 장치와 연동 등등 기본적인 기능들로 구성된다.
- RDD를 정의하는 API의 기반이 되며, 스파크 프로그래밍 추상화의 구조이다.
- RDD는 여러 컴퓨터 노드에 흩어져 있으면서 병렬 처리 될수 있는 아이템들의 모음을 표현한다. 코어는 이 모음들을 생성하고 조작할 수 있는 많은 API를 지원한다.

#### 스파크 SQL
- 정형 데이터를 처리하기 위한 스파크 패키지
- 다양한 데이터 소스를 지원한다.
- SQL과 복잡한 분석 작업을 서로 연결할수 있도록 지원한다.

#### 스파크 스트리밍
- 실시간 데이터 스트림을 처리 가능하게 해 주는 스파크의 컴포넌트이다.
- 스파크 스트리밍은 스파크 코어의 RDD API와 거의 일치하는 형태의 데이터 스트림 조작 API를 지원한다.
- 스파크 코어와 동일한 수준의 장애 관리, 처리량, 확장성을 지원하도록 설계되어 있다.

#### MLib
- 스파크는 일반적인 머신 러닝 기능들을 갖고 있는 라이브러리와 함께 배포된다.
- 분류, 회귀, 클러스터링, 협업 필터링등의 다양한 타입의 머신 러닝 알고리즘 뿐만 아니라 모델 평가 및 외부 데이터 불러오기 같은 기능도 지원한다.
- 경사 강하 최적화 알고리즘 같은 몇몇 저수준의 ML 핵심 기능들도 지원한다.
- 모든 기능들은 클러스터 전체를 사용하며 실행되도록 설계되었다.

#### 그래프X
- 그래프를 다루기 위한 라이브러리, 그래프 병렬 연산을 수행한다.
- 스파크 RDD API를 확장하였으며, 각 각선이나 점에 임의의 속성을 추가한 지향성 그래프를 만들수 있다.
- 그래프를 다루는 다양한 메소드들 및 일반적인 그래프 알고리즘들의 라이브러리를 지원한다.

#### 클러스터 매니저
- 스파크는 한 노드에서 수천 노드까지 효과적으로 성능을 확장할 수 있도록 만들어져있다. 그래서 유연성을 극대화하면서 이를 달성하기 위해 스파크는 하둡의 얀, 메소드, 스파크에서 지원하는 가벼운 구현의 클러스트 매니저인 '단독 스케줄러'등 다양한 클러스터 매니저 위에서 동작할 수 있다.
