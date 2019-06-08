# 직접 링크 네트워크

## 신뢰성 있는 전송
- 전송중에 변질된(손실된) 프레임은 버려지고 이것을 복구하는 방법이 필요하다.
- 대부분의 경우, 긍정응답(ACK)와 타임아웃이라는 두 개의 방법의 조합으로 이루어진다.
- ACK는 프로토콜이 앞서 보낸 프레임을 받았다고 상대방에게 알리기 위해 되돌려 보내는 작은 제어 프레임이다.
- 제어 프레임: 데이터는 포함하지 않고 헤더만 가진 프레임
- 프로토콜에 따라 ACK를 반대방향으로 보낼 데이터 프레임에 피기백 할수도 있다.
- 송신자가 ACK를 수신하면 프레임이 성공적으로 전달되었다는 것을 알게된다. 송신자가 적당한 시간이 지나 타임아웃되면 원래의 프레임을 재전송한다.
- 상기의 정책을 자동 반복 요구(ARQ)라고 한다.

### 정지 대기
- 가장 간단한 ARQ 방식은 정지 대기 알고리즘이다.
- 송신자는 한 프레임을 전송한 뒤 다음 프레임을 전송하기 전에 ACK를 기다린다. 타임아웃되면 원래의 프레임을 재전송한다.
- ACK 송신 지연등의 이유로 프레임이 중복되어 전달될 경우가 있으므로, 정지 대기 프로토콜 헤더에는 대개 1비트의 순서번호를 포함하여 해결한다. (송신자가 0 헤더 프레임을 재전송하여 보내면 프레임 1이 아닌 프레임 0의 두번째 복사본이라는 것을 인식하여 복사본을 무시한다.)
- 단점: 어떤 시간에 오직 한개의 프레임만을 전송한다는 것.

### 슬라이딩 윈도우
- 송신자가 송신가능한 링크의 용량만큼 프레임을 차례로 보내는 것이다.

#### 송신자의 경우
- 송신자는 SeqNum(순서번호)를 각 프레임에 할당한다.
- SWS(송신 윈도우 크기)는 송신자가 보낼 수 있는 미결된(ACK 받지 않은) 프레임 수의 상한선을 나타낸다.
- LAR은 가장 최근에 수신된 ACK의 순서번호를 나타내는 필드다.
- LFS는 마지막으로 송신한 프레임의 순서번호를 나타내는 필드다.
- 송신자는 `LFS - LAR <= SWS`를 유지하며 동작한다.
- ACK가 도착하면, 송신자는 LAR필드를 LFS쪽으로 이동시켜 SWS를 줄인다. 이럼으로써, 하나의 프레임을 송신할 수 있는 여유가 생긴 것이다.
- 송신자는 전송한 각 프레임에 타이머를 부착하고 ,ACK가 수신되기 전에 타이머가 종료되면 프레임을 재전송한다. 송신자는 프레임이 응답될 때까지 재전송을 준비하여야 하므로 SWS만큼의 프레임을 버퍼에 저장할 준비를 해야한다.

#### 수신자의 경우
- RWS(수신 윈도우 크기)는 수신자가 받아들이는 프레임 수의 상한성을 나타낸다.
- LAF는 받아들일 수 있는 최대한의 프레임의 순서번호를 나타내는 필드이다.
- LFR은 다음에 받을 것으로 예상되는 프레임 중에서 순서번호가 최소인 것을 표시하는 필드다.
- 수신자는 `LAS - LFR <= RWS`를 만족하며 동작한다.
- 순서번호를 가진 프레임이 도착하면, `SeqNum <= LFR`이거나 `SeqNum > LAF`이면, 프레임은 수신자 윈도우 밖에 해당하므로 버린다. 반대로 영역내에 순서번호이면, 받아들인다.
- SeqNumToAck는 ACK를 하지 않은 프레임중에서 큰 순서번호를 가리키게 하며, 이보다 작은 순서번호를 갖는 프레임은 모두 수신된 것을 의미한다.
- 누적응답: 수신자는 높은 순서번호를 갖는 프레임이 수신되었어도 SeqNumToAck에 대해서 ACK를 보낸다. 이후, `LFR=SeqNumToAck`로 하고 `LAF = LFR + RWS`로 조정한다.
- 선택적 응답: 수신자가 순서대로 받은 프레임중 최고 번호 프레임에 ACK를 보내는 것이 아니라 받은 프레임 각각에 대해 ACK를 보내는 것이다. 송신자에게 많은 정보를 제공하지만 구현의 복잡도는 올라간다.
- SWS는 `지연신간 * 대역폭`에 대하여 쉽게 계산된다.
- RWS는 의의로 결정할 수 있다. 흔히 `RWS=1`로 하여 수신자가 순서가 맞지 않는 프레임은 버퍼에 보관하지 않는 경우와, `SWS=RWS`로 하여 수진자가 송신자가 보낸 어떤 프레임도 버퍼에 보관하는 경우가 사용된다. `RWS > SWS`는 많은 프레임이 순서가 바뀌어 도착하는 것은 불가능 하기 때문에 의미가 없다.

