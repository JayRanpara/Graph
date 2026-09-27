import java.util.ArrayList;

public class ConstuctMatrix{
   public static void main(String[] args) {
      int M = 5;
      int edges[][] = {{1,2},{2,3},{4,5},{1,5}};
      int adjMat[][] = new int[M][M]; 

      for(int i=0; i<edges.length; i++) {
         int u = edges[i][0];
         int v = edges[i][1];

         adjMat[u-1][v-1] = 1;
         adjMat[v-1][u-1] = 1;
      }

      

      int deg[][] = new int[M][M];

      for(int i=0; i<adjMat.length; i++) {
         int count = 0;
         for(int j=0; j<adjMat[0].length; j++) {
           if(adjMat[i][j] == 1) {
               count++;
           }
         }
         deg[i][i] = count;
      }

      // for(int i=0; i<deg.length; i++) {
      //    for(int j=0; j<deg[0].length; j++) {
      //       System.out.print(deg[i][j]);
      //    }
      //    System.out.println();
      // }

      int lap[][] = new int[M][M];

      for(int i=0; i< lap.length; i++) {
         for(int j=0; j<lap[0].length; j++) {
            lap[i][j] = deg[i][j]-adjMat[i][j];
         }
      }

      for(int i=0; i<lap.length; i++) {
         for(int j=0; j<lap[0].length; j++) {
             System.out.print(lap[i][j]+" ");
         }
         System.out.println();
      }
      
   }
}