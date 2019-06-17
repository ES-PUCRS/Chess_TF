import javafx.scene.layout.GridPane;

public class Pawn extends Chessman {
    public final String type = "Pawn";    //Chessman type
    private BoardSquare shouldTry;  // BoardSquare that would catch by newSquarePos
    private GridPane board;         // All board to try square by square until target

    private int newSquarePos;       // New target between origin and new position
    private int nPosInt;            // New position on integer
    private int column;             // Current Column
    private int row;                // Current Row
    private int cQ;                 // Current quadrant
    private int nQ;                 // New quadrant


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
        nPosInt = nPos.getCoordinate().getIntPos();
        cQ = cPos.getCoordinate().getColumn();
        nQ = nPos.getCoordinate().getColumn();
        row = cPos.getCoordinate().getRow();
        column = cQ;

        this.board = board;
        
        /*Catch direction of movement
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
        */
        return true;
    }


    //Overload
    private int aux(){ return (aux(0)); }
    //Overload
    private int aux(int p){ return getSquare(0, p); }

    //Method to square of column-row that chessman can move;
    private int getSquare(int k, int p){
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
                if(nPosInt == getSquare(k, p))
                    return true;       
        }else{
            if (k == column)
                if(nPosInt == getSquare(k, p))
                    return true;
        }

        newSquarePos = getSquare(k, p);
        shouldTry = (BoardSquare) board.getChildren().get(newSquarePos);
        if(shouldTry.getChessman() != null)
            return false;

        return tryMove(k++, p);
    }
}