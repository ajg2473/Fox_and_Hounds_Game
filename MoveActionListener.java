/*
@author Andrew Grubbs and Roshan Matthews
@version 2.0
Date - October 23, 2017
Instructor - Dan Kennedy
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
@author Andrew Grubbs and Roshan Mathew
@version 2.0
Date - October 23, 2017
Instructor - Dan Kennedy
*/

public class MoveActionListener implements ActionListener
{
   // Attributes
   private static int nextStep = 1;
   private int x;
   private int y;
   private static int currentX;
   private static int currentY;
   private static int moveX;
   private static int moveY;
   private static int user;

   public MoveActionListener(int _x, int _y, int _player)
   {
      x = _x;
      y = _y;
      user = _player;
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      
      switch(nextStep) // First step then second step
      {  
         case 1:
            // User's first click
            currentX = x;
            currentY = y;
            System.out.println("X: " + x);
            System.out.println("Y: " + y);
            switch(user)
            {
               case 1:  // Confirm if user is hound player
                  if(FoxHoundsGUI.validateUser(currentX, currentY, user))
                  {nextStep = 2;}
                  else
                  {JOptionPane.showMessageDialog(null, "Sorry, it is hound player's turn");}
                  break;
               case 2:  // confirm if user is fox player
                  if(FoxHoundsGUI.validateUser(currentX, currentY, user))
                  {nextStep = 2;}
                  else
                  {JOptionPane.showMessageDialog(null, "Sorry, it is fox player's turn");}
                  break;
            }
            break;
         case 2: 
            // User's second click
            moveX = x;
            moveY = y;
            //System.out.println("X: " + moveX);
            //System.out.println("Y: " + moveY);
            
            // Rule - Check to see if user clicks fox/hound and clicks one next move on empty square. 
            if(FoxHoundsGUI.moveValidate(currentX, currentY, moveX, moveY, user))
            {
               // After confirm the next move is valid, update the board.
               if(FoxHoundsGUI.movePosition(currentX, currentY, moveX, moveY, user))
               {
                  // Rule - Check to see if either hound/fox wins
                  if(FoxHoundsGUI.winPosition(moveX, moveY, user))
                  {
                     String player = "";
                     if(user == 1)
                     {player = "hound";}
                     else
                     {player = "fox";}
                     
                     String message = "Player " + player +" has won the game. \nDo you want to play again?";
                     int answer = JOptionPane.showConfirmDialog(null, message , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                     if (answer == JOptionPane.YES_OPTION) {
                        // User clicked YES.
                        FoxHoundsGUI.startBoard();
                        FoxHoundsGUI.startIcon();
                        user = 2;
                     } else if (answer == JOptionPane.NO_OPTION) {
                     	// User clicked NO.
                        System.exit(1);
                     }	
                  }
                  // Change player
                  user = FoxHoundsGUI.changePlayer(user);
                  if(user == 1)
                  {
                     FoxHoundsGUI.player1.setSelected(true);
                     FoxHoundsGUI.player2.setSelected(false);
                  }
                  else
                  {
                     FoxHoundsGUI.player1.setSelected(false);
                     FoxHoundsGUI.player2.setSelected(true);
                  }
               }
               else
               {JOptionPane.showMessageDialog(null, "Incorrect move");}
            }
            else
            {JOptionPane.showMessageDialog(null, "Incorrect position");}
            nextStep = 1;
            break;
      }
     
   }
}