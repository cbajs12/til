## Web & Was

#### Web Server
- 작성된 html페이지 등을 네트워크망에 종속되지 않고, 웹서비스를 할수 있도록 하는 어플리케이션
- Http를 통해 웹 브라우저에서 요청하는 Html문서나 오브젝트등을 전송해주는 서비스 프로그램
- 웹 서버와 웹 컨테이너의 결합으로 다양한 기능을 컨테이너에 구현하여 다양한 역할을 수행할 수 있는 서버
- 웹 서버는 http요청을 처리할수 있는 것, 클라이언트로부터 요청을 웹 컨테이너로 전달하고, 그 결과를 웹 컨테이너로부터 받아 클라이언트에 전달한다.
- 이미지나 단순 html파일과 같은 리소스를 제공하는 서버는 웹 서버를 통하면 WAS를 이용하는 것보다 빠르고 안정적이다.
- 스크립트 언어도 웹 서버에 넣어서 작동시킬수 있다. 웹서버가 html을 읽을때 언어의존부분은 스크립트 언어가 웹서버가 읽을수 있도록 포팅하고 그것을 웹서버는 그대로 제공한다.
- 정적인 데이터들을 넣어서 처리시킨다.
- ex) apache, nginx

### WAS(Web Application Server)
- 웹 서비스나 프로그램등을 실행할 수 있는 기초적인 환경을 제공
- Clinet <-> (Web Server <-> Web Container) : WAS
- 동적인 데이터들의 대한 접근을 제어하여 보안성을 높인다.
- 동적인 데이터들을 넣어서 처리시킨다.
- ex) tomcat, jesus, ...

#### Web Container
- 웹 컨테이너는 클라이언트의 요청이 있을때 내부의 프로그램을 통해 결과를 만들어내고 이것을 다시 클라이언트에 전달해 주는 역할을 하는 것

### WAS와 웹서버의 차이
- 웹 서버는 정적인 데이터를 처리하는 서버이다.(js, images, css, ...)
- WAS는 동적인 데이터를 처리하는 서버이다.(java, ...)
- DB와 연결되어 데이터를 주고 받거나 프로그램으로 데이터 조작이 필요한경우에는 WAS를 활용해야한다.
- 웹서버 소프트웨어는 HTML/CGI등의 웹 문서들을 HTTP규약에 따라 웹 클라이언트와 주고 받으며 통신하는 것이 주 역할이다.
- WAS 소프트웨어는 규모가 크고 엔터프라이즈 환경에 필요한 트랜젝션, 보안, 트래픽 관리, DB커넥션 풀, 사용자 관리등의 기능을 제공
- 웹서버에 화면을 동적으로 보여주기 위해 여러가지 로직이 들어가는데, 한서버에 로직이 집중되면 속도와 보안에 문제가 생긴다. 이럴때, 화면에 뿌려주는 로직은 웹서버에 실제로 돌아가는 로직은 WAS에서 일을 나누어 역할을 분담시킨다.

### Nginx
- 가볍고 높은 성능을 목표로 하는 웹서버
- 아파치와 다른점은 쓰레드 기반이 아닌 이벤트 기반이다.
- 아파치보다 더 나은 성능을 보여준다.

### tomcat
- 웹 서버와 연동하여 실행할수 있는 자바환경을 제공
- java의 클래스 파일들을 실행 가능한 파일로 만들어 제공하는 역할한다.
- was에 속하나 Servlet엔진정도로 이야기할수 있다.
- 배포시의 톰켓의 8080포트로의 접근을 막아서 java 클래스에 대한 접근을 막고 웹 포트 80만을 사용하여 접근하도록 유도한다.

### reference
- http://aonenetworks.tistory.com/616
- http://sungbine.github.io/tech/post/2015/02/15/tomcat과%20apache의%20연동.html
- http://m.blog.naver.com/blogpyh/220019833857
