import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JButton; //imports JButton library
import java.awt.GridLayout; //imports GridLayout library
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

/*
@author Andrew Grubbs and Roshan Mathew
@version 2.0
Date - October 23, 2017
Instructor - Dan Kennedy
*/

public class FoxHoundsGUI extends JFrame 
{
   //Attributes
   private JFrame frame;
   public static JButton[][] button;
   private JMenuBar menuBar;
   private JMenu menu;
   private JMenuItem about;
   private JMenuItem quit;
   private JButton reset;
   private JLabel houndplayer;
   private JLabel foxplayer;
   static JPanel gridPanel;
   private static int player = 1;
   private static int[][] board = new int[8][8];
   private static int hound = 1;
   private static int fox = 2;
   public static JRadioButton player1, player2;
   
   String name1; 
   String name2; 
   
   public FoxHoundsGUI ()
   {
      // Set up JFrame GUI      
     
      JFrame frame = new JFrame("Fox & Hounds - The Game");
      JFrame aboutDialog = new JFrame();      
    
      setSize(600, 600);
      setLocation(700,200);
      
      //setting up the menubar and menuitems      
      JMenuBar menuBar = new JMenuBar();
      
      JMenu menu = new JMenu("File");
      about = new JMenuItem("About");     
      quit = new JMenuItem("Quit");
      
      GuiListener guiListener = new GuiListener(about,quit,reset);
      about.addActionListener(guiListener);
      quit.addActionListener(guiListener);
      
      menu.add(about);
      menu.add(quit);  
      
      menuBar.add(menu);
      setJMenuBar(menuBar);    
            
      //setting up the control panel
      JPanel controlPanel = new JPanel();
      JPanel playerOne = new JPanel();
      JPanel playerTwo = new JPanel();
      JPanel resetButton = new JPanel();
      controlPanel.setBackground(Color.lightGray);
      
      //configuring sub-panel for Player 01, Hound
      
      player1 = new JRadioButton("HOUND", true);
      ImageIcon houndPic = new ImageIcon("dog.png");
      JLabel houndLabel = new JLabel( houndPic );
      JLabel houndplayer = new JLabel("Player 1");
      playerOne.add(houndLabel);
      playerOne.add(player1);
     
      playerOne.setLayout(new BorderLayout());
      playerOne.add(houndLabel, BorderLayout.WEST);
      playerOne.add(player1, BorderLayout.EAST);
      playerOne.add(houndplayer, BorderLayout.SOUTH);
      playerOne.setBorder(BorderFactory.createLineBorder(Color.black));  
      
      //configuring sub-panel for Player 02, Foxes          
      
      player2 = new JRadioButton("FOXES", false);
      ImageIcon foxPic = new ImageIcon("foxBomb.png");
      JLabel foxLabel = new JLabel( foxPic );
      JLabel foxplayer = new JLabel("Player 2");
      playerTwo.add(foxLabel);
      playerTwo.add(player2);
      
      playerTwo.setLayout(new BorderLayout());
      playerTwo.add(foxLabel, BorderLayout.WEST);
      playerTwo.add(player2, BorderLayout.EAST);
      playerTwo.add(foxplayer, BorderLayout.SOUTH);
      playerTwo.setBorder(BorderFactory.createLineBorder(Color.black));
      
      //configuring JButton to Reset Game 
      
      JButton reset = new JButton("Reset Game");
      reset.setBorder(BorderFactory.createRaisedBevelBorder());
      resetButton.add(reset);
      reset.addActionListener(guiListener);
      
    
      //configuring layout for Control Panel 
      
      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
      controlPanel.add(playerOne);
      controlPanel.add(playerTwo);
      controlPanel.add(resetButton);
      
      controlPanel.setSize(new Dimension(200, 800));
      //controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
      
      TitledBorder panelBorder = BorderFactory.createTitledBorder("Control Panel");
      panelBorder.setTitleJustification(TitledBorder.CENTER);
      panelBorder.setTitleColor(Color.BLACK);
      controlPanel.setBorder(panelBorder);
      
      add(controlPanel, BorderLayout.EAST);
      
      createGrid();
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
      //read in the first player's name from user as a string 
      name1 = JOptionPane.showInputDialog ( "Hound: Enter Player 1's Name" ); 
      houndplayer.setText(name1);
      houndplayer.setBounds(50,50, 100,30);
      houndplayer.setHorizontalAlignment(SwingConstants.CENTER);

      //read in the second player's name from user as a string 
      name2 = JOptionPane.showInputDialog ( "Foxes: Enter Player 2's Name" ); 
      foxplayer.setText(name2);
      foxplayer.setBounds(50,100, 100,30); 
      foxplayer.setHorizontalAlignment(SwingConstants.CENTER);
      
      
   }
   
