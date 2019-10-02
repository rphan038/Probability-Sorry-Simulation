import java.util.*;
import java.lang.Math;
public class Simulation {
    private Board b;    //The Board
    private ArrayList<Player> red;  //The Red Players
    private ArrayList<Player> blue; //The Blue players
    private ArrayList<Player> green;//The green players
    private ArrayList<Player> yellow;   //The yellow players
    private ArrayList<Integer> deck;    //The deck of cards
    private ArrayList<Integer> uCards;  //The discard pile
    private boolean win;    //Keeps track of when there's a winner
    private Random rn;  //Random variable
    private int rWin;   //Keeps count of how many times red pieces reach home
    private int gWin;   //Keeps count of how many times green pieces reachhome
    private int bWin;   //Keeps count of how many times blue piece reach home
    private int yWin;   //Keeps count of how many times yellow piece reach hom
    private int R;  //How many times red wins overall
    private int G;  //How many times green wins overall
    private int B;  //How many times blue wins overall
    private int Y;  //How many times yellow wins overall
    public Simulation() {
        //Initializes all of the instance variables
        b = new Board();
        deck = new ArrayList<Integer>();
        uCards = new ArrayList<Integer>();
        red = new ArrayList<Player>();
        blue = new ArrayList<Player>();
        green = new ArrayList<Player>();
        yellow = new ArrayList<Player>();
        win = false;
        rn = new Random();
        rWin = 0;
        bWin = 0;
        yWin = 0;
        gWin = 0;
        R = 0;
        G = 0;
        Y = 0;
        B = 0;
    }
    //Main method that runs the simulation
    public static void main(String[] args) {
        Simulation s = new Simulation();
        s.run();
    }
    //The simulation
    public void run() {
        //Play the game x number of times
        for(int i = 0; i < 10000; i++) {
            rWin = 0;   //Reset red wins
            gWin = 0;   //Reset green wins
            yWin = 0;   //Reset yellow wins
            bWin = 0;   //Reset blue wins
            win = false;//New game-no one has won yet
            cards();    //Initializes and shuffles cards
            playerInit();//Initializes all of the players
            //Resets the board
            for(int j = 0; j < b.getBoard().length; j++) {
                b.getBoard()[j] = false;
            }
            //CURRENT PLAYER ORDER     RED BLUE YELLOW GREEN
            //First turn for all players
            firstTurn(red.get(0));
            firstTurn(blue.get(0));
            firstTurn(yellow.get(0));
            firstTurn(green.get(0));
            //Determines whose turn it is
            int col = 0;
            while(!win) {
                if(col == 0) { //Red's turn
                    redTurn();    //Strategy number one
                    if(rWin >= 4) { //If four of red's pieces are at home
                        win = true; //We have a winner
                        R++;    //Increment overall counter
                    }
                } else if(col == 1) {   //Blue's turn
                    strat1Blue(); 
                    if(bWin >= 4) { //IF four of blue's pieces are at home
                        win = true; //We have a winner
                        B++;    //Increment overall counter
                    }
                } else if(col == 2) {   //Yellow's turn
                    yellowTurn();   //Normal random movement
                    if(yWin >= 4) {//If four of yellow's pieces are at home
                        win = true; //We have a winner
                        Y++;    //Increment overall counter
                    }
                } else if(col == 3) {   //Green's turn
                    greenTurn();    //Normal random movement
                    if(gWin >= 4) {//If four of green's pieces are at home
                        win = true; //We have a winner
                        G++;    //Increment overall counter
                    }
                }
                col++;  //someone else's turn
                if(col >= 4)
                    col = 0;
            }
        }
        //Prints how many times each color won over x games
        System.out.println("R score " + R);
        System.out.println("B Score " + B);
        System.out.println("Y Score " + Y);
        System.out.println("G Score " + G);
        System.out.println((float) Y/10000); //Percentage of yellow wins
    }
    //Each piece should finish first before the next piece leaves home
    public void strat2Blue() {
        int n = 0;  //Number of spaces to move as determined by card
        //Checks if draw pile is empty and shuffles cards if it is
        emptyDeck();
        //Draw a card
        n = deck.remove(rn.nextInt(deck.size()));
        //Put in discard pile
        uCards.add(n);
        if(blue.get(0) != null) {
            if(!bWin(blue.get(0), n)) { //Checks if blue won
                //Mark spot as occupied
                b.getBoard()[blue.get(0).getPos()] = false;
                if(!loopBoard(blue.get(0).getPos(), n, blue.get(0))) {
                    //If pawn gets to end of board, reset to 0
                    if(!bWin(blue.get(0), n)) {
                        blue.get(0).setPos(n); 
                        slide(blue.get(0));
                    }
                }
            }
        } else if(blue.get(1) != null) {
            if(blue.get(1).getPos() == 4) {
                firstTurn(blue.get(1));
            } else {
                if(!bWin(blue.get(1), n)) { //Checks if blue won
                    //Mark spot as occupied
                    b.getBoard()[blue.get(1).getPos()] = false;
                    if(!loopBoard(blue.get(1).getPos(), n, blue.get(1))) {
                        //If pawn gets to end of board, reset to 0
                        if(!bWin(blue.get(1), n)) {
                            blue.get(1).setPos(n); 
                            slide(blue.get(1));
                        }
                    }
                }
            }
        } else if(blue.get(2) != null) {
            if(blue.get(2).getPos() == 4) {
                firstTurn(blue.get(2));
            } else {
                if(!bWin(blue.get(2), n)) { //Checks if blue won
                    //Mark spot as occupied
                    b.getBoard()[blue.get(2).getPos()] = false;
                    if(!loopBoard(blue.get(2).getPos(), n, blue.get(2))) {
                        //If pawn gets to end of board, reset to 0
                        if(!bWin(blue.get(2), n)) {
                            blue.get(2).setPos(n); 
                            slide(blue.get(2));
                        }
                    }
                }
            }
        } else if(blue.get(3) != null) {
            if(blue.get(3).getPos() == 4) {
                firstTurn(blue.get(3));
            } else {
                if(!bWin(blue.get(3), n)) { //Checks if blue won
                    //Mark spot as occupied
                    b.getBoard()[blue.get(3).getPos()] = false;
                    if(!loopBoard(blue.get(3).getPos(), n, blue.get(3))) {
                        //If pawn gets to end of board, reset to 0
                        if(!bWin(blue.get(3), n)) {
                            blue.get(3).setPos(n); 
                            slide(blue.get(3));
                        }
                    }
                }
            }
        }
    }
    //Strategy where player tries to get all of their pieces out on the board
    public void strat1Blue() {
        int n = 0;  //Number of spaces to move as determined by card
        int a = 0;  //Which pawn to move
        if(blue.size() == 4) { //Can only have a piece at home if have 4 piece
            if(blue.get(0).getPos() == 4) {
                a = 0;
            } else if(blue.get(1).getPos() == 4) {
                a = 1;
            } else if(blue.get(2).getPos() == 4) {
                a = 2;
            } else if(blue.get(3).getPos() == 4) {
                a = 3;
            } else {
                a = rn.nextInt(blue.size());
            }
        } else {
            a = rn.nextInt(blue.size());
        }
        //Checks if draw pile is empty and shuffles cards if it is
        emptyDeck();
        //Draw a card
        n = deck.remove(rn.nextInt(deck.size()));
        //Put in discard pile
        uCards.add(n);
        //Mark spot as occupied
        b.getBoard()[blue.get(a).getPos()] = false;

        if(!bWin(blue.get(a), n)) { //Checks if blue won
            if(!loopBoard(blue.get(a).getPos(), n, blue.get(a))) {
                //If pawn gets to end of board, reset to 0
                if(!bWin(blue.get(a), n)) {
                    blue.get(a).setPos(n); 
                    slide(blue.get(a));
                }
            }
        }
    }
    
