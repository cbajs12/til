# 애플리케이션

## 멀티미디어 애플리케이션
- RTP는 멀티미디어 애플리케이션에서 지원하는 타이밍 정보의 전송과 코딩 기법의 구별, 음성 및 영상과 같은 매체의 구별 등과 같은 공통된 기능들을 제공한다.
- RSVP는 네트워크의 자원 할당을 요청하여 애플리케이션에서 요구하는 QoS를 지원하는 데 사용된다.
- 멀티미디어 애플리케이션에서는 세션 제어 프로토콜이 필요하다. 예를 들어 인터넷 기반의 IP 전화를 사용할 때, 수신자에게 전화가 왔음을 알리는 어떠한 기법이 필요하다.

### 세션제어와 호 제어
- 비디오 스트림을 압축하는 방식, IP 주소, 포트 번호, 사용할 트랜스포트 프로토콜과 같은 종류의 정보를 전달하기 위해서 표준화된 포맷이나 프로토콜이 있다. 이것에는 SDP, SAP, SIP, SCCP가 있다.
- SDP와 SIP 프로토콜은 모든 세션 정보를 표준 포멧에 담아 알려진 멀티캐스트 주소로 보내는 것이다. SIP는 이넡넷 전화와 대화형 애플리케이션에 널리 사용된다.

#### SDP
- SDP에는 세션의 이름과 목적, 세션의 시작 시간과 정료 시간, 세션을 구성하는 미디어 종류(비디오, 오디오등), 세션을 수신하기 위한 구체적인 정보(멀티캐스트 주소, 트랜스포트 프로토콜등)들이 포함된다.
- SDP는 ASCII 포캣의 메시지를 사용하며, 각 라인들은 `<type> = <value>` 형식으로 구성되어 있다.
- HTML과 마찬가지로 SDP는 사용자가 읽기 쉽게 되어 있지만, 대신 기계가 분명하게 해석할 수 있도록 형식을 갖추고 있다.
- 각 정보 type들은 단일 문자로 정의된다. 예를 들어, v는 버전을 의미한다. 이것들은 SDP에서 버전에 따라 정의되고, 버전이 증가하면 세션 정보도 바뀌어야 한다.
- s는 세션 이름, i는 세션 설명, u는 세션 식별자 URI이다. e는 세션에 관련된 사람의 이메일 주소이다. c는 애플리케이션이 세션에 참가하는 데 필요한 기술적인 정보들을 담고 있으며, 이 세션에서 데이터 전송에 사용할 IP 멀티캐스트 주소를 제공한다. 세션의 참가자들은 이 주소를 통해 데이터를 수신할 수 있다. `m = <media> <port> <transport> <format>` 은 미디어 유형, 포트 번호, 트랜스포트 유형, 애플리케이션 포맷을 나타낸다.
- SDP를 사용하는 방법으로 SDP 메시지를 미리 정해진 멀티캐스트 주소로 보내어 멀티미디어 회의를 알려 줄 수 있다.

