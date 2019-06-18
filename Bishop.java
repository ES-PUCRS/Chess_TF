import javafx.scene.layout.GridPane;

public class Bishop extends ChessmanDefault {
    public final String type = "Bishop";    //Chessman type

    private int comparedPosition;   // Get if target is under or above current position
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
     *        c                                  7-c
     *  x1 →  Σ  [i * 8 + (c - k)] + 7  /\  x2 →  Σ  [i * 8 (c - k)] + 9 + (2 * k)  ⇔  cPos < nPos 
     *       i,k=0                              i,k=0
     * 
     *        c                                  7-c
     *  x1 →  Σ  [i * 8 + (c - k)] - 9  /\  x2 →  Σ  [i * 8 (c - k)] - 7 + (2 * k)  ⇔  cPos > nPos 
     *       i,k=0                              i,k=0
     * 
     */
    @Override 
    public boolean MoveFx(BoardSquare cPos, BoardSquare nPos, GridPane board){
        comparedPosition = cPos.getCoordinate().compareTo(nPos.getCoordinate());
        nPosInt = nPos.getCoordinate().getIntPos();
        cQ = cPos.getCoordinate().getColumn();
        nQ = nPos.getCoordinate().getColumn();
        row = cPos.getCoordinate().getRow();
        column = cQ;

        super.setBoard(board);
        
        if(row == nPos.getCoordinate().getRow())
            return false;

        //Catch direction of movement
        if(comparedPosition == -1){ //to Up
            if(cQ < nQ){
                return tryMove(9); //to Left
            }else 
                return tryMove(7); //to Right
        }else{  //to Down
            if(cQ < nQ){
                return tryMove(-7); //to Right
            }else
                return tryMove(-9); //to Left
        }
    }

    //Method to square of column-row that chessman can move;
    private int aux(int i, int k, int p){
        if(p == 0){
            return (i * SquareRows) + column % SquareRows;
        }else if(p == 9 || p == -7)
            return ((i * SquareRows) + (column - k)) + p + (2 * k);
        return ((i * SquareRows) + (column - k)) + p;
    }

    //Try square by square until final position
    private boolean tryMove(int p){
        if(p == 9 || p == -7)
            for(int k = 0, i = row; k < 7-column; k++){
                newSquarePos = aux(i, k, p);
                if(nPosInt == newSquarePos)
                    return true;
                if(!super.tryMoveNext(newSquarePos, nPosInt))
                    return false;
        
                if(comparedPosition == 1) i--;
                else i++;
            }
        else
            for(int k = 0, i = row; k < column; k++){
                newSquarePos = aux(i, k, p);
                if(nPosInt == newSquarePos)
                    return true;
                if(!super.tryMoveNext(newSquarePos, nPosInt))
                    return false;
        
                if(comparedPosition == 1) i--;
                else i++;
            }
        
        if(nPosInt != newSquarePos)
            return false;
        else
            return true;
    }
}