package p2;

/*
    class BogglePiece can build a board with row and col
*/
public class BogglePiece {
    int row;
    int col;
    char value;

    public BogglePiece(int row, int col, char value){
        this.row = row;
        this.col = col;
        this.value = value;
    }
}
