# 패킷 교환
- 직접 연결 네트워크들은 두가지 제한점이 있다. 첫 번째는 접속할 수 있는 호스트의 개수에 제한이 있다. 두 번째는 하나의 네트워크가 서비스할 수 있는 지역적인 범위에 한계가 있다.
- 컴퓨터 네트워크에서 호스트가 직접 연결되어 있지 않더라도 한 호스트에서 다른 호스트로 패킷을 이동할 수 있도록 해주는 것을 패킷 교환기(Switch)라고 한다.
- 이 Switch의 핵심 임무는 입력으로 도착한 패킷들을 적절한 목적지에 도달하도록 올바른 출력으로 포워드하거나, 스위치하는 일이다. 
- Switch가 해결해야 하는 주요 문제는 출력 단자들이 정해진 대역폭을 갖고 있다는 점, 도착하는 패킷들 중 어떤 특정 출력 단자로 보내져야하는 패킷의 수가 해당 출력 단자의 용량을 초과하게 되면 `경쟁`의 문제가 발생한다. 
- 스위치는 경쟁이 해결될 때까지 패킷들을 저장하는데, 이 작업이 너무 오랫동안 지속되면 버퍼 공간이 부족하게 되며, 결국 패킷을 버려야한다. 너무 자주 패킷이 버려지게 되면 교환기가 `혼잡`하게 되었다고 말한다.

## 스위칭 및 포워딩
- Switch는 링크를 서로 연결하여 보다 큰 네트워크를 구성하도록 해주는 장치이며, 복수 개의 입력과 복수 개의 출력을 가지는 장치로서, 입력된 패킷을 하나 또는 그 이상의 출력으로 전달한다.
- 점대점 링크, 버스, 링과 같은 토폴로지에 성형 토폴로지를 추가함으로써 네트워크를 확장한다.
- 성형 토폴로지의 장점: Switch가 고정된 숫자의 입출력 단자를 갖기 때문에 하나의 교환기에 연결될 수 있는 호스트의 수는 제한된다 하지만 여러 교환기를 서로 연결하면 큰 네트워크를 구성할 수 있다. 또한 점대점 링크를 사용하여 교환기에 연결되며, 넓은 지역에 걸친 네트워크를 구성하는 것이 가능하다. 마지막으로 새로운 호스트를 교환기에 연결하여 네트워크에 호스트를 추가하더라도, 기존에 연결되어 있던 호스트에게 제공되는 네트워크의 성능이 저하되지는 않는다.
- 성형 토폴로지의 마지막 장점은 공유매체 네트워크에서는 성립되지 않는다. 하지만 교환기가 충분한 총 처리량을 갖도록 설계되어 있다면 많은 호스트가 링크의 최고 속도로 전송하는 것이 가능하다.
- Switch는 링크의 집합에 연결되어 있고, 링크의 각각에 대해 링크의 다른 끝에 있는 노드와 통신하기 위해 적절한 데이터 링크 프로토콜을 수행한다.
- 스위칭(포워딩): 링크 중의 하나로부터 들어오는 패킷을 수신하고, 이들을 다른 링크로 전송하는 것이다. 네트워크 계층의 주요 기능이다.
- 포트: 링크의 입출력 단자를 의미한다.
- Switch는 패킷의 헤더에 있는 식별자를 보고 어느 출력 포트로 내보낼 것인지를 결정한다. 그것에는 여러 방법이 있는데 데이터그램(비연결성), 가상회선 그리고 소스 라우팅이 있다.
- 모든 네트워크에 공통되는 한 가지 점은 종단 노드를 식별하는 방법(주소)이 있어야 한다. 주소에서 유일한 요구조건은 네트워크의 어떤 두 노드도 같은 주소를 가지면 안된다는 것이다. 
- 포트를 식별하는 데는 두가지 방법이 있다. 한가지는 각 포트에 번호를 매기는 방법이고, 다른 하나는 포트가 향하는 노드의 이름을 통해 포트를 식별하는 방법이다.

### 데이터 그램
- Switch가 패킷을 어떻게 목적지로 전달할 것인가를 결정하는 데 충분한 정보를 모든 패킷이 포함하도록 하자는 것이다. 즉, 모든 패킷은 목적지의 완전한 주소를 포함한다.
- 패킷을 어떻게 포워딩할 것인지를 결정하기 위해 교환기는 포워딩 테이블(라우팅 테이블)을 참조한다.
- 라우팅: 패킷이 도착했을 때 포워딩(스위칭)할 수 있도록, 포워딩 테이블에 정확한 정보를 준비하는 배후의 작업들을 말한다.

