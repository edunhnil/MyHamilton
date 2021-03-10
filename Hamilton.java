/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Hamilton {
    int V,pathcount;
    int []path;
    int [][]graph;
    
    public void HamiltonCycle(int [][] g){
        V=g.length;
        path=new int[V];
        for (int i = 0; i < V; i++) {
            path[i]=-1;
        }
        graph=g;
        try
        {            
            path[0] = 0;//Đặt điểm đầu tiên cố định là 0
            pathcount = 1;            
            solve(0);
            System.out.println("No solution");
        }
        catch (Exception e)
        {
            System.out.println("Solution found");
            display();
        }
            
    }
    
    public void solve(int vertex) throws Exception
    {
        //Check dieu kien(Di qua tat ca cac diem va quay lai diem ban dau
        if (graph[vertex][0] == 1 && pathcount == V)
            throw new Exception("Solution found");
        //Co Hamilton path nhung khong co Hamilton cycle
        if (pathcount == V)
            return;
 
        for (int i = 0; i < V; i++)
        {
            //Kiểm tra 2 điểm có kết nối với nhau không
            if (graph[vertex][i] == 1 )
            {
                //Thêm vào path           
                path[pathcount++] = i;    
                
                //Xóa bỏ kết nốt       
                graph[vertex][i] = 0;
                graph[i][vertex] = 0;
 
                //Nếu điểm chưa có trong path thì đệ quy để tìm điểm tiếp theo
                if (!isExist(i))
                    solve(i);
 
                //Khôi phục connect
                graph[vertex][i] = 1;
                graph[i][vertex] = 1;
                //Xóa khỏi path
                path[--pathcount] = -1;                    
            }
        }
    } 
    //Hiển thị path
    public void display(){
        System.out.print("Path: ");
        for (int i = 0; i <= V; i++)
            System.out.print(path[i & V] +" ");
        System.out.println();
    }
    
    
    //Kiểm tra điểm đã xuất hiện trong path chưa
    public boolean isExist(int v){
        for (int i = 0; i < pathcount - 1; i++)
            if (path[i] == v)
                return true;
        return false;   
    }

    
    
}