   public void createGrid()         
   {         
         // Set up GridLayout 8 x 8 for JPanel
      
          gridPanel = new JPanel(new GridLayout(8,8)); 
           
         //Set up 8 x 8 JButton buttons
          button = new JButton[8][8];
          TitledBorder gridBorder = BorderFactory.createTitledBorder("Game Board");
          gridBorder.setTitleJustification(TitledBorder.CENTER);
          
          gridBorder.setTitleColor(Color.BLACK);
          gridPanel.setBorder(gridBorder);
          add(gridPanel, BorderLayout.CENTER); 
          JPanel imagePanel = new JPanel();
   
         //Set up game with GUI and pictures
         for(int i = 0; i < 8; i++) 
         {
            int k = i;
            for(int j = 0; j < 8; j++) 
            {
               button[i][j] = new JButton();
               button[i][j].setBorderPainted(false);
               button[i][j].setOpaque(true);
               board[i][j] = 0;
               k++;
               if(k % 2 == 0)
                  button[i][j].setBackground(Color.ORANGE);
               else
                  button[i][j].setBackground(Color.WHITE);
               button[i][j].addActionListener(new MoveActionListener(i, j, player));
               gridPanel.add(button[i][j]);
            }
         }      

      
         // Upload icons on the board.
         startIcon();
   }
   
   //Reset the Game
   public void resetBoard()
   {
      createGrid();
   }
   
   // This is restart new game and set all 2d array board = 0. 
   // Clear all icons from the buttons on the board. 
   public static void startBoard()
   {
      for(int x = 0; x < 8; x++)
      {
         for(int y = 0; y < 8; y++)
         {
            board[x][y] = 0;
            button[x][y].setIcon(null);
         }
      }
   }
   
   // Set foxes and hound icons on the board.
   public static void startIcon()
   {
      button[0][1].setIcon(new ImageIcon("foxBomb.png"));
      button[0][3].setIcon(new ImageIcon("foxBomb.png"));
      button[0][5].setIcon(new ImageIcon("foxBomb.png"));
      button[0][7].setIcon(new ImageIcon("foxBomb.png"));
      
      button[7][4].setIcon(new ImageIcon("dog.png"));
   
      board[0][1] = fox; //int fox = 2;
      board[0][3] = fox; //int fox = 2;
      board[0][5] = fox; //int fox = 2;
      board[0][7] = fox; //int fox = 2;
      
      board[7][4] = hound; //int hound = 1;
      player = 1;
   }
   
   // Rule - Check to see if fox or hound is a player. 
   public static boolean validateUser(int _currentX, int _currentY, int _player)
   {
      int x = _currentX;
      int y = _currentY;
      player = _player;
      boolean validate = false;
      
      switch(player)
      {
         case 1:
            if(board[x][y] == hound)
            {
               validate = true;
            }
            break;
         case 2: 
            if(board[x][y] == fox)
            {
               validate = true;
            }
            break;
      }
      return validate;
   }
   
   // Take turn from player to player. 
   public static int changePlayer(int _user)
   {
      player = _user;
      
      if(player == 1)
      {player = 2;}
      else
      {player = 1;}
      return player;
   }
   
