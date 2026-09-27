public class FloadWarshall{
   public static void main(String[] args) {
      int dist[][] = {{0,4,Integer.MAX_VALUE,5,Integer.MAX_VALUE},
                     {Integer.MAX_VALUE,0,1,Integer.MAX_VALUE,6},
                     {2,Integer.MAX_VALUE,0,3,Integer.MAX_VALUE},
                     {Integer.MAX_VALUE,Integer.MAX_VALUE,1,0,2},
                     {1,Integer.MAX_VALUE,Integer.MAX_VALUE,4,0}};

      
      int V = 5;

      for(int k=0; k<V; k++) {
         for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
               if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                  dist[i][j] = Math.min(dist[i][j],dist[i][k]+dist[k][j]);
               }
            }
         }
      }

      for(int i=0; i<V; i++) {
         for(int j=0; j<V; j++) {
            System.out.print(dist[i][j]+" ");
         }
         System.out.println();
      }
   }
}