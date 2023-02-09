import java.util.*;

public class Blackjack {

    public static ArrayList<String> suits = new ArrayList<String>() {
        {
            add("h");
            add("d");
            add("s");
            add("c");
        }
    };
    public static ArrayList<Integer> num = new ArrayList<>();
    public static ArrayList<String> cards = new ArrayList<>();
    public static ArrayList<String> hand = new ArrayList<>();
    public static ArrayList<Integer> value = new ArrayList<>();

    public static Random rand = new Random();
    public static int total = 0;
    public static void main(String[] args) {

        System.out.println("");
        
        for(int i = 0; i<suits.size(); i++){
            for(int j = 1; j<=13; j++){
                value.add(j);
                cards.add(j + suits.get(i));
                num.add(i*13+j);
            }
        }
        System.out.println(cards);
        System.out.println(num);
        
        //player
        //draws two cards
        hand.add(chooseCard());
        hand.add(chooseCard());

        System.out.println(hand + " " + total);

        while(total <= 21){
            break;
        }
    }

    public static String chooseCard(){
        String card;
        int i = rand.nextInt(num.size()-1);
        card = cards.get(i);
        num.remove(i);
        total += value.get(i);
        return card;
    } 

}