    public void strat1Red() {
        int n = 0;
        int a = 0;
        if(red.size() == 4) {
            if(red.get(0).getPos() == 4) {
                a = 0;
            } else if(red.get(1).getPos() == 4) {
                a = 1;
            } else if(red.get(2).getPos() == 4) {
                a = 2;
            } else if(red.get(3).getPos() == 4) {
                a = 3;
            } else {
                a = rn.nextInt(red.size());
            }
        } else {
            a = rn.nextInt(red.size());
        }
        
        emptyDeck();
        //Draw a card
        n = deck.remove(rn.nextInt(deck.size()));
        //Put in discard pile
        uCards.add(n);

        b.getBoard()[red.get(a).getPos()] = false;

        if(!rWin(red.get(a), n)) {
            if(!loopBoard(red.get(a).getPos(), n, red.get(a))) {
                if(!rWin(red.get(a), n)) {
                    red.get(a).setPos(n); 
                    slide(red.get(a));
                }
            }
            //b.getBoard()[blue.get(a).getPos()] = true;
            
            //Checks if piece landed on same spot as other spot
            //bump(blue.get(a));
        }
    }
    
    public void strat1Green() {
        int n = 0;
        int a = 0;
        if(green.size() == 4) {
            if(green.get(0).getPos() == 4) {
                a = 0;
            } else if(green.get(1).getPos() == 4) {
                a = 1;
            } else if(green.get(2).getPos() == 4) {
                a = 2;
            } else if(green.get(3).getPos() == 4) {
                a = 3;
            } else {
                a = rn.nextInt(green.size());
            }
        } else {
            a = rn.nextInt(green.size());
        }
        
        emptyDeck();
        //Draw a card
        n = deck.remove(rn.nextInt(deck.size()));
        //Put in discard pile
        uCards.add(n);

        b.getBoard()[green.get(a).getPos()] = false;

        if(!gWin(green.get(a), n)) {
            if(!loopBoard(green.get(a).getPos(), n, green.get(a))) {
                if(!rWin(green.get(a), n)) {
                    green.get(a).setPos(n); 
                    slide(green.get(a));
                }
            }
        }
    }
    
