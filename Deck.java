import java.util.*;
public class Deck{
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
    
    // The deck and the used cards of the deck
    private ArrayList<Card> deck;
    private ArrayList<Card> usedCards = new ArrayList<Card>();
    
    /**
     * Constructor for a new deck
     * Makes a new deck with cards 0-9 of all colors 
     * twice and their speical cards with 8 wild cards,
     * 4 +4's and 4 normal
     */
    public Deck(){
        deck = new ArrayList<Card>();
        for(int i = 0; i <= WILD4; i++){
            if(i < WILD){
                for(int j = 0; j < BLUE; j++){
                    if(i != 0){
                        Card card = new Card(j + 1, i);
                        deck.add(card);
                        Card card2 = new Card(j + 1, i);
                        deck.add(card2);
                    }else{
                        Card card = new Card(j + 1, i);
                        deck.add(card);
                    }
                }
            }else{
                for(int k = 0; k < 4; k++){
                    Card card = new Card(5, i);
                    deck.add(card);
                }
            }
        }
    }
    
    /**
     * Deals the top card, shuffles discard
     * if needed
     * @return The card on the top of the deck
     */
    public Card deal(){
        if(deck.size() <= 0){
            for(Card c: usedCards){
                deck.add(c);
            }
            shuffle();
            usedCards = new ArrayList<Card>();
        }
        return deck.remove(0);
    }
    
    /**
     * Shuffles the deck
     */
    public void shuffle(){
        for(int i = 0; i < deck.size(); i++)
        {
            int randomIndex = Randomizer.nextInt(deck.size());
            Card x = deck.get(i);
            Card y = deck.get(randomIndex);
            
            deck.set(i, y);
            deck.set(randomIndex, x);
        }
    }
    
    /**
     * Gets the entire deck
     * @return The deck
     */
    public ArrayList<Card> getCards(){
        return deck;
    }
    
    /**
     * Prints the entire deck
     */
    public void print(){
        for(Card c: deck){
            System.out.println(c);
        }
    }
    
    /**
     * Gets the top card without shuffling
     * @return The top card
     */
    public Card topCard(){
        return deck.remove(0);
    }
    
    /**
     * Adds a card to the discard pile
     */
    public void addUsedCard(Card c){
        usedCards.add(c);
    }
    
}
