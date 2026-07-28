#include<stdio.h>
#define size 6
int vis[6] = {0};


void dfs(int arr[6][6],int curr,int remove) {
   vis[curr] = 1;
   

   for(int i=0; i<sizeof(arr[i])/sizeof(arr[i][0]); i++) {
      if(arr[curr][i] == 1&&vis[i] == 0&&i != remove) {
         dfs(arr,i,remove);
      }
   }
}


void main() {
   int arr[6][6] = {{0,1,0,0,0,0},{1,0,1,1,0,0},{0,1,0,0,0,0},{0,1,0,0,1,1},{0,0,0,1,0,0},{0,0,0,1,0,0}};


   for(int i=0; i<size; i++) {
      for(int k=0; k<size; k++) {
         vis[k] = 0;
      }
      int start = -1;
      int count = 0;
      for(int j=0; j<size; j++) {
         if(j!=i) {
            start = j;
            break;
         }
      }
      dfs(arr,start,i);
      // if(vis[start]!=1) {
      //    count++;
      //    arr[start][i] = 0;
      //    dfs(arr,start);
      //    arr[start][i] = 1;
      // }
      // if(count>1) {
      //    printf("%d",i);
      // }
      int allvis = 1;
      for(int k=0; k<size; k++) {
         if(i!=k&&vis[k] == 0) {
            allvis = 0;
         }
      }
      if(allvis==0) {
         printf("%d ",i);
      }
   }
}