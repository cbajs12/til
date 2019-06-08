## Java Excutin option
- `java <options> <classfiles> <argument>` 또는 `java <options> -jar file.jar <argument>`
- classfiles: 호출될 클래스 파일이름
- file.jar: 호출될 jar파일 이름
- argument: main함수에 파라미터로 보내질 문자열

### options
- `-client` : 자바 HotSpot Client VM을 선택한다 (디폴트)
- `-server` : 자바 HotSpot Server VM을 선택한다
- `-classpath (-cp)`: 참조할 클래스 파일 패스를 지정하는데, jar파일, zip파일, 클래스파일의 디렉터리 위치를 기술한다. 각 클래스파일 패스는 콜론(:)을 통해서 분리시켜 기술한다.
- `-D <property name>=<property value>:` : 시스템의 property값을 설정한다.
- `jar 파일이름:` : jar파일로 압축되어져 있는 자바 프로그램을 실행시킨다.
- `-verbose:` : 자바프로그램이 실행되어지는 정보를 화면에 출력해준다. `-verbose:class` -> 로딩되어지는 각 클래스들의 정보를 화면에 출력한다.
- `-version` : 현재 JVM의 버전 정보만 출력
- `-X` : 비표준 자바옵션 리스트를 화면에 출력해준다.
- `-Xms, -Mmx` : 자바 구동시 JVM이 사용가능한 최대 메모리 사이즈를 변경
- `-XX:PermSize, -XX:MaxPermSize` : 클래스 메타 정보 메모리, Xms와 Xmx메모리와 별도로 관리된다.

### reference
- http://blog.bagesoft.com/m/908