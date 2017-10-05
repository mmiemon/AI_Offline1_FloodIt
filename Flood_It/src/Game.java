import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Game {
    //PriorityQueue<Vertice> pQueue = new PriorityQueue<Vertice>();
    PriorityQueue<Vertice> pQueue = new PriorityQueue<Vertice>( new Comparator<Vertice>() {
        public int compare(Vertice n1, Vertice n2) {
            return n1.f-n2.f;
        }
    });
    ArrayList<Vertice> visited = new ArrayList<Vertice>();
    Vertice initVertice;
    int n;
    int move;
    public Game(Vertice v,int n){
        this.initVertice=v;
        this.n=n;
        move=0;
    }
    public  Vertice myContains(PriorityQueue<Vertice> pq,Vertice a){
        for (Vertice v:pq) {
            if(v.sameBoard(a)) return v;
        }
        return null;
    }
    public  boolean myContainsBool(ArrayList<Vertice> arr,Vertice a){
        for (Vertice v:arr) {
            if(v.sameBoard(a)) return true;
        }
        return false;
    }

    public void colorBoard(int i,int j,int board[][],int color,int baseColor){
        if(board[i][j]!=baseColor) return;
        board[i][j]=color;
        if(i!=n-1) colorBoard(i+1,j,board,color,baseColor);
        if(j!=n-1) colorBoard(i,j+1,board,color,baseColor);
        if(i!=0) colorBoard(i-1,j,board,color,baseColor);
        if(j!=0) colorBoard(i,j-1,board,color,baseColor);
    }
    public ArrayList<Vertice> getNeighbour(Vertice v){
        ArrayList<Vertice> nb=new ArrayList<Vertice>();
        boolean foundColor[]=new boolean[7];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) foundColor[v.board[i][j]]=true;
        }
        for(int i=1;i<=6;i++){
            if(!foundColor[i] || i==v.board[0][0]) continue;
            else{
                int arr[][]=new int[n][n];
                for(int k=0;k<n;k++){
                    for(int j=0;j<n;j++) arr[k][j]=v.board[k][j];
                }
                Vertice a=new Vertice(arr,n);
                colorBoard(0,0,a.board,i,a.board[0][0]);
                a.color=i;
                a.parent=v;
                a.g=v.g+1;
                ConnectedComponents cc=new ConnectedComponents(a.board,n);
                a.f=a.g+cc.DFS();
                nb.add(a);
            }
        }
        return nb;
    }
    public void PrintVertice(Vertice v){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(v.board[i][j]+" ");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }
    public Vertice play(){
        pQueue.add(initVertice);
        while(true){
            Vertice v=pQueue.remove();
            //PrintVertice(v);
            if(v.isGoal()) return v;
            move++;
            visited.add(v);
            ArrayList<Vertice> nb=getNeighbour(v);
            for (Vertice t:nb) {
                //PrintVertice(t);
                if(myContainsBool(visited,t)==false){
                    Vertice a=myContains(pQueue,t);
                    if(a==null) pQueue.add(t);
                    else{
                        if(t.f<a.f){
                            pQueue.remove(a);
                            pQueue.add(t);
                        }
                    }
                }
            }
        }
    }
    public void PrintSolution(Vertice v){
        if(v.parent==null) {
            PrintVertice(v);
            return;
        }
        PrintSolution(v.parent);
        System.out.println("Select : "+v.color+"\n");
        PrintVertice(v);
    }
    public static void main(String[] args) {
        try{
            Scanner scanner = new Scanner(new File("input.txt"));
            while(true){
                int a=scanner.nextInt();
                if(a==0) break;
                System.out.println("New Game --------------------------");
                int arr[][]=new int[a][a];
                for(int i=0;i<a;i++){
                    for(int j=0;j<a;j++) arr[i][j]=scanner.nextInt();
                }
                Vertice v=new Vertice(arr,a);
                v.g=0;
                ConnectedComponents cc=new ConnectedComponents(v.board,a);
                v.f=v.g+cc.DFS();
                v.parent=null;
                Game g=new Game(v,a);
                Vertice z=g.play();
                g.PrintSolution(z);
                System.out.println("Total move : "+ g.move);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
