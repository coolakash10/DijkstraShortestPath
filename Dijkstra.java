/*********************************************************************************************************************
     **
     **  Dijkstra algorithm to find shortest path 
     **  Used data structure:
	 **				1. Limked List
	 **				2. Priority Queue
     **  Input in the form of array
     **  Output is shortest distance from source vertex
     
     **  Written By:    Akash Vishwas Londhe
     **
*********************************************************************************************************************/

//import package
import java.util.*;
import java.io.*;

public class Main
{
    //class to store edges with weight
    static class Item
    {
        int index;
        int value;
        Item(int index,int value)
        {
            this.index=index;
            this.value=value;
        }
    }
    
    static int[]dis;
    
    public static void dijkstra(LinkedList<Item>[]list,int source)
    {
        
        boolean[]visited=new boolean[list.length];
        
		//sorted as per least distance
        PriorityQueue<Item>queue=new PriorityQueue<Item>(list.length,new Comparator<Item>(){
            public int compare(Item i1,Item i2)
            {
                return i1.value-i2.value;
            }
        });
        
        queue.add(new Item(source,0));
        dis[source]=0;
     
        while(queue.size()!=0)
        {
            
            Item t=queue.poll();
            int index=t.index;
            int value=t.value;
            visited[index]=true;
            Iterator<Item>itr=list[index].iterator();
            
            while(itr.hasNext())
            {
                
                Item x=itr.next();
                if(!visited[x.index])
                if(dis[x.index]>(dis[index]+x.value))
                {
                    dis[x.index]=dis[index]+x.value;
                    queue.add(new Item(x.index,dis[x.index]));
                }
                
            }
        }
    }
    
    public static void main(String[]args) throws IOException
    {
        
        Scanner scan=new Scanner(System.in);
        
            //n =number of vertices
            int n=4; //scan.nextInt();
            //m = number of edges
            int m=4; //scan.nextInt();
            
            //array a to store edge from a[i][0] to a[i][1]
            int [][]a={{1,2},{1,4},{3,1},{4,3}};
            //array w to store weight on respective edge of arra a
            int[]w={24,20,3,12};
            LinkedList<Item>[]list=new LinkedList[n+1];
            
            for(int i=1;i<=n;i++)
                list[i]=new LinkedList();
            int x,y,r;    
            
            for(int i=0;i<m;i++)
            {
                
                x=a[i][0]; //scan.nextInt();
                y=a[i][1]; //scan.nextInt();
                r=w[i]; //scan.nextInt();
                list[x].add(new Item(y,r));
                list[y].add(new Item(x,r));
            }
            
            //source is vertex from which we want shortest distance
            int source=1; //scan.nextInt();
            
            dis=new int[n+1];
            
            Arrays.fill(dis,Integer.MAX_VALUE);
            
            dijkstra(list,source);
            
            //print shortest distance from source vertex if not exist any path then print -1
            for(int i=1;i<=n;i++)
            {
                if(dis[i]!=0)
                {
                    if(dis[i]==Integer.MAX_VALUE)
                        System.out.print(-1+" ");
                    else
                        System.out.println("From vertex 1 to "+i+" "dis[i]+" ");    
                }
                
            }

    }
}