#### 유한한 순서번호와 슬라이딩 윈도우
- 유한한 크기의 헤더 필드때문에 순서번호는 재사용할 수 밖에 없다.
- 가능한 순서번호의 개수가 전송 진행중인 상태에 잇는 프레임의 최대 개수보다 커야만 한다.
- RWS=SWS 상황일때, 송신 윈도우의 크기는 사용 가능한 순서번호 개수의 반을 넘어서는 안된다. `SWS < (MaxSeqNum +1)/2`

#### 프레임 순서와 흐름제어
- 슬라이딩 윈도우 프로토콜은 다른 역할로도 사용될 수 있다. 신뢰성 없는 링크를 통해 프레임을 신뢰성 있게 전달 하는 것과 프레임이 전송되는 순서를 유지하는 것(수신자는 순서가 바뀐 프레임을 상위 단계에 넘기지 말고 버퍼에 저장한다.) 그리고 수신자가 송신자의 전송속도를 조절할 수 있도록 하는 흐름제어(송신자가 수신자가 처리할 수 있는 것보다 많은 데이터를 전송하는 것을 방지)를 지원하는 것이다.
- 문제의 분리 원칙에 따라 신뢰성 있는 전달, 순서를 맞춘 전달 및 흐름제어를 링크 단계에서 해야할 것인지는 생각해볼 문제이다.

### 동시 논리 채널
- 간단한 정지대기 알고리즘을 사용하지만 파이프의 용량을 계속 채우는 채널
- 주어진 링크로 보내지는 프레임의 순서가 유지되지 않는다는 점이고 흐름제어에 관해서도 영향을 주지 않는다.
- 여러 논리 채널을 하나의 점대점 링크에 다중화하고, 각각의 논리 채널에 정지대기 알고리즘을 적용하는 것이다.
- 논리 채널로 전송되는 프레임 사이에는 아무런 관계도 존재하지 않는다. 이에 따라 여러 논리 채널 각각에 다른 프레임을 전송시킬 수 있으므로 송신자는 링크를 가득 채워 나갈수 있다.
- 송신자는 각 채널에 대해 3비트의 상태정보를 유지한다. 그중 한개의 1비트로 채널이 현재 전송중인지를 파악하고 1 비트의 순서번호는 채널로 프레임을 전송할 때 사용되며, 다른 1비트는 채널로 도착하게 될 프레임의 순서번호를 나타낸다. 노드가 하나의 프레임을 전송할 때는 비어있는 채널중 가장 낮은 번호의 채널을 사용하며, 비어있는 것이 없으면 정지대기로 작동한다.

## 이더넷
- CDMA/CD 방식 근거리 네트워크의 한 방식
- 여러 노드가 공유 링크를 통해 송신과 수신을 하는 다중 접근 네트워크
- Carrier sense: 모든 노드가 링크가 사용 중인지 유휴 상태인지를 검사
- Collision detect: 노드가 전송할 때 충돌검사도 같이 하여 전송 중인 프레임이 다른 노드가 보낸 프레임에 의하여 간섭을 받았을때 감지할수 있다는 것을 의미
- 호스트는 이더넷 세그먼트 탭에 트렌스시버로 연결하고 트렌스시버는 호스트에 꽂혀있는 이더넷 어뎁터에 연결된다.
- 트랜스시버: 회선이 유휴인 상태를 감지하고 호스트가 전송할때 신호를 보낸다. 
- 이더넷 프로토콜을 구성하는 모든 논리회로는 트랜스시버가 아닌 어댑터에서 구현된다.
- 여러개의 이더넷 세그먼트는 중계기를 이용해서 합쳐질 수 있다. 두 호스트 사이에는 5개 이상의 중계기가 있어서는 안된다.
- 중계기(repeater): 디지털 신호를 중계하는 장비
- 이더넷은 맨체스터 인코딩 방식을 지원하며, 하나의 이더넷은 최대 1024 호스트까지 지원한다.
- 호스트에 의하여 이더넷으로 송출된 모든 신호는 전체 네트워크로 방송된다. 신호는 양방향으로 전파되며, 중계기는 모든 출력 링크로 신호를 전송한다. 각 세그먼트 끝에 붙어있는 `터미네이터`는 신호를 흡수하여 반사된 신호가 되에 오는 신호에 간섭을 일으키는 것을 방지한다.
- 허브(hub): 다중 중계기로 여러개의 점대점 세그먼트를 통하여 네트워크를 구성할 수 있다. 복수개의 100Mbps 이더넷 세그먼트들도 허브에 의하여 연결될 수 있지만, 1000Mbps는 허브로 연결될 수 없다.
- 한 호스트가 전송하는 데이터는 이더넷 상의 모든 다른 호스트로 전달된다.
- 모든 호스트들이 같은 링크에 대한 접근을 위해서 경쟁하여, 모든 호스트들이 충돌 영역에 속해있다.

### 접근 프로토콜
- 매체 접근 제어(MAC): 이더넷 링크에 대한 접근을 제어하는 알고리즘, 네트워크 어댑터에서 하드웨어로 구현

