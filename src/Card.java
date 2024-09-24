package src;
/*
Card class, init with int for corresponding suit and rank

SUITS:
  0      1       2       3
  Clubs  Spades  Hearts  Diamonds

RANKS:
  1   2   3   4   5   6   7   8   9   10  11  12  13
  A   ... ... ... ... ... ... ... ... ... J   Q   K
*/

public class Card {
    private Solitaire.Rank rank;
    private Solitaire.Suit suit;
    private boolean flipped;
    public Card(int initRank, int initSuit){
        rank = intToRank(initRank);
        suit = intToSuit(initSuit);
        flipped = false;
    }
    public Solitaire.Rank getRank() {
        return rank;
    }
    public Solitaire.Suit getSuit() {
        return suit;
    }
    @Override
    public String toString(){
        if (flipped) {
            return rank.toString() + " of " + suit.toString();
        }
        else{
            return "████████████████████";
        }
    }
    public void flip(){
        flipped = true;
    }

    public boolean isFlipped(){
        return flipped;
    }

    public boolean isEqual(Card cardB){
        if (cardB.suit == suit && cardB.rank == rank){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean canGoOn(Card cardB){
        return Solitaire.rankToInt(rank) == Solitaire.rankToInt(cardB.rank) + 1 && suit == cardB.suit;
    }
    private Solitaire.Rank intToRank(int intRank) {
        return switch (intRank) {
            case 1 -> Solitaire.Rank.ACE;
            case 2 -> Solitaire.Rank.TWO;
            case 3 -> Solitaire.Rank.THREE;
            case 4 -> Solitaire.Rank.FOUR;
            case 5 -> Solitaire.Rank.FIVE;
            case 6 -> Solitaire.Rank.SIX;
            case 7 -> Solitaire.Rank.SEVEN;
            case 8 -> Solitaire.Rank.EIGHT;
            case 9 -> Solitaire.Rank.NINE;
            case 10 -> Solitaire.Rank.TEN;
            case 11 -> Solitaire.Rank.JACK;
            case 12 -> Solitaire.Rank.QUEEN;
            case 13 -> Solitaire.Rank.KING;
            default -> null;
        };
    }
    private  Solitaire.Suit intToSuit(int intSuit) {
        return switch (intSuit) {
            case 0 -> Solitaire.Suit.CLUBS;
            case 1 -> Solitaire.Suit.SPADES;
            case 2 -> Solitaire.Suit.HEARTS;
            case 3 -> Solitaire.Suit.DIAMONDS;
            default -> null;
        };
    }
}
