## daemon
- Daemon 이란 백그라운드로 실행되면서, 사용자의 인터페이스(tty)가 없는 프로그램을 말한다.
- 우리가 흔히 사용하는 리눅스 서비스들은 대부분 데몬으로 동작하며, -d로 끝나는 프로그램(예: sshd, syslogd)이 모두 해당된다. 
- 리눅스 명령인 nohup으로 백그라운드 구동은 가능하지만, kill 명령으로 종료해야 한다.따라서 비정상 종료시의 처리를 하려면, OS로부터 signal을 받아야 하므로 데몬으로 구동시켜야 한다.
- 데몬이란 주기적인 서비스 요청을 처리하기 위해서 커널상에 백그라운드 모드로 실행되는 프로세스로, 메모리 관리 방법에 따라 단독 데몬과 xinetd로 분리된다.
- 단독데몬 : 항상 백그라운드 모드로 실행되고 메모리를 상대적으로 많이 소비한다. 그러나 서비스(응답속도)가 빠르다. httpd와 같은 웹서비스 데몬이 대표적.
- xinetd(슈퍼데몬) : 요청이 있을때마다 xinetd가 서비스를 싱행시켜주므로 메모리 소비가 적다. 그러나 단독데몬에 비해 상대적으로 서비스 속도가 느리다.

### Controlling terminal
- Daemon 프로세스는 제어 터미널(controlling terminal)을 가지지 않는 것이 좋습니다. 만약 제어 터미널이 있을 경우, 다음과 같은 문제가 발생할 수 있습니다.
--> 사용자가 터미널 escape 문자를 써서 (대표적으로 CONTROL-c, CONTROL-z 등) 원치 않게 프로세스를 끝내 버리거나, suspend시킬 수 있습니다.
--> 원격 터미널 연결이 끊길 경우, 원치 않게 프로세스가 종료될 수 있습니다.

### Daemon Setting
```sh
export APP_LIBRARY=${ROOT_PATH}/commons-dbcp-1.2.2.jar
export APP_LIBRARY=$APP_LIBRARY:${ROOT_PATH}/commons-pool-1.3.jar
```
- APP_LIBRARY += jar하겠다는 의미

### reference
- http://win100.tistory.com/120
- http://cinsk.github.io/articles/daemon.html