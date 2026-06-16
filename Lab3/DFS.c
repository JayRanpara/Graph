#include<stdio.h>


void dfs(int arr[5][5],int curr, int vis[]) {
   vis[curr] = 1;
   printf("%d",curr);

   for(int i=0; i<sizeof(arr[i])/sizeof(arr[i][0]); i++) {
      if(arr[curr][i] == 1&&vis[i] == 0) {
         dfs(arr,i,vis);
      }
   }
}


void main() {
   int arr[5][5] = {{0,1,1,0,0},{1,0,1,0,0},{1,1,0,1,1},{0,0,1,0,0},{0,0,1,0,0}};
   int vis[5] = {0};
   dfs(arr,0,vis);
}