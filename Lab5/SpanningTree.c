#include<stdio.h>
#define size 5

int vis[size];
int braches[size][size];
int queue[size];
int front = -1;
int rear = -1;


void enqueue(int data) {
   if(front == -1) {
      front = 0;
   }
   if(rear == size) {
      printf("queue is full");
   }
   queue[++rear] = data;
}



int dequeue() {
   if(front == -1) {
      return -1;
   }
   
   int top = queue[front];
   if(front == rear) {
      front = rear = -1;
   }
   else {
      front++;
   }
   return top; 
}

int isEmpty() {
   return front == -1 && front == rear;
}

void bfs(int start, int end) {
   int vis[5] = {0};
   enqueue(start);

   while(!isEmpty()) {
      int curr  = dequeue();

      if(vis[curr] == 0) {
         printf("%d",curr);
         vis[curr] = 1;

         for(int i = 0; i<sizeof(braches[i])/sizeof(braches[i][0]); i++) {
            if(curr==end) {
               return;
            }
            if(braches[curr][i] == 1&&vis[i] == 0) {
               enqueue(i);
            }
         }
      }
   }
}
void dfs(int arr[5][5],int curr) {
   vis[curr] = 1;
   

   for(int i=0; i<sizeof(arr[i])/sizeof(arr[i][0]); i++) {
      if(arr[curr][i] == 1&&vis[i] == 0) {
         braches[curr][i] = 1;
         dfs(arr,i);
      }
   }
}

void main() {
   int arr[5][5] = {{0,1,1,0,0},{1,0,1,1,0},{1,1,0,0,1},{0,1,0,0,1},{0,0,1,1,0}};
   dfs(arr,0);
   int nulity = 0;
   int rank = 0;
   printf("braches\n");

   for(int i=0; i<size; i++) {
      for(int j=0; j<size; j++) {
         if(braches[i][j] == 1) {
            rank++;
            printf("(%d %d)",i,j);
         }
      }  
   }
   for(int i=0; i<size; i++) {
      for(int j=0; j<size; j++) {
         if(arr[i][j] == 1) {
            arr[j][i] = 0;
         }
      }
   }
   printf("\nchords.\n");
   for(int i=0; i<size; i++) {
      for(int j=0; j<size; j++) {
         if(braches[i][j] == 0&&arr[i][j] == 1&&braches[j][i] == 0) {
            nulity++;
            printf("(%d %d)",i,j);
            
         }
      }
   }

   for(int i=0; i<size; i++) {
      for(int j=0; j<size; j++) {
         if(braches[i][j] == 0&&arr[i][j] == 1&&braches[j][i] == 0) {
            int start = i;
            int end = j;
            printf("\n");
            bfs(i,j);
            
         }
      }
   }
  printf("\n rank of graph");
  printf("%d",rank);
  printf("\n nulity of graph");
  printf("%d",nulity);
}