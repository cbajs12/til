## File Input Output
### os.path module
- os.path.abspath(path) : 현재 경로를 prefix로 해서 입력받은 경로를 절대 경로로 바꿔서 반환
- os.path.basename(path) : 입력받은 경로의 기본 이름을 반환
- os.path.commonprefix(path_list) : 입력받은 path_list로부터 공통적인 prefix를 추출해서 반환, 문자열 연산에 의한것이기 때문에 잘못된 경로 나올수 있다.
- os.path.dirname(path) : 입력받은 파일/디렉터리의 경로를 반환
- os.path.exists(path) : 입력받은 경로가 존재하면 True반환, 존재하지않거나 읽기 권한 없는 경우에도 False반환
- os.path.expanduser(path) : 입력받은 경로안의 `~`를 현재 사용자 디렉터리의 절대 경로로 대체
- os.path.getatime(path) : 입력받은 경로에 대한 최근 접근 시간을 반환
- os.path.getsize(path) : 입력받은 경로에 대한 바이트 단위의 파일크기를 반환
- os.path.isabs(path) : 경로가 절대이면 True, 아니면 False
- os.path.normcase(path) : 해당  os에 맞게 입력받은 경로의 문자열을 정규화한다.
- os.path.split(path) : 입력받은 경로를 디렉터리 부분과 파일부분으로 나눈다.

### glob
- glob.glob(path) : 경로에 대응되는 모든 파일 및 디렉터리의 리스트를 반환
- glob.iglob(path) : glob과 동일한 동작을 수행하지만 이터레이터를 반환
