## Linux Booting

### 전원 스위치 ON
- 시스템 전원 공급
- 메인보드의 ROM-BIOS에 있는 BIOS프로그램 실행
- BIOS프로그램은 메모리의 특정번지에 자동로드
- Cpu는 특정번지의 BIOS프로그램을 자동 실행

### BIOS 실행내용
- 자체진단기능(POST)실행
- 부팅매체(하드디스크등)검색
- 부팅매체에 있는 부트로더(GRUB) 로드함
- 부트로더가 메모리에 적재되면 BIOS는 종료되고, 시스템 제어권은 부트로더가 갖게됨

### 부트로더 실행
- GRUB는 실행과 함께 /boot/grub/grub.conf파일을 읽어서 어떤 부팅메뉴(커널)로 부팅할 것이가를 결정
- GRUB는 커널 이미지를 불러드리고 시스템 제어권을 커널에게 넘겨줌

### 커널의 로딩
- 커널은 Swapper프로세스(PID 0)를 호출함
- swapper는 커널이 사용할 각 장치드라이브들을 초기화하고 init프로세스(PID 1)를 실행
- init프로세스가 실행되면서 /etc/inittab파일을 읽어 그 내용들을 차례대로 실행

### init프로세스 실행내용들
- `name : level-number : options : process -options`
- name : 각 행의 이름들
- level-number : 해당 행의 설정내용을 어떤 부팅레벨에서 실행할 것인가를 설정
- options : 다음에 오는 process를 실행할때 적용할 프로세스 속성(wait, off, sysinit, powerfail, respawn, once, initdefault, powerokwait등)
- 부팅 레벨 : 0(halt), 1(single user mode), 2(multiuser without NFS), 3(Full multiuser mode), 4(unused), 5(X11), 6(reboot)

#### `/etc/rc.d/rc.sysinit`파일 실행
- 시스템 초기화하는 스크림트들이 주로 들어있다.
- default path설정
- /etc/sysconfig/network파일 실행
- 네트워크 설정 확인 및 적용
- fsck실행으로 파일시스템 점검하기 (`/etc/fstab`파일 참조)
- kernel module loading등

#### 부팅레벨
- `I0:0:wait/etc/rc.d/rc 0` -> 부팅레빙이 0이면, /etc/rc.d/rc0.d의 파일들이 순차대로 실행됨.
- 결정된 부팅레벨에 따라서 조건에 맞는 행을 실행
- 각 서비스데몬(sshd, sendmail...)들이 실행되며, 서비스데몬들의 로딩되는 메시지들이 부팅화면에 출력
- 디렉토리들에는 링크파일들이 있으며, /etc/rc.d/init.d/디렉토리의 파일들로 링크되어 있다.
- 각 디렉토리의 마지막에는 /etc/rc.d/rc.local파일이 실행되는 링크파일이 있다.

#### `ca::ctrlatdel:/sbin/shutdown -t3 -r now`
- ctrl + alt + del 키를 동시에 눌렀을때 실행될 내용을 설정한 행
- `ca::ctrlatdel:/sbin/shutdown -t3 -r -a now`로 변경하면, ctrl+alt+del키를 사용할수 있는 사용자를 제한할수 있다.
- /etc/shutdown.allow파일에 설정된 사용자만 이키 사용가능

#### 시스템 전원공급
- `pf::powerfail:/sbin/shutdown -f -h +2 "Power Failure; System Shutting Down"`
- pf는 UPS전원이 부족할 경우 2분후에 자동적으로 시스템 종료
- `pr:12345:powerokwait:/sbin/shutdown -c "Power Restored; Shutdown Cancelled"`
- pr은 다시 전원공급 되었을때 예약된 종료실행을 자동취소시킴

#### 가상콘솔
- `1:2345:respawn:/sbin/mingetty tty1` 2,3,4,5번 부팅레벨에서만 해당되는 내용, Alt+f1을 눌렀을 때에 보여짐(첫번째 가상콘솔)
- respawn속성으로 로그인후에 콘솔화면에서 작업후에 exit으로 로그아웃하면 자동으로 로그인 창이 다시 실행됨