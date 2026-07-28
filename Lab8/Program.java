import java.util.ArrayList;
import java.util.Collections;

public class Program {

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

   public static void ischeck(ArrayList<Integer>list,ArrayList<ArrayList<Integer>>adj,boolean vis[]) {
      for(int val:list) {
         vis[val] = true;

         for(int v:adj.get(val)) {
            vis[v] = true;
         }
      }
   }

   public static void ischeck2(ArrayList<Integer>list,ArrayList<ArrayList<Integer>>adj,boolean vis[]) {
      for(int val:list) {
         // vis[val] = true;

         for(int v:adj.get(val)) {
            vis[v] = true;
         }
      }
   }

   public static void main(String[] args) {
      int adj[][] = {{0,1,1,0,0,0},{1,0,1,1,0,0},{1,1,0,0,1,0},{0,1,0,0,1,1},{0,0,1,1,0,1},{0,0,0,1,1,0}};

      ArrayList<ArrayList<Integer>>adjlist = new ArrayList<>();


      for(int i=0;i<adj.length; i++) {
         adjlist.add(new ArrayList<>());
      }

      for(int i=0; i<adjlist.size(); i++) {
         for(int j=0; j<adj[0].length; j++) {
            if(adj[i][j] == 1) {
               adjlist.get(i).add(j);
            }
         }
      }

      // System.out.println(adjlist);

      ArrayList<ArrayList<Integer>>allpair = new ArrayList<>();

      helper(5, allpair, new ArrayList<>());

      // System.out.println(allpair);

      
      Collections.sort(allpair,(a,b)->{
         return a.size() - b.size();
      });


      System.out.println(allpair);
      
      for(ArrayList<Integer>list:allpair) {
         boolean vis[] = new boolean[6];
         ischeck(list,adjlist,vis);
         
         boolean isvalid = true;
         for(int i=0; i<vis.length; i++) {
            if(vis[i] == false) {
               isvalid = false;
            }
         }

         if(isvalid) {
            System.out.println("domination number ");
            System.err.println(list.size());
            break;
         }
      }

      for(ArrayList<Integer>list:allpair) {
         boolean vis[] = new boolean[6];
         ischeck2(list,adjlist,vis);
         
         boolean isvalid = true;
         for(int i=0; i<vis.length; i++) {
            if(vis[i] == false) {
               isvalid = false;
            }
         }

         if(isvalid) {
            System.out.println("total domination number ");
            System.err.println(list.size());
            break;
         }
      }
   }
}