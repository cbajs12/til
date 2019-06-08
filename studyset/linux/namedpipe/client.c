#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
int readLine(int fd, char *str) {
	int n;
	do {
		n = read(fd, str, 1);
	} while(n > 0 && *str++ != NULL);
	return (n > 0);
}

int main(int argc, char *argv[]) {
	int fd, wr, rd, length, i, num, pid;
	char message[100];
	char buf[100];
	char buf2[100];
	do {
		fd = open("pipe", O_WRONLY);		//server와 write용 pipe오픈
		if(fd == -1) sleep(1);
	} while(fd == -1);
	pid = getpid();		//자신의 pid저장
	printf("[client]This client is %d\n",pid);
	write(fd, &pid, sizeof(pid));		//server에 pid 보냄
	sprintf(buf,"%dw",pid);			//버퍼에 server와 같은 이름 저장
	sprintf(buf2,"%dr",pid);		//버퍼에 server와 같은 이름 저장	
	//printf("%s, %s, made\n",buf,buf2);

	do {
		wr = open(buf, O_RDONLY);		//server와 pid이름이 들어간 read용 pipe오픈
		if(wr == -1) sleep(1);
	} while(wr == -1);
	do {
		rd = open(buf2, O_WRONLY);		//server와 pid이름이 들어간 write용 pipe오픈
		if(rd == -1) sleep(1);
	} while(rd == -1);
	printf("[client]Processing...\n");
	while(1){
		printf("[client]Guess number 0~99\n");
		scanf("%d",&num);		//user로부터 숫자 받음
		if(num > 100 || num < 1){		//받은 숫자가 범위을 넘을경우 이하 과정 생략
			printf("[client]Just input number 0~99 \n");
			continue;
		}
		write(rd, &num,sizeof(num));		//server로 user 숫자 보냄
		readLine(wr, message);		//server로부터 메세지 받음
		printf("%s \n", message);		//메세지 출력
		if(strlen(message) <= 30){		//정답일 경우 메세지의 길이가 30
			break;						//그 이상일경우 프로세스 종료
		}
	}
	printf("[client]Bye...\n");
	close(wr);
	close(rd);
	remove(buf);		//개별적으로 사용되는 파이프 제거
	remove(buf2);		//개별적으로 사용되는 파이프 제거
	close(fd);
	return 0;
}