#### 프레임 형식
- 모든 이더넷 프레임은 512비트(64바이트) 길이 이상이어야 한다. 최소길이 64바이트는 14바이트 헤더와 46바이트 데이터 및 4바이트 CRC를 합친것이다.
- 프리앰블:  64비트로 구성되며, 0과 1이 교대로 연속되므로 수신자가 신호에 동기화할 수있도록 한다.
- 주소: 48비트로 구성되며 발신지와 목적지 호스트의 주소 필드가 각각 존재한다.
- 패킷 타입: 역다중화 키로 사용, 프레임이 많은 상위 프로토콜중에서 어느 프로토콜로 전달되어야 하는 것인지를 식별
- 각 프레임은 1500바이트까지 데이터를 담을 수 있다. 또한, 호스트는 적어도 46바이트의 데이터를 포함하도록 해야 하는데 프레임이 충돌을 검출하기에 충분할 만큼 길어야 하기 때문이다.
- CRC: 오류 검출용이고 32비트 크기이다.
- 포스트앰블: 8비트로 구성되며, 프레임의 끝을 나타낸다.
- 이더넷은 비트 중심의 프레이밍 프로토콜이며, 송신자의 어댑터는 프리앰블과 CRC 및 포스트앰블을 붙여 전송하고 수신자의 어댑터는 이들을 제거한다.

#### 주소
- 모든 이더넷 호스트는 유일한 이더넷 주소를 가진다. 이 주소는 어댑터가 ROM에 심어진 상태로 가지고 있다.
- 주소는 콜론으로 구분되는 6개 숫자 그룹으로 표현된다. 각 숫자 그룹은 6바이트 주소중 한바이트에 해당되며, 각 숫자는 4비트니블에 해당되는 두 자리의 16진수 숫자로 표시된다.
- 각각의 어댑터가 유일한 주소를 갖도록 하기위해 이더넷 장비 제작사는 서로 다른 숫자를 할당받으며, 이것을 자신이 제작하는 모든 어댑터의 주소 앞에 붙인다.
- 이더넷을 통해 전송되는 프레임은 네트워크에 연결된 모든 어댑터에 의하여 수신된다. 각 어댑터는 자신의 주소로 온 프레임을 인식하고, 이것을 호스트에 전달한다.
- 이더넷은 모든 비트가 1인 주소를 `방송` 주소로 취급하고 이것을 모두 호스트로 전달한다. 또한 첫번째 비트가 1이면서 방송주소가 아닌것은 `멀티캐스트`주소라 판단하고 호스트가 설정한 멀티캐스트 주소만을 받아들인다.

#### 전송 알고리즘
- 1-persistent: 어댑터가 송신할 프레임을 갖고있고 회선이 비어있으면 곧바로 전송한다. 그러나 프레임은 있지만 회선이 사용중일때는 회선이 빌때까지 대기한다. 즉, 어댑터가 회선이 비게 될 때마다 1의 확률로 전송하게 된다.
- p-persistent: 회선이 비게된 후에 p의 확률로 전송을하고 q(1-p)의 확률로 전송을 미룬다. p가 1보다 작은 이유는 복수의 어댑터가 사용 중인 회선이 비기를 기다릴수 있기 때문이고 모든 어댑터가 동시에 전송을 하는것은 바람직 하지 않기때문이다.
- 각 어댑터들이 동시에 프레임을 보내서 네트워크에서 중복이 발생하는 경우 충돌이 발생했다고 한다.
- 어댑터가 충돌이 발생하는 것을 감지하면 32비트의 `재밍` 순서를 확실히 보내고 전송을 중단한다. 따라서 충돌이 난 경우 전송기는 64(프리앰블) + 32(재밍 순서)비트를 최소한 송신한다.
- 어댑터가 `꼬마 프레임`이라고 불리는 96비트만을 보내는 경우 두 호스트가 서로 가까이 붙어있는 경우이다.
- 방금 보낸 프레임이 다른 프레임과 충돌하지 않았다는 것을 확신하기 위해서는 전송기는 512비트까지 송신을 할 필요가 있다. 그래서 모든 이더넷 프레임은 512비트(64바이트) 길이 이상이어야 한다는 것이다.
- 이더넷의 길이가 2500m로 제한되는 것과 최대 4개의 중계기를 사용해야하는 제한에서는 512비트의 프레임이 최악에 이더넷의 끝에서 충돌난 경우를 파악하기 적절한 크기이기 때문이다 .
-  어댑터는 충돌감지 후에 전송을 중단하고 일정 시간 대기후 다시 전송을 한다. 전송이 실패할 때마다 기다리는 시간을 두배로 늘린다(지수 백오프 방식). 어댑터는 대개 16번까지 재전송을 시도한다.

### 특징들
- 이더넷은 부하가 작은 경우에 가장 잘 동작한다. 과부하의 경우 너무 많은 네트워크 용량이 충돌에 의해 낭비된다.
- 대부분의 이더넷은 표준 허용보다 보수적으로 사용된다. 최대 1024개를 연결할 수 있지만 200개가 상한으로 사용된다.
- 이더넷 어댑터가 흐름제어를 하지 않아도 호스트가 종단간에서 흐름제어를 제공한다.