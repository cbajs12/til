## Date and time
- c++ std에는 적절한 date type이 없다. 그래서 c++는 c로 부터 구조체와 함수들을 상속하여 사용한다.
- `<ctime>` 헤더를 사용하면 된다.
- tm 구조체는 date와 time을 가진 형태다.
```cpp
struct tm {
   int tm_sec;   // seconds of minutes from 0 to 61
   int tm_min;   // minutes of hour from 0 to 59
   int tm_hour;  // hours of day from 0 to 24
   int tm_mday;  // day of month from 1 to 31
   int tm_mon;   // month of year from 0 to 11
   int tm_year;  // year since 1900
   int tm_wday;  // days since sunday
   int tm_yday;  // days since January 1st
   int tm_isdst; // hours of daylight savings time
}
```
- clock_t, size_t 그리고 time_t 타입들은 시스템 시간과 날짜를 integer의 형태로 표현할 수 있다.

### 함수들
- `time_t time(time_t *time);` :  1970년 1월 1일 부터 초시간으로 경과된 시스템의 현재 날짜를 반환한다.
- `char *ctime(const time_t *time);` :  로컬타임에 대한 tm  구조체 포인터를 반환한다.
- `clock_t clock(void);` :  불려진 프로그램이 러닝타임의 양 값을 반환한다.
- `char * asctime ( const struct tm * time );` : 구조체에 저장된 값을 `day month date hours:minutes:seconds year\n\0`의 형식으로 변환 한 string 포인터를 반환한다.
- `struct tm *gmtime(const time_t *time);`:  UTC로 표현된 시간의 tm 구조체의 포인터를 반환한다.
- `time_t mktime(struct tm *time);` : 구조체의 시간과 동일한 캘린더 시간을 반환 한다.
- `double difftime ( time_t time2, time_t time1 );` : time1과 time2의 초시간 단위의 차이를 계산한다.
- `size_t strftime();` : 특정 형식의 날짜와 시간으로 표현할 때 사용한다.

### 예제
```cpp
#include <iostream>
#include <ctime>

using namespace std;

int main( ) {
   // current date/time based on current system
   time_t now = time(0);
   
   // convert now to string form
   char* dt = ctime(&now);

   cout << "The local date and time is: " << dt << endl;

   // convert now to tm struct for UTC
   tm *gmtm = gmtime(&now);
   dt = asctime(gmtm);
   cout << "The UTC date and time is:"<< dt << endl;
}
```

```cpp
#include <iostream>
#include <string>
#include <stdio.h>
#include <time.h>

// Get current date/time, format is YYYY-MM-DD.HH:mm:ss
const std::string currentDateTime() {
    time_t     now = time(0);
    struct tm  tstruct;
    char       buf[80];
    tstruct = *localtime(&now);
    // Visit http://en.cppreference.com/w/cpp/chrono/c/strftime
    // for more information about date/time format
    strftime(buf, sizeof(buf), "%Y-%m-%d.%X", &tstruct);

    return buf;
}

int main() {
    std::cout << "currentDateTime()=" << currentDateTime() << std::endl;
    getchar();  // wait for keyboard input
}
```

### references
- https://www.tutorialspoint.com/cplusplus/cpp_date_time.htm
- http://stackoverflow.com/questions/997946/how-to-get-current-time-and-date-in-c

