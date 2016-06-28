
package snakeandladder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ImageManager
{
   static final int ROWS = 10;
   static final int COLS = 10;   
    
    static BufferedImage[] ImageSpitter(BufferedImage image)
    {
        BufferedImage images[] = new BufferedImage[100];
        
        final int mini_height = image.getHeight()/ROWS;
        final int mini_width = image.getWidth()/COLS;
        
        int count=0;
        for(int x=ROWS-1 ;x>=0; x--)
        {
           if(x%2==1)
           {
               for(int y=0; y<COLS; y++)
               {
                   images[count] = new BufferedImage(mini_width, mini_height, image.getType());
                   Graphics2D gr = images[count].createGraphics();
                   gr.drawImage(image, 0, 0,mini_width, mini_height, mini_width * y, mini_height * x, mini_width * y + mini_width, mini_height * x + mini_height, null);
                   gr.dispose();
                   count++;
               }
           }
           else
           {
               for(int y=COLS-1 ;y>=0; y--)
               {   
                   images[count] = new BufferedImage(mini_width, mini_height, image.getType());
                   Graphics2D gr = images[count].createGraphics();
                   gr.drawImage(image, 0, 0,mini_width, mini_height, mini_width * y, mini_height * x, mini_width * y + mini_width, mini_height * x + mini_height, null);
                   gr.dispose();
                   count++;
               }
           }
        }
        
        
        return images;
    }
    
    
    
    
}










public class SnakeAndLadder 
{
JFrame frame;
 JPanel  board_Pane;
 JLabel[] fragments_label;
 JLabel  dice_label;
 JButton player_A_button;
 JButton player_B_button;
 
 JLabel A_label;
 JLabel B_label;

    public SnakeAndLadder() {
        createFrame();
        addComponents();
    }
    
   
   
 
 
    void createFrame()
    {
        frame = new JFrame();
        frame.setLayout(null);
        
        frame.setPreferredSize(new Dimension(1000,600));
        frame.setTitle("Snakes And Ladders!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    void addComponents()
    {
       BufferedImage img=null,A_bufImg=null,B_bufImg=null;
        try {
        img = ImageIO.read(new File("Images/board.jpg"));
         A_bufImg = ImageIO.read(new File("Images/A.png"));
         B_bufImg = ImageIO.read(new File("Images/B.png"));
       
        }catch (IOException ex) {
        Logger.getLogger(SnakeAndLadder.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
         BufferedImage resizedImg = new BufferedImage(500, 500, BufferedImage.TRANSLUCENT);
    Graphics2D g2 = resizedImg.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(img, 0, 0, 500,500,null);
    g2.dispose();
    img = resizedImg;
       
       
        ImageIcon A_icon = new ImageIcon(A_bufImg);
        A_icon.setImage(A_icon.getImage().getScaledInstance(img.getWidth()/10,img.getHeight()/10, 0));
        A_label = new JLabel(A_icon);       
        
        ImageIcon B_icon = new ImageIcon(B_bufImg);
        B_icon.setImage(B_icon.getImage().getScaledInstance(img.getWidth()/10,img.getHeight()/10, 0));
        B_label = new JLabel(B_icon);

        board_Pane= new JPanel(new GridLayout(10, 10, 3, 3));
        fragments_label = new JLabel[100];
        BufferedImage[] images = ImageManager.ImageSpitter(img);
        for(int i=0;i<images.length;i++)
            fragments_label[i] = new JLabel(new ImageIcon(images[i]));
       
        int i;
        for(i=99;i>=90;i--)
            board_Pane.add(fragments_label[i]);
        for(i=80;i<=89;i++)
            board_Pane.add(fragments_label[i]);
         for(i=79;i>=70;i--)
            board_Pane.add(fragments_label[i]);
        for(i=60;i<=69;i++)
            board_Pane.add(fragments_label[i]);
         for(i=59;i>=50;i--)
            board_Pane.add(fragments_label[i]);
        for(i=40;i<=49;i++)
            board_Pane.add(fragments_label[i]);
         for(i=39;i>=30;i--)
            board_Pane.add(fragments_label[i]);
        for(i=20;i<=29;i++)
            board_Pane.add(fragments_label[i]);
         for(i=19;i>=10;i--)
            board_Pane.add(fragments_label[i]);
        for(i=0;i<=9;i++)
            board_Pane.add(fragments_label[i]);
        
        //---------------------------
        
        dice_label = new JLabel();
        dice_label.setSize(200, 200);
        ImageIcon dice_icon = new ImageIcon("Images/nil.png");
        dice_icon.setImage(dice_icon.getImage().getScaledInstance(200, 200, 1));
        dice_label.setIcon(dice_icon);
        dice_label.setBounds(750, 100, 200, 200);
        frame.add(dice_label);
        
        //---------------------------
        
        
        JFrame j = new JFrame();
        j.add(board_Pane);
        j.setLayout(new FlowLayout());
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        j.add(board_Pane);
        j.pack();
        j.setVisible(true);
        
        fragments_label[0].setIcon(A_icon);
        fragments_label[99].setIcon(B_icon);
       
       
        //-------------------------------------
        player_A_button = new JButton("Player A");
        player_B_button = new JButton("Player B");
        player_A_button.setBounds(500, 500, 100, 50);
        player_B_button.setBounds(620, 500, 100, 50);
        
        frame.add(player_A_button);
        frame.add(player_B_button);
        
        //---------------------------
        
     }
    
    
   
}





//**********************************************************************************************
//**********************************************************************************************

class Game
{
    public static void main(String args[])
    {
         javax.swing.SwingUtilities.invokeLater(new Runnable() 
                                                  {
                                                    public void run() 
                                                    {
                                                      new SnakeAndLadder();
                                                        
                
                                                    }
                                                  });
    }
    
    
}
    
    




