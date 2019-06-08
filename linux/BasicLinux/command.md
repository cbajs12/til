## Command
### export 명령어
> export [환경변수]=[값]

- export 명령은 환경 변수를 지정, 변경하거나 현재 정의되어 있는 환경 변수를 보여주는 명령이다.
- 환경 변수를 변경함으로써 명령 프롬프트에서 실행한 프로그램에 영향을 미치게 된다. 다만 export 명령에 의해 변경된 환경 변수는 export 명령을 실행한 사용자의 로그아웃 전까지만 유효하며 영구적으로 변경하기 위해서는 환경 변수를 정의하는 설정 파일(/etc/profile 등) 을 직접 수정해야 한다.

### touch
> touch [옵션] 파일명

- 파일의 날짜시간정보를 변경하는 명령어이다.
- 아무런 옵션없이 사용하면 서버의 현재시간으로 파일의 최근사용한 시간과 최근변경시간을 변경한다.
- 특정옵션이 사용되지 않는다면 파일의 크기가 0인 빈파일을 생성한다.
- `-t`라는 옵션을 사용하면 서버의 현재시간이 아닌 지정된 시간으로 파일 날짜시간정보를 변경한다.

> touch newfile // 빈파일

> touch -c newfile // 현재시간으로 날짜정보 변경

> touch -t 'YYYYMMDDhhmm' newfile // 날짜정보를 마음대로 변경

> touch -r wantfile newfile // 날짜정보를 지정한 파일과 동일하게 변경

### source
> source [-h] 파일명

- 파일안의 명령어를 읽는다. 즉, 현재 쉘의 환경변수가 재설정된다.
- 해당 파일에 설정된 모든 변수들이 현재 쉘의 일부가 된다.
- bash의 내부 명령어이다. 그래서 bash쉘이 작동중일때만 동작한다.
- `source` 대신 `.`사용해도 같은 역할을 한다.

### reference
- http://eoworld.tistory.com/entry/Linux-리눅스-명령어-export
- http://webdir.tistory.com/151
- http://www.dreamy.pe.kr/zbxe/CodeClip/98064
