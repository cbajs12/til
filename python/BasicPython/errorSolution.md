## Error and Solution

### 1. pip3
#### Solution
- pip3의 경로가 python3가 아닌 python2를 가르킨다면, 긴급 해결책으로써 .bash_profile에 `alias python3 -m pip`를 설정해주면 된다.

### 2. Non-ASCII Character
#### Error
- SyntaxError: Non-ASCII character '\xec' in file D:\app\views.py on line 41, but no encoding declared; see http://python.org/dev/peps/pep-0263/ for details

#### Solution
- `# -*- coding: utf-8 -*-` 을 파일 상단에 추가한다. 대신 `#`을 제거해서는 안된다.