#### 데이터그램 네트워크의 특징
- 호스트는 언제 어디서나 패킷을 보낼 수 있다. 포워딩 테이블에 정확히 기재되어 있다는 가정하에서, 어떤 패킷도 교환기에 도착하는 즉시 포워드될 수 있기 때문이다. 
- 호스트가 패킷을 보낼 때, 호스트는 네트워크가 패킷을 전달할 수 있는지 또는 목적지가 동작상태인지 알 방법이 없다. 
- 각 패킷은 같은 목적지로 보내진 이전의 패킷에 독립적으로 포워딩된다. 따라서, 호스트 A ->  호스트 B로 가는 연속되는 두 패킷이 완전히 다른 경로로 갈수 있다. 
- 교환기 또는 링크의 고장은, 고장난 지점을 돌아 다른 경로를 찾을 수 있고 해당 포워딩 테이블을 갱신할 수 있으므로, 통신에 심각한 영향을 미치지 않는다.

### 가상회선
- 연결성 모델이라고도 하는데, 발신지 호스트와 목적지 호스트 사이에 가상의 연결을 설정하는 것이 필요하다. 이를 위해서는 연결 설정과 데이터 전송의 단계가 필요하다.

#### 연결 설정
- 출발지와 목적지 사이에 있는 각 교환기들에 연결상태를 설정하는 것이 필요하다.
- 하나의 연결에 대한 연결상태는 해당 연결이 통과하는 각각의 교환기에 있는 `VC 테이블` 엔트리들로 구성된다.
- VC 테이블의 내용: 연결에 속하는 패킷들의 헤더 안에 실려 있고, 교환기에서 연결을 착오 없이 식별하는데 사용되는 회선 식별자(VCI), 해당 VC의 패킷이 도착하는 입력 인터페이스, 해당 VC의 패킷이 교환기를 빠져나가는 출력 인터페이스, 나가는 패킷들에 사용될 VCI
- 하나의 엔트리는 지정된 입력 인터페이스에 패킷 하나가 도착하고 그 패킷이 헤더에 지정된 VCI 값을 가지고 있으면, 지정된 출력 VCI값으로 VCI를 바꾸고 지정된 출력 인터페이스로 패킷을 전송한다.
- 교환기에 도착하는 패킷들의 VCI와 그들이 도착하는 인터페이스의 조합이 가상 회선을 유일하게 식별한다. 
- VCI는 전역적으로, 즉 네트워크 전체에서 의미를 가지는 연결에 대한 식별자일 필요는 없으며, 해당 링크에서만 의미를 가진다. 즉, 링크에 국한된 영역을 가진다. 새로운 연결이 생성될 때마다 연결이 지나갈 각 링크에서 해당 연결에 대한 새로운 VCI를 할당하여야 한다. 이때, 각 링크에서 결정되는 VCI는 기존의 연결들이 해당 링크에서 현재 사용되지 않는 것이어야 한다.
- 연결상태를 설정하는 데는 크게 두 종류의 접근 방법이 있다. 첫째는 네트워크 관리자가 상태를 구성하도록 하는 것이며, 이 가상 회선은 영구적이다. 관리자에 의하여 삭제 될수 있다. 따라서 `PVC`이라는 것은 오랫동안 지속되는 VC로 생각할 수 있다. 다른 방법에서는 호스트가 네트워크에게 상태를 설정하도록 메시지를 보낼 수 있다. `시그널링`이라고 부르며, 결과로 생겨나는 가상 회선은 스위칭이 되었다고 한다. 스위칭된 가상 회선(SVC)의 두드러진 특징은 네트워크 관리자의 관여 없이 호스트가 동적으로 VC를 설정하고 삭제할 수 있다는 점이다.
- 가상 회선의 장점중 하나는 호스트가 데이터를 보내도 된다는 것을 아는 시점에서는 네트워크에 관해 많은 것을 알수 있다. 수신자로의 경로가 실존한다는 것, 수신자가 데이터를 받으려 하는것등이다.
- 홉 단위의 흐름제어: 가상 회선이 초기화될때, 각 가상 회선에 대해 버퍼를 할당한다. 가상 회선 상의 각각의 노드 쌍 사이에는 슬라이딩 윈도우 프로토콜이 수행되며, 이 프로토콜은 송신 노드가 수신노드에 할당된 버퍼를 넘치게 하지 않도록 하는 흐름제어를 추가하여 수행한다. 연결 요구 메시지가 처리될 때 어떤 노드가 충분한 버퍼를 갖고 있지 못하다면 회선은 거절된다. 이러한 세가지 작업을 수행하면서 각 노드는 회선을 따라 도착하는 패킷을 보관하는 데 필요한 버퍼를 확실히 준비할수 있다.
- 데이터그램 네트워크는 연결 설정 단계가 없고 각 교환기가 각각의 패킷을 독립적으로 처리하기 때문에, 데이터그램 네트워크가 자원을 어떻게 의미있게 할당하는가는 확실하게 드러나지 않는다. 대신 각각의 도착하는 패킷은 다른 패킷과 버퍼 공간을 놓고 경쟁하게 된다.
- 가상 회선 기술의 예로는 프레임 릴레이와 ATM이 있다.
- 많은 네트워크 공급자들이 프레임 릴레이 PVC 서비스를 제공한다. 프레임 릴레이를 이용해서 VPN을 구축하기도 한다.
- 수 많은 교환기의 VC 테이블을 정확히 구성하는 작업은 과도한 부담이 된다. 따라서 시그널링은 영구적인 VC를 설정할때도 사용된다.
- 시그널링 작업

