public class Player {
    private int posI;   //Position of player
    private String color;   //color of player
    private boolean moved;  //If the player has moved
    public Player(int pos, String c) {
        //Initializes the instance variables
        posI = pos;
        color = c;
        moved = false;
    }
    
    public boolean getMoved() {
        return moved;
    }
    
    public void setMoved() {
        moved = true;;
    }
    
    public String getColor() {
        return color;
    }
    
    public int getPos() {
        return posI;
    }
    //Changes the player's position by adding its current pos to the parameter
    public void setPos(int n) {
        posI = posI + n;
    }
    //Changes the player's position to the one marked in parameter
    public void begPos(int q) {
        posI = q;
    }
}
