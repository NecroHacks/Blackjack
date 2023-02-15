import java.util.*;

public class Blackjack {
    static Colors color = new Colors();

    public static ArrayList<String> suits = new ArrayList<String>() {
        {
            add(color.Red);
            add(color.Yellow);
            add(color.Cyan);
            add(color.Green);
        }
    };
    //Cards, value of cards, and cards with suites to differentiate.
    public static ArrayList<Integer> num = new ArrayList<>();
    public static ArrayList<String> cards = new ArrayList<>();
    public static ArrayList<Integer> value = new ArrayList<>();

    public static ArrayList<String> hand = new ArrayList<>();

    public static Random rand = new Random();
    public static int total = 0;
    public static int ace = 0;
    public static void main(String[] args) {
        String c;
        System.out.println(color.Reset);
        
        
        for(int i = 0; i<suits.size(); i++){
            for(int j = 1; j<=13; j++){

                if(j == 1){
                    value.add(1);
                    c = (suits.get(i) + "A" + color.Reset);
                }
                else if(j>=11){
                    value.add(10);
                    if(j==11){
                        c = (suits.get(i) + "J" + color.Reset);
                    }
                    else if(j==12){
                        c = (suits.get(i) + "Q" + color.Reset);
                    }
                    else{
                        c = (suits.get(i) + "K" + color.Reset);
                    }
                }
                
                else{
                    value.add(j);
    
                    c = (suits.get(i) + j +color.Reset);
                }
                
                cards.add(c);
                num.add((i*13+j));

            }
        }
        
        System.out.println(cards);
        System.out.println(num);
        
        //player
        //draws two cards
        hand.add(chooseCard());
        hand.add(chooseCard());

        System.out.println(hand + " " + total);

        while(total < 17){
            hand.add(chooseCard());
            System.out.println(hand + " " + total);
        }
    }
    
    /**
     * 
     * @return A random card within the deck, and removes that card for future pulls.
     */
    public static String chooseCard(){
        String card;
        int i = rand.nextInt(num.size()-1);
        card = cards.get(num.get(i));
        //total += value.get(num.get(i));
        calcTotal(value.get(num.get(i)));
        num.remove(i);
        //System.out.println(num);
        return card;
    } 

    /**
     * Calculates the total value of the cards in your hand, following Blackjack rules. Aces are worth 1 or 11.
     * @param number
     * @return sum of all cards in hand
     */
    public static int calcTotal(int number){
        if(total >= 11){
            total += number;
        }
        else{
            if(number ==  1){
                total += 11;
                ace ++;
            }
            else{
                total += number;
            }
        }
        if(total > 21 && ace > 0){
            total -= 10;
            ace--;
        }

        return total;
    }

    public static void botPlay(){

    }

}
