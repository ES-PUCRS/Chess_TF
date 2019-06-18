import javafx.scene.layout.GridPane;

public class Pawn extends ChessmanDefault {
    public final String type = "Pawn";    //Chessman type
    //private int newSquarePos;       // New target between origin and new position


    public Pawn(Team team){
        super(team, "Pawn");
    }
    
    /**
     * Mathematics:
     * 
     *  i = row; 
     *  c = column;
     *  Q = pos % 8;
     *  P = Square sum;
     *  pos = (i * 8) + c;
     *  cPos = Current Position;
     *  nPos = New Position;
     * 
     *              Board
     *     { ∀ c ∈ N | 0 ≤ c ≤ 7 }
     *     { ∀ i ∈ N | 0 ≤ i ≤ 7 }
     *                                          +----------------------+
     *  x1 = {[i * 8 + (c - k)] % 8 ≠ 0}        ¦ Q > nQ ⇒ P = -9, 7; ¦
     *  x2 = {[i * 8 + (c - k)] % 8 ≠ 7}        ¦ Q < nQ ⇒ P = -7, 9; ¦
     *                                          +----------------------+
     * 
     *  Team.White ⇒ {i = i - 7; [-8, -7, -9]};
     * 
     * 
     *       7-i
     *        Σ  [(i * 8) + c] + 8
     *      i,k=0
     * 
     *       7-i                             7-i
     *  x1 →  Σ  [(i * 8) + c] + 7  \/  x2 →  Σ  [(i * 8) + c] + 9 
     *      i,k=0                           i,k=0
     * 
     */
    @Override 
    public boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board){
       super.setBoard(board);



        return true;
    }
}