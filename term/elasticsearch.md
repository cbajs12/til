## ElasticSearch
- 아파치 루신(Apache Lucene)을 기반으로 개발된 오픈소스 분산 검색 엔진(서버)이다.
- 엘라스틱 서치는 고성능의 확장가능한 오픈소스 풀텍스트 검색 및 분석 엔진이다. 준실시간으로 대량의 데이터의 빠르게 저장할 수 있고 검색할 수 있고, 분석할 수 있다.

### 특징
- 분산(Distributed) + 확장성 : Elasticsearch는 scale horizontally(규모가 수평적으로 늘어나도록) 하게 설계되어 있기 때문에 더 많은 용량이 필요하면 그저 노드를 추가하고 클러스터가 인식할 수 있게하여 추가적인 하드웨어로 이용할 수 있도록 해주면 된다.(같은 클러스터 내에서 라면 초기설정 그대로도 노드끼리 연결이 되지만, 다른 클러스터에 있다면 설정이 필요)
- 고가용성(High availability) : Elasticsearch 는 동작중에 죽은 노드를 감지하고 삭제하며 사용자의 데이터가 안전하고 접근가능하도록 유지한다. 즉, 동작중에 일부 노드에 문제가 생기더라도 문제없이 서비스를 제공한다.
- 멀티 태넌시(Multi-tenancy) : 클러스터는 여러개의 인덱스들을 저장하고 관리할 수 있으며, 독립된 하나의 쿼리 혹은 그룹 쿼리로 여러 인덱스의 데이터를 검색할 수 있다.
- 전문 검색(Full text search) : Elasticsearch는 강력한 전문검색을 지원한다.
- 문서 중심(Document oriented) : Elasticsearch는  복잡한 현실세계의 요소들을 구조화된 JSON 문서 형식으로 저장한다. 모든 필드는 기본적으로 인덱싱되며, 모든 인덱스들은 단일 쿼리로 빠르게 사용할 수 있다.
- Schema free : JSON 문서 구조를 통해 데이터를 인덱싱하고 검색가능하게 한다. (스키마가 개념이 없다!) 그리고 사용자의 데이터가 어떻게 인덱싱 될 것인가에 대한 것은 사용자가 커스터마이징할 수 있다.

### 개념
#### Near Realtime (NRT)
엘라스틱 서치는 NRT 검색 플랫폼이다. 약간 지연이 있다는 것을 의미한다. 일반적으로 1초 내외이며 검색되기 전에 도큐먼트를 인덱스하는 시간이 소요된다.

#### Cluster
클러스터는 전체 데이터를 하나 또는 그 이상의 노드의 집합에서 유지하고 있다는 것을 의미한다. 이는 통합 인덱싱 및 모든 노드를 통한 검색 기능을 제공한다는 것을 의미한다. 클러스터는 유니크한 이름으로 식별되며 기본은 "elasticsearch" 이다. 이름은 매우 중요하다. 노드는 하나의 클러스터의 부분이며, 만약 노드가 셋업 된다면 이름으로 클러스터에 조인한다. 가장 좋은 방법은 프로덕션의 클러스터명을 명시적으로 지정하는 것이다.

#### Node
노드는 클러스터의 구성이 되는 싱글 서버 이다. 데이터를 저장하고, 클러스터 인덱싱에 참여하며 검색 기능을 제공한다. 클러스터와 마찬가지로 노드도 이름으로 식별된다. 기본은 랜덤 마블 캐릭터명이 시작시에 할당된다. 만약 기본값을 원하지 않는다면 어떤 이름도 지정할 수 있다. 이름은 관리 목적상 중요하다. 노드는 클러스터명에 의해 특정한 클러스터에 조인한다. 기본으로 각 노드는 elasticsearch라는 클러스터명으로 조인할려고 시도할 것이다. 만약 네트워크상에 기본값의 클러스터가 존재한다면 곤란하므로 클러스터명은 꼭 지정하도록 하자.

#### Index
인덱스는 비슷한 특성을 가진 도큐먼트의 집합이다. 예를 들어 고객 데이터를 위한 인덱스를 가질 수 있고, 프로덕트 카탈로그를 위한 인덱스를 가질 수 있다. 인덱스는 이름으로 식별되며 이름은 인덱스를 수행하거나, 검색하거나, 업데이트하거나 삭제를 수행할때 사용할 수 있다.

