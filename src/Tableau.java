package src;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tableau {
    private Pile[] piles = {
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile(),
            new Pile()
    };
    private int printWidth = 23;

    public void addCard(int pileNo, Card card){
        piles[pileNo].addCard(card);
    }

    public void flipCard(int pileNo){
        piles[pileNo].flipCard();
    }

    @Override
    public String toString(){
        String string = "";

        for (int i = 0; i<100; i+=1){
            int end =0;
            for (int j = 0; j < 7; j += 1) {
                Card card = piles[j].getCardAt(i);
                if (card == null) {
                    string += String.format("%-"+ printWidth + "s", " ");
                    end ++;
                }
                else {
                    string += String.format("%-" + printWidth + "s", card);
                }
            }
            if (end==7){
                break;
            }
            else{
                string += "\n";
            }
        }
        return string;
    }
}
