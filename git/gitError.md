## Git Error
### rewinding
에러 이름 : First, rewinding head to replay your work on top of it...

해결책 : git fetch origin; git reset --hard origin/<branch>

### MERGE_HEAD
에러 이름 : You have not concluded your merge (MERGE_HEAD exists)

해결책 : 

1) git merge --abort [Since git version 1.7.4]

2) Resolve the conflict.

3) Don't forget to add and commit the merge.

4) git pull now should work fine.ll