#### SIP
- SIP는 응답/요청 모델을 기반으로 하는 HTTP와 유사한 애플리케이션 계층의 프로토콜이다. SIP는 사용자의 위치(특정 사용자와 통신하기 위해 필요한 장치를 지정) /유용성(사용자가 참석하고자 하거나 참석할 수 있는 세션들을 지정) /능력(사용할 미디어 및 코딩 방법과 같은 사항 결정), 세션 셋업(참여자들이 사용할 포트 번호를 비롯한 세션 변수의 설정) /관리(호 전환과 같은 세션 정보의 전달과 세션 변수의 수정등의 기능)의 기능을 지원한다.
- SIP와 HTTP의 큰 차이점은 SIP가 사람대 사람의 통신에 사용된다는 점이다. 따라서 기계 위치가 아닌 각 사용자의 위치를 파악하는 것이 중요하다.
- 사용자가 자신에게 온 호출을 적절한 수준에서 관리할 수 있도록 SIP에서는 프록시 개념을 사용한다. SIP 프록시는 통화하고자 하는 상대방에게 초기화 요청을 하기 위해 우선 접속하는 곳 정도로 생각할 수 있다. 프록시는 전화걸기를 대신해 주기도 한다.
- 두명의 사용자가 연결을 위해 한 사용자가 세션을 초기화하는 경우 먼저 사용자의 도메인을 담당하는 로컬 프록시에 SIP 초기화 메시지를 보낸다. 이러한 초기화 메시지에는 SIP URI가 포함된다. 이것은 사용자에 대해서는 식별이 가능하지만 시간에 따라 사용자가 이동할 수 있으므로 그러한 위치 정보까지는 제공하지 못한다. 
- 프록시가 유저의 초기화 메시지를 받으면, SIP URI를 보고 이 메시지를 어느 도메인 프록시로 보내야 할지를 안다. 상대 프록시는 상대방의 이름으로 DB를 검색하여 상대방이 현재 메시지를 받을 수 있는 장치의 IP 주소 목록들을 찾아 낸다. 이때 장치는 여러 개가 될 수도 있다. 프록시는 찾아낸 장치들을 통해 상대방에게 메시지를 전달한다. 여러 개의 장치를 통해 메시지를 보내는 것을 `포킹`이라고 한다. 한꺼번에 모든 장치에 신호를 보낼 수 있지만, 순차적인 방법도 가능하다.
- 한 사용자가 상대방에게 보내는 초기화 메시지는 SIP invite 메시지라고 한다. invite 메시지가 사용자의 프록시 서버에 도착할 때, 이 프록시 서버는 상대방의 프록시 서버로 해당 메시지를 포워딩만 하는 것은 아니다. invite를 보낸 송신 측에게 응답 메시지까지 보낸다. HTTP에서처럼 모든 응답은 고유의 응답 코드를 가지고 있다. 예를 들어 호출 측의 프록시에 의해 에러 없이 수신되었다는 것을 나타내는 `100 trying`이 있다. 또한 invite 메시지가 상대방에 도착하면 상대방에게 알림과 동시에 `180 ringing` 메시지로 응답한다. 특히, `200 OK` 메시지는 상대방이 통신 연결을 수락했을 때, 보내지며 이걸 받은 송신측은 ACK를 다시 상대방으로 보낼 것이다. 이때부터 상호 간의 통화를 시작할 수 있다. 이 시점에서 양측은 상대방의 주소를 알기 때문에, 프록시 서버를 거치지 않고 바로 ACK를 보낸다.
- 그러므로 통화 내용은 프록시 서버를 통하여 전송되었던 메시지들보다 다양한 경로를 통하여 전송된다. 더욱이 프록시 서버 장애가 발생하더라도 통화는 문제없이 진행된다. 그리고 이때부터 프록시 서버는 더이상 통화에 관여하지 않는다. 한 쪽에서 통화를 종료하고자 할 때는 BYE 메시지를 보내고, 문제가 없다면 200 OK로 응답을 받을 것이다.
- 상대방은 최초 연결의 200 OK 메시지 속에 세션의 속성과 invite 메시지에서 제안합 옵션을 고려한 SDP 메시지를 전송한다. 이 방법으로 서로 간에 받아들을 수 있는 세션 매개변수들을 협상한다.
- invite 메시지를 위한 정보는 컴퓨터 상에 미리 설정되어 있거나 혹은 DHCP 서버로부터 획득할 수 있다. 상대방의 프록시 서버를 찾는 방법은 SIP 프록시 서버의 IP 주소를 반환하는 특별한 종류의 DNS룩업 과정을 통해 이루어 진다.
- 사용자는 SIP 등록 메시지를 자산의 도메인에 속한 레지스트라로 보냄으로써 위치 서비스를 등록할 수 있다. 이 메시지를 통해 레코드 주소와 접속 주소를 바인딩할 수 있다. 레코드 주소는 사용자에게 잘 알려진 주소인 SIP의 URI와 같다. 접속 주소는 사용자가 위치하고 있는 곳을 가리키는 서브 주소와 유사하다. 바인딩 작용은 자신의 도메인의 프록시 서버에서 일어난다.

