public class Bishop extends ChessmanDefault {
    public final String type = "Bishop";    //Chessman type

    private int comparedPosition;   // Get if target is under or above current position
    private int newSquarePos;       // New target between origin and new position
    private int nPosInt;            // New position on integer
    private int row;                // Current Row
    private int cQ;                 // Current quadrant


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
    //A preset vars to catch movement validation
    @Override 
    public boolean chessmanMovement(BoardSquare cPos, BoardSquare nPos){
        comparedPosition = cPos.getCoordinate().compareTo(nPos.getCoordinate());
        nPosInt = nPos.getCoordinate().getIntPos();
        cQ = cPos.getCoordinate().getColumn();
        row = cPos.getCoordinate().getRow();

        if(row == nPos.getCoordinate().getRow())
            return false;

            switch(compass()){
                case 1: return tryMove(9);  // (cQ < nQ)   pos < nPos
                case 2: return tryMove(7);  // (cQ > nQ)
                case 3: return tryMove(-7); // (cQ < nQ)   pos > nPos
                case 4: return tryMove(-9); // (cQ > nQ)
            }
        return false;
    }

    //Trying catch failures and verifying cPos to nPos 
    //Try square by square until target
    @Override
    public boolean tryMove(int p){
        if(p == 9 || p == -7)
            for(int k = 0, i = row; k < 7 - cQ; k++){
                newSquarePos = super.sumPosition(i, k, p) + (2 * k);
                if(nPosInt == newSquarePos)
                    return true;
                if(!super.tryMoveNext(newSquarePos, nPosInt))
                    return false;
        
                if(comparedPosition == 1) i--;
                else i++;
            }
        else
            for(int k = 0, i = row; k < cQ; k++){
                newSquarePos = super.sumPosition(i, k, p);
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