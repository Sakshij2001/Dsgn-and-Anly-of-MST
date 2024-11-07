package MSTtest;
import java.util.*;

public class MSTtest{
       public static void main(String[] args) throws Exception{
        double time_kruskal,time_prims;
        Scanner sc = new Scanner(System.in);
        System.out.println("*****Select the option from below list*****");
        System.out.println("Option 1: 8 vertices");
        System.out.println("Option 2: 250 vertices");
        System.out.println("Option 3: 1000 vertices");
        System.out.print("Your option is: ");
        int option=sc.nextInt();
        KruskalAlgo kl=new KruskalAlgo();
        time_kruskal=kl.ui(option);
        PrimsAlgo pr=new PrimsAlgo();
        time_prims=pr.ui(option);
        if(time_kruskal>time_prims){
            System.out.println(time_kruskal+" > "+time_prims);
            System.out.println("Kruskal Algorithm takes more time than Prim's Algorithm");
        }
        else if(time_kruskal<time_prims){
            System.out.println(time_kruskal+" < "+time_prims);
            System.out.println("Prim's Algorithm takes more time than Kruskal Algorithm");
        }
        else if(time_kruskal==time_prims){
            System.out.println("Prim's Algorithm and Kruskal Algorithm take same time");
        }
        else
            return;
    }
}