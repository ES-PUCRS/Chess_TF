public class King extends ChessmanDefault {
    public final String type = "King";    //Chessman type

    private int newSquarePos;       // New target between origin and new position
    private int cPosInt;            // Current position on integer
    private int nPosInt;            // New position on integer
    private int row;                // Current Row


    public King(Team team){
        super(team, "King");
    }
    
    //A preset vars to catch movement validation
    @Override 
    public boolean chessmanMovement(BoardSquare cPos, BoardSquare nPos){
        cPosInt = cPos.getCoordinate().getIntPos();
        nPosInt = nPos.getCoordinate().getIntPos();
        row = cPos.getCoordinate().getRow();
        
        switch(compass()){
            case 1: return tryMove(1, 9);
            case 2: return tryMove(7);
            case 3: return tryMove(-7);
            case 4: return tryMove(-1, -9);
            case 5: return tryMove(8);  
            case 6: return tryMove(-8);   
            case 7: return tryMove(1);
            case 8: return tryMove(-1);
        }
        return false;
    }



    //Override
    public boolean tryMove(int p, int k){
        int dif = cPosInt - nPosInt;
        if(dif != 1 && dif != -1)
            return tryMove(k);
        return tryMove(p);
    }
    //Trying catch failures and verifying cPos to nPos 
    //Try square by square until target
    @Override
    public boolean tryMove(int p){
        newSquarePos = super.sumPosition(row, 0, p);
        if(nPosInt == newSquarePos)
            return true;
        else return false;
    }
}