    public void strat1Yellow() {
        int n = 0;
        int a = 0;
        if(yellow.size() == 4) {
            if(yellow.get(0).getPos() == 4) {
                a = 0;
            } else if(yellow.get(1).getPos() == 4) {
                a = 1;
            } else if(yellow.get(2).getPos() == 4) {
                a = 2;
            } else if(yellow.get(3).getPos() == 4) {
                a = 3;
            } else {
                a = rn.nextInt(yellow.size());
            }
        } else {
            a = rn.nextInt(yellow.size());
        }
        
        emptyDeck();
        //Draw a card
        n = deck.remove(rn.nextInt(deck.size()));
        //Put in discard pile
        uCards.add(n);

        b.getBoard()[yellow.get(a).getPos()] = false;

        if(!yWin(yellow.get(a), n)) {
            if(!loopBoard(yellow.get(a).getPos(), n, yellow.get(a))) {
                if(!yWin(yellow.get(a), n)) {
                    yellow.get(a).setPos(n); 
                    slide(yellow.get(a));
                }
            }
        }
    }

    public void blueTurn() {
        int n = 0;  //Number of spaces to move as determined by card
        
        //This next variable decides which piece is moving
        int a = rn.nextInt(blue.size());
        //If that piece is at the start...
        if(blue.get(a).getPos() == 4) {
            //Call the first turn method
            firstTurn(blue.get(a));
        } else {
            //Checks to see if deck is empty
            emptyDeck();
            //Draw a card
            n = deck.remove(rn.nextInt(deck.size()));
            //Put in discard pile
            uCards.add(n);

            b.getBoard()[blue.get(a).getPos()] = false;

            if(!bWin(blue.get(a), n)) {
                if(!loopBoard(blue.get(a).getPos(), n, blue.get(a))) {
                    if(!bWin(blue.get(a), n)) {
                        blue.get(a).setPos(n);
                        slide(blue.get(a));
                    }
                }
                b.getBoard()[blue.get(a).getPos()] = true; 
            }
        }
    }

