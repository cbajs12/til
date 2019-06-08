## 람다 아키텍쳐
- 실시간 분석을 지원하는 빅데이터 아키텍처

### 전통적인 방법 - 배치
- 어떤 특정시간에 계산을 미리 해놓는 것
- 실시간 데이터 반영: 배치 테이블과 그날의 데이터 테이블을 두개 같이 운용, 어제까지의 데이터는 일별 배치로 생성된 테이블을 사용, 오늘 데이터 부분은 사용자별로 기록된 로그 테이블을 사용하여 두 테이블을 조인하면 현재까지의 통계값까지 볼수 있다.

#### 실시간 집계 테이블
- 매일 배치를 돌리면서, 로그 서버에서 클라이언트에서 받은 로그를 원본 데이터 테이블에 계속 저장을 하고, 오늘 통계에 대한 실시간 집계 테이블에 개별 이벤트의 값을 계산해서 더해준다.

### 람다 아키텍처 활용
- 배치 처리를 위해서 로그 서버는 모든 로그 데이터를 저장소에 저장하고, 배치 처리 계층에서 일정한 주기로 배치 처리로 계산해서 배치 뷰를 만든다.
- 다른 흐름으로 실시간 처리 쪽에 데이터를 전송하고 실시간 집계를 해서 실시간 집계 테이블을 만든다. 마지막으로 이 두개의 뷰를 합쳐 통계를 만든다.
- 배치뷰는 배치로 돌때만 쓸수 있고 평상시에는 데이터 읽기만 가능, 이를 통하여 데이터가 변경되거나 오염되는 것을 막을수 있다.
- 실시간 뷰는 실시간으로 데이터를 쓰고 읽을수 있는 시스템을 사용
- 배치/ 실시간 처리 부분에서는 복잡한 계산을 위하여 프로그램을 이용하여 알고리즘을 삽입할 수 있어야 한다.
- 저장소로써는 대량의 데이터를 저비용으로 안정성 있게 저장할수 잇는 것이 필요하고 배치 처리시에 신속하게 복잡한 알고리즘을 적용하는 것이 필요하다 (HDFS/ amazon S3와 Map&Reduce)
- 배치 데이터를 저장할 장소가 필요한데, 데이터를 저장하고 고속으로 액세스할 수 있도록 하는것이 필요(HBase(`NoSQL`), amamzon RedShift)
- 실시간 처리는 복잡한 알고리즘을 빠르게 데이터를 처리할 수 있는 솔루션이 필요 (Storm, Spark)
- 실시간 뷰는 빠른 읽기와 쓰기를 지원해야 하기 위한 솔루션이 필요 (Redis)
- 배치 계층의 저장소에는 가공 전의 원본 데이터 모두를 저장한다. 데이터 처리 후에도, 저장소에 데이터를 삭제하지 않는다. 이렇게 함으로써, 배치 뷰의 데이터가 잘못 계산 혹은 유실 되었을때 복구 가능하고, 현재 데이터 분석에서 없었던 새로운 통계를 제공할 때에도, 저장된 데이터를 활용 가능하다.

### 람다 아키텍처 재구성
- 배치 뷰와 실시간 뷰가 다른 솔루션을 사용할 경우 상호 솔루션 간 데이터 조인이 불가능하다 그렇기 때문에 실시간 뷰와 배치 뷰 부분에 RDBMS를 사용하여 정렬된 데이터를 볼기 쉽도록 할수 있다.
- 저장소에 저장된 원본 데이터를 R과 같은 데이터 분석하여 새로운 통계 모델을 설계하고 검증해 볼수 있다.