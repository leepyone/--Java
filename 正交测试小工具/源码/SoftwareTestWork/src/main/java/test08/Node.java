package test08;

public class Node {
    private  int n;
    private  int row;
    private  int column;
    private  int[][] numbers;
    private  int[][] words;

    public Node(int n,int row,int column){
        this.n=n;
        this.row=row;
        this.column=column;
        this.numbers = new int[row][column];
        this.words= new int[column][n];
    }
}
