package src;

public class Tableau {
    public Pile[] piles = {
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile()
    };

    public void addCard(int pileNo, Card card){
        piles[pileNo].placeCard(card, true);
    }

    public void flipCard(int pileNo){
        piles[pileNo].flipLastCard();
    }

    public boolean tableauEmpty(boolean empty){
        for (int i = 0; i<7; i+=1){
            if (piles[i].getLength() != 0){
                return false;
            }
        }
        return true;
    }
    public void printTableau(){
        int printWidth = 20;
        StringBuilder string = new StringBuilder(String.format("%-" + (int) ((printWidth / 2) - 1) + "s", " "));    //print pile numbers
        for (int i = 0; i<7; i+=1){
            string.append(String.format("%-" + printWidth + "s", i + 1));
        }

        string.append("\n");

        System.out.println(string);

        for (int i = 0; i<100; i+=1){   //for up to 100 cards,
            int end =0;
            for (int j = 0; j < 7; j += 1) {    //print card at i of each pile
                Card card = piles[j].getCardAt(i);
                if (card == null) {
                    System.out.printf("%-" + printWidth + "s", "");
                    end ++;
                }
                else {
                    String printColour = "";
                    System.out.printf(card.getColour()+ "%-" + printWidth + "s" + Solitaire.ANSI_RESET, card);
                }
            }
            if (end==7){
                break;
            }
            else{
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }
}
