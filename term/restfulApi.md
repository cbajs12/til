## Restful API
- Representational state transfer의 웹에서 운영되는 소프트웨어 아키텍처 스타일
- HTTP URI를 통해 Resource를 명시하고, HTTP Method를 통해 해당 Resource에 대한 CRUD Operation을 적용한다.

### 특징
- 무상태 : 각요청 간 클라이언트의 Context는 서버에 저장되어서는 안된다.
- Cacheable : 클라이언트는 응답을 Caching할수 있어야 한다.
- 자체 표현구조 : API메시지만 보고도 어떤 API인지를 이해할수 있는 표현 구조를 가진다.

### ROA
- 웹의 모든 리소스를 URI로 표현하고 구조적이고 유기적으로 연결하여 비상태지향적인 방법으로 정해진 method만을 사용하여 리소를 사용하는 아키텍쳐

#### Addressablilty
- 제공하는 모든 정보를 URI로 표시할수 있어야한다.

#### Connectedness
- 일반 웹 페이지처럼 하나의 리소스들은 서로 주변의 연관 리소스들과 연결되어 표현되어야 한다.
```html
<user>
	<name>jack</name>
    <age>12</age>
</user>
```

#### Statelessness
- 현재 클라이언트의 상태를 절대로 서버에서 관리하지 않는다
- 모든 요청은 일회성의 성격을 가지며, 이전의 요청에 영향을 받지 말하야한다.
- URI에 현재 State를 표현할 수 있어야 한다.

#### Homogeneous Interface
- Http에서 제공하는 기본적인 4가지의 method와 추가적인 2가지의 method를 이용해서 리소스의 모든 동작을 정의한다.
- Get, Post, Put, Delete, Head(리소스 메타데이터 보기), Option(리소스의 지원 method 체크)

### 중심규칙
- URI는 정보의 자원을 표현해야한다.
- 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE등)으로 표현한다.
- 자원은 Collection과 Element로 나누어 표현할수 있다.

#### Collection
- `http://example.com/resources/`
- get : 컬렉션에 속한 자원들의 URI나 그 상세사항의 목록을 보여준다.
- put : 전체 컬렉션은 다른 컬렉션으로 교체한다.
- post : 해당 컬렉션에 속하는 새로운 자원을 생성한다. 자원의 URI는 시스템에 의해 할당된다.
- delete : 전체 컬렉션을 삭제한다.

#### Element
- `http://example.com/resources/item1`
- get : 요청한 컬렉션 내 자원을 반환한다.
- put : 해당 자원을 수정한다.
- post : 해당 자원에 귀속되는 새로운 자원을 생성한다.
- delete : 해당 컬렉션내 자원을 삭제한다.

#### Document
- 도큐먼트는 컬렉션과는 달리 단수명사나 명사의 조합으로 표현되어 URI에 나타난다.
- 단수 리소스는 도큐먼트, 복수 리소스는 컬렉션으로 칭한다.
- `http://api.soccer.restapi.org/leagues/seattle/teams/trebuchet`에서 leagues는 컬렉션, seattle은 도큐먼트
- URI는 문서의 계층 구조를 표현하고 있다.
- Content-Type에 대해서 명시하여 원하는 리소스를 선택할수 있으므로 URI내에는 파일 확장자를 포함하지 않는것이 좋다.
> dogs/1.xml -> dogs/1 & Content-Type : application/xml

#### Controller
- URI에 동사를 쓰는 것은 대체로 옳지않다.
- 컨트롤러 리소스를 정의하여, URI경로의 제일 마지막 부분에 동사의 형태로 표시되어 해당 URI를 통해 접근했을때 일어날 행위를 생성
> http://api.college.restapi.org/students/morgan/register  -> 리소스 morgan을 등록

#### 쿼리 스트링
- 필요한 정도의 정보만을 요구하기 위해 쿼리스트링에 구분 값과 페이징 값을 포함할수있다.

> LinkedIn
/people:(id,first-name,last-name,industry)
이 경우 people 리소스를 요청하되 마치 SQL 쿼리에서 가져올 필드를 제한하는 것처럼 필요한 필드에 대해서만 괄호로 묶어서 지정한 것을 볼 수 있습니다.

>Facebook
/joe.smith/friends?fields=id,name,picture
이 경우 이름(혹은 계정이름)이 joe.smith인 사람의 정보를 가져오되 LinkedIn의 예와 같이 필드를 제한(id,name,picture)해서 가져오도록 한 예입니다.

### Header
- HTTP 요청과 응답을 보낼때 특정 헤더를 포함해 요청, 응답, 리소스에 대한 메타정보를 전달할수 있다.

#### 요청 해더
- `Accept` 응답으로 받고 싶은 미디어 타입을 명시
> GET /data HTTP/1.1
Host: example.org
Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8 

- /data리소스에 대해서 기본적으로는 html이나 xhtml의 형식으로 응답 받고 싶되, 여의치 않으면 xml도, 그것도 안되면 모든 응답(*/*)을 받겠다는것을 말한다.
- q는 선호도를 나타낸다
- 서버에서 세가지 미디어 타입을 모두 지원할수 없다면 406상태코드를 보내야 한다.

- `Accept-Charset` 응답으로 받고싶은 캐릭터셋에 대하여 명시 하는 헤더
- `User-Agent` 현재 요청을 보낸 Agent의 정보를 표시하기 위해 사용
- `Referer` 해당 요청을 보내기 바로 직전에 참조하던 리소스 혹은 주소에 대한 정보를 나타내기 위함

#### 응답 헤더
- `Content-Length` 요청과 응답 메시지의 엔티티 바디가 얼마나 큰지에 대한 정보를 나타내기 위해 사용, 단위는 바이트
- `Last-Modified` 해당 리소스가 마지막으로 갱신된 시간을 나타내기 위하여 사용

#### 상태 코드
- `200` (ok) 일반적인 요청 성공
- `201` (created) 리소스 생성 성공
- `202` (accepted) 비동기 요청에 대한 응답
- `301` (moved permanently) 리소스가 이동되었을 경우, 새로 리소스가 이동된 URI를 응답 Location헤더에 명시해야 한다.
- `400` (bad request) 일반적인 요청실패, 서버가 이해할수 없는 형식의 요청
- `401` (unauthorized) 리소스 접근 권한을 가지고 있지 않다
- `403` (forbidden) 감춰진 리소스에 접근하려 할때 응답
- `404` (not found) 해당 URI와 매치되는 리소스가 없다
- `405` (method not allowed) 지원하지 않는 요청을 하였을때 응답
- `406` (not acceptable) 해당 미디어 타입에 대해서 지원하지 않을때 사용
- `409` (conflict) 요청의 형식에 문제는 없지만 리소스 상태에 의하여 해당 요청 자체를 수행할수 없는 경우
- `500` (internal server error) 일반적인 서버에러
- `503` (service unavaliable) 현재 서버에 과부하가 걸려있거나 유지보수를 위하여 잠시 접근이 거부될때 사용

### 기타규칙들
- URI는 플랫폼 중립적이어야 한다. 정보를 보여줄때 여러 플랫폼을 구별해야 한다면, `Request Header`의 `User-Agent`값을 참조하는것이 좋다.
- Header의 `Accept`헤더를 이용해서 요청 환경에서 정보의 버전과 포맷을 지정할수 있게 한다.
- URI에는 소문자를 사용해야 한다.

### reference
- https://spoqa.github.io/2012/02/27/rest-introduction.html
- https://spoqa.github.io/2013/06/11/more-restful-interface.html
- https://lalwr.blogspot.kr/2016/01/restful.html
