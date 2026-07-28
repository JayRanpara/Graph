#include<stdio.h>
#define size 5

int vis[size];
int braches[size][size];
int chords[size*size][2];
int queue[size];
int front = -1;
int rear = -1;
int result[100][2];
int arr2[5][5] = {{0,1,1,0,0},{1,0,1,1,0},{1,1,0,0,1},{0,1,0,0,1},{0,0,1,1,0}};
int chordCount = 0;

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
void fundamentalCutset(int arr[size][size]) {
    for(int bsrc = 0; bsrc < size; bsrc++) {
        for(int bdest = 0; bdest < size; bdest++) {
            if(braches[bsrc][bdest] == 1) {   // branch edge
                for(int c = 0; c < chordCount; c++) {  // loop over chords
                    int csrc = chords[c][0];
                    int cdest = chords[c][1];

                    // remove branch + chord
                    arr[bsrc][bdest] = 0;
                    arr[csrc][cdest] = 0;

                    // reset visited
                    for(int v = 0; v < size; v++) vis[v] = 0;
                    dfs(arr, 0);

                    // check connectivity
                    int disconnected = 0;
                    for(int v = 0; v < size; v++) {
                        if(vis[v] == 0) disconnected = 1;
                    }

                    if(disconnected) {
                        printf("Cutset: branch (%d,%d), chord (%d,%d)\n",
                               bsrc, bdest, csrc, cdest);
                        // break if you only want the first cutset
                        // return; 
                    }

                    // restore edges
                    arr[bsrc][bdest] = 1;
                    arr[csrc][cdest] = 1;
                }
            }
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
   // printf("\nchords.\n");
   for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            if(arr[i][j] == 1 && braches[i][j] == 0 && braches[j][i] == 0) {
                chords[chordCount][0] = i;
                chords[chordCount][1] = j;
                chordCount++;
            }
        }
    }

   fundamentalCutset(arr);
   // for(int i=0; i<sizeof(chords)/sizeof(chords[0]); i++) {
   //    printf("\n%d",chords[i][0]);
   //    printf("%d\n",chords[i][1]);
   // }

}