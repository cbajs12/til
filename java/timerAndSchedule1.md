### Timer & Schedule 1
#### ScheduledExecutorService
- Java의 ThreadPool로서 1개 혹은 2개 이상의 Thread를 실행시키고 싶을때 사용하는것.
- 주기적으로 실행되는 SingleThreadPool도 존재 `Executors.newSingleThreadScheduledExecutor();`

#### newSingleThreadScheduledExecutor
- 싱글스레드의 executor를 만들어서 주기적인 작업을 처리할때 사용한다.
- 스레드의 작업들은 병렬로 작동하지않고, 하나의 한 작업만 작동한다.
- 주기적인 작업의 제어를 위한 메소드 `schedule()`, `scheduleAtFixedRate()`, `scheduleWithFixedDelay()`등을 가지고 있다.

#### schedule()
- 초기 일정시간 지난후 작업을 한번만 진행한다.

#### scheduleAtFixedRate()
- 초기 일정시간 지난후 interval시간마다 작업을 실행한다. 이전 작업이 끝나지 않았다면, 작업이 taskQueue에 저장되고 이것이 심화되면 에러가 날수있다.

#### scheduleWithFixedDelay()
- 초기 일정시간 지난후 작업을 시작하고, 작업이 끝난후 delay시간후에 작업을 실행한다.

#### References
- `http://qiita.com/opengl-8080/items/ee8e926cf75e4d6058a2`
- `http://stackoverflow.com/questions/21436761/working-of-newsinglethreadscheduledexecutor-if-thread-already-busy`
