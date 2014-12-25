




import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Notification_Before_Long_Break extends JFrame {
	
	private final String MESSAGE_SPEND_FEW_MINUTES ="spend a few minutes taking a rest from the display";
	
	private final String MESSAGE_LONG_BREAK = "It's time for a long Break";
	

	private final String NORMAL ="C:\\Users\\azura\\workspace_intermediate_java\\EyeCare\\icons\\pictures\\Azura_Cika_Chibi.png";
	
	static Timer timer; 
	
   public Notification_Before_Long_Break() {
       super("ShapedWindow");
       getContentPane().setBackground(Color.WHITE);

       // It is best practice to set the window's shape in
       // the componentResized method.  Then, if the window
       // changes size, the shape will be correctly recalculated.
       addComponentListener(new ComponentAdapter() {
           // Give the window an elliptical shape.
           // If the window is resized, the shape is recalculated here.
           @Override
           public void componentResized(ComponentEvent e) {
               /*setShape(new Ellipse2D.Double(0,0,getWidth(),getHeight()));*/
           }
       });

       setUndecorated(true);
       setSize(400,200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       getContentPane().setLayout(null);
       
       
       handleIcon();
       handleTitle();
       handleMessage();
       
       setTimeToClose(5000);
       
   }

   public void handleIcon(){
       getContentPane().setLayout(null);
       JLabel lblPictureHere= new JLabel();
       lblPictureHere.setBounds(623, 307, 244, 225);
       lblPictureHere.setIcon(new ImageIcon(NORMAL));
       lblPictureHere.setForeground(Color.WHITE);
       getContentPane().add(lblPictureHere);
   }
   
   public void handleTitle(){
   	   /*buat nge handle Opening*/
       JLabel lblTimeForShort = new JLabel("Soon will be the long break");
       lblTimeForShort.setBackground(Color.BLACK);
       lblTimeForShort.setFont(new Font("Tahoma", Font.BOLD, 18));
       lblTimeForShort.setForeground(Color.BLACK);
       lblTimeForShort.setBounds(78, 67, 244, 26);
       getContentPane().add(lblTimeForShort);
   }
   
   public void handleMessage(){
       /*buat nge handle pesan*/
       JLabel lblNewLabel = new JLabel("Ready for long break?");
       lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
       lblNewLabel.setForeground(Color.BLACK);
       lblNewLabel.setBounds(118, 107, 332, 19);
       getContentPane().add(lblNewLabel);    
       
       JLabel lblCikare = new JLabel("");
       lblCikare.setIcon(new ImageIcon(Notification_Before_Long_Break.class.getResource("/images/icons/Title.png")));
       lblCikare.setFont(new Font("Times New Roman", Font.BOLD, 18));
       lblCikare.setBounds(20, 11, 77, 48);
       getContentPane().add(lblCikare);
       {
       	JLabel label = new JLabel("");
       	label.setIcon(new ImageIcon(Notification_Before_Long_Break.class.getResource("/images/icons/TitleBar.png")));
       	label.setBounds(-54, 10, 272, 70);
       	getContentPane().add(label);
       }
   }
   
   /*cuma ambil nilai ramdom aja kok sumpah*/
   public static int randInt(int min, int max) {

       // NOTE: Usually this should be a field rather than a method
       // variable so that it is not re-seeded every call.
       Random rand = new Random();

       // nextInt is normally exclusive of the top value,
       // so add 1 to make it inclusive
       int randomNum = rand.nextInt((max - min) + 1) + min;

       return randomNum;
   }
   
   private void setTimeToClose(int milisecond){
   	 timer = new Timer(milisecond, new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               /*setVisible(false);*/
               dispose();
              //I want to place my code here so then this class will close, and then the other class will open
           }
       });
       timer.setRepeats(false);
       timer.start();
   }
   
   public void runNotification(){
   	 // Determine what the GraphicsDevice can support.
       GraphicsEnvironment ge = 
           GraphicsEnvironment.getLocalGraphicsEnvironment();
       GraphicsDevice gd = ge.getDefaultScreenDevice();
       final boolean isTranslucencySupported = 
           gd.isWindowTranslucencySupported(TRANSLUCENT);

       //If shaped windows aren't supported, exit.
       if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT)) {
           System.err.println("Shaped windows are not supported");
           System.exit(0);
       }

       //If translucent windows aren't supported, 
       //create an opaque window.
       if (!isTranslucencySupported) {
           System.out.println(
               "Translucency is not supported, creating an opaque window");
       }

       // Create the GUI on the event-dispatching thread
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               Notification_Before_Long_Break sw = new Notification_Before_Long_Break();
               
               // Set the window to 70% translucency, if supported.
               if (isTranslucencySupported) {
                   sw.setOpacity(0.7f);
               }

               // Display the window.
               sw.setVisible(true);
           }
       });
   }
   
  /*  */
}

