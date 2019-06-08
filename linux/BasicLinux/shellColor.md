## Shell color
### prompt
- bash shell에서 프롬프트는 `$PS1`이라는 shell변수에 들어있다. `echo $PS1`을 사용하면 어떤 정보가 저장되어 있는지 확인할 수있다.
- `\h`는 현재의 호스트 이름
- `\W`는 현재의 위치의 디렉토리
- `\u`는 현재의 로그인된 사용자
- `\v`는 배쉬 쉘의 버전
- `\t`는 HH:MM:SS포멧으로 24시 기준의 현재시간 표현
- `\T`는 HH:MM:SS포멧으로 12시 기준의 현재시간 표현
- `\d`는 요일, 월, 날짜 포멧으로 날짜를 표현

> \h:\W \u$ == {호스트이름} : {현재 디렉토리} {로그인된 사용자}$

#### prompt 색상 정보
- `0;30` : 검정
- `0;34` : 파랑
- `0;31` : 빨강
- `0;36` : 청록
- `0;35` : 보라
- `0;32` : 초록
- `0;33` : 갈색
- `\e[` : 색깔 시작
- `x;y` : 색깔을 짝으로 사용
- `\e[m` : 색깔 끝

> export PS1="\e[0;31m[\u@\h \W]\$ \e[m "

### 디렉토리 색상
- `/etc/DIR_COLORS` 파일 혹은 `~/.bashrc`에서 `LS_COLORS` 환경 변수를 수정한 후 `eval 'dircolors -t'` 하면 바로 적용.

> export LS_COLORS="di=01;31":"fi=01;37":"ex=01;32":"ln=01;36":"so=01;33"


#### 디렉토리 종류
- di = directory
- fi = file
- ln = linker file
- no = text
- ex = exe
- so = socket
- pi = name pipe
- bd = block device
- cd = charicter device

#### 속성
- 00 delete_method
- 01 bold
- 05 blink
- 07 reverse


### references
- http://blog.saltfactory.net/linux/change-prompt-in-terminal.html
