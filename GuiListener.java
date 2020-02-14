import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
@author Andrew Grubbs and Roshan Mathew
@version 2.0
Date - October 23, 2017
Instructor - Dan Kennedy
*/
/*Class to handle the events of GUI*/

public class GuiListener implements ActionListener
{
   private JMenuItem about;
   private JMenuItem quit;
   private JButton reset;
   
   public GuiListener(JMenuItem _about,JMenuItem _quit,JButton _reset)
   {
      about = _about;
      quit = _quit;
      reset = _reset;
   }


    public void actionPerformed(ActionEvent e)
   {
      String actionString = e.getActionCommand();      
      if(actionString.equalsIgnoreCase("about") )
      {
         JFrame dialogframe = new JFrame("Fox And Hounds - Gameplay");
         JOptionPane.showMessageDialog(dialogframe,"This game is played on an 8×8 chess/checkerboard. Only the dark "+ "\n" + 
         "squares are used. The four hounds are initially placed on the dark " + "\n" + 
         "squares are initially placed on the dark squares at one edge of the " + "\n" + 
         "board; the fox is placed on any dark square on the opposite edge. " + "\n" + 
         "The objective of the fox is to cross from one side of the board to " + "\n" + 
         "the other, arriving at any one of the hounds' original squares; the " + "\n" + 
         "hounds' objective is to prevent it from doing so."+ "\n" + "\n" + 
         
         "The hounds move diagonally forward one square. The fox moves " + "\n" + 
         "diagonally forward or backward one square. However there is no " + "\n" + 
         "jumping, promotion, or removal of pieces. The play alternates with " + "\n" + 
         "the fox moving first. The player controlling the hounds may move " + "\n" + 
         "only one of them each turn." + "\n" + "\n" + 
         
         "The fox is trapped when it can no longer move to a vacant square. " + "\n" + 
         "It is possible for two hounds to trap the fox against an edge of the " + "\n" + 
         "board (other than their original home-row) or even one corner " + "\n" + 
         "where a single hound may do the trapping. Should a hound reach " + "\n" + 
         "the fox's original home row it will be unable to move further." );
      }
      else if(actionString.equalsIgnoreCase("quit") )
      {
         System.exit(0);
      }
      else if(actionString.equals("Reset Game") )
      {
         FoxHoundsGUI.startBoard();
         FoxHoundsGUI.startIcon();
         
      }
      
   }

}