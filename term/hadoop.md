## What is Hadoop?
- 대용량 데이터를 분산 처리 할 수 있는 자바 기반의 오픈소스 프레임워크
- 분산 파일 시스템(HDFS)에 데이터를 저장하고, 분산 처리 시스템(맵리듀스)를 이용해 데이터를 처리
- 데이터의 복제본을 저장하기 때문에 데이터의 유실이나 장애가 발생했을때 복구가 가능
- 여러대의 서버에 데이터를 저장하고 데이터가 각 서버에서 동시에 데이터를 처리
- 분석을 위한 데이터를 처리하기 위해 ETL(Extraction, Transformation, Loading)과정을 거친다. 데이터를 추출하고, 변환한후 DW(Data Warehouse) 혹은 DM(Data Mart)에 전속과 적재하는것을 하둡이 도와줄수 있다.
- 하둡은 트랜잭션이나 무결성이 반드시 보장되어야 하는 데이터를 처리하는데 적합하지 않다 왜냐하면, 배치성으로 데이터를 저장하고 처리하는데 적합한 시스템으로 구성 되어있기 때문이다.
- 데이터 무결성이 중요한 데이터는 트랜잭션별로 무결성을 보장하는 기존 RDBMS에서 처리하고, 하둡은 배치성으로 데이터를 저장하고 처리해야한다.

### 하둡의 단점
#### 고가용성 지원 문제
- 가용성이란 시스템 장애 발생후 정상으로 돌아오는 상태를 분석하는 척도
- HDFS는 네임노드와 데이터 노드로 구성 네임노드가 HDFS에 저장하는 모든 데이터의 메타데이터 정보를 관리
- 만약 네임노드에 장애가 발생하면 데이터를 더는 HDFS에 저장할수 없고, 네임노드의 데이터가 유실될 경우 기존에 저장된 파일도 조회할수 없게된다.
- 2.0버전에는 네임노드의 고가용성을 지원함

#### 파일 네임스페이스 제한
- 네임노드가 관리하는 메타 정보는 메모리로 관리되기 때문에 메모리 용량에 따라 HDFS에 저장하는 파일과 디렉터리 개수가 제한 받는다.

#### 데이터 수정 불가
- 한번 저장한 파일은 더는 수정할 수 없다.
- 파일의 이동과 이름변경의 작업은 가능하나 내용의 수정은 불가
- 0.21버전에서는 append기능이 제공

#### POSIX 명령어 미지원
- 기존 파일 시스템에서 사용하던 rm, mv같은 POSIX형식의 파일 명령어를 이용할수 없다.
- 하둡에서 제공하는 별도의 셀 명령어와 API를 통해 파일을 제어해야한다.
