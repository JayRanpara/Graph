import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EdgeConnectivity{
   public static void helper(int n,ArrayList<ArrayList<Integer>>list,ArrayList<Integer>list2) {
      if(n == -1) {
         list.add(new ArrayList<>(list2));
         return;
      }
      ArrayList<Integer>newlist2 = new ArrayList<>(list2);
      newlist2.add(n);
      helper(n-1, list, newlist2);
      helper(n-1, list, list2);
   }

   public static void dfs(boolean vis[], int arr[][], int curr) {
      vis[curr] = true;

      for(int i=0; i<arr[curr].length; i++) {
         if(arr[curr][i] == 1 && !vis[i]) {
            dfs(vis,arr,i);
         }
      }
   }
   public static void main(String args[]) {
      int [][] arr = {{0,1,0,1},{1,0,1,0},{0,1,0,1},{1,0,1,0}};

      int countedge = 0;

      for(int i=0; i<arr.length; i++) {
         for(int j=0; j<arr[0].length; j++) {
            if(arr[i][j] == 1) {
               countedge ++ ;
            }
         }
      }

      countedge/=2;

      ArrayList<ArrayList<Integer>>adj = new ArrayList<>();

      

      for(int i=0; i<arr.length; i++) {
         for(int j=0; j<arr[0].length; j++) {
            if(arr[i][j] == 1) {
               if(!adj.contains(Arrays.asList(j,i))) {
                  adj.add(new ArrayList<>(Arrays.asList(i,j)));
               }
            }
         }
      }

      System.out.println(adj);

      int size = adj.size();
      // System.out.println(size);

      ArrayList<ArrayList<Integer>>helper = new ArrayList<>();

      helper(size-1, helper, new ArrayList<>());

      
      Collections.sort(helper,(a,b)->{
         return a.size() - b.size();
      });
      System.err.println(helper);

      

      for(ArrayList<Integer>list:helper) {
         int isbreak = 0;
         for(int el:list) {
            ArrayList<Integer> list2 = adj.get(el);
            int src = list2.get(0);
            int desc = list2.get(1);

            arr[src][desc] = 0;
            arr[desc][src] = 0;


            // dfs(vis,arr,0);

            
            // arr[src][desc] = 1;
            // arr[desc][src] = 1;
         }  
         boolean vis[] = new boolean[4];


         dfs(vis,arr,0);
         for(int i=0; i<4; i++) {
               if(!vis[i]) {
                  isbreak = 1;
                  System.out.println("edge connectivity = "+list.size());
               }
            }

         for(int el:list) {
            ArrayList<Integer> list2 = adj.get(el);
            int src = list2.get(0);
            int desc = list2.get(1);

            // arr[src][desc] = 0;
            // arr[desc][src] = 0;


            // dfs(vis,arr,0);

            
            arr[src][desc] = 1;
            arr[desc][src] = 1;
         }  
         if(isbreak == 1) {
            break;
         }
         // System.out.println();
      }
   }
}