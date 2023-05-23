import java.awt.Color;
import java.text.BreakIterator;
import java.util.*;
import java.util.concurrent.locks.StampedLock;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//TODO: change "System.out.println();" to use GFG.java

public class Blackjack {
    static Colors color = new Colors();

    public static ArrayList<String> suits = new ArrayList<String>() {
        //using colors instead, might switch to something else like {c,h,s,d} to make it easier on GUI
        {
            add("♥");
            add("♦");
            add("♣");
            add("♠");
        }
    };

    //Cards, value of cards, and cards with suites to differentiate.
    private static ArrayList<Integer> num = new ArrayList<>();
    private static ArrayList<String> cards = new ArrayList<>();
    private static ArrayList<Integer> value = new ArrayList<>();

    private static ArrayList<String> hand = new ArrayList<>();
    private static ArrayList<String> botHand = new ArrayList<>();

    private static Random rand = new Random();
    //private static Scanner in = new Scanner(System.in);

    public static int total = 0;
    public static int botTotal = 0;
    public static int ace = 0;
    public static int botAce = 0;
    public static int bet;
    public static int bal = 100;
    private static int i = 0;
    public static JFrame frame;

    private static JTextField playerCardsBox = new JTextField();
    private static JTextField playerTotalBox = new JTextField();

    private static JTextField botCardsBox = new JTextField();
    private static JTextField botTotalBox = new JTextField();

    private static JButton hitButton = new JButton();
    private static JButton standButton = new JButton();
    private static boolean play;
    private static JTextField moneys = new JTextField();
    private static JTextField betDisplay = new JTextField();
    private static JButton resetButton = new JButton();

    private static JTextField message = new JTextField();
    public static void main(String[] args) {}

