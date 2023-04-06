import java.awt.Color;
import java.text.BreakIterator;
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
    public static ArrayList<String> botHand = new ArrayList<>();

    public static Random rand = new Random();
    public static Scanner in = new Scanner(System.in);

    public static int total = 0;
    public static int botTotal = 0;
    public static int ace = 0;
    public static int botAce = 0;
    public static int bet;
    public static int bal = 100;
    public static void main(String[] args) {
        reset();
        System.out.println(cards);
        System.out.println(num);
        

        //player
        //draws two cards
        
        // hand.add(chooseCard());
        // hand.add(chooseCard());

        System.out.println("Press enter to continue...");

        
            //init bet
            while(true){
                try{
                    in.nextLine();
                    System.out.println("Enter bet: ");
                    bet = in.nextInt();
                    in.nextLine();
                    if(bet>0 && bet<=bal){
                        break;
                    }
                    System.out.println(color.Red + "Enter a valid number(0 - "+ bal + ")" + color.Reset);

                }
                catch(InputMismatchException e){
                    System.out.println(color.Red + "Enter a valid number(0-"+ bal + ")" + color.Reset);
                    
                }
            }


            reset();
            total = 0;
            hand.add(chooseCard(true));
            hand.add(chooseCard(true));
    
            botHand.add(chooseCard(false));
            

            System.out.println(hand + " " + total);
            System.out.println(botHand + " " + botTotal);

            while(total < 21){
                if(!play()){
                    break;
                }
                System.out.println(hand + " " + total);
                
            }
            
            if(total == 21){
                if(botTotal == 21){
                    tie();
                    
                }
                else{
                    win();
                    
                }
            }
            else if(total > 21){
                lose();
                              
            }
            else{
                if(botTotal>21){
                    win();

                }
                else if(total>botTotal){
                    win();

                }
                else if(total==botTotal){
                    tie();

                }
                else{
                    lose();

                }
            }
        
    }
    
    /**
     * 
     * @return A random card within the deck, and removes that card for future pulls.
     */
    public static String chooseCard(boolean player){
        String card;
        int i = rand.nextInt(num.size()-1);
        card = cards.get(num.get(i));
        //total += value.get(num.get(i));
        if(player){ 
            calcTotal(value.get(num.get(i)));
        }
        else{
            calcBotTotal(value.get(num.get(i)));
        }
        num.remove(i);
        //System.out.println(num);
        return card;
    } 

    /**
     * Calculates the total value of the cards in the bots hand, following Blackjack rules. Aces are worth 1 or 11.
     * @param number
     * @return sum of all cards in hand
     */
    public static int calcBotTotal(int number){
        if(botTotal >= 11){
            botTotal += number;
        }

        else{
            if(number ==  1){
                botTotal += 11;
                ace ++;
            }
            else{
                botTotal += number;
            }

        }

        if(botTotal > 21 && ace > 0){
            botTotal -= 10;
            ace--;
        }

        return botTotal;
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

    //Starts the play
    public static boolean play(){   
        System.out.println("Hit(h) or Stand(s)?");
        String act = in.nextLine();
        if(act.toLowerCase().equals("h")){
            hand.add(chooseCard(true));
            return true;
        } 
        else if(act.toLowerCase().equals("s")){
            return false;
        }
        else{
            System.out.println("Enter a valid response");
            return true;
        }

    }

    //I feel like these are self explanitory
    public static void win(){
        System.out.println("You win!");
        //Win bet x2
        bal += bet;
    }

    public static void lose(){
        System.out.println("You lose");
        //Lose bet
        bal -= bet;
    }

    public static void tie(){
        System.out.println("You tied");
        //Return ballance (do nothing)
    }

    /**
     * Resets your hand and the deck
     */ 
    public static void reset(){
        String c;
        System.out.println(color.Reset);
        hand.clear();
        num.clear();
        
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
    }
}
