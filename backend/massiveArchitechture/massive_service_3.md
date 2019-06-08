# 대용량 서비스 레퍼런스 아키텍처

## Analysis layer
- 트랜잭션 처리에 의한 결과와 로그를 분석하는 계층

### OLAP 방식의 분석 시스템
- RDBMS 기반의 분석 시스템, 분석에 최적화된 DB이다.
- ETL: 여러 데이터 소스로부터 수집해서 변환 및 저장하는 역할을 담당
- 클라우드 환경의 특징은 로그 수집 대상이 되는 서버들이 분산되어 있으며 처리 용량에 따라 스케일링이 되어 로그 수집 대상이 상황에 따라 바뀐다. 이러한 솔루션으로느 Flume
- 데이터 웨어하우스와 데이터 마트: 데이터를 OLAP 형태의 DB에 저장한다. 모든 OLTP 시스템에서 수집된 모든 데이터는 데이터 웨어하우스라는 중앙 집중화된 DB에 저장된다. 저장된 데이터는 각 부서마다 정제되어 저장되는데 저장되는 장소를 데이터 마트라고 한다.

### Map Reduce 기반 분석 시스템
- 대용량 데이터의 분석에 특화된 방법
- 하나의 큰 데이터를 여러 개의 조각으로 나눠서 처리하는 단계(map)와 처리 결과를 하나로 합쳐서 결과를 내는 단계(reduce)로 나누어 진다.

### Map Reduce + OLAP
- 맵리듀스는 데이터를 분석할 때마다 여러 가지 알고리즘을 직접 작성해야한다. 다각적인 각도에서 분석하기에는 OLAP기반이 더 유연성을 가진다. 그래서 이 두 조합을 연결하여 사용하는 방식이다.
- ETL과 로그 수집 시스템을 통해 데이터를 수집하고 대용량 파일 시스템(Hadoop)에 저장한후 하둡 인프라를 이용하여 데이터를 정제 및 가공한 후에 OLAP DB에 저정한다. 그리고 이 데이터로 리포팅 도구로 여러 뷰로 생성한다.

### 실시간 분석 시스템
- OLAP와 맵리듀스 방식은 일반적으로 배치 방식이라고 하는 처리 방식이다. 하지만 이러한 방식은 결과가 늦게 나온다.
- 실시간 방식은 트랜잭션이 발생하면서 실시간으로 리포트를 받고 거기에 따른 의사결정을 한다.
- 실시간 데이터 분석은 스트리밍 기술이 필요한다. 실시간으로 흐르는 데이터에 대해서 통계 분석을 할 수 있는 기술이 필요하다. storm이나 spark를 이용한다.