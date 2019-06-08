## Git Basic
### 기본
* `git clone` : Repository를 가져온다.
* `git add <filename>` : 깃에 추가
* `git status` : 깃에 대한 변경사항을 보여줌
* `git log`: 깃 활동에 대한 로그 보여줌
* `git commit` : 깃 Commit
* `git push` : Commit한 것을 레파지토리에 추가

### Tip
* `git rm --cached "filename" `: git add 명령어로 추가한것을 취소
* `git mv --force myfile MyFile` :  Git 파일의 대소문자 변경
* `git reset --soft HEAD~` : 마지막 Commit을 취소한다.
* `git reset [--soft/mixed/hard] <TARGET_VERSION>` : 특정 Commit 취소한다.
* `git reset --merge ORIG_HEAD` or `git reset --hard ORIG_HEAD` : 마지막 Merge 취소한다.

### Upstream에서 바뀐 내용 가져오기
1. `git remote add upstream "git url"` : 'remote`를 추가하고 그것을 'upstream'이라 부르게한다. (이미 했으면 생략 가능) 
2. `git fetch upstream` : 'upstream'으로 부터 정보를 가져온다.
3. `git checkout master` : 나의 'master branch'에서 checkout한다.
4. `git rebase upstream/master` : 내 'master branch'를 rewrite한다.
5. `git push -f origin master` : Merge한것을 Push한다.

### Git으로 branch만들고 내용 푸쉬하기
1) git checkout -b [name_of_your_new_branch]

2) git push origin [name_of_your_new_branch] : 깃허브에 브렌치 인식

3) git add

4) git commit

5) git push --set-upstream origin [name_of_your_new_branch]

### Git branch
git branch -d the_local_branch : 삭제

### Git confilct 해결
git checkout -- <file name>
git reset --hard
