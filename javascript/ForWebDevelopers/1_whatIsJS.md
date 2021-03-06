## 1. what is Javascript

### 자바스크립트 구현
- 코어(ECMAScript) + 문서 객체 모델(DOM) + 브라우저 객체 모델(BOM)

#### ECMAScript
- ECMAScript는 웹 브라우저에 종속되지 않는다.
- ECMAScript는 입력이나 출력을 담당하는 메서드가 없다.
- 웹 브라우저는 ECMAScript를 구현하는 호스트 환경일 뿐이다.
- 호스트 환경은 ECMAScript를 기본적으로 구현하고 이 환경 자체의 인터페이스 구실을 하도록 디자인된 확장을 제공한다. 확장이란 DOM 같은 것을 말한다. 이러한 확장은 ECMAScript의 핵심 타입과 문법을 이용해 환경에 밀접한 기능을 제공한다.
- ECMAScript는 문법, 타입, 선언문, 키워드, 예약어, 연산자, 객체를 정의한다.

#### DOM
- DOM은 XML을 HTML에서 사용할 수 있도록 확장한 API이다.
- DOM은 전체 페이지를 노드의 계층 구조로 변환한다.
- HTML 페이지의 각 부분은 각기 다른 데이터를 포함하는 다양한 타입의 노드로 표현된다.

#### BOM
- 브라우저 창에 접근하고 조작할 수 있게 하는 인터페이스이다.
- BOM을 이용하면 브라우저에 표시된 페이지와는 별개의 컨텍스트에서 브라우저와 상호작용할 수 있다.
- BOM은 일차적으로 브라우저 창과 프레임을 다루는 인터페이스이긴 하지만 일반적으로는 브라우저에 고유한 자바스크립트 확장도 BOM의 일부로 간주한다. (브라우저 창을 새로 띄움, 브라우저 크기 조절, 브라우저의 상세정보(navigator), 불러온 페이지의 상세 정보(location), 사용자 화면 해상도(screen), 쿠키, XMLHttpRequest등)