#### 주의점
- 호스트 A는 첫 번째 데이터 패킷을 보내기 전에 연결 요구가 네트워크의 먼 곳에 갔다가 되돌아오는 것을 기다려야한다. 따라서 가상 회선 네트워킹에서는 데이터를 보내기 전에 최소한 한번의 왕복지연시간동안의 지연이 있다,
- 연결 요구는 호스트B의 주소 전체를 포함해야하는 반면, 각 데이터 패킷은 하나의 링크상에서만 유일한 작은 식별자만을 포함한다. 따라서 헤더에 의한 패킷당 부하는 데이터그램 모델에 비해 줄어든다.
- 연결 내의 교환기 또는 링크가 고장이 나면 연결은 끊어지며, 새로운 연결이 설정되어야 한다. 또한, 교환기들 내의 테이블 저장 공간을 풀기 위해서 이전의 연결을 해지시켜야 한다.
- 교환기가 어느 링크로 연결 요구를 포워딩시킬 것인지를 결정하는 방법에 관해서는 윤곽만 언급한다. 이 문제는 라우팅 알고리즘이 필요하다.

### 소스 라우팅
- 네트워크를 통해 패킷을 스위칭하는 데 필요한 네트워크 토폴로지에 관한 모든 정보가 발신지 호스트, 즉 소스에 의하여 제공된다는 점이다.
- 각 교환기의 각 출력 포트에 번호를 할당하고 패킷의 헤더에 그 번호를 넣는 것이다. 이때 스위칭 기능은 간단하다. 입력으로 도착하는 각 패킷에 대해 교환기는 헤더에 있는 포트번호를 읽고 패킷을 해당 출력 포트로 전송한다.
- 그러나 네트워크에서 송신과 수신 호스트 사이에는 일반적으로 두 개 이상의 교환기가 있기 때문에, 패킷의 헤더는 경로상의 모든 교환기가 어느 출력 포트로 패킷을 보내야 할 지를 결정하는 데 필요한 정보를 충분히 담을 수 있어야 한다.
- 해결하는 한 방법은 포트의 순서 목록을 헤더에 두고, 경로상의 다음 교환기에 해당되는 번호가 항상 목록의 맨 앞에 오도록 목록을 회전시키는 방법이다.
- 소스 라우팅은 데이터그램 및 가성 회선 모두에서 사용될 수 있다.
- 소스 라우팅은 엄격한 방식과 느슨한 방식으로 나뉜다. 엄격한 방법은 경로상의 모든 노드가 규정되어야 하는 반면, 느슨한 방식은 지나가야 하는 노드들의 집합만을 규정하고 한 노드에서 다른 노드로 어떻게 가는지는 규정하지 않는다.

#### 주의점
- 호스트 A는 경로상의 모든 교환기에 대해 정확한 경로를 표시하는 헤더를 구성할 만큼 네트워크 토폴로지에 관해 알고 있어야 한다는 점이다.
- 헤더가 얼마나 커야 하는지 예측할수 없다.
- 몇가지의 변형이 있다. 헤더를 회전시키는 대신 교환기를 거치면서 제거하는 방법이 있다. 또 다른 것은 헤더가 다음 포트에 해당하는 현재값에 대한 포인터를 갖고 있도록 하는 것으로, 각 교환기는 헤더를 회전시키는 대신 포인터값을 갱신하면 되므로 구현이 효율적일 수 있다.