    public static void game(JFrame f){
        frame = f;
        reset();
        System.out.println(cards);
        System.out.println(num);
        
        // JTextField textFieldUserName = new JTextField(50);
        // textFieldUserName.setBounds(50,50,150,20);
        // textFieldUserName.setBackground(Color.red);
        // frame.add(textFieldUserName);

        hitButton.setText("Hit");
        standButton.setText("Stand");
        hitButton.setBounds(100,200,100,30);
        standButton.setBounds(300,200,100,30);

        hitButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //code goes here
                try{
                    chooseCard(true);
                    if(total>21){
                        hitButton.setVisible(false);
                        standButton.setVisible(false);
                        while(botTotal < 17){
                            botHand.add(chooseCard(false));
                            //System.out.println(botHand + " " + botTotal);
                        }
                        message.setVisible(true);
                        check();
                    }
                }
                catch(Exception ex){
                    System.err.println("Something went wrong.");
                }
                //System.out.println(hand + " " + total);
            }
        });
        standButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //code goes here
                try{
                    hitButton.setVisible(false);
                    standButton.setVisible(false);
                    while(botTotal < 17){
                        botHand.add(chooseCard(false));
                        //System.out.println(botHand + " " + botTotal);
                    }
                    message.setVisible(true);
                    check();
                }
                catch(Exception ex){
                    System.err.println("Something went wrong.");
                }
                //System.out.println(hand + " " + total);
            }
        });

        message.setBounds(200, 350, 100, 30);
        message.setEditable(false);
        message.setVisible(false);

        resetButton.setBounds(200, 400, 100, 30);
        resetButton.setText("Next round");
        resetButton.setVisible(false);

        frame.add(resetButton);
        frame.add(message);
        frame.add(hitButton);
        frame.add(standButton);
        hitButton.setVisible(false);
        standButton.setVisible(false);

        JTextField playerCardsPrompt = new JTextField();
        JTextField botCardsPrompt = new JTextField();

        playerCardsPrompt.setBounds(200, 130, 100, 20);
        playerCardsPrompt.setEditable(false);
        playerCardsPrompt.setVisible(false);
        playerCardsPrompt.setText("Cards:");

        playerCardsBox.setBounds(200, 150, 100, 50);
        playerCardsBox.setEditable(false);
        playerCardsBox.setVisible(false);
        playerCardsBox.setText("");
        
        playerTotalBox.setBounds(200, 200, 100, 20);
        playerTotalBox.setEditable(false);
        playerTotalBox.setVisible(false);
        playerTotalBox.setText("");

        botCardsPrompt.setBounds(200, 230, 100, 20);
        botCardsPrompt.setEditable(false);
        botCardsPrompt.setVisible(false);
        botCardsPrompt.setText("Dealer:");

        botCardsBox.setBounds(200, 250, 100, 50);
        botCardsBox.setEditable(false);
        botCardsBox.setVisible(false);
        botCardsBox.setText("");
        
        botTotalBox.setBounds(200, 300, 100, 20);
        botTotalBox.setEditable(false);
        botTotalBox.setVisible(false);
        botTotalBox.setText("");

        frame.add(botCardsPrompt);
        frame.add(botTotalBox);
        frame.add(botCardsBox);
        frame.add(playerCardsPrompt);
        frame.add(playerTotalBox);
        frame.add(playerCardsBox);

        betDisplay.setBounds(400,0,100,25);
        betDisplay.setVisible(true);
        betDisplay.setEditable(false);

        moneys.setBounds(0,0,100,25);
        moneys.setVisible(true);
        moneys.setEditable(false);

        frame.add(betDisplay);
        frame.add(moneys);
        betDisplay.setText(""+bet);
        moneys.setText("Ballance: "+bal);

        //System.out.println("Press enter to continue...");
        JButton confirm = new JButton("Confirm");
        JTextField totalBet = new JTextField(" ");
        JTextArea prompt = new JTextArea("Enter bet:");
        confirm.setBounds(150,225,200,25);  //(xpos, ypos, xsize, ysize)
        totalBet.setBounds(150,200,200,25); //(xpos, ypos, xsize, ysize)
        prompt.setBounds(150,150,200,50);   //(xpos, ypos, xsize, ysize)

        //totalBet.setBackground(Color.black); //Textbox
        //totalBet.setForeground(Color.white); //Text

        frame.add(totalBet);
        frame.add(prompt);
        frame.add(confirm);
        totalBet.setText(null);

        prompt.setEditable(false);
        frame.setVisible(true);

        hitButton.setBackground(Color.green);
        standButton.setBackground(Color.red);

        resetButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //code goes here
                try{
                    totalBet.setVisible(true);
                    prompt.setVisible(true);
                    confirm.setVisible(true);
                    playerCardsPrompt.setVisible(false);
                    playerCardsBox.setVisible(false);
                    playerTotalBox.setVisible(false);
                    botCardsPrompt.setVisible(false);
                    botCardsBox.setVisible(false);
                    botTotalBox.setVisible(false);
                    message.setVisible(false);
                    resetButton.setVisible(false);
                    
                }
                catch(Exception ex){
                    System.err.println("Something went wrong.");
                }
            }
        });

        confirm.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //code goes here
                try{
                    if(totalBet.getText().isEmpty() || Integer.parseInt(totalBet.getText()) < 0 || Integer.parseInt(totalBet.getText()) > bal){
                        throw new Exception();
                    }
                    else{
                        bet = Integer.parseInt(totalBet.getText());
                        betDisplay.setText("Bet: "+bet);
                        moneys.setText("Ballance: "+(bal-bet));
                        confirm.setVisible(false);
                        totalBet.setVisible(false);
                        prompt.setVisible(false); 
                        playerCardsBox.setVisible(true);
                        playerTotalBox.setVisible(true);
                        playerCardsPrompt.setVisible(true);

                        botCardsPrompt.setVisible(true);
                        botTotalBox.setVisible(true);
                        botCardsBox.setVisible(true);

                        hitButton.setVisible(true);
                        standButton.setVisible(true);
                        

                        play = true;

                        round();
                    }
                }

                catch(Exception ex){
                    prompt.setText("Enter bet:\nEnter a number between 0 and "+bal);
                }
            }
        });



    }

    public static void round(){
        //System.out.println(hand);

        //resets the game
        reset();
        total = 0;
        botTotal = 0;
        hand.add(chooseCard(true));
        hand.add(chooseCard(true));

        botHand.add(chooseCard(false));
        
    }
    
    /**
     * Checks for who won
     */
    public static void check(){
        
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
        moneys.setText("Bal: "+bal);
        betDisplay.setText("Bet: 0");
        bet = 0;
        resetButton.setVisible(true);
        
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
            playerTotalBox.setText("Total: " + total);
            playerCardsBox.setText(playerCardsBox.getText()+" "+card);
            
        }
        else{
            calcBotTotal(value.get(num.get(i)));
            botTotalBox.setText("Total: " + botTotal);
            botCardsBox.setText(botCardsBox.getText()+" "+card);
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
                botAce ++;
            }
            else{
                botTotal += number;
            }

        }

        if(botTotal > 21 && botAce > 0){
            botTotal -= 10;
            botAce--;

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
            if(number == 1){
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
            //System.out.println("djkghjsdfghjfdsgjkdshngkjdsfhbjkdsfjd");
        }

        return total;
    }

    //Starts the play
    /*public static boolean play(){
        //JButton hit = new JButton("Hit");
        //JButton stand = new JButton("Stand");
        
        hitButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //code goes here
                try{
                    play = true;
                    System.out.print("stgrdg");
                }
                catch(Exception ex){
                    System.err.println("Something went wrong.");
                }
            }
        });
        standButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //code goes here
                try{
                    play = false;
                }
                catch(Exception ex){
                    System.err.println("Something went wrong.");
                }
            }
        });
        //while(i!=0){return play;}
        //return false;
    }*/

    //I feel like these are self explanitory
    //TODO: changes sysouts to display on screen
    public static void win(){
        //System.out.println("You win!");
        //Win bet x2
        message.setText("You win!");
        bal += bet;
    }

    public static void lose(){
        //System.out.println("You lose");
        //Lose bet
        message.setText("You lose.");
        bal -= bet;
    }

    public static void tie(){
        //System.out.println("You tied");
        //Return ballance (do nothing)
        message.setText("You tied");
    }

    /**
     * Resets your hand and the deck
     */ 
    public static void reset(){
        String c;

        resetButton.setVisible(true);

        playerCardsBox.setText("");
        playerTotalBox.setText("Total: ");
        botCardsBox.setText("");
        botTotalBox.setText("Total: ");

        hitButton.setVisible(true);
        standButton.setVisible(true);
        //System.out.println(color.Reset);

        hand.clear();
        botHand.clear();
        num.clear();
        cards.clear();

        ace = 0;
        botAce = 0;

        for(int i = 0; i<suits.size(); i++){
            for(int j = 1; j<=13; j++){

                if(j == 1){
                    value.add(1);
                    c = (suits.get(i) + "A");
                    
                }
                else if(j>=11){
                    value.add(10);
                    if(j==11){
                        c = (suits.get(i) + "J");
                        
                    }
                    else if(j==12){
                        c = (suits.get(i) + "Q");
                        
                    }
                    else{
                        c = (suits.get(i) + "K");
                        
                    }
                }
                
                else{
                    value.add(j);
                    
                    c = (suits.get(i) + j);
                }
                
                cards.add(c);
                num.add((i*13+j));
            }
        }
    }
}