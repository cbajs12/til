## key/value pair RDD
### 기본
- 빈번하게 집합 연산등에 쓰이며 간혹 기본적인 ETL(Extract, Transform, Load)작업에도 데이터를 키/값포멧으로 변화하는 방식으로 쓴다.
- 키/값을 가지고 있는 RDD에 대해 특수한 연산들을 제공한다.
- 페어 RDD는 각 키에 대해 병렬로 처리하거나 네트워크상에서 데이터를 다시 그룹핑하게 해준다.

### 생성
- 일반 RDD를 페어 RDD로 변환하고 싶다면 키/값 쌍을 되돌려주는 `map()`사용하여 변환할수 있다.
- 키를 가지는 데이터로 동작하는 함수들을 고려해 튜플로 구성된 RDD를 반환해 만들어야 한다.
- 스칼라나 파이썬에서 메모리에 있는 데이터로부터 페어 RDD를 만들어 내려면 페어 데이터세트에 SparkContext.parallelize()만 호출하면 된다.

### 트랜스포메이션
- 페어 RDD는 기본 RDD에서 가능한 모든 트랜스포메이션을 사용할수 있다.
- 페어 RDD는 튜플을 가지므로 튜플을 처리하는 함수를 전달해야한다.
- 페어 RDD의 값에 대한 작업이 필요한 경우, 키/값 쌍으로 작업하는 것이 번거로울 수도 있다. 그때 `mapValues(func)`를 사용하면 된다.

#### 집합연산
- 데이터셋이 키/값 쌍으로 표현될때 동일 키에 대해 집계된 통계를 산출하는 작업
- `reduceByKey()`는 여러개의 병합 연산을 실행하는데, 하나의 연산은 하나의 키에 대한 작업이 되고, 각 작업은 동일 키에 대한 값을 하나로 합치게 된다.
- `foldByKey()`에 제공되는 제로 밸류도 다른 데이터 값과 병합 함수에서 합쳐질때에 값에 변경이 생기면 안된다.
- `combineByKey()`는 한 파티션 내의 데이터들을 하나씩 처리하게 되며, 각 데이터는 이전에 나온 적이 없는 키를 갖고 있을 수도 있고 이전 데이터와 같은 키를 가질수도 있다.

#### 병렬화 수준 최적화
- 모든 RDD는 고정된 개수의 파티션을 갖고 있으며 이것이 RDD에서 연산이 처리 될때 동시 작업의 수준을 결정하게 된다.
- 집합 연산이나 그룹화 작업을 요청할때, 스파크에 특정 개수의 파티션을 사용하라고 요청할수 있다.
- `repartition()`은 집합 연산이나 그룹화 작업 코드 범위 바깥에서 RDD의 파티셔닝을 바꾸고 싶을때 사용, 새로운 파티션 구성을 위해 네트워크로 데이터 교환이 일어난다.(오버헤드가 큰 작업이다.)
- `coalesce()`는 `repartition()`의 최적화 버전이며, RDD의 파티션 개수를 줄이는 경우에 한해서는 데이터 이동이 발생하지 않는다.

#### 데이터 그룹화
- 만약 데이터가 이미 원하는 값을 키로 쓰고 있을 경우, `groupByKey()`를 쓰면 RDD의 키를 사용해서 데이터를 그룹화한다.
- `groupBy()`는 쌍을 이루지 않았거나 현재 키와 관계되지 않은 다른 조건을 써서 데이터를 그룹화하고자 하는 경우에 쓰인다.
- `cogroup()`는 함수를 써서 여러 RDD에 동일 키를 공유해 데이터를 그룹화할 수도 있다. 키에 대한 교집합을 구하기 위한 용도로도 쓰이며, 동시에 세개 이상의 RDD에 대해 작업도 가능하다.

#### 조인
- `innerjoin`은 양쪽 RDD에 모두 존재하는 키만이 결과가 된다.
- `leftOuterJoin()`을 쓰면 결과 RDD는 원본 RDD에 있는 각 키의 엔트리들을 가진다. 결과에서 각 키에 대한 값들 중에서 원본 RDD에 대한 값들은 튜플로 표시되며, 다른쪽 페어 RDD값들은 `Option`으로 표시된다.
- `rightOuterJoin()`은 `leftOuterJoin()`과 거의 같지만 키가 다른쪽 RDD에 존재해야 하며, 다른쪽 RDD가 아니라 원본 RDD에 `Option`표시된다.

#### 데이터 정렬
- 한번 데이터를 정렬하고 나면 이후에 데이터에 대한 `collect()`나 `save()`같은 함수 호출들은 결과가 정렬되어 나온다.

### 액션
- 기본적은 RDD의 액션들을 모두 사용할수 있다.

### 데이터 파티셔닝
- 분산 프로그램에서 통신은 비용이 매우 크므로 네트워크 부하를 최소화 할수있는 데이터배치는 프로그램 성능을 비약적으로 향상시킬수 있다.
- 스파크도 네트워크 비용을 줄이기 위해 RDD의 파티셔닝 제어 방법을 선택할 수 있다.
- 파티셔닝은 조인 같은 키중심의 연산에서 데이터셋가 여러번 재활용될 때만 의미가 있다.
- 스파크 파티셔닝은 모든 RDD의 키/값 쌍에 대해 가능하며, 시스템이 각 키에 제공된 함수에 따라 값들을 그룹화 하도록 한다.
- 스파크는 각 키가 어떤 노드로 전달되는지 같은 명시적인 제어를 제공하지 않지만, 어떤 키의 모음들이 임의의 노드에 함께 모여 있게 되는것은 보장해 준다.
- 같은 범위의 키들이 같은 노드에 모이도록 RDD를 범위별 파티셔닝을 수행할수 있다.
- 많은 스파크의 연산들은 결과 RDD에 알려진 파티션 정보를 자동적으로 포함한다.
- RDD가 어떻게 파티션될지 `partitioner`속성을 써서 결정할 수 있다.
- 스파크는 내부적으로 각각의 연산이 파티셔능에 어떤 영향을 주는지 파악하고 있으며, 데이터를 파티션하는 연산에 의해 만들어진 RDD에는 자동적으로 `partitioner`를 세팅한다.

#### 사용자 지정 파티셔너
- 스파크는 추가로 사용자에게 자체적인 `partitioner`객체를 만들어서 어떤 식으로 파티셔닝을 수행할지 튜닝이 가능하도록 해준다.
- (Java)사용자 지정 파티셔너를 구현하기 위해서는 `org.apache.spark.Partitioner`클래스를 상속하고 세개의 메소드를 구현해야 한다.
- `numPartitions: Int` : 생성할 파티션의 개수를 되돌려 준다.
- `getPartition(key:Any): Int` : 주어진 키에 대한 파티션 ID를 되돌려 준다.
- `equals()` : 동일값 객체 검사 메소드
- (Python) RDD.partitionBy에 추가 인자로 해시 함수를 만들어 전달해 주면 된다. 전달한 해시 함수는 다른 RDD와 id로 비교하게 된다. 만약 동일 파티셔너를 여러 RDD에 적용하고 싶다면 매번 lambda함수를 새로 만들지 말고 동일한 함수 객체를 전달하도록 한다.