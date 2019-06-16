//Package import
import java.util.InputMismatchException;

import chessmen.*;


public class Game{
    private static int ProgramCounter = 0;
    private static BoardSquare FPos;
    private static BoardSquare SPos;
    private static Board board;

    //Send all chessmen on start position
    public static Chessman UnboxChessmen(int pos){
        return iniPosPiece(pos);
    }

    //Get Board onAction event and deal with
    public static void setPointer(BoardSquare square) throws InputMismatchException{
        
        switch(ProgramCounter){
            case 0: FPos = square;
                    System.out.println("\nPrimeira POS: \n"+FPos.getChessman() + "\n" + FPos.getCoordinate());
                    ProgramCounter++;
                    //GameRun();
                    break;
            case 1: SPos = square;
            System.out.println("\nSegunda POS: \n"+SPos.getChessman() + "\n" + SPos.getCoordinate());
                    //GameRun();
                    break;
            default: System.out.println("Err.: 01 - Out of program routine.");
            //ScreenGx.reloadBoard();
        }
            FPos.moveChessman(SPos);
            System.out.println("\nMoved: \n"+SPos.getChessman() + "\n" + SPos.getCoordinate());
    }

    public static void GameRun(){
        switch(ProgramCounter){
            case 0: if(canMove(FPos))
                        ProgramCounter++;
                    else
                        throw new InputMismatchException("There is not possible moviments."); 
                    break;
            case 1: ProgramCounter++;
                    break;
            //default: throw new  
        }
    }

    //Test if the chessman can move to designed position
    public static boolean canMove(BoardSquare CPos, BoardSquare NPos){
        CPos.getChessman();
        return false;
    }
    public static boolean canMove(BoardSquare pos){
        return false;
    }

    //Get all board points to verify movements
    public static void refreshBoard(Board b){
        board = b;
    }


    
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


/*

// Faça um programa que leia uma string e verifique se ela é palíndromo. 
// Palíndromos são aquelas palavras que podem ser lidas tanto da esquerda para direita 
// ou da direita para esquerda. Exemplo: arara. 
// Crie um método recursivo para verificar se a string é palíndromo.

import java.util.Scanner;

public class Exercicio12{

public static void main (String args[]){

Scanner teclado = new Scanner (System.in);

System.out.print ("\fInforme uma string: ");
String palavra = teclado.nextLine();

if (palindromo(palavra)) System.out.print ("É um palíndromo."); 
else System.out.print ("Não é um palíndromo.");
    }

public static boolean palindromo (String palavra){
String palavral = palavra.toLowerCase();
return palindromoR (palavral, palavral.length()-1);    
}    

private static boolean palindromoR (String palavra, int comp){
int compInvertido = (palavra.length()-1)-comp;    
if ((compInvertido==comp || compInvertido == comp-1) && (palavra.charAt(comp)==palavra.charAt(compInvertido))) return true;
return palindromoR (palavra, comp-1) && (palavra.charAt(comp) == palavra.charAt(compInvertido));
}
    
}

*/