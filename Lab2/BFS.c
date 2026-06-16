#include<stdio.h>
#define size 10

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


void main() {
   int arr[5][5] = {{0,1,1,0,0},{1,0,1,1,0},{1,1,0,0,1},{0,1,0,0,1},{0,0,1,1,0}};
   int vis[5] = {0};
   enqueue(0);

   while(!isEmpty()) {
      int curr  = dequeue();

      if(vis[curr] == 0) {
         printf("%d",curr);
         vis[curr] = 1;

         for(int i = 0; i<sizeof(arr[i])/sizeof(arr[i][0]); i++) {
            if(arr[curr][i] == 1&&vis[i] == 0) {
               enqueue(i);
            }
         }
      }
   }
}