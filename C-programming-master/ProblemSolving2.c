#include <stdio.h>
#include <string.h>

void main()
{
	int num = 10,i;
	int doubleNum = num*2;
	for ( i = 1;i<doubleNum;i++){
		if (i<11){
			printf("%d \n",i);
		}
		else{
			printf("%d \n",doubleNum - i);
		}
	}

	return 0;
}
