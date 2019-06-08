## OS
### os module
- os.getcwd() : 현재 작업 디렉터리의 위치를 가져올 때 사용
- os.chdir() : 현재 작업 디렉터리 위치를 변경
- os.access(path, mode) : 입력 받은 path에 대해 mode에 해당하는 작업(F_OK(해당 path 존재여부), R_OK(해당 path 읽기 가능여부),  ... )이 가능한지 반환
- os.listdir(path) : 해당 경로에 존재하는 파일, 디렉터리의 목록 반환
- os.mkdir(path, mode) : path에 해당 디렉터리 생성
- os.remove(path) : 파일 삭제
- os.rmdir(path) : 디렉터리 삭제
- os.stat(path) : 경로에 해당하는 정보를 얻어온다 (inode, device, link, size, last change time, ...)
- os.umask(mask) : umask 설정, 수행하면 이전 mask값이 반환
- os.pipe() : 파이프를 생성, 함수를 실행하면 읽기, 쓰기 전용 파이프의 파일 디스크립터가 반환
- os.fdopen(fd, mode, bufsize) : 파일 디스크립터를 이용해 파일 객체를 생성
- os.popen(command, mode, bufsize) : 인자로 전달된 command를 수행하며 파이프를 연다.
- os.environ() : 환경변수를 나타내는 사전
- os.getpid() : 현재 프로세스 아이디를 반환 
- os.strerror(code) : 에러코드에 해당하는 에러 메시지 보여준다.
- os.system(command) : command를 실행하며, 성공한 경우 0을 반환
- os.startfile(path, operation) : path를 os에서 지정된 프로그램으로 실행, operation으로 명시적으로 수행할 프로그램을 지정할 수 있다. (system()을 사용하는것과 유사하지만 system()은 파이썬 프로그램의 실행이 멈추고, startfile()은 멈추지 않고 계속 실행)
- os.execl(), os.execlp(), os.execv(), os.exevp(), ... : 현재 프로세스에서 새로운 프로그램을 실행하며 반환은 하지 않는다.
 
### sys module
- 파이썬 인터프리터와 관련된 정보와 기능을 제공하는 모듈
- sys.argv :  파이썬 스크립트로 넘어온 인자의 리스트
- sys.exc_info() : 현재 발생한 예외 정보를 튜플로 반환
- sys.prefix, sys.executable : 파이썬이 설치된 경로와 실행 파일을 나타낸다.
- sys.modules : 현재 로딩되 있는 모듈을 사전 형식으로 나타낸다.
- sys.path : 모듈을 찾을때 참조하는 경로를 나타낸다.
- sys.exit(arg) : 프로세스를 종료시킨다. arg가 0인경우에는 정상적으로 종료, 아니면 비정상 종료 처리
- sys.getdefaultencoding() : 현재 사용 중인 기본 문자열 인코딩을 반환
- sys.stdin, sys.stdout, sys.stderr : 표준 입력, 출력, 에러 스트림에 대응되는 패일 객체를 나타낸다.

### threading module
- 스레드를 사용하려면 일반적으로 threading.Thread를 상속받은 클래스 객체를 생성해 사용하며, 생성자를 재정의하는 경우 반드시 Thread._init_()을 수행해야 한다.
- Thread.start() :  스레드 시작
- Thread.run() : 스레드 동작 정의
- Thread.join(timeout) : 스레드 종료되기를 기다린다, timeout이 정의된 경우 최대 정의된 시간만큼 기다린다.

#### Lock 객체
- 하나의 프로세스에서 2개 이상의 스레드가 동시에 수행되는 경우, 스레드 간에 프로세스 공간의 메모리를 공유한다.
- Lock 객체로 동기화를 지원
- unlocked 상태에서 acquire()가 호출되면 locked상태로 바뀌며, locked상태에서 release()가 호출되면 unlocked 상태로 바뀌게 된다.

### queue module
- 큐, 우선순위 큐, 스택을 제공한다, 스레드 환경을 지원한다.
- queue.Queue(maxsize) : 큐를 생성
- queue.LifoQueue(maxsize) : 스택 특성의 큐 생성
- queue.PriorityQueue(maxsize) : 우선순위큐 생성, 아이템은 (순위, 아이템)의 튜플로 입력
- put(item, block, timeout) : 큐에 아이템 입력
- put_nowait(item) : 블로킹없이 큐에 아이템 입력, 큐에 객체가  풀이면, queue.Full 예외 발생
- get(block, timout) : 생성된 큐 객체 특성에 맞춰, 아이템 1개 반환
- get_nowait() : 블로킹없이 큐 객체 반환, 아이템 없는경우, queue.Empty예외 발생

###  weakref module
- 객체가 메모리에 저장되면 레퍼런스 카운터로 관리된다. 객체가 사용하지 않게되면, 카운터가 줄어든다. 레퍼런스 카운터가 0이면 가비지 컬렉션이 일어난다.
- weakref는 약한 참조를 만드는데 사용한다.  약한 참조는 변수가 객체를 참조할때, 레퍼런스 카운터를 증가 시키지 않고 참조하는 객체를 얻을 수 있다.
- class weakref.ref(object, callback) : 객체에 대한 약한 참조 객체를 반환, 메모리에 존재하는 경우 객체에 대한 참조를 반환, 그렇지 않은 경우 None을 반환
- weakref.proxy(object, callback) : 객체에 대한 약한 참조 프록시를 생성, 명시적으로 약한 참조 객체를 생성하는 대신 프록시를 이용해 객체의 참조를 얻는다.
- weakref.getweakrefcount(object) : 객체에 대한 약한 레퍼런스 카운터를 반환
- weakref.getweakrefs(object) : 객체를 참조하는 약한 참조 객체와 프록시를 리스트로 반환

#### weak dictionary
- 약한 사전은 구성 요소인 키와 값중 하나가 약한 참조로 구성되어 있는 경우이다.
- 사용자가 사전에 입력해도 레퍼런스 카운터가 증가되지 않으며, 원본 객체가 삭제되면 자동적으로 사전의 객체도 삭제되어 None이 된다.
- weakref.WeakKeyDictionary([dict]) : 키를 약한 참조로 갖는 사전 객체를 생성
- weakref.WeakValueDictionary([dict]) : 값을 약한 참조로 갖는 사전 객체를 생성
