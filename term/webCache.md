## Web Cache
- 클라이언트가 요청하는 html, image등에 대해 첫 요청시에 파일을 내려받아 특정 위치에 복사본을 저장하고, 이후 동일한 URL의 Resource요청은 다시 내려받지 않고 내부에 저장한 파일을 사용하여 더 빠르게 서비스 하기 위한것

### Browser caches
- 브라우저 또는 HTTP요청을 하는 Client Application에 의해 내부 디스크에 캐쉬
- Cache된 Resource를 공유하지 않는 한 개인에 한정된 Cache
- 브라우저의 Back버튼 또는 이미 방문한 페이즈를 재 방문하는 경우 극대화

### Proxy caches
- Braowse cache와 동일한 원리로 동작하며 client나 server가 아닌 네트워크상에 동작
- 회사나 IPS의 방화벽에 설치되며 대기시간, 트래픽 감소, 접근정책, 제한 우회, 사용률 기록등 수행
- 한정된 수의 클라이언트를 위하여 무한대의 웹서버의 컨텐츠를 캐쉬

### Gatewat caches
- 서버 앞단에 설치되어 요청에 대한 캐쉬 및 효율적인 분배를 통해 가용성, 신뢰성, 성능등을 향상
- Encrtpiton /SSL acceleration, Load balancing, serve/cache static content, compression등을 수행
- 무한대의 클라이언트들에게 한정된 수의 웹서버 컨텐츠를 제공

### Cache control
#### HTML Headers
- 파일이 이전과 비교하여 변경 되었는가를 체크하는 validation과 캐쉬의 만료 여부를 체크하는 freahness로 구성
- validation : if-None-Match(Request), Entity Tag(Response)
- freshness : Cache-Control(Request, Response)

### Cache 동작
- 브라우저는 서버에 index.html파일 요청
- 서버는 index.html파일 찾고 존재하면 파일 내용을 브러우저에게 몇가지 header값과 함께 응답
- 브라우저는 응답 받은 내용을 브라우저에 표시하고 응답 헤더의 내용에 따라 캐쉬 정책을 수행 (Last-modified, etag, expires, cache-control에 내용에 따라 수행)

#### 재요청 - Last-modified
- 브라우저는 최초 응답시 받는 Last-modified값을 if-modified-since라는 헤더에 포함시켜 페이지를 요청
- 서버는 요청 파일의 수정 시간을 if-Modified-Since값과 비교하여 동일하다면 `304`(not modified)로 응답, 다르면 `200`(ok)와 함께 새로운 last-modified값을 응답헤더에 전송
- 브라우저는 응답코드가 304인 경우 캐쉬에 페이즈를 로드, 200이라면 새로 다운받은후 Last-modified값을 갱신

#### 재요청 - etag
- 브라우저는 최초 응답시 받은 etag값을 if-none-match라는 헤더에 포함시켜 페이지를 요청
- 서버는 요청 파일의 etag값을 if-none-match값과 비교하여 동일하다면 `304`(not modified)로 응답, 다르면 `200`(ok)와 함께 새로운 etag값을 응답헤더에 전송
- 브라우저는 응답코드가 304인 경우 캐쉬에 페이즈를 로드, 200이라면 새로 다운받은후 etag값을 갱신
- etag는 서버마다 생성하는 값이 다르며 파일마다 고유한 값을 가진다.
- last-modified(ver1.0)과 etage(ver1.1)는 validation체크를 한다. 이를 위하여 서버와 한번의 통신이 발생하게 되어 요청과 응답에서 header와 cookie등에 의한 데이터 전송이 발생

#### 재요청 - expires
- 브라우저는 최초 응답시 받은 expires시간을 비교하여 기간 내라면, 서버를 거치지 않고 바로 캐쉬에서 로드, 만료되었다면 validation작업 수행

#### Cache-Control
- 브라우저는 최초 응답시 받은 cache-control중 max-age값을 GMT와 비교하여 기간내라면 캐쉬에서 로드, 기간이 만료되었으면 validation 수행
- expires(ver1.0)와 cache-control(1.1)은 freshness체크한다.
- 시간은 HTTP date형태이고 GMT를 사용
- 서버가 Last Modified Time 또는 Last Access Time을 기준으로 하여 일정 시간 이후로 Expires또는 max-age를 설정

