
/* MP8 - Tree Recovery

	Members:
	Asilo, Marvaux Jean T.
	Chua, Orjan A.
	Garcia, Raphael Taylor F.
	Jimenez, Carl Justin P.

	BSCS 2-2
*/

#include<stdio.h>
#include<string.h>

char preOrd[26];
char inOrd[26];
int j;

void postOrder(int firstChar,int lastChar)
{
	int i;

	if(firstChar > lastChar)
		return;

	for(i = 0; i < lastChar; i++){

		if(preOrd[j] == inOrd[i]) 
			
			break;

	}

	j++;

	postOrder(firstChar, i - 1);

	postOrder(i + 1, lastChar);

	printf("%c",inOrd[i]);

}
int main(){

	int len;

	FILE *fp;

	fp = fopen("mp8input01.txt", "r");

		
	while(fscanf(fp, "%s%s", preOrd, inOrd) != EOF){

		j = 0;
		
		len = strlen(inOrd);

		postOrder(0, len - 1);

		printf("\n");

	}
	
	return 0;

	fclose(fp);
}
