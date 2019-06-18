public class King extends ChessmanDefault {
    public final String type = "King";    //Chessman type
    private BoardSquare shouldTry;  // BoardSquare that would catch by newSquarePos

    private int newSquarePos;       // New target between origin and new position
    private int nPosInt;            // New position on integer
    private int column;             // Current Column
    private int row;                // Current Row
    private int cQ;                 // Current quadrant
    private int nQ;                 // New quadrant


    public King(Team team){
        super(team, "King");
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
     *                                          +-------------------+--------------------+
     *  x1 = {[i * 8 + (c - k)] % 8 ≠ 0}        ¦ Q > nQ ⇒ P = -1; ¦ L > nL ⇒ P = -8;   ¦
     *  x2 = {[i * 8 + (c - k)] % 8 ≠ 7}        ¦ Q < nQ ⇒ P =  1; ¦ L < nL ⇒ P =  8;   ¦
     *                                          +-------------------+--------------------+
     * 
     * 
     *           c                            i
     *  x1 → 1 + Σ  (i * 8) + c  \/  x2 → 8 + Σ  (i * 8) + c  ⇔  cPos < nPos 
     *         i,k=0                        c,k=0
     * 
     *            c                             i
     *  x1 → -1 + Σ  (i * 8) + c  \/  x2 → -8 + Σ  (i * 8) + c  ⇔  cPos > nPos 
     *          i,k=0                         c,k=0
     * 
     */
    //A preset vars to catch movement validation
    @Override 
    public boolean chessmanMovement(BoardSquare cPos, BoardSquare nPos){
        return true;
    }

    //Trying catch failures and verifying cPos to nPos 
    //Try square by square until target
    @Override
    public boolean tryMove(int p){
        return true;
    }
}