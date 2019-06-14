//Set Package
package chessmen;



public class Queen extends Chessman{
    public final String type = "Queen"; 
    private final Team team;

    public Queen(Team team){
        this.team = team;
    }

    @Override
    public Team getTeam(){
        return team;
    }

    @Override
    public String getType(){
        return type;
    }

    @Override
    public boolean moveFx(){
        return true;
    }
}