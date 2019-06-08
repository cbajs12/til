## Virtualenv & Virtualwrapper
- virtualenv는 개발환경을 가상으로 만들어 분리해서 사용함으로써 버전이 꼬이는것을 방지한다.
- virtualwrapper는 virtualenv를 편하게 사용 할 수 있는 여러가지 도구를 제공하는 패키지이다.

### 간편하게 python2/3 설정
#### python2
> virtualenv envname

#### python3
> virtualenv -p python3 envname

### 파이썬 버전별 virtualenv설정하기
#### python2 virtualenv 설치
> pip install --user virtualenv

> vi ~/.bash_profile

> alias virtualenv2='~/Library/Python/2.7/bin/virtualenv'

> source ~/.bash_profile

#### python2 virtualenv 사용
> virtualenv2 project_file_name

> source project_file_name/bin/activate

#### python3 virtualenv 설치
> pip3 install --user virtualenv

> vi ~/.bash_profile

> alias virtualenv3='~/Library/Python/3.4/bin/virtualenv'

> source ~/.bash_profile

#### python3 virtualenv 사용
> virtualenv3 project_file_name

> source project_file_name/bin/activate

- deactive를 원하면 `deactivate`를 사용하면된다.

#### reference
- http://joebergantine.com/blog/2015/apr/30/installing-python-2-and-python-3-alongside-each-ot/

### virtualenv와 virtualwrapper 사용하기
#### 환경설정
- virtualenv가 설치되었다면, 가상환경들이 설치될 디렉토리를 만들어주고 환경설정에 추가해줘야 한다. -> `mkdir ~/.virtualenvs`

> pip install virtualenvwrapper  //virtualenvwrapper 설치

- virtualwrapper를 사용할 때, virtualenv를 어디서 참조하는지, 어디에 가상환경을 구성하는지 설정을 해주어야 한다.

> vi .bash_profile

> export WORKON_HOME=~/.virtualenvs  //Write it at the end of line

> source /usr/local/bin/virtualenvwrapper.sh

> source .bash_profile //write on bash

- pip를 가상환경에 구속해주어야 한다.

> export PIP_REQUIRE_VIRTUALENV=true -> 가상환경 안에서만 pip가 실행되도록 한것

> export PIP_DOWNLOAD_CACHE=$HOME/.pip/cache -> 이미 설치된 패키지를 다른 가상환경에 설치하려 하면 다시 다운로드 받지 않도록 한다.

- pip를 가상환경 안에서만 종속시켰기 때문에 global한 설치할수 없다.
```vi
#syspip 혹은 syspip3를 이용하여 global한 pip를 사용할수있다.
syspip(){
    PIP_REQUIRE_VIRTUALENV="" pip "$@"
}
syspip3(){
    PIP_REQUIRE_VIRTUALENV="" pip3 "$@"
}
```
- pycharm과 연동하려면, 환경설정에서 파이썬 인터프리터를 virtualenv폴더 안에 있는 인터프리터를 골라주면 된다.

#### reference
- http://gadgetlip.tistory.com/entry/Python에서-Virtualenv-사용하기
- http://intersnipe.blogspot.kr/2015/01/mac-django-virtualenv-virtualenvwrapper.html
