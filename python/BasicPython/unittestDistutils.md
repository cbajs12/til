## unittest & distutils
###  unittest module
- 테스트를 수행할 클래스를 만드는, 반드시 unittest.TestCase 클래스를 상속받아야한다.
- test로 시작하는 멤버 메서드안에 assert로 시작하는 함수로 테스트 결과를 확인하는 작업을 해야만, unittest.main()이 호출되었을때 제대로 테스트를 수행한다.
- assertEqual(first, second, msg) : first와 second가 같은지 테스트 한다. 같은 않은 경우 테스트가 실패하며 msg를 출력
- assertNotEqual(first, second, msg) : first와 second가 다른지 테스트, 같은 경우 테스트가 실패하며 msg 출력
- assertTrue(expr, msg) : expr이 True인지 테스트, False인경우 테스트 실패하며 msg 출력
- assertFalse(expr, msg) : expr이 False인지 테스트, True인경우 테스트 실패하며 msg 출력

#### TestSuite
- 테스트 케이스나 테스트 스위트의 집합을 의미
- unittest.TestSuite()

####  FunctionTestCase
- 기존 작성된 함수를 이용해 TestCase를 만드는 방법
- unittest.FunctionTestCase()

### pydoc
- 자동으로 help() 함수와 거의 동일한 스타일의 도움말을 생성해주는 툴

### doctest
- 예제 코드도 데스트 데이터로 쓸수 있게 해주는 툴

### distutils
- python, c 확장 모듈을 모은 것들을 플랫폼에 종속되지 않고 배포할 수 있다.
- 배포를 하기 위해서는 setup.py라는 파일을 생성해야한다.
- distutils 모듈에 필요한 대부분의 정보는 setup() 함수의 인자로부터 전달 받는다.
- setup.py 스크립트만으로는 처리가 불가능한 경우가 생기는데 이럴때 setup configuration 파일을 생성한다.
