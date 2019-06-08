### Basic

#### setting
- '.profile' - 로그인할 때 로드된다. PATH처럼 로그인할 때 로드해야 하는데 bash와 상관없는 것들을 여기에 넣는다.
- '.bash_profile' - 로그인할 때 로드된다. 'bash completion'이나 'nvm'같이 로그인할 때 로드해야 하는데 Bash와 관련된 것이면 여기에 넣는다.
- '.bashrc' - 로그인하지 않고 Bash가 실행될 때마다 로드된다.
- alias설정 -> .bash_profile안에 `alias <alias_name> = 'command'`작성후, `source .bash_profile`로 적용

#### reference
- https://dogfeet.github.io/articles/2012/bash-profile.html
