# 혼잡 제어와 자원 할당

## 큐잉관리 기법
- 각 라우터는 패킷이 전송되기 전에 기다리는 동안 어떻게 버퍼링될 것인지에 관한 큐잉관리 기법을 구현해야만 한다.
- 큐잉 알고리즘은 대역폭과 버퍼 공간을 할당하는 것으로 이루어져 있다. 큐잉 알고리즘은 또한 패킷이 전송되기 위해 얼마나 기다리는지에 따라 지연시간에 직접적인 영향을 미친다.

### FIFO
- 라우터에 처음 도착한 패킷이 맨 먼저 전송된다. 패킷이 도착했을때, 큐가 가득 차 있다면 라우터는 패킷을 폐기한다. 이것을 `tail drop`이라 하는데 FIFO의 꼬리 끝에 도착한 패킷이 버려지기 때문이다.
- FIFO는 scheduling discipline이다. 즉, 패킷이 전송되는 순서에 따라 결정된다. Tail drop은 폐기 정책인데 이것은 어떤 패킷이 버려지는가를 결정한다. FIFO와 tail drop은 각각 스케줄링 분야와 폐기 정책의 간단한 보기로 평범한 큐 구현인 하나의 묶음으로 보이기도 한다.
- FIFO with tail drop은 인터넷 라우팅에서 가장 많이 사용되고 모든 큐잉 알고리즘에서 가장 간단한 것이다. 이런 간단한 큐잉의 접근은 네트워크의 맨 가장자리에 혼잡 제어 및 자원 할당에서의 모든 책임을 미룬다. 그러므로 인터넷의 혼잡 제어는 라우터에서 도움이 없이 TCP에서 혼잡 상태를 감지하고 대응하는 모든 책임을 진다.
- 단순한 FIFO 큐잉의 또 다른 변형은 우선순위 큐이다. 기본적인 생각은 발신지에서 패킷을 보낼 때 각 패킷마다 우선순위를 부여하는 것이다. 예를 들면, IP Type of Service(TOS) 필드를 사용하는 경우이다. 각 라우터는 패킷의 우선순위에 따라서 다중 FIFO 큐를 구현한다. 항상 라우터는 다음 순위의 큐로 진행하기 전에 가장 높은 우선순위 큐에 있는 패킷을 먼저 전송하게 되며, 각 우선순위에 해당하는 큐에서는 패킷을 FIFO 방식으로 관리한다. 이런 개념은 최대 노력 전달 모델의 기본적인 출발점이 된다. 그러나 이러한 기법은 특정 우선순위 계층에 대해서 어떤 보장을 하는 등의 일은 하지 않는다. 단지 높은 우선순위의 패킷이 전달 순서에 맨 앞줄에 끼어들 수 있도록 할 뿐이다.
- 우선순위 큐잉의 문제점은 우선순위 큐가 다른 나머지 큐들에게 기아 현상을 줄 수 있는 것이다. 즉, 우선순위 큐에 적어도 하나의 우순선위 패킷이 존재하는 한 하위순위 큐는 서비스를 받지 못한다. 이러한 이유로, 큐에 끼어드는 우선순위 흐름의 양을 엄격히 제한할 필요가 있다. 
- 이것은 사용자가 마음대로 우선순위를 높이지 못하다록 하는 기법이 전제되어야 한다. 즉, 우선순위를 높이지 못하게 막든지 사용자에게 부과요금을 부과하는 형식이 있어야 할 것이다. 이를 수행하기 위한 한 가지 방법은 경제 원리를 이용하는 것인데, 즉 네트워크는 낮은 우선순위의 패킷보다 높은 우선순위의 패킷을 전달하는 것에 더 많은 요금을 부과하는 것이다. 그러나 인터넷과 같은 분산된 환경에서 이러한 기법을 구현하는 것에 대해서는 특별한 방안이 없다.
- 인터넷이 사용되는 우선순위 큐에서 하나의 상황은 가장 중요한 패킷들을 보호하는 것이다. 전형적으로 라우팅 업데이트는 토폴로지 변화 이후에 라우팅 테이블을 안정화시키기 위해서 필수적이다. 종종 그러한 중요한 패킷들에는 특별한 큐가 존재한다. 그 패킷들은 IP 헤더 상의 TOS 필드에 의해서 인식된다.

