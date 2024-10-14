package src;
import java.util.*;

public class Solitaire {
    public enum Rank {  //set the rank enums
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    public enum Suit {  //set the suits enums
        CLUBS, SPADES, HEARTS, DIAMONDS
    }

    public static final String ANSI_RESET = "\u001B[00m";
    public static final String ANSI_BLACK = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[38m";
    public static final String ANSI_ERROR = "\u001B[41;30m";

    public static ArrayList<Card> deck = new ArrayList<>();   //The starting deck
    public static Tableau myTableau = new Tableau();  //create the tableau
    public static Foundation[] foundations = {new Foundation(0),    //create the foundations
                                              new Foundation(1),
                                              new Foundation(2),
                                              new Foundation(3)};
    public static int points = 0;
    public static int moves = 0;
    public static boolean gameContinue = true;
    public static boolean gameWon = true;

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j<=3; j+=1){    //fill with all cards
            for (int i = 1; i<=13; i+=1){
                deck.add(new Card(i,j));
            }
        }
        Collections.shuffle(deck);  //shuffle
        for(int i = 0; i<7; i++){                       //add correct card amount to all piles in the tableau
            for(int j = 0; j<=i; j++){
                myTableau.addCard(i,deck.getFirst());
                deck.removeFirst();
            }
        }
        
        for (int i = 0; i<7; i++){      //flip last cards of each pile in tableau
            myTableau.flipCard(i);
        }

            while (gameContinue){
                System.out.printf("\nPOINTS: %d\n", points);
                System.out.printf("MOVES: %d\n", moves);
                System.out.println("\nDRAW PILE (P):");
                System.out.println("["+deck.getFirst().getColour() + deck.getFirst()+ANSI_RESET+"]"+"\n");
                System.out.println("FOUNDATIONS (C, S, H ,D):");
                System.out.println(Arrays.toString(foundations)+"\n");
                myTableau.printTableau();
                Scanner in = new Scanner(System.in);
                System.out.println("What's the next move?");
                String moveCode = in.nextLine();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                executeMoveCode(moveCode);
            }
            System.out.println("Thanks for playing!");


    }
    public static void executeMoveCode(String moveCode){
        String[] moveCodeChar = moveCode.toUpperCase().split("");
        if(moveCodeChar.length == 1){
            if(moveCodeChar[0].equals("D")){
                drawFromDeck();
            }
            else if(moveCodeChar[0].equals("Q")){
                gameContinue = false;
            }
            else{
                wrongInput();
            }
            return;
        }
        String source = moveCodeChar[0];
        String destination = moveCodeChar[1];

        try{    //try to parse source int
            int sourceInt = Integer.parseInt(source);
            try{    //try to parse destination int
                int destinationInt = Integer.parseInt(destination);
                moveCard(myTableau.piles[sourceInt-1], myTableau.piles[destinationInt-1]);  //both source and destination are ints
            } catch (NumberFormatException e) {     //destination isn't an int
                switch (destination){
                    case "C":{
                        moveCard(myTableau.piles[sourceInt-1], foundations[0]);
                        break;
                    }
                    case "S":{
                        moveCard(myTableau.piles[sourceInt-1], foundations[1]);
                        break;
                    }
                    case "H":{
                        moveCard(myTableau.piles[sourceInt-1], foundations[2]);
                        break;
                    }
                    case "D":{
                        moveCard(myTableau.piles[sourceInt-1], foundations[3]);
                        break;
                    }
                    default:{
                        wrongInput();
                    }
                }
            }
        } catch (NumberFormatException e) { //source isnt an int
            try{    //destnation is an int
                int destinationInt = Integer.parseInt(destination);
                switch (source){    //player is moving from deck to pile, otherwise print error
                    case "P":{
                        moveCard(deck, myTableau.piles[destinationInt-1]);
                        break;
                    }
                    default:{
                        wrongInput();
                    }
                }

            } catch (NumberFormatException f) { //destination isn't an int Z
                switch (source){    //player is moving from deck to foundation, otherwise print error
                    case "P":{
                        switch (destination){
                            case "C":{
                                moveCard(deck, foundations[0]);
                                break;
                            }
                            case "S":{
                                moveCard(deck, foundations[1]);
                                break;
                            }
                            case "H":{
                                moveCard(deck, foundations[2]);
                                break;
                            }
                            case "D":{
                                moveCard(deck, foundations[3]);
                                break;
                            }
                            default:{
                                wrongInput();
                            }
                        }
                        break;
                    }
                    default:{
                        wrongInput();
                    }
                }
            }
        }
        //moveCard(myTableau.piles[selectedPileInt], foundations[selectedFoundationInt]);
    }

