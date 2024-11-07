package MSTtest;
import java.util.*;
import java.io.*;

public class KruskalAlgo{
    public int v,e,count,c=1;
    public double cost,min;
    public double graph[][];
    public int array[];
    public double ui(int option) throws IOException {
        System.out.println("*****Kruskal Algorithm*****");
        int i, j;
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
        
        // Read the number of vertices
        if ((line = input.readLine()) != null) {
            v = Integer.parseInt(line);
        } else {
            // Handle file format error or empty file
            System.err.println("Error: No data found in the file or invalid format.");
            return 0;
        }
        
        // Read the number of edges
        if ((line = input.readLine()) != null) {
            e = Integer.parseInt(line);
        } else {
            // Handle file format error or empty file
            System.err.println("Error: Insufficient data in the file or invalid format.");
            return 0;
        }
        graph = new double[v][v];
        count = v - 1;
        array = new int[v];
        
        for (i = 0; i < v; i++) {
            for (j = 0; j < v; j++) {
                graph[i][j] = 0;
            }
        }
        
        for (i = 0; i < v; i++) {
            array[i] = 0;
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
        double startTime = new Date().getTime();
        this.algo();
        double endTime = new Date().getTime();
        double time = endTime - startTime;
        System.out.println("Execution time: " + time/1000 + " Second");
        return (time/1000);
    }
    
    
    public void algo(){
        System.out.println("The selected edges are...");
        while(count>0){
            int i,j,p=0,q=0;
            boolean ucomp;
            min = Double.MAX_VALUE;
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
                cost=cost+graph[p][q];
                System.out.println("Edge: "+ p +" , "+q+" Weight: "+graph[p][q]);
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

}