import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GFG extends JFrame{
        // Main driver method
        
        public static void main(String[] args)
        {
            JFrame frame = new JFrame("Blackjack"); // creating instance of JFrame
            final JTextField textField = new JTextField(); // creating instance of JTextField
            JButton startButton = new JButton("Start Game"); // creating instance of JButton
            
            startButton.setBounds(150, 200, 220, 50); // x axis, y axis, width, height
            startButton.setBackground(Color.BLUE); // box color    
            startButton.setForeground(Color.RED); // text color
            textField.setBounds(50,50, 150,20); 

            // JTextField textFieldUserName = new JTextField(50);
            // textFieldUserName.setBounds(50,50,150,20);
            // textFieldUserName.setBackground(Color.red);
            // frame.add(textFieldUserName);

            frame.add(startButton); // adding buttxon in JFrame
            frame.setSize(500, 600); // 400 width and 500 height
            frame.setLayout(null); // using no layout managers
            frame.setVisible(true); // making the frame visible


            startButton.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    //code goes here
                    startButton.setVisible(false);
                    play(frame);

                }
            });





            
        }

        public static void play(JFrame frame){

            Blackjack.game(frame);

        }
}
