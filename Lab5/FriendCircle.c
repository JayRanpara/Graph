#include<stdio.h>
#define size 4

int vis[size];

void dfs(int arr[4][4],int curr) {
   vis[curr] = 1;
   

   for(int i=0; i<sizeof(arr[i])/sizeof(arr[i][0]); i++) {
      if(arr[curr][i] == 1&&vis[i] == 0) {
         dfs(arr,i);
      }
   }
}


void main() {
   int arr[size][size] = {{0,1,0,0},{1,0,0,0},{0,0,0,1},{0,0,1,0}};
   int count = 0;

   for(int i=0; i<size; i++) {
      if(vis[i] == 0) {
         count++;
         dfs(arr,i);
      }
   }
   printf("%d",count);
}