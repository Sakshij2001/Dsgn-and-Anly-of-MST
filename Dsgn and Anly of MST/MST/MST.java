package MST;
import java.util.*;

public class MST{
    public int v,e,count,cost=0,c=1,min,sourceVertex, flag = 0,vertex =0;
    public static int option;
    public int graph[][];
    public int array[];
    public static Scanner input = new Scanner(System.in);

    public void ui(){
        int i,j,n=1;
        System.out.print("Enter the total number of vertices in the graph: ");
        v=input.nextInt();
        count=v-1;
        System.out.print("Enter the total number of edges in the graph: ");
        e=input.nextInt();
        graph=new int[v][v];
        array=new int[v];
        for(i=0;i<v;i++){
            for(j=0;j<v;j++){
                graph[i][j]=999;
            }
        }
        for(i=0;i<v;i++){
            array[i]=0;
        }
        System.out.println("----------Please enter source and destination node with weight of it's edges----------");
        while(n<=e){
            i=j=0;
            System.out.print("Enter "+n+" source node: ");
            i=input.nextInt();
            System.out.print("Enter "+n+" destination node: ");
            j=input.nextInt();
            System.out.print("Enter weight of "+i+" - "+j+": ");
            graph[i][j]=graph[j][i]=input.nextInt();
            n++;
        }
        if(option==2){
            System.out.print("Enter the Source vertex :- ");
            sourceVertex = input.nextInt();
            if (sourceVertex < v)
                array[sourceVertex] = 1;
            else
                System.out.println("INVALID VALUE!!!");
        }
    }
    public void kruskalAlgo(){
        System.out.println("The selected edges are...");
        while(count>0){
            int i,j,p=0,q=0;
            boolean ucomp;
            min=Integer.MAX_VALUE;
            for(i=0;i<v;i++){
                for(j=0;j<v;j++){
                    if(graph[i][j]!=0 && min>=graph[i][j]){
                        min=graph[i][j];
                        p=i;
                        q=j;
                    }
                }
            }
            ucomp=find(p,q);
            if(ucomp==true){
                if(graph[p][q]!=999){
                    cost=cost+graph[p][q];
                    System.out.println("Edge: "+ p +" , "+q+" Weight: "+graph[p][q]);
                }
                count--;
            }
            graph[p][q]=graph[q][p]=0;
        }
        System.out.println("The cost of minimum Spanning tree is "+cost);
    }
    public boolean find(int p,int q){
        int i,temp;
           if(array[p]==0 && array[q]==0){
                array[p]=array[q]=c;
                c++;
                return true;
            }
            else if(array[p]==0 || array[q]==0){
                if(array[p]==0)
                    array[p]=array[q];
                else
                    array[q]=array[p];
                return true;
            }
            else if(array[p]<array[q]){
                temp=array[q];
                for(i=0;i<v;i++){
                    if(array[i]==temp)
                        array[i]=array[p];
                }
                return true;
            }
            else if(array[q]<array[p]){
                temp=array[p];
                for(i=0;i<v;i++){
                    if(array[i]==temp)
                        array[i]=array[q];
                }
                return true;
            }
            else
                return false;
    }
    public void primsAlgo(){
        System.out.println("The selected edges are...");
        while (c <= v - 1){
            min = Integer.MAX_VALUE;
            for(int i = 0;i <v;i++){
                for (int j = 0; j < v; j++){
                    if (array[i] == 1 && array[j] == 0){
                        if (i != j && min > graph[i][j]){
                            min = graph[i][j];
                            e = i;
                            vertex = j;
                        }
                    }
                }
            }
            array[vertex] = 1;
            c++;
            if(min!=999){
                cost = cost + min;
                System.out.println(e+"->"+vertex+"="+min);
            }
        }
        for(int i = 0;i < v;i++)
            if(array[i] == 0){
                flag = 1;
                break;
            }
        if (flag == 1)
            System.out.println("No Spanning tree.");
        else
            System.out.println("The cost of minimum Spanning tree is "+cost);
    }
    public static void main(String[] args){
    MST algo=new MST();
    System.out.println("Please select the algorithm for Minimum Spanning Tree");
    System.out.println("1. Kruskal Algorithm");
    System.out.println("2. Prim's Algorithm");
    System.out.println("Your option is: ");
    option = input.nextInt();
    algo.ui();
    if(option==1){
        double startTime = new Date().getTime();
        algo.kruskalAlgo();;
        double endTime = new Date().getTime();
        double time = endTime - startTime;
        System.out.println("Execution time: " + time/1000 + " Second");
    }
    else if(option==2){
        double startTime = new Date().getTime();
        algo.primsAlgo();;
        double endTime = new Date().getTime();
        double time = endTime - startTime;
        System.out.println("Execution time: " + time/1000 + " Second");
    }
    else
        return;
    }
}