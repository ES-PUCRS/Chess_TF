//Package import
import chessmen.*;


public class Game{

    public static Chessman UnboxChessmen(int pos){
        return iniPosPiece(pos);
    }


    private static Team iniPosTeam(int pos){
        if(pos <= 15)
            return Team.BLACK;
        else if(pos >= 48)
            return Team.WHITE;
        else 
            return Team.NONE;
    }
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