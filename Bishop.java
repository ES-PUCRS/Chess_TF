import javafx.scene.layout.GridPane;

public class Bishop extends Chessman {
    public final String type = "Bishop";    //Chessman type
    private BoardSquare shouldTry;  // BoardSquare that would catch by newSquarePos
    private GridPane board;         // All board to try square by square until target

    private int newSquarePos;       // New target between origin and new position
    private int nPosInt;            // New position on integer
    private int column;             // Current Column
    private int row;                // Current Row
    private int cQ;                 // Current quadrant
    private int nQ;                 // New quadrant


    public Bishop(Team team){
        super(team, "Bishop");
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
     * 
     *                                          +----------------------+
     *  x1 = {[i * 8 + (c - k)] % 8 ≠ 0}        ¦ Q > nQ ⇒ P = -9, 7; ¦
     *  x2 = {[i * 8 + (c - k)] % 8 ≠ 7}        ¦ Q < nQ ⇒ P = -7, 9; ¦
     *                                          +----------------------+
     * 
     * 
     *         c                                   7-c
     *  x1 →  Σ  [i * 8 + (c - k)] + 7  /\  x2 →  Σ  [i * 8 (c - k)] + 9 + (2 * k)  ⇔  cPos < nPos 
     *       i,k=0                                i,k=0
     * 
     *         c                                   7-c
     *  x1 →  Σ  [i * 8 + (c - k)] - 9  /\  x2 →  Σ  [i * 8 (c - k)] - 7 + (2 * k)  ⇔  cPos > nPos 
     *       i,k=0                                i,k=0
     * 
     */
    @Override 
    public boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board){
        nPosInt = nPos.getCoordinate().getIntPos();
        cQ = cPos.getCoordinate().getColumn();
        nQ = nPos.getCoordinate().getColumn();
        row = cPos.getCoordinate().getRow();
        column = cQ;

        this.board = board;
        
        //Catch direction of movement
        if(cPos.getCoordinate().compareTo(nPos.getCoordinate()) == 1){
            if(cQ < nQ){
                if(aux() != 7)
                    return tryMove(9);
            }else if(aux() != 0)
                    return tryMove(7);
        }else{
            if(cQ < nQ){
                if(aux() != 7)
                    return tryMove(-7);
            }else if(aux() != 0)
                    return tryMove(9);
        }
        return false;
    }


    //Overload
    private int aux(){ return (aux(0)); }
    //Overload
    private int aux(int p){ return aux(0, p); }

    //Method to square of column-row that chessman can move;
    private int aux(int k, int p){
        if(p == 0){
            return (row * SquareRows) + column % SquareRows;
        }else if(p == 9 || p == -7)
            return ((row * SquareRows) + (column - k)) + p + (2 * k);
        return ((row * SquareRows) + (column - k)) + p;
    }

    //Aux method
    private boolean tryMove(int p){
        return tryMove(0, p);
    }
    //Try square by square until final position
    private boolean tryMove(int k, int p){
        if(p == 9 || p == -7){
            if(k == 7 - column)
                if(nPosInt == aux(k, p))
                    return true;       
        }else{
            if (k == column)
                if(nPosInt == aux(k, p))
                    return true;
        }

        newSquarePos = aux(k, p);
        shouldTry = (BoardSquare) board.getChildren().get(newSquarePos);
        if(shouldTry.getChessman() != null)
            return false;

        return tryMove(k++, p);
    }
}