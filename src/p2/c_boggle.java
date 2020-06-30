package p2;
import java.util.ArrayList;
import java.util.HashSet;

public class c_boggle {

    //Define global variables
    String input_boggle;
    WordTrie trie = new WordTrie();
    String fileLocation = "src/p2/words.txt";
    int boardWidth;
    int boardHeight;
    BogglePiece[][] board;
    ArrayList<String> solutions = new ArrayList<>();


    public void set_legal_words(){
        trie.addWordFile(fileLocation);
    }

    public ArrayList<BogglePiece> getAdjacentPieces(BogglePiece piece){
        ArrayList<BogglePiece> adjacentPieces = new ArrayList<>();

        //check north
        if(piece.row+1 < this.boardHeight){
            adjacentPieces.add(this.board[piece.row+1][piece.col]);
        }

        //check north east
        if(piece.col+1 < this.boardWidth && piece.row+1 < this.boardHeight){
            adjacentPieces.add(this.board[piece.row+1][piece.col+1]);
        }

        //check east
        if(piece.col+1 < this.boardWidth){
            adjacentPieces.add(this.board[piece.row][piece.col+1]);
        }

        //check south east
        if(piece.col+1 < this.boardWidth && piece.row > 0){
            adjacentPieces.add(this.board[piece.row-1][piece.col+1]);
        }

        //Check south
        if(piece.row > 0){
            adjacentPieces.add(this.board[piece.row-1][piece.col]);
        }

        //check south west
        if(piece.row > 0 && piece.col > 0 ){
            adjacentPieces.add(this.board[piece.row-1][piece.col-1]);
        }

        //check west
        if(piece.col > 0 ){
            adjacentPieces.add(this.board[piece.row][piece.col-1]);
        }

        //check north west
        if(piece.row+1 < this.boardHeight && piece.col > 0){
            adjacentPieces.add(this.board[piece.row+1][piece.col-1]);
        }

        return adjacentPieces;
    }

    public BogglePiece getPieceAt(int row, int col){
        //check that piece is in board bounds
        if(col >= 0 && col < this.boardWidth && row >= 0 && row < this.boardHeight ){
            return(this.board[row][col]);
        } else{
            return null;
        }
    }

    public void buildBoard(){

        char[] Chars = input_boggle.toLowerCase().toCharArray();
        int charIndex = 0;
        for(int i=0; i<this.boardHeight; i++){
            for(int j=0; j<this.boardWidth; j++){
                BogglePiece curPiece = new BogglePiece(i,j,Chars[charIndex]);
                board[i][j] = curPiece;
                charIndex++;
            }
        }

    }
    public void printBoard(){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i < this.boardHeight; i++){
            for(int j=0; j < this.boardWidth; j++){
                sb.append(this.board[i][j].value);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    public void solutionScanner(BogglePiece curPiece, StringBuffer curWord, HashSet<BogglePiece> usedPieces){
        //add piece to used pieces list
        usedPieces.add(curPiece);

        //add new letter to word
        curWord.append(curPiece.value);

        //check if curWord is even a prefix
        if(this.trie.isPrefix(curWord.toString())){

            //check if curWord is a solution
            if(this.trie.isWord(curWord.toString())){
                if(curWord.length()>2) {
                    this.solutions.add(curWord.toString());
                }
            }
            //Check all directions recursively
            ArrayList<BogglePiece> adjacentPieces = getAdjacentPieces(curPiece);
            for(BogglePiece piece : adjacentPieces){
                //check that piece hasn't been used
                if(!usedPieces.contains(piece)){
                    StringBuffer newWord = new StringBuffer(curWord);
                    solutionScanner(piece, newWord, usedPieces);
                }
            }

        } //End Checking all directions

        //done with current piece, pop it off the list
        usedPieces.remove(curPiece);
    }


    public ArrayList<String> solve_board(int boardWidth, int boardHeight, String boardLetters) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.input_boggle = boardLetters;
        this.board = new BogglePiece[boardHeight][boardWidth];

        //clear solutions from last time
        solutions.clear();

        buildBoard();
        printBoard();

        HashSet<BogglePiece> usedPositions = new HashSet<>();

        //move across all pieces on the boar
        for (int i = 0; i < this.boardHeight; i++) {
            for (int j = 0; j < this.boardWidth; j++) {
                StringBuffer curWord = new StringBuffer("");
                BogglePiece startPiece = getPieceAt(i, j);
                solutionScanner(startPiece, curWord, usedPositions);
            }
        }
        return solutions;
    }
}
