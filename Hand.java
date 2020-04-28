import java.util.*;
public class Hand{
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
    
    // The cards in the hand
    private ArrayList<Card> cards;
    
    /**
     * Constructor for a new hand (player)
     */
    public Hand(){
        cards = new ArrayList<Card>();
    }
    
    /**
     * Adds a card to the hand
     * @param c The card to add
     */
    public void addCard(Card c){
        cards.add(c);
    }
    
    /**
     * Gets a card at the index provided
     * @param index The index to return with
     * 1 added (from user input)
     */
    public Card getCard(int index){
        return cards.get(index - 1);
    }
    
    /**
     * Uses a card
     * @param index The index to use with
     * 1 added (from user input)
     */
    public void useCard(int index){
        cards.remove(index - 1);
    }
    
    /**
     * Gets the cards in thehand
     * @return The cards in the hard     
     */
    public ArrayList<Card> getHand(){
        return cards;
    }
    
    /**
     * Converts the hand to a string
     * @return The hand as a string
     */
    public String toString(){
        String handString = "";
        int i = 1;
        sort();
        for(Card c: cards){
            handString += Integer.toString(i) + ": " + c + "\n";
            i++;
        }
        handString = handString.substring(0, handString.length() - 1);
        return handString;
    }
    
    /**
     * Sorts the cards in the hand by
     * color and rank
     */
    public void sort(){
        ArrayList<Card> sortedCards = new ArrayList<Card>();
        ArrayList<Card> reds = new ArrayList<Card>();
        ArrayList<Card> yellows = new ArrayList<Card>();
        ArrayList<Card> greens = new ArrayList<Card>();
        ArrayList<Card> blues = new ArrayList<Card>();
        ArrayList<Card> specials = new ArrayList<Card>();
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).getColor() == RED){
                reds.add(cards.get(i));
            }else if(cards.get(i).getColor() == YELLOW){
                yellows.add(cards.get(i));
            }else if(cards.get(i).getColor() == GREEN){
                greens.add(cards.get(i));
            }else if(cards.get(i).getColor() == BLUE){
                blues.add(cards.get(i));
            }else if(cards.get(i).getColor() == ALL){
                specials.add(cards.get(i));
            }
        }
        cards = new ArrayList<Card>();
        for(Card c: reds){
            sortedCards.add(c);
        }
        for(Card c: yellows){
            sortedCards.add(c);
        }
        for(Card c: greens){
            sortedCards.add(c);
        }
        for(Card c: blues){
            sortedCards.add(c);
        }
        for(Card c: specials){
            sortedCards.add(c);
        }
        for(Card c: sortedCards){
            cards.add(c);
        }
    }
}