### FQ
- FIFO 큐잉의 주된 문제는 패킷을 보낸 발신지를 구별하지 않으며, 또한 패킷이 속한 흐름에 따라서 각 패킷을 구분하지 않는다는 점이다. 이것은 두 가지 단계에서 문제가 된다. 먼저 발신지에서 완전히 구현된 어떤 혼잡 제어 알고리즘이 라우터로부터 매우 적은 도움만으로도 혼잡 상태를 적절히 조절할 수 있다는 것이 명백하지 않다는 점이다. 또 다른 단계에서는 전체 혼잡 제어 기법이 발신지에서 구현되고, FIFO 큐잉이 이러한 기법을 발신지가 어떻게 잘 지키는가를 감시하는 수단을 제공하지 않기 때문에, 불법적으로 규칙을 지키지 않는 발신지가 네트워크 용량 가운데 임의의 커다란 부분을 차지하는 것이 가능해진다는 점이다.
- 인터넷에서 본다면, 어떤 애플리케이션이 TCP를 사용하지 않을 때 결과적으로 종단 간 혼잡 제어 기법을 무시하는 것은 분명 가능하다. 이러한 애플리케이션은 인터넷의 라우터를 자신이 보낸 패킷으로 홍수가 나게 할 수 있으며, 따라서 다른 애플리케이션의 패킷이 폐기되도록 할 수 있다.
- FQ는 이러한 문제를 처리하기 위해서 제안된 알고리즘이다. FQ의 개념은 라우터에 의해서 현재 조절되는 각 흐름에 대해서 독집적인 큐를 운용하는 것이다. 그런 다음 라우터는 이들 큐를 라운드 로빈 방식으로 서비스 하게 한다. 한 흐름이 너무 빨리 패킷을 보낸다면 자신의 큐가 가득 차게 된다. 이렇게 큐가 특정 길이까지 차게 되면, 이후에 도착하는 추가적인 패킷은 폐기된다. 이런식으로 운용됨으로써 어떤 한 발신지는 다른 흐름에게 피해를 주면서 네트워크 용량에서 자신이 할당받은 용량을 임의로 증가시키지 못하게 된다.
- FQ에서는 라우터가 어떤 발신지에게 자신의 상태에 대한 어떠한 정보도 주지 않는다는 것이며, 발신지가 얼마나 빨리 패킷을 보내는가에 대한 어떤 종류의 제한도 하지 않는다는 점이다. 다시 말해서, FQ는 종단 간 혼잡 제어 기법과 함께 쓰이기 위해서 설계되었을 뿐이라는 점이다. 단지 불법적으로 규칙을 지키지 않는 발신지가 종단 간 알고리즘을 충실하게 따르는 다른 발신지의 데이터 전달을 방해하지 않도록 트래픽을 분리할 뿐이다. FQ는 또한 제대로 규칙을 지키는 일련의 흐름에 대해서 혼잡 제어 기법에 의하여 공정성을 획득될 수 있도록 한다.
- 라우터에서 처리되는 패킷이 반드시 같은 길이를 가지지 않는다. 실제로 외부로 나가는 링크의 대역폭을 공정한 방식으로 할당하기 위해서는 패킷의 길이를 고려해야 할 필요가 있다. 
- 이것을 위해 `bit-by-bit 라운드 로빈`이 필요하다. 즉, 라우터가 흐름 1로부터 한 비트를 전송하고 나면 다시 흐름 2로부터 한 비트를 전송하는 식이다. 사실 다른 패킷의 비트를 사이사이 끼워 넣어 전송하는 것은 가능하지 않다. 따라서 FQ 기법은 먼저 bit-by-bit 라운드 로빈을 사용하여 보내진 것 처럼 여기고 어떤 주어진 패킷의 전송이 끝나는 때를 결정하게 된다. 그런 다음, 전송할 일련의 패킷을 정렬하기 위해서 앞에서 결정된 시간을 사용함으로써 위에서 말한 bit-by-bit 전송을 시뮬레이션 하게 된다.
- bit-by-bit 라운드 로빈의 알고리즘을 이해하기 위해서는 하나의 흐름이 가지는 행동을 고려해야 한다. 한 클럭마다 모든 활동 중인 한 흐름의 한 패킷에 있는 한 비트가 전송된다고 하면, 이때 이 흐름에 Pi는 패킷 i의 길이를 나타내고, Si는 라우터가 패킷 i를 보내기 시작할 때의 시간이며, Fi는 라우터가 패킷 i를 보내는 것을 끝마치는 시간이라고 하자. Pi가 패킷 i를 보내는 데 걸린 클럭의 수로 표현된다면, 쉽게 `Fi = Si + Pi`라는 것을 알수 있다.
- 패킷 i를 보내는 것을 언제 시작하는 지는 라우터가 이 흐름으로부터 패킷 i-1을 보내는 것을 끝마친 후에 패킷 i가 이 라우터에 도착하는가 아니면 그 후에 도착하는가에 달려 있다. 끝마치기 전에 도착한다면 패킷 i-1의 마치막 비트가 전송된 후 즉시 패킷 i의 처음 비트가 전송된다. 반면에 패킷 i가 도착하기 전에 라우터는 이미 패킷 i-1의 전송을 끝냈을 수도 있다. 이는 곧 이 흐름에 대한 큐가 비어 있는 어떤 주기의 시간이 있었음을 의미하며, 라운드 로빈 방식은 이 흐름으로부터 다른 어떠한 패킷도 보내지 않았음을 의미한다. 우리가 Ai를 패킷 i가 라우터에 도착한 시간이라고 가정한다면, `Si = max(Fi-1, Ai)`가 된다. 따라서 `Fi - max(Fi-Ai) + Pi`의 계산을 할 수 있다.
- 여러 개의 흐름이 있는 상황을 가정하여, Ai를 결정하는 방법을 찾아 보자. 패킷이 언제 도착하는지 그 시간을 읽을 수 없다. bit-by-bit 라운드 로빈에서는 하나의 클럭에 모든 흐름이 하나의 비트를 처리해야 한다. 그래서 흐름이 많은 곳에서는 긴 클럭이 필요하다. 예를 들어 만약 n개의 흐름이 있는 경우에는 한 클럭에 n비트가 전송되어야 한다. 이 시간은 Ai를 계산하는 데 고려되어야 한다.
- 모든 흐름에 대하여 도착하는 패킷의 Fi를 계산하여 이들 Fi를 타임스탬프로 사용한다. 이때 다음 번에 전송할 패킷은 항상 가장 낮은 타임스탬프를 가진 패킷이 된다. 가장 낮은 타임스탬프를 가진 패킷은 앞에서 말한 근거에 의해서 다른 패킷이 전송되기 전에 전송이 끝나야만 하기 때문이다.
- 한 패킷이 어떤 흐름으로부터 도착했을 때, 이 패킷이 이미 다른 흐름으로부터 도착하여 큐에서 전송되기를 기다리는 패킷보다 그 길이가 더 짧다면, 그 패킷은 길이가 더 긴 패킷보다 더 앞쪽으로 큐에 삽입될 수 있다는 것을 의미한다. 그렇지만 새로 도착한 패킷이 현재 전송되고 있는 패킷을 선점할 수는 없다.
- 두 개의 흐름에 대한 큐에서 알고리즘은 흐름 2의 큐에서 패킷을 선택하기 전에 흐름 1의 큐에서 전송할 패킷을 두개 선택한다. 왜냐하면 흐름 2의 패킷은 10이 걸리는 반면  흐름 1의 두 패킷은 총 8이 걸리기 때문이다.
- 흐름 1에서 패킷이 도착했지만, 이미 라우터가 흐름 2의 패킷을 보내기 시작했다. 따라서 완전히 정확한 bit-by-bit FQ를 사용한다면, 흐름 1의 짧은 패킷이 흐름 2의 긴 패킷보다 먼저 전송이 끝나겠지만, 실제 구현에서는 전송중인 패킷을 선점하지 않는다.
- FQ에 대해서 두 가지 주의할 점이 있다. 첫 번째, 큐에 적어도 하나의 패킷이 있는 한 링크는 결코 유휴 상태가 되지 않다는 점이다. 이러한 특성을 가진 큐잉 기법을 `작업보존`이라고 한다. 작업보존의 한 가지 효과는 어떤 링크가 아주 많은 흐름을 가지고 있지만 다른 흐름이 어떤 데이터도 보내고 있지 않다면, 그 링크의 대역폭 모두를 데이터를 보내는 하나의 흐름이 사용할 수 있다는 점이다. 그러나 다른 흐름이 데이터를 보내기 시작하면 사용할 수 있는 링크의 대역폭은 그들과 공평하게 나누게 된다.
- 두 번째 주의할 점은 한 링크가 자신의 용량을 모두 사용하고 있고, n개의 흐름이 데이터를 보내고 있다면 링크 대역폭의 1/n 이상을 사용할 수 없다. 그 이상을 보내려고 한다면 패킷은 점차적으로 더 큰 타임스탬프를 할당받게 되어 이들 패킷은 큐에 더 오래 머무르게 된다. 결과적으로는 큐는 오버플로우가 될 것이다. 이때 폐기될 패킷이 어느 흐름이 되는가는 각 큐별로 별도로 결정될 것이다. 이것은 각 큐의 폐기 정책에 의해 결정된다. FQ는 다양한 폐기 정책으로 구성된 FIFO와 비슷한 스케줄링 알고리즘이다.
- FQ의 작업 보존 성질 때문에, 하나의 흐름에 사용되고 있지 않는 어떤 대역폭도 자동으로 다른 흐름에 주어지지는 않는다. 예를 들면, 라우터를 지나는 네 개의 흐름이 잇고 모두 패킷을 보낸다고 하면, 각각의 하나의 흐름은 1/4의 대역폭을 할당받을 것이다. 그러나 그 중 하나가 유휴 상태가 지속되고 모든 패킷을 라우터의 큐에서 버린다면 사용 가능한 대역폭은 남은 세 개의 흐름에서만 나누어질 것이다. 그리고 각각은 1/3의 대역폭만 받을 것이다. 따라서 다른 흐름들이 그들의 지분을 사용하지 않는다면 각 흐름은 자기에게 할당된 대역폭을 최소한 보장받을 수 있고, 다른 흐름이 사용하지 않는 지분을 추가로 사용할 수 있다.
- WFQ라는 FQ의 변형이 있는데, 이것은 각각의 흐름에 어떠한 가중값이 할당되는 방식이다. 이 가중값은 논리적으로 라우터가 해당 큐를 서비스할 때 얼마나 많은 비트가 전송되어야 하는가를 지정해 준다. 이는 곧 해당 흐름이 할당받아야 하는 링크의 대역폭 비율을 효과적으로 조절하게 된다. 
- 단순한 FQ의 경우 각 큐에는 가중값 1을 주게 되는데, 이는 논리적으로 각 큐의 서비스 타임마다 단지 1비트씩 전송된다는 것을 의미한다. 따라서, n개의 흐름이 있을 때 각 흐름은 링크 대역폭의 1/n을 얻게 된다. 그러나 WFQ에서 첫 번째 큐는 가중값 2를 가지고, 두 번째 큐는 가중값 1을, 세 번째 큐는 가중값 3을 가진다고 하고, 각 큐는 항상 전송할 패킷을 가지고 있다면 첫 번째 흐름은 가용한 대역폭의 1/3을 가지고 되고, 두 번째는 1/6, 세번째는 1/2을 할당받게 될 것이다.
- WFQ 라우터로 트래픽을 보내는 발신지는 각 큐에 할당할 가중값이 얼마나 되는가를 라우터에 알려 주어야만 한다. 이는 곧 발신지가 라우터에 이러한 정보를 알려 주어야만 한다는 점에서 WFQ가 의존한다는 것을 의미한다. 비록 가중값이 흐름에 할당될 대역폭에 대해서 간접적으로 연관을 가지기 때문에 미약해 보일지는 모르지만 이것은 예약의 한 종류이다.
- 가중값이 링크의 대역폭에 간접적인 연관을 가지는 이유는, 예를 들어 흐름에 할당될 대역폭은 해당 링크를 얼마나 많은 다른 흐름이 공유하고 있는가에 달려 있기 때문이다.
- WFQ는 간단한 흐름을 정의한 트래픽에서의 classes를 이행할 수 있어야 한다. 예를 들면, 큐를 할당하고 각각의 class에 비중을 주는 IP 헤더에 classes를 확인하기 위한 TOS를 사용할 수 있다.