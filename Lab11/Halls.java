import java.util.ArrayList;

public class Halls {

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
   public static void main(String[] args) {
      int n = 4;
      int m = 4;
      int [][] mat = {{0,0,0,0,0,1,0,1},{0,0,0,0,0,1,0,1},{0,0,0,0,1,0,1,1},{0,0,0,0,0,0,0,1},{0,0,1,0,0,0,0,0},{1,1,0,0,0,0,0,0},{0,0,1,0,0,0,0,0},{1,1,1,1,0,0,0,0}};

      ArrayList<ArrayList<Integer>>adj = new ArrayList<>();

      for(int i=0; i<n+m; i++) {
         adj.add(new ArrayList<>());
      }

      for(int i=0; i<mat.length; i++) {
         for(int j=0; j<mat[0].length; j++) {
            if(mat[i][j] == 1) {
               adj.get(i).add(j);
            }
         }
      }
      System.out.println(adj);


      ArrayList<ArrayList<Integer>>list = new ArrayList<>();
      helper(n-1, list, new ArrayList<>());

      System.out.println(list);

      boolean flag = true;
      for(ArrayList<Integer>list2:list) {
         
         int intcount = list2.size();
         int count = 0;
         boolean vis[] = new boolean[n+m];

         for(int val:list2) {
            for(int val2:adj.get(val)) {
               vis[val2] = true;
            }
         }

         for(int i=0; i<vis.length; i++) {
            if(vis[i]) {
               count++;
            }
         }

         if(count<intcount) {
            flag = false;
            System.out.println("Here we can't get perfect matching using hall's condition");
            break;
         }

      }

      if(flag) {
         System.out.println("we can get perfect matching this satisfy hall's condition");
      }

      

   }
}
