##  With C/C++
### 확장 모듈
#### 필요한 이유
- 확장 모듈을 이용해 파이썬에서 C/Cpp 라이브러리 함수 혹은 시스템 콜을 할 수 있는 새로운 내장 객체 타입을 구현 할 수 있다.
- C가 파이썬보다 빠르게 연산을 한다.
- 파이썬 코드를 C/Cpp 모듈이나, C 확장형으로 만들면 우리가 생성한 파이썬 코드의 핵심적인 부분을 그대로 공개하지 않고 배포할 수가 있다.
- 확장을 지원하려면 C/Cpp에서 python.h 헤더파일을 포함해야 한다.
- PyObject는 파이썬의 객체를 C의 데이터 타입으로 표현할 수 있는 구조체이다.

#### 모듈 초기화
- C/Cpp로 모듈을 만들었다면 모듈 초기화와 지역 이름공간에 이름을 정의하는 것을 C/Cpp에서 처리해줘야 한다.
- 모듈 초기화하려면 `Py_InitModule()`함수를 사용하면 된다.
- 초기화 함수의 이름은 반드시 `PyInit_<module_name>`형식이어야 한다.
- `PyInit_<module_name>` 함수는 반드시 생성된 모듈 객체의 포인터를 반환값으로 호출자에게 넘겨줘야 하는데, `sys.module`에 사용자 모듈을 등록하려면 반드시 필요하기 때문이다.
- 파이썬에서 인자는 세 번째 멤버 변수에 의해 어떤 형식으로 전달될지 결정된다. `METH_VARARGS`는 튜플 형태로 인자를 전달받는 것을 의미하고, `PyArg_ParseTuple` 함수를 이용해 인자를 처리 할 수 있다. `METH_KEYWORDS`는 사전 형식으로 인자를 받을때 사용한다. 네 번째 변수는 함수의 설명을 적는 부분이다.

#### 레퍼런스 카운트
- 파이썬은 레퍼런스가 필요할 때마다 malloc과 free같은 함수를 써서 새로운 메모리 공간을 확보하지 않고 타입에 대한 인스턴스의 레퍼런스 카운트를 통한 객체 참조 전략을 가지고 있다.
- 모든 객체는 자신이 참조된 횟수를 관리하는 카운터를 가지고 있다, 참조될때 마다 카운터를 하나씩 증가시키고, 참조가 해제될 때마다 레퍼런스 카운터를 하나 감소시킨다.
- 카운터가 0이 되면 그때서야 객체는 메모리 공간에서 삭제된다.

### ctypes
- `ctypes` 모듈을 이용하면 C의 데이터 타입이나, DLL 혹은 공유 라이브러리의 함수를 직접 사용할수 있다.
- 구조체와 유니온은 파이썬에서 class로 표현되기 때문에 일단 class 객체를 생성하고 `_fields_` 멤버 변수를 이용해 각 멤버 변수를 설정해 준다.
