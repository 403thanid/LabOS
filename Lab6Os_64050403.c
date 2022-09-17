//64050403 นายฐานิศร์ จรูญสิริเศรษฐ์
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int sum;
int sumChild;
int sumMother = 0;
int i;
void *runner(void *param);
int main(int argc, char *argv[]) {
	pthread_t tid;
	pthread_attr_t attr;
	if (argc != 2) {
		fprintf(stderr, "usage: pthread <integer value>\n");
		exit(1);
	}
	if (atoi(argv[1]) < 0) {
		fprintf(stderr, "number must be >=0\n");
		exit(2);
	}
	pthread_attr_init(&attr);
	pthread_create(&tid, &attr, runner, argv[1]);

    int sumInput = atoi(argv[1]) ;
	sumMother = 0;
	if (sumInput > 0) {
		for (i = 0; i <= sumInput; ++i) {
			sumMother += i;
		}
	}

	printf("mother sum = %d\n", sumMother);
	pthread_join(tid, NULL); //ถ้าไม่มี Question5
    //Q5 1)แสดงลูกก่อนแม่แล้วแสดงผลลัพธ์ 2)แสดงผลแม่และผลลัพธ์เบยโดยไม่แสดงลูก 3)แสดงแม่แล้วผลลัพธ์ก่อนแล้วแสดงลูกออกมา
    sum = sumChild - sumMother;
    printf("sum (mother - child) = %d\n", sum);
}
void *runner (void *param) {
	int sumInput = atoi(param)*2;
	sumChild = 0;
	if (sumInput > 0) {
		for (i = 0; i <= sumInput; ++i) {
			sumChild += i;
		}
	}
    printf("child  sum = %d\n", sumChild);
	pthread_exit(0);
}