#### H.323
- H.323은 패킷 네트워크 상에서 멀티미디어 통신을 위한 ITU의 주요 권고로서, 호 제어를 위한 H.225를 포함한 다른 많은 표준으로 구성되어 있다.
- H.323은 인터넷 전화에 보편적으로 사용되는 프로토콜이다. 호를 발생시키고 종료시키는 장치는 H.323 터미널이라 불린다.
- 이 터미널은 인터넷 전화 애플리케이션을 실행시키는 기기이다. 이 전자 기기는 네트워크 소프트웨어와 이더넷 포트로 구성된 일전의 전화기와 같은 장치이다. H.323 터미널은 다른 H.323 터미널과 직접 통신할 수 있지만, 게이트키퍼로 알려진 중계장치에 의해 호가 성립되기도 한다. 게이트키퍼의 기능에는 다양한 주소 형식 간을 변환하거나, H.323 애플리케이션이 사용하는 대역폭을 고나리하기 위해 동시에 수용할 수 있는 호의 수를 조절하기도 한다. H.323에는 H.323 네트워크와 다른 유형의 네트워크를 상호 연결하는 게이트웨이의 개념이 포함된다. 게이트키퍼가 제공하는 주요 기능 중의 하나는 터미널이 게이트웨이를 찾도록 도와주는 것이다. 호의 최종 목적지와 상대적으로 가까운 게이트웨이를 찾기 위해서 가능한 여러 경로 중 하나를 선택해야 한다. H.323 터미널이 일반전화기에 대하여 호를 생성할 경우, 게이트웨이는 실질적으로 H.323 호를 위한 종단점 역할을 하며 공중 전화망을 통하여 전달되어야 하는 신호 정보와 미디어 스트림을 적절히 변환해야 한다.
- H.323의 가장 중요한 부분은 H.245 프로토콜이다. H.245 메시지는 지원 가능한 수많은 오디오 코덱의 목록을 알려 주고, 호의 종단점은 자신이 지원하는 오디오 코덱의 목록으로 응답한다. 이러한 과정을 거쳐 두 종단 간에 지원할 수 있는 코딩 방법이 결정된다. 특히, H.245는 하나 이상의 미디어 스트림을 제공하기 위한 RTP와 RTCP에 의해 사용되는 UDP 포트 번호를 알려 주는 역할을 하기도 한다. 이 과정이 완료되면, 미디어 스트림을 전송하기 위한 RTP와 관련된 제어 정보를 전송하는 RTCP를 사용하여 호를 진행한다.