#### Type
인덱스내에 하나 혹은 몇몇의 타입을 정의할 수 있다. 타입은 인덱스의 논리적인 카테고리 또는 파티션이다. 일반적으로  도큐먼트를 위해 정의된 타입은 공통 필드의 집합을 가지고 있다. 예를 들면 블로그 플랫폼이 있고 하나의 인덱스에 데이터에 모든 데이터들이 저장된다고 가정하자. 이 인덱스에 유저 데이터를 위한 타입을 정의할 수 있고, 블로그 데이터를 위한 또 다른 타입을 정의할 수도 있으며 코멘트 데이터를 위한 또다른 타입을 정의할 수도 있다.

#### Document
도큐먼트는 인덱싱 될 수 있는 정보의 단위이다. 예를 들어 한명의 고객을 위한 도큐먼트가 있고, 하나의 제품을 위한 또다른 도큐먼트가 있을 수 있다. 도큐먼트는 JSON으로 표현된다.
인덱스나 타입내에 많은 도큐먼트들이 저장될 수 있다. 물리적으로는 인덱스내에 도큐먼트가 저장되지만 인덱스내에 타입으로 도큐먼트는 인덱스되거나 할당 될 수 있다.

#### Shard & Replicas
인덱스는 잠재적으로 싱글 노드의 하드웨어 용량을 초과하는 대량의 데이터를 저장될 수 있다. 예를 들어 수백만의 도큐먼트가 하나의 인덱스에 저장되고 1TB의 용량을 초과한다면 검색 요청은 매우 느리게 동작하게 될 것이다.

이러한 문제를 해결하기 위해 엘라스틱 서치는 shard라고 불리는 다수의 조각으로 인덱스를 분리하는 기능을 제공한다. 인덱스를 생성하며 원하는 만큼 샤딩을 지정할 수 있다. 각 샤드는 모든 기능을 수행하는 독립된 인덱스이다.

샤딩은 두가지 이유에서 매우 중요하다.
컨텐츠의 볼륨을 수평적으로 분할 및 확장할 수 있다.
샤드에 분산되고 병렬의 작업을 수행할 수 있으므로, 성능이나 처리량을 증대시킬수 있다.
샤드에 어떻게 분산되는지에 대한 메커니즘과 또한 검색 요청에 도큐먼트를 조합하여 돌려주는 것에 대한 것은 전적으로 엘라스틱 서치에 의해 관리된다

네트워크나 클라우드 환경에서 언제든 고장이 발생할 수 있다. 이러한 이유에서 샤드 노드는 오프라인이 되거나 사라져 버릴 수 있다. 이러한 경우에 대비하여 엘라스틱 서치는 인덱스의 샤드의 복제 샤드라고 불리는 복제본을 만든다.

리플리케이션은 주요한 두가지 이유는 다음과 같다.
샤드나 노드가 고장났을때 고가용성을 제공한다. 이러한 이유에서 복제 샤드는 같은 노드에 위치해서는 안된다.
검색은 모든 복제본에서 수행되기 떄문에 볼륨이나 처리량을 스케일 아웃을 할 수 있다.
요약하자면 각 인덱스는 다수의 샤드로 분할 될 수 있다. 인덱스는 또한 여러벌의 복제본을 가질 수 있다. 샤드의 수와 복제본의 수는 인덱스를 생성하는 시점에 정의할 수 있다. 인덱스가 생성된 후에도 복제본의 수의 동적으로 변경할 수 있다. 하지만 샤드의 수는 after-the-fact 변경할 수 없다.

기본적으로 엘라스틱 서치에서 각 인덱스는 5개의 샤드와 하나의 복제본이 할당된다. 이 의미는 클러스터에 적어도 두개의 노드가 있어야 한다는 것을 의미한다. 그리고 5개의 샤드당 하나의 복제본이 지정되므로 하나이 인덱스당 총 10개의 샤드를 가지게 될 것이다.

### reference
- http://killsia.tistory.com/entry/엘라스틱서치ElasticSearch란
- http://opennote46.tistory.com/143