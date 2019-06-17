//Package import
import java.util.InputMismatchException;
import javafx.scene.layout.GridPane;


public class Game{
    private static int ProgramCounter = 0;
    private static BoardSquare fPos;
    private static BoardSquare sPos;
    //private static boolean single;
    private static GridPane board;

    //Send all chessmen on start position
    public static Chessman UnboxChessmen(int pos){
        return iniPosPiece(pos);
    }

    public static void setOnBoard(GridPane onBoard){
        board = onBoard;
    }

    //Get Board onAction event and deal with
    public static void setPointer(BoardSquare square) throws InputMismatchException{
        switch(ProgramCounter){
            case 0: fPos = square;
                    System.out.println("\nSquare 1: " + fPos.getCoordinate() +"\nProgramCounter: "+ProgramCounter+"\nChessman: "+fPos.getChessman());
                    GameRun();
                    break;
            case 1: sPos = square;
                    System.out.println("\nSquare 2: " + sPos.getCoordinate() +"\nProgramCounter: "+ProgramCounter+"\nChessman: "+sPos.getChessman());
                    GameRun();
                    break;
        }
    }

    public static void GameRun(){
        switch(ProgramCounter){
            case 0: System.out.println(canMove(fPos));
                    if(canMove(fPos)){
                        ProgramCounter++;
                    }else
                        posClear();
                    break;

            case 1: System.out.println(canMove(fPos, sPos));
                    if(canMove(fPos, sPos)){
                        fPos.moveChessman(sPos);
                        System.out.println("\nMoved: "+ fPos.getCoordinate());
                        posClear();
                    }else{
                        throw new InputMismatchException("Selected chessman can not move to target.");
                    }
                    break;
            }
    }

    /**
     * First position can not be null;
     * New position state
     *      New position can not be fill with same team Chessman
     *
     * @param cPos - Current Position
     * @param nPos - New Position
     * @return True if the chessman can assume that new coordinate
     */
    //Test if the chessman can move to designed position
    public static boolean canMove(BoardSquare cPos, BoardSquare nPos){
        if(cPos.getChessman() == null){
            posClear();
            throw new InputMismatchException("There is no chessman at this position.");
        }else if(nPos.getChessman() != null){
            if(nPos.getChessman().getTeam() == cPos.getChessman().getTeam()){
                posClear();
                throw new InputMismatchException("Can not move to a same team chessman position.");
            }else{
                ProgramCounter++;
                return cPos.getChessman().MoveFx(cPos, nPos, board);
            }
        }else{
            ProgramCounter++;
            return cPos.getChessman().MoveFx(cPos, nPos, board);
        }
    }


    public static boolean canMove(BoardSquare pos){
        if(pos.getChessman() != null)
            return true;
        return false;
    }

    private static void posClear(){
        fPos = null;
        sPos = null;
        ProgramCounter = 0;
    }



/*
    //Get all board points to verify movements
    public static void setPlayType(boolean b){
        single = b;
    }
*/
    
    /*
     *   Private Methods aux class *
     */

    //Set chessman team at start position
    private static Team iniPosTeam(int pos){
        if(pos <= 15)
            return Team.BLACK;
        else if(pos >= 48)
            return Team.WHITE;
        else 
            return Team.NONE;
    }

    //Create all chessmen
    private static Chessman iniPosPiece(int pos){
        if(pos >= 8 && pos <=15 || pos >= 48 && pos <= 55)
            return new Pawn(iniPosTeam(pos));
        else if(pos == 0 || pos == 7 || pos == 56 || pos == 63)
            return new Rook(iniPosTeam(pos));
        else if(pos == 1 || pos == 6 || pos == 57 || pos == 62)
            return new Knight(iniPosTeam(pos));
        else if(pos == 2 || pos == 5 || pos == 58 || pos == 61)
            return new Bishop(iniPosTeam(pos));
        else if(pos == 3 || pos == 60)
            return new Queen(iniPosTeam(pos));
        else if(pos == 4 || pos == 59)
            return new King(iniPosTeam(pos));
        return null;
    }   
}