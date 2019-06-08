## umask
- umask는 파일 또는 디렉토리 생성시에 파일과 디렉토리의 퍼미션(permission)을 설정하기 위한 마스크값을 설정하거나 확인하는 쉘 내부 명령어이다.
- 이 umask 값이란 모든 계정 사용자들에게 존재하는 값으로서 각 계정 사용자들이 생성 하는 파일 또는 디렉토리의 퍼미션을 결정하기 위한 값이다.
- 기본값으로 umask 값은 022 이다,umask 값이 022일 경우에 새로 생성되는 파일의 퍼미션은 644이며, 새로 생성되는 디렉토리의 퍼미션은 755이다.
- umask : 000, file : 666, directory : 777
- umask : 001, file : 665, directory : 776
- umask : 002, file : 664, directory : 775
- umask : 022, file : 644, directory : 755

### reference
- http://klero.tistory.com/entry/umask-관리
