# 애플리케이션

## 웹서비스
- 서로 다른 기업에서 사용하고 있는 애플리케이션 간의 직접적인 상호 작용을 기업 간 전자상거래 통합(B2B)이라고 부르며, 하나의 기업 내에서 사용하고 있는 애플리케이션 간의 직접적인 상호 작용을 기업 애플리케이션 통합(EAI)이라고 부른다.
- 네트워크의 확장성이란 네트워크의 크기가 확장된다는 의미가 아니라, 네트워크 애플리케이션의 수가 확장된다는 의미이다. 다양한 B2B와 EAI 관련 네트워크 애플리케이션을 빠르게 개발하기 위해서는 애플리케이션 프로토콜의 설계와 구현 작업을 단순화하고 자동화하는 기술이 필요하다.
- 웹 서비스는 네트워크에 분산되어 있는 클라이언트 애플리케이션에서 제공하는 서비스를 개별 애플리케이션이 원격으로 제공받을 수 있는 개념에서 시작되었다. 웹 서비스 아키텍처로는 SOAP와 REST가 있다.
- SOAP 아키텍처는 개별 네트워크 애플리케이션에 적합한 프로토콜을 생성하는 데 유용한 기법을 제공한다. SOAP에서 제공하는 기법의 중요한 요소로는 프로토콜 명세에 대한 프레임워크, 명세를 기반으로 한 프로토콜의 자동 구현을 지원하는 소프트웨어 도구, 그리고 프로토콜의 재사용을 제공하는 명세서의 부분 모듈화 기능이 있다.
- REST 아키텍처는 개별 웹 서비스들을 URI로 분류되어 HTTP에 의해서 접근되는 WWW 자원처럼 취급한다. 실질적으로는 REST 아키텍처는 웹 아키텍처이다. 웹 아키텍처의 장점은 안정성과 이미 검증된 확장성이다. 그러나 WWW에서 사용되고 있는 HTTP는 절차적 혹은 동작기반의 원격 서비스 실행에는 적합하지 못한 약점이 있다.

### 커스텀 응용 계층 프로토콜
- SOAP 아키텍처는 WSDL과 SOAP 표준에 기반을 두고 있다. 두 표준은 W3C에서 제정되었다.
- WSDL은 애플리케이션 프로토콜의 명세와 구현을 위한 프레임워크이며, SOAP는 트랜스포트 프로토콜의 명세와 구현을 위한 프레임워크이다.
- WSDL과 SOAP은 프로토콜 정의 언어로 기술된다. 두언어는 XML을 기반으로 하였다. WSDL과 SOAP를 사용하는 웹 서비스 개발자들은 제3자(thrid-party)에 의해 개발된 소프트웨어 제작 도구 및 애플리케이션 서버와 같은 지원 소트프웨어를 활용한다.

#### 애플리케이션 프로토콜 정의
- WSDL은 애플리케이션 프로토콜의 명세와 구현을 위하여 애플리케이션 프로토콜에서 순차적으로 이루어지는 작업에 대한 모델을 정의한다. 웹 서비스 인터페이스는 클라이언트 및 웹 서비스 간의 상호작용을 표현하는 작업들의 명칭이다. 그리고 각 작접은 RPC 시스템에서의 원격 호출 프로시저와 유사한 개념이다.
- 각 작업은 일반적인 메시지와 에러 발생을 알리는 실패 메시지를 포함한 모든 메시지들의 전송 순서를 명시하는 MEP를 기술한다. 이미 정의되어 있는 MEP가 있으며, 새로운 커스텀 MEP를 정의할 수도 있지만, 일반적으로 In-Only(클라이언트가 서비스에게 전송하는 메시지), In-Out(클라이언트가 서비스에게 요청하는 메시지와 서비스가 요청 메시지에 해당하는 응답 메시지로 구성)가 자주 사용된다.
- WDSL에서는 XML 스키마를 사용한 추상적 데이터 모델로서 메시지의 타입과 형식을 정의한다. XML 스키마는 프리미티브 데이터 타입과 컴파운드 데이터 타입을 정의하는 방법을 제공하고 있다.
- SOAP의 핵심 기능은 확장성을 고려한 단순 메시지 전달 프레임워크를 제공하는 것이다.
-  WDSL/SOAP의 이점은 기존의 애플리케이션을 새로운 환경에 적응시키거나 랩핑하여 웹 서비스에 맞게 조절하는 것이다.

