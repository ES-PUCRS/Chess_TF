public class Rook extends ChessmanDefault {
    public final String type = "Rook";    //Chessman type
        
    private int newSquarePos;       // New target between origin and new position
    private int nPosInt;            // New position on integer
    private int column;             // Current column
    private int row;                // Current Row


    public Rook(Team team){
        super(team, "Rook");
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
     *                                          +--------------------+--------------------+
     *  x1 = {[i * 8 + (c - k)] % 8 ≠ 0}        ¦  Q > nQ ⇒ P = -1; ¦  L > nL ⇒ P = -8;  ¦
     *  x2 = {[i * 8 + (c - k)] % 8 ≠ 7}        ¦  Q < nQ ⇒ P =  1; ¦  L < nL ⇒ P =  8;  ¦
     *                                          +--------------------+--------------------+
     * 
     * 
     *          7-c                                  7-i
     *  x1 → 1 * k + Σ  (i * 8) + c  \/  x2 → 8 * k + Σ  (i * 8) + c  ⇔  cPos < nPos 
     *          k=0                                  k=0
     * 
     *            c                                     i
     *  x1 → -1 * k + Σ  (i * 8) + c  \/  x2 → -8 * k + Σ  (i * 8) + c  ⇔  cPos > nPos 
     *          i,k=0                                  k=0
     * 
     */
    //A preset vars to catch movement validation
    @Override 
    public boolean chessmanMovement(BoardSquare cPos, BoardSquare nPos){
        nPosInt = nPos.getCoordinate().getIntPos();
        column = cPos.getCoordinate().getColumn();
        row = cPos.getCoordinate().getRow();
        
        if(column != nPos.getCoordinate().getColumn() && row != nPos.getCoordinate().getRow())
            return false;
        

        switch(compass()){
            case 6: return tryMove(-8);   // (cQ == nQ)   Pos < nPos
            case 8: return tryMove(-1);   // (cQ > nQ)    Pos == nPos
            case 7: return tryMove(1);    // (cQ < nQ)    Pos == nPos
            case 5: return tryMove(8);    // (cQ == nQ)   Pos > nPos
        }
    return false;
    }

    //Trying catch failures and verifying cPos to nPos 
    //Try square by square until target
    @Override
    public boolean tryMove(int p){
        switch(p){
            case -8: if(aux(p, row))
                        if(nPosInt == newSquarePos)
                            return true;
                    break;
            case -1: if(aux(p, column))
                        if(nPosInt == newSquarePos)
                            return true;
                    break;
            case 1: if(aux(p, 7 - column))
                        if(nPosInt == newSquarePos)
                            return true;
                    break;
            case 8: if(aux(p, 7 - row))
                        if(nPosInt == newSquarePos)
                            return true;
                    break;
        }

        if(nPosInt != newSquarePos)
            return false;
        else
            return true;
    }

    private boolean aux(int p, int stop){
        for(int k = 0; k < stop; k++){
            if(nPosInt == newSquarePos)
                return true;
            newSquarePos = super.sumPosition(row, 0, p * k);
            System.out.println("newSquarePos -> "+newSquarePos);
            System.out.println("Row: "+row+" Column: "+column+" p: "+p+" k: "+k);
            if(!super.tryMoveNext(newSquarePos, nPosInt))
                return false;
        }
        return true;
    }
}