import java.util.*;
public class Card{
    // Constants for colors
    private static final int RED = 1;
    private static final int YELLOW = 2;
    private static final int GREEN = 3;
    private static final int BLUE = 4;
    private static final int ALL = 5;
    
    // Constants for special cards
    private static final int SKIP = 10;
    private static final int DRAW2 = 11;
    private static final int REVERSE = 12;
    private static final int WILD = 13;
    private static final int WILD4 = 14;
    
    // Arrays
    private String[] colors = {"Red", "Yellow", "Green", "Blue", "All"};
    private String[] specialCards = {"Skip", "Draw 2", "Reverse", "Wild", "Wild Draw 4"};
    
    private int color;
    private int rank;
    public Card(int c, int r){
        color = c;
        rank = r;
    }
    
    public int getColor(){
        return color;
    }
    
    public int getRank(){
        return rank;
    }
    
    public void setColor(int c){
        color = c;
    }
    
    public String getValue(){
        if(rank < 10){
            return Integer.toString(rank);
        }
        return specialCards[rank - 10];
    }
    
    public String toString(){
        return colors[color - 1] + " " + getValue();
    }
}
