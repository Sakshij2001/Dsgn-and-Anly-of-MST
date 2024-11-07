package MSTtest;
import java.util.*;
import java.io.*;

public class PrimsAlgo {
    int vertex =0;
    double min,total=0;
    int vertices,edge,sourceVertex, flag = 0, k = 1;
    double[][] graph;
    int[] visitedVertex;

    public double ui(int option) throws IOException{
        System.out.println("*****Prims Algorithm*****");
        Scanner sc = new Scanner(System.in);
        String dir = System.getProperty("user.dir");
        File file;
        if(option==1)
        file = new File(dir +"\\MSTtest\\LargeData\\TinyEWG.txt");
        else if(option==2)
        file = new File(dir +"\\MSTtest\\LargeData\\MediumEWG.txt");
        else if(option==3)
        file = new File(dir +"\\MSTtest\\LargeData\\1000EWG.txt");
        else{
            System.out.println("There is no such option");
            return 0;
        }

        BufferedReader input = new BufferedReader(new FileReader(file));
        String line;
        
        if ((line = input.readLine()) != null) {
            vertices = Integer.parseInt(line);
        } else {
            System.err.println("Error: No data found in the file or invalid format.");
            return 0;
        }
        
        if ((line = input.readLine()) != null) {
            edge = Integer.parseInt(line);
        } else {
            System.err.println("Error: Insufficient data in the file or invalid format.");
            return 0;
        }

        graph =new double[vertices][vertices];
        visitedVertex =new int[vertices];
        for(int v = 0; v < vertices; v++)
        visitedVertex[v] = 0;
        for(int i=0;i<vertices;i++)
        {
            for(int j=0;j<vertices;j++)
                {
                    graph[i][j]=999;
                }
        }
        while ((line = input.readLine()) != null) {
            String[] str = line.split(" ");
            int source = Integer.parseInt(str[0]);
            int destination = Integer.parseInt(str[1]);
            double weight = Double.parseDouble(str[2]);
            graph[source][destination] = weight;
            graph[destination][source] = weight; 
        }        
        input.close();
        sourceVertex = 0;
        if (sourceVertex < vertices)
            visitedVertex[sourceVertex] = 1;
        else
            System.out.println("INVALID VALUE!!!");
        double startTime = new Date().getTime();
        this.algo();    
        double endTime = new Date().getTime();
        double time = endTime - startTime;
        System.out.println("Execution time: " + time/1000 + " Second"); 
        return (time/1000);
 
    }
    
    public void algo(){
        System.out.println("The selected edges are...");
        while (k <= vertices - 1){
            min = Integer.MAX_VALUE;
            for(int i = 0;i <vertices;i++){
                for (int j = 0; j < vertices; j++){
                    if (visitedVertex[i] == 1 && visitedVertex[j] == 0){
                        if (i != j && min > graph[i][j]){
                            min = graph[i][j];
                            edge = i;
                            vertex = j;
                        }
                    }
                }
            }
            visitedVertex[vertex] = 1;
            total = total + min;
            k++;
            System.out.println(edge+"->"+vertex+"="+min);
        }
        for(int i = 0;i < vertices;i++)
            if(visitedVertex[i] == 0){
                flag = 1;
                break;
            }
        if (flag == 1)
            System.out.println("No Spanning tree.");
        else
            System.out.println("The cost of minimum Spanning tree is "+total);
    }
}