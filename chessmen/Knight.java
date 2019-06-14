//Set Package
package chessmen;


public class Knight extends Chessman{
    public final String type = "Knight"; 
    private final Team team;

    public Knight(Team team){
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