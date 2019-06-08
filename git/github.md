## Github
### ssh키 등록
1)
> git config --global user.name "이름"

> git config --global user.email"깃에 등록 이메일"

2)
ls -al ~/.ssh : 터미널에서 등록된 키를 확인한다. id_rsa.pub 등이 있다. 없다면 새로 만든다.

3)
ssh-keygen -t rsa -C “깃에 등록 이메일”

4)
> Enter passphrase (empty for no passphrase):

> Enter same passphrase again:

> (비밀번호를 입력하라고 하면 비밀번호를 입력해준다. - 안쓰는걸 추천)

5)
> eval “$(ssh-agent -s)”

> ssh-add ~/.ssh/id_rsa

> (새로운 키를 에이전트에 추가한다.)

6)
pbcopy < ~/.ssh/id_rsa.pub(새로 만든 키를 클립보드에 저장한다)

7)
> github.com 에 로그인하고 Settings에 들어가 SSH Keys 메뉴를 선택한다. 

> Add 버튼을 선택한 후 클립보드에 복사된 내용을 붙여 넣는다.

8)
> ssh -T git@github.com

> 터미널에 입력하여 확인한다. 다음과 같은 메세지가 나타나면 성공.

> `Hi username! You've successfully authenticated, but GitHub does not provide shell access.`

### refernces
- https://hojunpark.wordpress.com/2015/01/06/깃헙github에-ssh-키-만들고-등록하기/