    public static void drawFromDeck(){
        Card heldCard = deck.getLast();
        heldCard.flip();
        deck.removeLast();
        deck.addFirst(heldCard);
    }

    public static int rankToInt(Rank rank) {
        return switch (rank) {
            case ACE -> 0;
            case TWO -> 1;
            case THREE -> 2;
            case FOUR -> 3;
            case FIVE -> 4;
            case SIX -> 5;
            case SEVEN -> 6;
            case EIGHT -> 7;
            case NINE -> 8;
            case TEN -> 9;
            case JACK -> 10;
            case QUEEN -> 11;
            case KING -> 12;
        };
    }
    public static int suitToInt(Suit suit){
        return switch (suit) {
            case Suit.CLUBS -> 0;
            case Suit.SPADES -> 1;
            case Suit.HEARTS -> 2;
            case Suit.DIAMONDS -> 3;
        };
    }
    public static Suit intToSuit(int intSuit) {
        return switch (intSuit) {
            case 0 -> Suit.CLUBS;
            case 1 -> Suit.SPADES;
            case 2 -> Suit.HEARTS;
            case 3 -> Suit.DIAMONDS;
            default -> null;
        };
    }

    public static void wrongMove(){
        System.out.println(ANSI_ERROR + " Oops, can't put that there! "+ ANSI_RESET);
    }

    public static void wrongInput(){
        System.out.println(ANSI_ERROR + " Incorrect input format! " + ANSI_RESET);
    }

    public static <A,B> void moveCard(A source, B destination){
        //if moving from pile
        if (source instanceof Pile) {
            //moving to foundation
            if (destination instanceof Foundation) {    //moving from pile to foundation
                var heldCard = ((Pile) source).getLastCard();   //last card
                //check if move is legal, if so add to foundation
                var moveSuccess = ((Foundation) destination).placeCard(heldCard);
                if (moveSuccess) {
                    //and remove from pile
                    ((Pile) source).removeLastCard();
                    points+=20;
                    moves +=1;
                } else {
                    //if invalid move, print error message
                    wrongMove();
                }
            } else if (destination instanceof Pile) { //moving  from pile to another pile
                int n = ((Pile) source).getLength();    //get length of source pile
                for (int i=0; i<n; i+=1){       //loop through source pile
                    Card heldCard = ((Pile) source).getCardAt(i);       //pick up each card
                    if (!heldCard.isFlipped()){     //if card is hidden, continue
                        continue;
                    }
                    var moveSuccess = ((Pile) destination).placeCard(heldCard, false);  //else, try and place at destination
                    if (moveSuccess) {      //if successful, remove from source
                        //and remove from pile
                        ((Pile) source).removeCardAt(i);
                        points +=5;
                        moves +=1;
                        n-=1;
                        i-=1;
                    } else {    //else print error message
                        wrongMove();
                        break;
                    }
                    //This will shift all legal cards to the destination from the first legal card.
                }
            }
        } else if (source instanceof ArrayList<?>) {    //moving from draw pile
            var heldCard = (Card)((ArrayList<?>) source).getFirst();   //last card
            if (destination instanceof Foundation) {    //moving from draw pile to foundation
                //check if move is legal, if so add to foundation
                var moveSuccess = ((Foundation) destination).placeCard(heldCard);
                if (moveSuccess) {
                    //and remove from pile
                    ((ArrayList<?>) source).removeFirst();
                    Card newFirst = (Card)((ArrayList<?>) source).getFirst();
                    points+=10;
                    moves +=1;
                    if (!newFirst.isFlipped()){
                        ((ArrayList<?>) source).removeFirst();
                        newFirst.flip();
                        ((ArrayList<Card>) source).addFirst(newFirst);
                    }
                } else {
                    //if invalid move, print error message
                    wrongMove();
                }
            } else if (destination instanceof Pile) { //moving from draw pile to another pile
                var moveSuccess = ((Pile) destination).placeCard(heldCard, false);  //else, try and place at destination
                if (moveSuccess) {      //if successful, remove from source
                    //and remove from pile
                    ((ArrayList<?>) source).removeFirst();
                    Card newFirst = (Card)((ArrayList<?>) source).getFirst();
                    if (!newFirst.isFlipped()){
                        ((ArrayList<?>) source).removeFirst();
                        newFirst.flip();
                        ((ArrayList<Card>) source).addFirst(newFirst);
                    }
                } else {    //else print error message
                    wrongMove();
                }
            }
        }
    }
}