    public void greenTurn() {
        int n = 0;  //Number of spaces to move as determined by card
        //This next variable decides which piece is moving
        int a = rn.nextInt(green.size());
        //If that piece is at the start...
        if(green.get(a).getPos() == 34) {
            //Call the first turn method
            firstTurn(green.get(a));
        } else {
            //Checks to see if deck is empty
            emptyDeck();
            //Draw a card
            n = deck.remove(rn.nextInt(deck.size()));
            //Put in discard pile
            uCards.add(n);

            b.getBoard()[green.get(a).getPos()] = false;

            if(!gWin(green.get(a), n)) {
                if(!loopBoard(green.get(a).getPos(), n, green.get(a))) {
                    green.get(a).setPos(n); 
                    slide(green.get(a));
                }
                b.getBoard()[green.get(a).getPos()] = true; 
            }
        }
    }

    public void yellowTurn() {
        int n = 0;  //Number of spaces to move as determined by card
        //This next variable decides which red piece is moving
        int a = rn.nextInt(yellow.size());
        //If that piece is at the start...
        if(yellow.get(a).getPos() == 19) {
            //Call the first turn method
            firstTurn(yellow.get(a));
        } else {
            //Checks to see if deck is empty
            emptyDeck();
            //Draw a card
            n = deck.remove(rn.nextInt(deck.size()));
            //Put in discard pile
            uCards.add(n);

            b.getBoard()[yellow.get(a).getPos()] = false;

            if(!yWin(yellow.get(a), n)) {
                if(!loopBoard(yellow.get(a).getPos(), n, yellow.get(a))) {
                    yellow.get(a).setPos(n); 
                    slide(yellow.get(a));
                }
                b.getBoard()[yellow.get(a).getPos()] = true;
            }
        }
    }

    public void redTurn() {
        int n = 0;  //Number of spaces to move as determined by card
        //This next variable decides which red piece is moving
        int a = rn.nextInt(red.size());
        //If that piece is at the start...
        if(red.get(a).getPos() == 49) {
            //Call the first turn method
            firstTurn(red.get(a));
        } else {
            //Checks to see if deck is empty
            emptyDeck();
            //Draw a card
            n = deck.remove(rn.nextInt(deck.size()));
            //Put in discard pile
            uCards.add(n);

            b.getBoard()[red.get(a).getPos()] = false;

            if(!rWin(red.get(a), n)) {
                if(!loopBoard(red.get(a).getPos(), n, red.get(a))) {
                    red.get(a).setPos(n); 
                    slide(red.get(a));
                }
                b.getBoard()[red.get(a).getPos()] = true; 
            }
        }
    }

