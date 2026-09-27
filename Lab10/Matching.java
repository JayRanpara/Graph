import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Matching{
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
      int [][] arr = {{0,0,0,1,1,0},{0,0,0,0,1,1},{0,0,0,0,0,1},{1,0,0,0,0,0},{1,1,0,0,0,0},{0,1,1,0,0,0}};

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


      System.out.println("matching");
      ArrayList<ArrayList<Integer>>mat = new ArrayList<>();

      for(ArrayList<Integer>list:helper) {
         int freq[] = new int[arr.length];
         boolean isValid = true;

         for(int val:list) {
            int u = adj.get(val).get(0);
            int v = adj.get(val).get(1);

            freq[v]++;
            freq[u]++;
         }


         for(int i=0; i<freq.length; i++) {
            if(freq[i]>1) {
               isValid= false;
            }
         }

         if (isValid) {
            mat.add(list);
         }
      }

      System.out.println(mat);

      // maximul

      ArrayList<ArrayList<Integer>>maximal = new ArrayList<>();

      for(ArrayList<Integer>list:mat) {

         int freq[] = new int[arr.length];


         for(int val:list) {
            int u = adj.get(val).get(0);
            int v = adj.get(val).get(1);

            freq[v]++;
            freq[u]++;
            
            // for(ArrayList<Integer>list2:adj) {
            //    int u1 = list2.get(0);
            //    int v2 = list2.get(1);

               
            //    if(u1!=u&&v!=v2) {

            //       if(!(freq[u1]>1)) {
            //          freq[u1]++;
            //       }
            //       if(!(freq[v2]>1)) {
            //          freq[v2]++;
            //       }
            //    }
            // }
           

         }
          boolean flag = true;

            for(ArrayList<Integer>list2:adj) {
               int u1 = list2.get(0);
               int v2 = list2.get(1);

               if(freq[u1]==0&&freq[v2]==0) {
                  flag = false;
                  break;
               }
            }

         

         if(flag) {
            maximal.add(list);
         }


      }
      System.out.println("maximal");
      System.out.println(maximal);

      System.out.println("maximum");
      int size2 = mat.get(mat.size()-1).size();

      ArrayList<ArrayList<Integer>>maximum = new ArrayList<>();

      for(ArrayList<Integer>list:mat) {
         if(list.size() == size2) {
            maximum.add(list);
         }
      }

      System.out.println(maximum);


      System.out.println("perfect matching");
      ArrayList<ArrayList<Integer>>perfect = new ArrayList<>();
      for(ArrayList<Integer>list:mat) {
         boolean vis[] = new boolean[arr.length];

         for(int val:list) {
            int u = adj.get(val).get(0);
            int v = adj.get(val).get(1);

            vis[u] = true;
            vis[v] = true;
         }

         boolean isValid = true;
         for(int i=0; i<vis.length; i++) {
            if(vis[i] == false) {
               isValid=false;
               break;
            }
         }
         
         if(isValid) {
            perfect.add(list);
         }
      }

      System.out.println(perfect);
   }
}