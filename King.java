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
    
    //A preset vars to catch movement validation
    @Override 
    public boolean chessmanMovement(BoardSquare cPos, BoardSquare nPos){
        nPosInt = nPos.getCoordinate().getIntPos();
        column = cPos.getCoordinate().getColumn();
        row = cPos.getCoordinate().getRow();
        
        switch(compass()){
            case 1: return tryMove(9);
            case 2: return tryMove(7);
            case 3: return tryMove(-7);
            case 4: return tryMove(-9);
            case 5: return tryMove(8);  
            case 6: return tryMove(-8);   
            case 7: return tryMove(1);    
            case 8: return tryMove(-1);    
        }
        return false;
    }
    //Trying catch failures and verifying cPos to nPos 
    //Try square by square until target
    //Trying catch failures and verifying cPos to nPos 
    //Try square by square until target
    @Override
    public boolean tryMove(int p){
        switch(p){
            case 9: return aux(p);
            case 7: return aux(p);
            case -7: return aux(p);
            case -9: return aux(p);
            case 8: return aux(p);
            case -8: return aux(p);
            case 1: return aux(p);
            case -1: return aux(p);
        }
    return false;
    }
    
    private boolean aux(int p){
        newSquarePos = super.sumPosition(row, 0, p);
        if(nPosInt == newSquarePos)
            return true;
        else return false;
    }
}