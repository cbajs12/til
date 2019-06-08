## Data Store & Load
- 하둡 맵리듀스에서 지원하는 `InputFormat`과  `OutputFormat` 인터페이스를 통해 데이터에 접근할 수 있어서, 흔히 쓰이는 파일 포맷과 저장 시스템을 사용할수 있다.

### 데이터 소스
- 파일 포맷과 파일 시스템
- 스파크 SQL을 사용한 구조화된 데이터
- 데이터베이스와 키/값 저장소

### 파일 포맷
- 스파크는 텍스트 파일, 일부 구조화된 JSON, 시퀸스 파일같이 폭 넓게 지원한다.
- 스파크에서 지원하는 입력 포맷들은 파일확장자에 기반하여 압축된 포맷까지 투명하게 관리된다.
- 하둡 파일 API도 사용할 수 있는데, 하둡의 인터페이스는 키/값 데이터를 필요로 하므로, 키를 무시하는 파일 포맷을 쓸때는 더미키를 쓰는 것이 일반적이다.

#### 텍스트 파일
- RDD에 텍스트 파일 하나를 읽어 들이면 각  라인이 RDD의 개별 데이터로 들어가게 된다.
- 페어RDD로 처리할떄에는 키를 파일명, 데이터는 각 내용으로 하면 된다.
- 다중 파일 입력은 `textFile()`에 파일들이 있는 디렉터리 경로를 전달하는 방법이 있다.
- 때때로 입력된 부분이 어느 파일에서 왔는지 알아야 한다면 한번에 파일 하나씩 처리하는게 좋다.
- 파일들이 메모리에 모두 불러들여 작업 할수 있을정도로 작다면, `SparkContext.wholeTextFiles()` 메소드로 파일명을 키로하는 페어 RDD를 받을수 있다.
- 텍스트 저장시에는 `saveAsTextFile()`을 사용하지만, 데이터의 어느 부분이 어떤 파일로 갈지 제어 할 수 없다.

#### JSON
- 데이터를 텍스트 파일로 불러온 뒤  JSON파서를 써서 값들을 매핑하는 방법
- JSON 직렬화 라이브러리를 사용해서 문자열로 뽑아낼수 있는 방법
- (java, scala)사용자 하둡 포맷을 써서 프로그래밍 하는 방법
- 텍스트 관련 방법과  JSON 라이브러리를 혼합하여 사용한다.

#### CSV, TSV
- `CSV`는 쉼표로 구분된 데이터를 뜻한다.
- `TSV`는 탭으로 구분된 데이터를 뜻한다.
- 텍스트 파일을 읽고 라이브러리를 사용하여 처리하는 방식
- `CSV`출력은 레코드마다 필드 이름을 쓰지 않으므로 일관성있는 출력을 얻기 위해 매핑을 만들 필요가 있다.

#### 시퀸스 파일
- 키/값 쌍의 비중첩 파일로 구성된 하둡의 파일 포맷이다.
- 동기화 표시를 가지고 있어서, 동기화 부분까지 파일을 탐색 했을때 필요한 레코드 경계까지만 재동기화를 할수 있게 해준다. 이것은 병렬로 효율적으로 읽을수 있게 해준다.
- 시퀸스 파일 출력은 시퀸스 파일이 키/값으로 이루어져 있으므로, 페어RDD가 필요하다.

#### 오브젝트 파일
- 오브젝트 파일로 값을 쓰는 것은 자바 직렬화를 사용한다.
- 오브젝트 파일은 스파크 내에서 스파크 작업들끼리 통신용도로 많이 쓰이며, 자바 직렬화는 느리다.
- 오브젝트 파일을 쓰는 가장 주된 이유는 거의 대부분의 객체들을 저장하는 데에 특별히 다른 작업이 필요하지 않다는 점이다.

#### 하둡 입출력 포맷
- 하둡API를 사용한다.

#### 파일 압축
- 디스크 공간과 네트워크 부하를 줄이기 위해 데이터를 압축할 필요가 있다.
- 대부분의 하둡 출력 포맷에는 데이터를 압축하는 데에 쓸 압축 코덱을 지정할 수 있다.

### 파일 시스템
#### 로컬 시스템
- 스파크는 로컬 파일 시스템에서 파일 불러오기를 지원하지만, 파일들이 클러스터의 모든 노드에서 동일 경로에 있기를 요구한다.
- 만약 파일들이 클러스터의 모든 노드에 잇는 것이 아니라면 로컬에서 드라이버로 파일을 불러 스파크를 거치지 않고 `parallelize`를 호출해 내용을 작업 노드에 분배할수 있다. 하지만 느리다.

#### 아마존 S3
- 작업노드가 EC2에 있다면 특히 빠르지만, 공용 인터넷을 거쳐야 한다면 성능이 저하될수 있다.

#### HDFS
- 높은 데이터 처리량을 소화하면서도 노드 장애에 유연하다.
- 스파크와 HDFS는 동일 머신들에 함께 설치할 수 있으며, 성능이 높아진다.

### 스파크 SQL
- 스파크로 구조화/반구조화 데이터를 작업하는 방법
- 구조화된 데이터라는 것은 스키마를 가진 데이터를 말한다.

#### 아파치 하이브
- 하둡의 일반적인 구조화 데이터 소스
- 일반 텍스트에서부터 칼럼 지향 포맷까지 다양한 형식으로  HDFS나 다른 저장 시스템에 테이블을 저장 할수 있다. 스파크는 하이브가 지원하는 어떤 테이블도 읽을수 있다.

#### JSON
- JSON 데이터가 레코드마다 일관된 스키마를 가진다면, 스파크 SQL은 스키마를 예상하여 각 레코드를 불러올 수 있다.

### DB
- 스파크는 커넥터를 지원하는 어떤 관계형DB의 데이터든 불러오기가 가능하다.

#### 카산드라
- 커넥터를 사용하여 통신하며, 스파크SQL을 쓰지 않지만 `CassandraRow`객체를 포함한 RDD를 리턴해 주며, 이것은 스파크SQL의 Row객체와 동일한 메소드들을 가지고 있다.

#### HBase
- 스파크는 `org.apache.hadoop.hbase.mapreduce.TableInputFormat`을 구현한 하둡 입력 포맷을 써서 HBase에 접근할 수 있다.

#### 엘라스틱 서치
- 엘라스틱서치는 루신기반의 오픈소스 검색엔진이다.
- 엘라스틱서치-하둡을 써서 통신할수 있다.
