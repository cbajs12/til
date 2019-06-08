## Date
- 한 시점의 시간을 표현하는 정보를 묶어서 `struct_time` 시퀸스 객체로 표현한다.
- `struct_time` 객체의 모든 속성은 읽기 전용
- tm_year, tm_mon, tm_hour등이 존재

### time module
- time.time() : 1970 1/1 자정 이후로 누적된 초를 float 단위로 반환
- time.sleep(secs) : 현재 동작중인 프로세스를 주어진 초만큼 정지
- time.gmtime([secs]) : 입력된 초를 변환해, UTC 기준의 `struct_time` 시퀸스 객체로 반환한다.
- time.asctime([t]) : `struct_time` 시퀸스 객체를 인자로 받아 모듈에 지정된 형태로 변환
- time.strptime(string[, format]) : 사용자가 정의한 형식 문자열을 `struct_time` 객체로 반환
- time.strftime(format[, t]) : `struct_time` 객체를 사용자가 정의한 형식으로 변경해 문자열로 반환

### datetime module
#### date class
- 그레고리안 달력의 날짜를 표현 (datetime.date(year, month, day))
- date.fromtimestamp(timestamp) : 타임스탬프 값을 인자로 받아 date 객체를 반환
- date.replate(year, month, day) : 입력된 인자로 변겨된 date객체를 반환, 원본은 변경되지 않는다.
- date.isoformat : date의 객체를 yyyy-mm-dd 문자열로 반환
#### time class
- 시, 분, 초와 같은 시간을 표현
- 대부분의 함수는 date와 비슷

#### datetime class
- date class + time class
- datetime.datetime(year, month, day [, hour, minute, sceond, miscrosecond, tzinfo])
- datetime.combine(date, time) : date 객체와 time 객체를 입력받아 datetime 객체를 생성

#### timedelta class
- 두 날짜 혹은 시간 사이의 기간을 표현
- 인자가 양수인 경우 현시점으로부터 이후를 나타내고, 음수면 현 시점 이전을 나타낸다.
- timedelta는 동일한 기간을 표현하는 방식이 다양하게 표현될 수 있기 때문에, 입력된 값을 가지고 timedelta 객체에서 정규화 과정을 거쳐 유일한 표현 방식으로 변경