### 캐싱 전략
- 일관된 URL사용, 동일한 URL은 동일한 사이트라면 다른 페이지에서도 캐쉬되어 사용될수 있다.
- 자주 바뀌는 파일과 아닌 파일을 분리, 그래야 각 리소스에 대해 최적의 freshness를 설정가능
- 다운로드 가능한 파일의 내용이 바뀌면 URL을 바꾼다. 그래야 올바른 수정 버전을 제공 가능
- SSL을 최소화 한다. 암호화된 페이지는 캐쉬되지 않는다.

### 캐싱 문제점
- freshness를 체크하는 Expries와 Cache-control는 파일이 아직 유효하다고 판단되는 경우 서버에 전송조차 보내지 않고 캐쉬를 사용. 만약 서버에서 css, javascript가 변경되면 유효기간이 만료되기 전까지 또는 사용자가 강제로 캐쉬를 삭제하기 전까지는 사용자마다 다른 화면을 보인다.
- 여러 분산 서버를 사용할 경우, 서버가 서로 바라보는 파일이 다르다면 파일의 수정시간이 다를것이고, 각 사용자가 다른 서버를 접속되는 순간마다 캐쉬는 무효화 되며 다시 처음부터 다운받는 작업을 하게 된다.
- Etag는 개본적으로 정해놓은 몇개의 값을 가지고 MD5Hash등의 방법으로 digest한 값이다. 파일의 내용만 가지고 digest한다면 서버가 다르다 해도 문제 없지만, 파일 내용을 매번 digest하여 비교하는것은 비효율적이다. 그래서 각 서버(Apahche, NginX등)마다 설정해놓은 기본값을 가지고 Etag를 생성한다. 서버마다 Etag의 값이 달라질수 있다.

### 문제 해결책
#### fingerprint적용
- url에 `?fingerprint=fffd`와 같이 파라메터추가
- 브라우저는 fingerprint가 변경될경우, url이 변경되어 새로운 리소스라고 인식하여 무조건 새로 다운한다.
- 파일 내용이 변경되면 fingerprint값만 변경해주면 된다.
- 한번 적용되면 변경할일 없는 library들은 굳이 fingerprint적용할 필요는 없다.
- 문제는 파일이 변경될때마다 모든 페이지를 찾아가며 fingerprint를 변경해야한다.

### 기타
#### two types of requests
- 브라우저는 상황에 따라 2가지 유형의 request를 서버에 날린다.
- UnConditional(download)
-- 브라우저가 캐쉬된 파일을 가지고 있지 않은경우
-- 유저가 ctrl + 새로고침을 하는 경우
-- 링크, 이전 & 다음버튼, 주소창에 입력후 엔터를 치는 경우

- Conditional(validate)
-- cache-control 또는 expires 만료된 경우
-- 캐쉬가 저장될때 vary header와 같이 전달되었던 경우
-- meta tag를 이용한 refresh가 발생한 경우
-- 자바스크립트의 location을 통해 reload된 경우
-- cross-host HTTPS를 통해 요청되는 경우
-- 유저가 새로고침을 하는 경우

- conditional request가 발생되면 요청되는 모든 리소스에 대하여 freshness여부에 상관없이 무조건 revalidation을 요청한다.

#### Heuristic expiration
- Expired 또는 cache-control을 설정하지 않앗는데 freshness가 유효하다 판단하여 서버에 요청하지 않고 캐쉬되는 경우가 있다.
- Response헤더에 expiration times가 명시되어 있지 않지만, Last-Modified는 명시된 경우 브라우저마다 Heuristic expiration times를 부여한다.

#### Content-legth in response header
- HTTP 1.1에서는 TCP/IP커넥션이 끊어지지 않고 유지되는 Keep Alive connection이 지원된다. Response헤더에 content-length항목이 추가되면 이 기능이 활성화 되어, 각 Request를 위해 매번 설정하고 연결을 맺고 끊는 과정 없이 하나의 커넥션으로 전부 요청하기 때문에 페이지의 속도가 더욱 빨라진다.

### reference
- http://cyberx.tistory.com/9
