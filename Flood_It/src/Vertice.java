public class Vertice {
    int n;
    int board[][];
    int g,f;
    Vertice parent;
    int color;
    public Vertice(int board[][],int n){
        this.n=n;
        this.board=board;
    }
    public Vertice(){
        n=-1;
    }
    boolean isGoal(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]!=board[0][0]) return false;
            }
        }
        return true;
    };
    boolean sameBoard(Vertice t){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]!=t.board[i][j]) return false;
            }
        }
        return true;
    }

}
