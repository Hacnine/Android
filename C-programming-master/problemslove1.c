#include <stdio.h>
#include <string.h>
 
void main()
{
    int sum [15], i,j, len;
    int n,min,max;
    char string1[100];
  
    printf("Enter the string : ");
    for (i = 0; i < 15; i++)
{
    scanf("%[^\n]s", &string1[i]);
    len = 14;
        
    for (i = 0; i < 14; i++)
    {
	 sum[i] = string1[i] + string1[j];
         j++;
         printf("%d \n",sum[i]);                       //49,50,51,52
   }
}

    min = max = sum[0];
    for(i=1; i<14; i++)
    {
     if(min>sum[i])
       min=sum[i];   
   	if(max<sum[i])
	   max=sum[i];    
}
	printf("\n min %d ",min);   
	printf("\n max %d ",max); 
	
	int found = 0;
	
	for(i=0; i<15; i++)
    {
        /* 
         * If element is found in array then raise found flag
         * and terminate from loop.
         */
        if(sum[i] == min)
        {
            found = 1;
            break;
        }
    }
    
    if(found == 1)
    {
        printf("\n%d is found at index %d and the letters are %c and %c.", min, i,string1[i],string1[i+1] );
    }

  return 0;

}


/*
test case
123456789:;<=>?

1sbv%f@AdB(fek9

*/