   // Rule - Check to see if user clicks fox/hound and clicks one next move on empty square. 
   public static boolean moveValidate(int _currentX, int _currentY, int _moveX, int _moveY, int _user)
   {
      int currX = _currentX;
      int currY = _currentY;
      int nextX = _moveX;
      int nextY = _moveY;
      player = _user;
      
      boolean validate = false;
      
      switch(player)
      {
         case 1: 
            if(board[currX][currY] == hound){
               if(board[nextX][nextY] != hound){
                  validate = true;
               }
            }
            break;
         case 2:
            if(board[currX][currY] == fox){
               if(board[nextX][nextY] != fox){
                  validate = true;
               }
            }
            break; 
      }
      return validate;
   }
   
   // After confirm the next move is valid, update the board. 
   public static boolean movePosition(int _currentX, int _currentY, int _moveX, int _moveY, int _user)
   {
      boolean validateMove = false;
      int currX = _currentX;
      int currY = _currentY;
      int moveX = _moveX;
      int moveY = _moveY;
      player = _user;
      
      switch(player)
      {
         case 1: // Player hound
         // hound's move can't be on the same row
            if((currX != moveX) && (currY != moveY)) 
            {
               // Allow hound to move down and up
               if(((currX + 1) == moveX) || ((currX - 1) == moveX)) 
               {
                  // Allow hound to left and right
                  if((currY == (moveY + 1)) || (currY == (moveY - 1)))
                  {
                     // User's second click for the next move is confirmed
                     if(board[moveX][moveY] == 0)
                     {
                        // Update icon on the next move and change position's number
                        board[moveX][moveY] = hound;
                        board[currX][currY] = 0;
                        button[currX][currY].setIcon(null);
                        button[moveX][moveY].setIcon(new ImageIcon("dog.png"));
                        validateMove = true;
                     }
                  }
               }
            }
            break;
         case 2: // Player fox
         // fox's move can't be on the same row
            if((currX != moveX) && (currY != moveY)) 
            {
               // Allow fox to move down, not move up
               if((currX + 1) == moveX)
               {
                  // Allow fox to left and right
                  if(((currY + 1) == moveY) || ((currY - 1) == moveY))
                  {
                     // User's second click for the next move is confirmed
                     if(board[moveX][moveY] == 0)
                     {
                        // Update icon on the next move and change position's number
                        board[moveX][moveY] = fox;
                        board[currX][currY] = 0;
                        button[currX][currY].setIcon(null);
                        button[moveX][moveY].setIcon(new ImageIcon("foxBomb.png"));
                        validateMove = true;
                     }
                  }
               }
            }
            break;
      }
      return validateMove;
   }
   
   // Rule - Check to see if either hound/fox wins
   public static boolean winPosition(int _moveX, int _moveY, int _user)
   {
      boolean validateWin = false;
      int moveX = _moveX;
      int moveY = _moveY;
      int markX = 0;
      int markY = 0;
      player = _user;
      
      switch(player)
      {
         case 1: // Hound player wins when hound reaches to the first row. 
            if(moveX == 0)
            {validateWin = true;}
            break;
         case 2:
            // Fox player wins when hound is surrounded by foxes
            for(int x = 0; x < 8; x++)
            {
               for(int y = 0; y < 8; y++)
               {
                  if(board[x][y] == 1)
                  {
                     // Find out where hound is at
                     markX = x;
                     markY = y;
                  }
               }
            }
            // Rule - the hound is on the west side of the board
            if(markY == 0)
            {
               if((board[markX - 1][markY + 1] == fox) && (board[markX + 1][markY + 1] == fox))
               {
                  validateWin = true;
               }
            } 
            // Rule - the hound is on the east side of the board.
            else if(markY == 7)
            {
               if((board[markX + 1][markY - 1] == fox) && (board[markX - 1][markY - 1] == fox))
               {
                  validateWin = true;
               }
            }
            else 
            {
               // Rule - the hound is on the center of the board. 
               if((board[markX + 1][markY + 1] == fox) && (board[markX - 1][markY + 1] == fox))
               {
                  if((board[markX - 1][markY - 1] == fox) && (board[markX + 1][markY - 1] == fox))
                  {
                     validateWin = true;
                  }
               }
            }
            break;
      }
      
      return validateWin;
   }
   
   // Main method.
   public static void main(String [] args) 
   {
      new FoxHoundsGUI();
   }
}
