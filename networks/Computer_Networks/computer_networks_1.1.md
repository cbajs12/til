## 1. 기초

### 요구사항
- 링크 : 컴퓨터의 직접적인 연결매체
- 노드 : 링크를 통하여 이어진 컴퓨터들
- 점대점 링크 : 한 쌍의 노드로 연결된 링크
- 다중 접속: 두개 이상의 노드가 단일 링크를 공유
- 회선 교환망: 주로 전화 시스템에 사용, 링크들 사이에 전용회선을 만든뒤, 보내는 노드가 이 회선을 통하여 연속된 데이터 비트를 목적지 노드에 보낼수 있게 함
- 패킷 교환망: 주로 컴퓨터 시스템에 사용, 저장-전달 방식을 사용하여, 완전한 패킷을 받아서 내부 메모리에 저장한 후 다음 노드에 전달
- 패킷(메세지): 블록화된 데이터를 지칭
- 독립된 네트워크(클라우드): 네트워크를 구성하는 내부노드(스위치)와 네트워크를 사용하는 외부노드(호스트)로 구성
- 인터네트워크(internet): 클라우드들이 서로 연결된 형태, 두 개 이상의 네트워크에 연결된 노드는 공통적으로 라우터(게이트웨이)를 중매기로 사용 `TCP/IP의 Internet과는 다르다`

- 주소: 노드를 나타내는 바이트 문자열
- 라우팅: 주소를 이용해 목적지 노드까지 메시지를 전달하는 방법을 체계적으로 결정하는 과정
- 유니캐스트: 한개의 목적지 노드에게만 메시지를 전달
- 멀티캐스트: 몇몇 그룹의 노드들에게만 전달
- 브로드캐스트: 모든 노드들에게 전달
- 다중화: 다중 사용자 간의 시스템 자원 공유를 위한 방법
- 역다중화: 다중화한 것을 원래의 흐름으로 분리하는 방법
- STDM: 다중화의 한 방법으로 시간을 동일한 크기의 조각으로 나누어 라운드 로빈 방식으로 각각의 흐름에 데이터를 보낼수 있는 기회를 제공
- FDM: 다중화의 한 방법으로 서로 다른 주파수로 각각의 흐름을 전송하는 방법
- 통계적 다중화: STDM과 FDM의 유휴 상태가 큰점, 최대 흐름수가 고정되고 사전에 알려져야하 하는 단점등을 극복하기 위한 방법, 유휴시간을 줄여 패킷교환의 효율성을 증대
- 요구/응답 채널: 한쪽에서 송신된 메세지는 반드시 반대쪽에서 수신되어야 하며, 한번 송신된 메세지는 오직 한번만 수신되어야한다. 프라이버시 보장과 데이터의 무결성이 보장되어야 한다.
- 메시지 흐름: 모든 메시지가 전송되는 것을 보장할 필요는 없다. 일부 수신이 되지 않더라도 이상 없이 동작하여야한다. 그러나, 프레임의 순서는 보장하여야 한다. 또한, 프라이버시와 무결성이 보장되어야한다.
