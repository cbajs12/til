#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>
int readLine(int fd, char *str) {
	int n;
	do {
		n = read(fd, str, 1);
	} while(n > 0 && *str++ != NULL);
	return (n>0);
}
void inthandler(){		//ctrl + c 핸들러
	printf("[server]inthandler\n");
	remove("pipe");		//처음 커뮤니케이션용 pipe 제거
	exit(0);
}

int main() {
	int fd, rd, wr, length, pid, r, num, i, n, ppid;
	char message[100];
	char buf[100];
	char buf2[100];
	signal(SIGINT, inthandler);	//핸들러 제어를 위한 signal
	while(1) {
		printf("[server]server on\n");
		unlink("pipe"); mkfifo("pipe", 0660); 	//처음 커뮤니케이션용 pipe생성
		fd = open("pipe", O_RDONLY);
		printf("[server]Made and Opened pipe for read...\n");
		read(fd, &pid, sizeof(pid));	//client로부터 pid읽어옴
		printf("[server]Pid %d is connecting \n", pid);
		printf("[server]Receiving from client...\n");
		if((ppid = fork()) == 0){
			//child process
			sprintf(buf,"%dw",pid);		//버퍼에 pid + w를 조합하여 저장
			sprintf(buf2,"%dr",pid);	//버퍼에 pid + r를 조합하여 저장

			unlink(buf); 
			mkfifo(buf,0660);		//버퍼에 저장된 이름으로 write용 파이프개설
			unlink(buf2); 
			mkfifo(buf2,0660);		//버퍼에 저장된 이름으로 read용 파이프개설

			wr = open(buf,O_WRONLY);
			rd = open(buf2,O_RDONLY);
			printf("[server]Made and Opened pipes for commuication..\n");

			r = rand() % 100;	//랜덤 수 제작
			n = 1;		//카운트용 변수
			while(read(rd, &num, sizeof(num))!=0){		//읽어올것이 있으면 계속 loop
				printf("[server]Client %d is guessing\n",pid);		
				
				if(num < r){		//client에서 받아온 값과 랜덤값 비교
					sprintf(message,"Number is less than random number.\n");
					length = strlen(message) + 1;
					//printf("%d",length);
					write(wr,message,length);		//client에 메세지 보냄
				}else if(num > r){
					sprintf(message,"Number is greater than random number.\n");
					length = strlen(message) + 1;
					//printf("%d",length);
					write(wr,message,length);
				}else if(num == r){
					sprintf(message,"Number is correct in %d times\n",n);		//메세지의 길이 30
					length = strlen(message) + 1;
					//printf("%d",length);
					write(wr,message,length);
				}
				n++;	//count 증가
			}
		}else{
			printf("[server]Parent process...\n");
			//continue;
		}
	}
	close(fd);
	return 0;
}