import java.util.*;
import java.lang.*;
class Main extends ConsoleProgram{
  private ConsoleProgram consoleProgram = new ConsoleProgram();
  private ArrayList<Hand> players = new ArrayList<Hand>();
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

    private boolean reversedBool = false;
    private void playRound(Deck deck){
        int numPlayers = 13;
        do{
            numPlayers = readInt("Number of players: ");
        }while(numPlayers > 12);
        for(int i = 0; i < numPlayers; i++){
            players.add(new Hand());
        }
        for(int k = 0; k < 7; k++){
            for(int j = 0; j < numPlayers; j++){
                players.get(j).addCard(deck.deal());
            }
        }
        Card start = new Card(0, 0);
        do{
            start = deck.topCard();
            deck.addUsedCard(start);
        }while(start.getRank() >= SKIP);
        playerTurns(deck, start, 1, 0);
    }
    private void playerTurns(Deck deck, Card onTop, int player, int specialCardUsed){
        clearScreen();
        Card playedCard = onTop;
        if(specialCardUsed == 12){
            reversedBool = !reversedBool;
            deck.addUsedCard(onTop);
        }else{
            readLine("Enter to continue, player " + player + " ");
            clearScreen();
            if(specialCardUsed != 0){
                deck.addUsedCard(onTop);
            }
            Hand hand = players.get(player - 1);
            if(specialCardUsed == WILD4){
                hand.addCard(deck.deal());
                hand.addCard(deck.deal());
                hand.addCard(deck.deal());
                hand.addCard(deck.deal());
                System.out.println("New hand (+4'd): ");
                System.out.println(hand);
                readLine("Enter to continue ");
            }else if(specialCardUsed == SKIP){
                readLine("You've been skipped. ");
            }else if(specialCardUsed == DRAW2){
                hand.addCard(deck.deal());
                hand.addCard(deck.deal());
                System.out.println("New hand (+2'd): ");
                System.out.println(hand);
                readLine("Enter to continue ");
            }else{
                System.out.println(onTop);
            }
            if(specialCardUsed != WILD4 && specialCardUsed != SKIP && specialCardUsed != DRAW2){
                System.out.println(hand);
                boolean pickedCard = false;
                int specialCardPicked = 0;
                while(!pickedCard){
                    int playedCardIndex = readInt("What card would you like to play? (0 to draw) ");
                    boolean playedPlusFour = false;
                    if(playedCardIndex == 0){
                        hand.addCard(deck.deal());
                        System.out.print(hand);
                        readLine("\nEnter to continue ");
                        pickedCard = true;
                        // break; not working for some reason?
                    }
                    if(playedCardIndex != 0){
                        try{
                            playedCard = hand.getCard(playedCardIndex);
                        }catch(Exception e){
                            System.out.println("Try again.");
                        }
                        if(playedCard.getColor() == onTop.getColor() || playedCard.getRank() == onTop.getRank() || playedCard.getColor() == 5){
                            if(playedCard.getRank() >= WILD){
                                int color = readInt("1: Red \n2: Yellow \n3: Green \n4: Blue\nEnter a number: ");
                                playedCard.setColor(color);
                            }
                            hand.useCard(playedCardIndex);
                            pickedCard = true;
                            specialCardPicked = playedCard.getRank();
                        }
                    }else{
                        playedCard = onTop;
                    }
                }
                playerTurns(deck, playedCard, !reversedBool ? (player + 1 > players.size() ? 1 : player + 1) : (player - 1 < 1 ? players.size() : player - 1), specialCardPicked);
            }else{
                playerTurns(deck, playedCard, !reversedBool ? (player + 1 > players.size() ? 1 : player + 1) : (player - 1 < 1 ? players.size() : player - 1), 1);
            }
        }
        playerTurns(deck, playedCard, !reversedBool ? (player + 2 > players.size() + 1 ? 2 : player + 2) : (player == 1 ? players.size() - 1 : player - 2 < 1 ? players.size() : player - 2), 1);
    }

    public void run()
    {
        Deck deck = new Deck();
        deck.shuffle();
        deck.shuffle();
        playRound(deck);
    }
    
    public static void clearScreen() {  
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }  
}