### 일반 응용계층 프로토콜
- WSDL/SOAP 웹 서비스 구조는 개별 애플리케이션에 맞는 프로토콜을 사용하여 네트워크에 연결된 개별 애플리케이션을 통합하는 방법이 가장 좋다고 가정한다. REST 웹 서비스 구조는 WWW의 하부 구조를 적용하여 네트워크에 연결된 개별 애플리케이션을 통합하는 방법이 가장 좋다고 가정한다.
- 일반적으로 웹 구조에서는 개별 웹 서비스가 URI로 구별되고, HTTP로 접근이 가능한 자원으로 간주된다. HTTP는 하나의 어드레싱으로 자원에 접근하는 응용계층 프로토콜로 설명할 수 있다.
- WSDL에서는 사용자가 동작을 정의할 수 있는 반면에, HTTP는 오직 몇 개의 기능만을 사용자에게 단순히 제공하고 있으며, 그 중 몇몇 기능은 방화벽에 종종 차단된다. REST 모델을 적용하면, 복잡한 문제는 페이로드 관점으로 옮겨진다. 즉 프로토콜로 모든 것을 표현하기 보다는 프로토콜은 단순화된 메시지 전달 기능만 담당하고, 페이로드에 보다 복잡한 기능을 구현한다. 즉 프로토콜로 모든 것을 표현하기 보다는 프로토콜은 단순화된 메시지 전달 기능만 담당하고, 페이로드에 보다 복잡한 기능을 구현한다. 페이로드는 자원의 개념적인 상태를 표현하고 있다. GET은 자원의 현재 상태에 대한 표현을 가져올 수 있고, POST는 요구된 자원의 상태에 대한 표현을 보낼 수 있다.
- 자원 상태에 대한 표현은 추상적이어서 특정 웹 서비스를 구체화한 인스턴스로써 자원의 상태가 구현되어 표현될 필요는 없다. 개별 메시지마다 반드시 리소스의 완전한 상태를 전송할 필요는 없다. 웹 서비스에서는 하나의 프로토콜과 주소 공간을 다른 웹 자원을 공유할 수 있기 때문에, 자원에 대한 상태 정보를 직접 가져오는 것보다는 URI를 이용하여 해당 자원이 있는 웹을 참조해서 자원에 대한 상태 정보를 가져올 수 있다.
- 이러한 기법은 데이터 지향적이거나 문서전달 방법의 일종이다. 이 구조에서 응용 프로토콜을 정의하는 것은 문서의 구조를 정의하는 것으로 이루어진다. XML과 JSON이 자원 상태를 표현하는 언어로 가장 많이 쓰인다. 상호 운용성은 웹 서비스와 클라이언트가 서로 동의한 자원 상태 표현 방식에 의존적이다. 웹 서버와 클라이언트는 페이로드 형식에 대하여 상호 동의를 해야 한다. REST 구조에서는 항상 HTTP 프로토콜을 사용하기 때문에 프로토콜과 관련된 상호 운용성은 문제가 되지 않는다.
- 웹에서도 중간 노드를 사용할 수 있다. 웹 프록시는 보안기능을 담당하거나 캐시를 저장한다. 게이트웨이는 웹 서비스를 지원하지 않는 시스템을 웹 구조에서 사용할 수 있게 한다. HTTP는 중간 노드가 메시지를 해석할 수 있도록 해준다. 예를 들면 읽기만 가능한 GET의 메시지와 이에 대한 응답을 가로채어 캐시에 저장한 중간 노드는 동일한 GET 메시지를 보낸 애플리케이션에게 캐시된 응답을 바로 보낼 수 있다.
- 웹 표준은 안정화 및 확장이 잘된다. 또한 웹은 SSL/TLS과 같은 보안 기능을 수반한다. HTTP와 같은 표준화된 프로토콜은 역방향 호환성 기법을 제공할 수 있는 확장성을 지원하도록 설계되었다. HTTP는 헤더 형식과 새로운 명령, 그리고 새로운 컨텐츠 타입을 사용하여 확장성을 제공한다.