    public boolean loopBoard(int k, int m, Player p) {
        boolean t = false;  //Keeps track if the piece is looping around
        //k is position before moving
        //m is how many going to move
        int w = k + m;  //If the number need to move goes off the board
        if(w > 59) {
            b.getBoard()[p.getPos()] = false;
            int u = 59 - k; //How many spaces left
            int v = m - u;  //How many spaces after 0
            p.begPos(v - 1);    //Set space
            b.getBoard()[p.getPos()] = true;
            slide(p);
            t = true;   //Operation succeeded
        } else if(w < 0) {  //If piece goes backwards
            b.getBoard()[p.getPos()] = false;
            p.begPos(59);
            b.getBoard()[59] = true;
            slide(p);
        }
        return t;
    }
    //Determines if red has won
    public boolean rWin(Player p, int n) {
        boolean home = false;   //Keeps track if red has reached home
        if(p.getPos() == 47 && p.getColor().equals("red")) {
            Player tmp = p;
            //Increase the number of pieces at home
            rWin++;
            //If there are four pieces at home, win
            if(rWin == 4) {
                win = true;
                //R++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            red.remove(tmp);
        } else if((p.getPos() + n > 47) && p.getColor().equals("red")
        && (p.getPos() < 47)) {
            Player tmp = p;
            //Increase the number of pieces at home
            rWin++;
            //If there are four pieces at home, win
            if(rWin == 4) {
                win = true;
                //R++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            red.remove(tmp);
        }
        return home;
    }

    public boolean bWin(Player p, int n) {
        boolean home = false;
        if(p.getPos() == 2 && p.getColor().equals("blue")) {
            Player tmp = p;
            //Increase the number of pieces at home
            bWin++;
            //If there are four pieces at home, win
            if(bWin == 4) {
                win = true;
                //B++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            blue.remove(tmp);
        } else if(p.getPos() + n > 2 && p.getColor().equals("blue")
            && (p.getPos() < 2)) {
            Player tmp = p;
            //Increase the number of pieces at home
            bWin++;
            //If there are four pieces at home, win
            if(bWin == 4) {
                win = true;
                //B++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            blue.remove(tmp);
        } else if(loopBoard(p.getPos(), n, p) && p.getColor().equals("blue")){
            if(p.getPos() >= 2) {
                Player tmp = p;
                //Increase the number of pieces at home
                bWin++;
                //If there are four pieces at home, win
                if(bWin == 4) {
                    win = true;
                    //B++;
                }
                //This particular red piece will no longer
                //move
                home = true;
                blue.remove(tmp);
            }
        }
        return home;
    }

    public boolean gWin(Player p, int n) {
        boolean home = false;
        if(p.getPos() == 32 && p.getColor().equals("green")) {
            Player tmp = p;
            //Increase the number of pieces at home
            gWin++;
            //If there are four pieces at home, win
            if(gWin == 4) {
                win = true;
                //G++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            green.remove(tmp);
        } else if(p.getPos() + n > 32 && p.getColor().equals("green")
        && (p.getPos() < 32)) {
            Player tmp = p;
            //Increase the number of pieces at home
            gWin++;
            //If there are four pieces at home, win
            if(gWin == 4) {
                win = true;
                //G++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            green.remove(tmp);
        }
        return home;
    }

    public boolean yWin(Player p, int n) {
        boolean home = false;
        if(p.getPos() == 17 && p.getColor().equals("yellow")) {
            Player tmp = p;
            //Increase the number of pieces at home
            yWin++;
            //If there are four pieces at home, win
            if(yWin == 4) {
                win = true;
                //Y++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            yellow.remove(tmp);
        } else if(p.getPos() + n > 17 && p.getColor().equals("yellow")
        && (p.getPos() < 17)) {
            Player tmp = p;
            //Increase the number of pieces at home
            yWin++;
            //If there are four pieces at home, win
            if(yWin == 4) {
                win = true;
                //Y++;
            }
            //This particular red piece will no longer
            //move
            home = true;
            yellow.remove(tmp);
        }
        return home;
    }
    //Determines if the draw pile is empty
    public void emptyDeck() {
        if(deck.size() == 0) {
            deck = uCards; //The draw deck will become the discard pile
            Collections.shuffle(deck);  //Shuffle
        }
    }
    
    public void slide(Player p) {
        if(p.getPos() == 1 && !p.getColor().equals("blue")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(3);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 9 && !p.getColor().equals("blue")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(4);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 46 && !p.getColor().equals("red")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(3);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 54 && !p.getColor().equals("red")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(4);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 31 && !p.getColor().equals("green")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(3);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 39 && !p.getColor().equals("green")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(4);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 16 && !p.getColor().equals("yellow")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(3);
            b.getBoard()[p.getPos()] = true;
        } else if(p.getPos() == 24 && !p.getColor().equals("yellow")) {
            b.getBoard()[p.getPos()] = false;
            p.setPos(4);
            b.getBoard()[p.getPos()] = true;
        }
    }

    public void bump(Player p) {
        if(p.getColor().equals("red")) {
            for(int i = 0; i < blue.size(); i++) {
                if(p.getPos() == blue.get(i).getPos()) {
                    blue.get(i).begPos(4);
                }
            }
            for(int i = 0; i < green.size(); i++) {
                if(p.getPos() == green.get(i).getPos()) {
                    green.get(i).begPos(4);
                }
            }
            for(int i = 0; i < yellow.size(); i++) {
                if(p.getPos() == yellow.get(i).getPos()) {
                    yellow.get(i).begPos(4);
                }
            }
        } else if(p.getColor().equals("green")) {
            for(int i = 0; i < blue.size(); i++) {
                if(p.getPos() == blue.get(i).getPos()) {
                    blue.get(i).begPos(4);
                }
            }
            for(int i = 0; i < red.size(); i++) {
                if(p.getPos() == red.get(i).getPos()) {
                    red.get(i).begPos(4);
                }
            }
            for(int i = 0; i < yellow.size(); i++) {
                if(p.getPos() == yellow.get(i).getPos()) {
                    yellow.get(i).begPos(4);
                }
            }
        } else if(p.getColor().equals("blue")) {
            for(int i = 0; i < red.size(); i++) {
                if(p.getPos() == red.get(i).getPos()) {
                    red.get(i).begPos(4);
                }
            }
            for(int i = 0; i < green.size(); i++) {
                if(p.getPos() == green.get(i).getPos()) {
                    green.get(i).begPos(4);
                }
            }
            for(int i = 0; i < yellow.size(); i++) {
                if(p.getPos() == yellow.get(i).getPos()) {
                    yellow.get(i).begPos(4);
                }
            }
        } else if(p.getColor().equals("yellow")) {
            for(int i = 0; i < blue.size(); i++) {
                if(p.getPos() == blue.get(i).getPos()) {
                    blue.get(i).begPos(4);
                }
            }
            for(int i = 0; i < green.size(); i++) {
                if(p.getPos() == green.get(i).getPos()) {
                    green.get(i).begPos(4);
                }
            }
            for(int i = 0; i < red.size(); i++) {
                if(p.getPos() == red.get(i).getPos()) {
                    red.get(i).begPos(4);
                }
            }
        }
    }
    //First move of the piece
    public void firstTurn(Player p) {
        emptyDeck();    //Checks to see if draw deck is empty
        int n = deck.remove(rn.nextInt(deck.size()));   //Draw a card
        if(n == 1 || n == 2) {  //Card needs to be a 1 or a 2
            uCards.add(n);  //Add card to discard pile
            if(!loopBoard(p.getPos(), n, p))    //Check if loop around board
                p.setPos(n);    //Changes position of piece
            b.getBoard()[p.getPos()] = true;    //Marks spot
            slide(p);
            while(n == 2) { //If card is 2, you can draw again
                emptyDeck();
                b.getBoard()[p.getPos()] = false;
                n = deck.remove(rn.nextInt(deck.size()));
                if(!loopBoard(p.getPos(), n, p))
                    p.setPos(n);
                b.getBoard()[p.getPos()] = true;
                uCards.add(n);
                slide(p);
            }
            p.setMoved();
        }
    }
    //Initializes the players
    public void playerInit() {
        for(int j = 0; j < 4; j++) {    //There are four of each color
            red.add(new Player(49, "red"));
            blue.add(new Player(4, "blue"));
            green.add(new Player(34, "green"));
            yellow.add(new Player(19, "yellow"));
        }
    }
    //Determines the cards in the deck
    public void cards() {
        for(int i = 0; i < 45; i++) {
            if(i < 4) {
                deck.add(2);
            } else if(i < 8) {
                deck.add(3);
            } else if(i < 12) {
                deck.add(4);
            } else if(i < 16) {
                deck.add(5);
            } else if(i < 20) {
                deck.add(7);
            } else if(i < 24) {
                deck.add(8);
            } else if(i < 28) {
                deck.add(10);
            } else if(i < 32) {
                deck.add(11);
            } else if(i < 36) {
                deck.add(12);
            } else if(i < 40) {
                deck.add(13);
            } else {
                deck.add(1);
            }
        }
        Collections.shuffle(deck);  //Shuffles cards
    }
}
