public class ConnectedComponents {
    int a[][];
    int n;
    boolean visited[][];
    public ConnectedComponents(int a[][],int n){
        this.a=a;
        this.n=n;
        visited=new boolean[n][n];
    }

    public int DFS(){
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if (!visited[i][j]) {
                    count++;
                    DFS_Visit(i, j);
                }
            }
        }
        return count;
    }
    public void DFS_Visit(int i,int j){
        visited[i][j]=true;
        if(i!=0) {if(!visited[i-1][j]&&a[i][j]==a[i-1][j]) DFS_Visit(i-1,j);}
        if(j!=0) {if(!visited[i][j-1]&&a[i][j]==a[i][j-1]) DFS_Visit(i,j-1);}
        if(i!=n-1) {if(!visited[i+1][j]&&a[i][j]==a[i+1][j]) DFS_Visit(i+1,j);}
        if(j!=n-1) {if(!visited[i][j+1]&&a[i][j]==a[i][j+1]) DFS_Visit(i,j+1);}
    }
}
