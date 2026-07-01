// #include<stdio.h>
// #define size 10

// int queue[size];
// int front = -1;
// int rear = -1;

// int distance[6][6];
// void enqueue(int data) {
//    if(front == -1) {
//       front = 0;
//    }
//    if(rear == size-1) {
//       printf("queue is full");
//       return;
//    }
//    queue[++rear] = data;
// }
// int dequeue() {
//    if(front == -1) {
//       return -1;
//    }
   
//    int top = queue[front];
//    if(front == rear) {
//       front = rear = -1;
//    }
//    else {
//       front++;
//    }
//    return top; 
// }

// int isEmpty() {
//    return front == -1 && front == rear;
// }

// void bfs(int i, int arr[6]) {
//    int vis[6] = {0};
//    enqueue(i);

//    while(!isEmpty()) {
//       int curr  = dequeue();

//       if(vis[curr] == 0) {
//          vis[curr] = 1;

//          for(int j=0;  j<6; j++) {
//             if(arr[j] == 1&&vis[j] == 0) {
//                distance[i][j] = distance[i][curr]+1;
//                enqueue(j);
//             }
//          }
//       }
//    }
// }

// void main() {
//    int mat[6][6] = {{0,1,0,0,0,0},{1,0,1,1,0,0},{0,1,0,0,0,0},{0,1,0,0,1,1},{0,0,0,1,0,0},{0,0,0,1,0,0}};

//    // int deg[6] = {0};

//    // for(int i=0; i<6; i++) {
//    //    int sum = 0;
//    //    for(int j=0; j<6; j++) {
//    //       sum+=mat[i][j];
//    //    }
//    //    deg[i] = sum;
//    // }

//    // for(int i=0; i<6; i++) {
//    //    printf("%d",deg[i]);
//    // }

//    // printf("pendant vertex");

//    // for(int i=0; i<6; i++) {
//    //    if(deg[i] == 1){
//    //       printf("%d",i);
//    //    }
//    // }

//    for(int i=0; i<6; i++) {
//       for(int j=0; j<6; j++) {
//          if(i == j) {
//             distance[i][j] = 0;
//          }
//          else {
//             distance[i][j] = -1;
//          }
//       }
//    }

   
//    for(int i=0; i<6; i++) {
//       bfs(i,mat[i]); 
//    }
   

//    for(int i=0; i<6; i++) {
//       for(int j=0; j<6; j++) {
//          printf("%d",distance[i][j]);
//       }
//       printf("\n");
//    }
// }


#include<stdio.h>
#define size 10

int queue[size];
int front = -1;
int rear = -1;

int distance[6][6];

void enqueue(int data)
{
    if(front == -1)
        front = 0;

    if(rear == size-1)
    {
        printf("Queue Full\n");
        return;
    }

    queue[++rear] = data;
}

int dequeue()
{
    if(front == -1)
        return -1;

    int x = queue[front];

    if(front == rear)
        front = rear = -1;
    else
        front++;

    return x;
}

int isEmpty()
{
    return front == -1;
}

void bfs(int start, int mat[6][6])
{
    int vis[6] = {0};

    enqueue(start);

    while(!isEmpty())
    {
        int curr = dequeue();

        if(vis[curr])
            continue;

        vis[curr] = 1;

        for(int j=0;j<6;j++)
        {
            if(mat[curr][j]==1 && vis[j]==0)
            {
                if(distance[start][j]==-1)
                    distance[start][j]=distance[start][curr]+1;

                enqueue(j);
            }
        }
    }
}

int main()
{
    int mat[6][6]={
        {0,1,0,0,0,0},
        {1,0,1,1,0,0},
        {0,1,0,0,0,0},
        {0,1,0,0,1,1},
        {0,0,0,1,0,0},
        {0,0,0,1,0,0}
    };

    for(int i=0;i<6;i++)
    {
        for(int j=0;j<6;j++)
        {
            if(i==j)
                distance[i][j]=0;
            else
                distance[i][j]=-1;
        }
    }

    for(int i=0;i<6;i++)
    {
        front = rear = -1;
        bfs(i, mat);
    }

    printf("Distance Matrix:\n");

    for(int i=0;i<6;i++)
    {
        for(int j=0;j<6;j++)
            printf("%2d ",distance[i][j]);

        printf("\n");
    }

    return 0;
}