### 멀티미디어 애플리케이션을 위한 자원 할당
- RTP의 전송 계층 기능은 애플리케이션의 데이터 스트림을 전달하는 것이며, SIP와 H.323과 같은 세션 제어 프로토콜은 멀티미디어 애플리케이션의 통신을 초기화하고 제어하는 데 사용된다. 
- RTCP와 같은 프로토콜은 최대 노력 네트워크에서 제공할 수 있는 서비스 품질의 정보를 애플리케이션에 제공한다. RTCP는 패킷 손실률과 지연 특성에 대한 정보를 멀티미디어 애플리케이션에게 전달할 수 있다. 이러한 정보를 기반으로 하여 대역폭이 부족한 경우에, 애플리케이션은 낮은 비트 전송률 코덱으로 변경하여 사용한다. 그리고 손실률이 높을 경우에 정보 손실을 감지하거나 정정하는 데 필요한 부가적인 정보를 추가할 수 있는 코덱으로 변경할 수 있지만 이러한 방법은 사용하지 않는 것이 좋다. 왜냐하면 TCP에서 패킷 손실이 발생하였을 때 오히려 TCP의 윈도우 사이즈를 늘리는 것과 유사하다. 일반적으로 TCP에서는 혼잡을 회피하기 위해서는 TCP의 윈도우 사이즈를 줄여 네트워크 상에 존재하는 패킷의 양을 줄이고자 한다.
- 차별화된 서비스는 애플리케이션에게 확장 가능한 자원을 할당하는 데 사용된다. 멀티미디어 애플리케이션은 패킷을 생성한 후에 패킷의 IP 헤더 속에 DSCP를 설정하여, 향후 이 패킷이 적절한 서비스 품질을 제공받을 수 있도록 한다. 예를 들면, 음성 패킷의 DSCP를 EF로 설정하면 패킷의 전달 경로에 존재하는 라우터는 낮은 지연과 높은 우선순위 큐잉을 제공한다. SIP와 같은 호 신호 패킷은 AF로 종종 설정되며, 이러한 경우에는 최대 노력 트래픽과 구별되게 별도의 다른 큐에 저장되어 손실 위험을 줄일 수 있다. 물론 라우터와 같은 네트워크 장치들이 DSCP의 의미를 이해하고 그에 따른 적절한 조치를 수행할 수 있어야 한다.
- 일반적인 인터넷 라우터들은 모든 패킷에 대하여 최대 노력 서비스만을 제공하므로 DSCP를 무시한다. 그러나 기업 또는 회사 네트워크에서는 자체 네트워크에서 송수신되는 멀티미디어 트래픽의 원활한 처리를 위해서 DiffServ 기법을 사용한다. 인터넷 백본망으로 나가는 외부 연결 장치에 DiffServ를 사용함으로써, 인터넷에 접속된 일반 가정 사용자도 VoIP 혹은 다른 멀티미디어 애플리케이션의 서비스 품질을 개선시킬 수 있다. 이러한 기법은 많은 광대역 인터넷 접속 방식이 가지고 있는 비대칭성이라는 속성 때문에 효과적이다. 예를 들어, 외부 방향 링크가 내부 방향 링크보다 느린 경우에는, 외부 방향 링크의 DiffServ는 적절한 자원 할당을 수행하고 이를 통해서 지연과 손실에 민감한 애플리케이션에게 차별화된 품질 서비스를 제공할 수 있다. DiffServ에서 제공하는 차별화된 서비스 기법은 단순하기 때문에 애플리케이션에서 요구되는 서비스 품질을 모든 조건하에서 만족시키지 못한다.
- 일반적으로 많은 호(call)를 매우 좁은 네트워크 파이프 속에 억지로 넣기 보다는 연결 가능한 호만큼 처리하고 나머지 호들은 블로킹하는 것이 멀티미디어 애플리케이션의 입장에서는 더 낫다. 예를 들어, 두명이 동시에 저급 품질의 서비스를 받는 것보다 한 명이 최상의 품질을 한 명은 대기 상태로 만드는 것이 낫다.
- 네트워크에서 제공되는 품질에 영향을 받을 수 있는 애플리케이션은 일반적으로 수락 제어를 사용한다. 수락 제어는 애플리케이션에서 전송하는 트래픽을 처리하는 데 필요한 네트워크 자원이 충분하지 않으면, 애플리케이션에 No를 통보한다.
- RSVP와 같은 세션 제어 프로토콜은 No라는 수락 거절을 직접 통보하는 것과는 다른 수락 제어 옵션을 멀티미디어 애플리케이션에게 제공한다. SIP나 H.323 같은 세션 제어 프로토콜이 통화의 시작 시점에서 종단 노드와 중간 노드(프록시 혹은 게이트키퍼) 간의 정보 교환에 포함되어 전송되어 새로 요청된 통화에 충분한 자원을 할당할 수 없으면 No를 통보한다.
- Off-path 기반의 수락 제어 방식에서는 데이터를 송수신하는 경로에서 자원 할당에 대한 수락 제어 결정과정이 수행되지 않는다. 이에 대한 다른 대안으로는 on-path 기반의 수락 제어 방식이 있다. 이와 관련된 대푝적인 예가 IP 네트워크에서의 RSVP이다.
- 두 사람 간의 단순화 전화 통화를 고려하면, 먼저 자원 예약을 하기 전에 전화 통화에 필요한 대역폭을 알아야 한다. 즉 사용되는 코덱에 대한 정보를 알고 있어야 한다. 따라서 2대의 전화에서 사용되는 코덱에 대한 정보를 교환하는 데 사용되는 세션 제어 과정을 먼저 수행해야 한다. 일반적으로 세션 과정이 시작되는 것을 알리는 신호로는 상대방의 전화 벨을 울리는 것이다. 그러나 세션 과정을 처리하는 동안 수락 제어 과정이 중간에 수행되어 수락 제어 과정이 실패하는 경우에는 통화가 이루어지지 않기 때문에 세션 과정을 시작하는 시점에서 전화 벨을 울려서는 안된다.
- SIP 메시지는 전화와 전화간에 직접적으로 전송된다. 반면에, RSVP 메시지는 라우터가 요청된 호의 수락을 위하여 충분한 자원 할당이 가능한지 확인한다. 맨 처음 2개의 SIP 메시지는 코덱 정보의 교환을 위하여 전송되며, 이러한 메시지는 SDP 기반의 메시지 형태로 전달되고 사용 가능한 코덱 정보를 주고 받는 데 사용된다. PRACK는 예비 승인을 나타낸다. 이들 메시지가 교환된 후에 RSVP PATH 메시지를 전송하면 양방향 호간에 요구되는 자원 예약 과정이 시작된다. RSVP 메시지에는 요구되는 자원의 양이 기술되어 있다. 다음 단계로, 실질적인 자원 예약을 수행하는 RESV 메시지가 전송된다. 호 설정을 시작한 전화기에서 RESV 메시지를 받게 되면, 자원 예약의 완료를 알리는 SDP 메시지를 단일 방향으로 전송한다. 또한 수신 전화기가 송신 전화기로부터 SDP 메시지와 RESV를 받게 되면, 자원 예약의 완료를 알리는 SDP 메시지를 전송하고 이를 수신한 송신 전화기에서는 벨이 울린다. 그 이후는 SIP 신호 전송과 매체 흐름과정과 유사하게 동작된다.
