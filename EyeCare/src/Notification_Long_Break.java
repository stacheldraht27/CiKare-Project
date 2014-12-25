import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.eyecare.controller.SystemPreferences;
import com.eyecare.controller.Time;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Notification_Long_Break extends JFrame {
	
	private final String NORMAL ="/images/icons/Azura_Cika_Chibi.png";
	private final String WINDOW_AZURA_BRIGITA = "/images/icons/window_azura_brigita.png";

	private final String MESSAGE_SPEND_FEW_MINUTES ="spend a few minutes taking a rest from the display";
	private final String MESSAGE_LOOK_OUTSIDE = "look outside the window";
	
	private final String VOICE_LONG_BREAK="/sound/it's_time_for_a_long_break,_spend_a_few_minutes_taking_a_rest_from_the_display.wav";
	
	private final String MESSAGE_LONG_BREAK = "It's time for a long Break";
	
	static Timer timer; 
	Time systemTime;
	int duration;

	Settings_Page settingsPage= new Settings_Page();
	SystemPreferences sPreference =new SystemPreferences();
	
	public void setDuration(int duration) {
		 this.duration = duration;
	}
	
	public int getDuration() {
		 return duration;
	}
	 
    public Notification_Long_Break(){
        super("ShapedWindow");
        getContentPane().setBackground(Color.BLACK);

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
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setUndecorated(true);
        setSize(screenSize.width,screenSize.height);
       /* setBounds(0,0,screenSize.width,screenSize.height);*/
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        handleIcon();
        handleTitle();
        handleMessage();
            
        
        
        
        System.out.println("perulangan ke-"+1);
    }

    public void handleIcon(){
        getContentPane().setLayout(null);
        
         
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(Notification_Long_Break.class.getResource("/images/icons/Title.png")));
        label.setBounds(705, 292, 132, 47);
        getContentPane().add(label);
        
        
        if(sPreference.getBooleanSystemPreferences(settingsPage.HAVE_WINDOW_NEARBY, settingsPage.HAVE_WINDOW_NEARBY_VALUE)){
        	JLabel lblPictureWindows= new JLabel();
            lblPictureWindows.setBounds(653, 350, 217, 210);
            lblPictureWindows.setIcon(new ImageIcon(Notification_Long_Break.class.getResource(WINDOW_AZURA_BRIGITA)));
            lblPictureWindows.setForeground(Color.WHITE);
            getContentPane().add(lblPictureWindows);
            
            JLabel lblNormal = new JLabel(MESSAGE_LOOK_OUTSIDE);
            lblNormal.setBounds(998, 438, 1920, 19);
            lblNormal.setFont(new Font("Tahoma", Font.PLAIN, 15));
            lblNormal.setForeground(Color.WHITE);
            getContentPane().add(lblNormal);
            
    	}else{   	
    		JLabel lblPictureNormal = new JLabel();
        	lblPictureNormal.setIcon(new ImageIcon(Notification_Long_Break.class.getResource(NORMAL)));
        	lblPictureNormal.setBounds(629, 315, 265, 238);
        	getContentPane().add(lblPictureNormal);
        	
        	 JLabel lblLookOutside = new JLabel(MESSAGE_SPEND_FEW_MINUTES);
             lblLookOutside.setBounds(915, 438, 366, 23);
             lblLookOutside.setFont(new Font("Tahoma", Font.PLAIN, 15));
             lblLookOutside.setForeground(Color.WHITE);
             getContentPane().add(lblLookOutside);
    	}
    }
    
    public void handleTitle(){
    	   /*buat nge handle Opening*/
        JLabel lblTimeForShort = new JLabel(MESSAGE_LONG_BREAK);
        lblTimeForShort.setBounds(957, 282, 244, 175);
        lblTimeForShort.setBackground(Color.WHITE);
        lblTimeForShort.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTimeForShort.setForeground(Color.WHITE);
        getContentPane().add(lblTimeForShort);
    }
    
    public void handleMessage(){
        /*buat nge handle pesan*/
    	
    	int index = sPreference.getIntSytemPreferences(settingsPage.CB_LONG_BREAK_DURATION, settingsPage.CB_LONG_BREAK_DURATION_VALUE);
        JLabel lblTimeLeft = new JLabel("Rest for-"+ settingsPage.comboBoxTimeOffContent[index]);
        lblTimeLeft.setForeground(Color.WHITE);
        lblTimeLeft.setBounds(957, 537, 161, 14);
        getContentPane().add(lblTimeLeft);
    
        
        JButton btnSkip = new JButton("SKIP");
        btnSkip.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {	
	        		if(sPreference.getBooleanSystemPreferences(settingsPage.ENABLE_STRICT_MODE, settingsPage.ENABLE_STRICT_MODE_VALUE)){
	        			 JOptionPane.showMessageDialog(null,
	     	                    "You are in strict mode");			
	        	    }else{
	        	    	dispose();    	   
	        	    }
	        	}
	        });
	        btnSkip.setBounds(1130, 534, 89, 23);
	        getContentPane().add(btnSkip);
	       
    }
    
    private void handleVoice(){
    	if(sPreference.getBooleanSystemPreferences(settingsPage.ENABLE_SOUND, settingsPage.ENABLE_SOUND_VALUE)){
    		Clip voice = new Clip();
            voice.play(VOICE_LONG_BREAK);
    	}   	
    }
 
    public void runNotification(){
    	/*memasukan nilai duration ke this.duration*/
    	
    	
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
            	
            	handleVoice();
            	System.out.println("perulangan ke-"+2);
            	
                Notification_Long_Break sw = new Notification_Long_Break();
                sw.setTimeToClose(getDuration());
                System.out.println("durasi nya adalah-"+getDuration());
                
                // Set the window to 75% translucency, if supported.
                if (isTranslucencySupported) {
                    sw.setOpacity(0.75f);
                }

                // Display the window.
                sw.setVisible(true);
            }
        });
    }
    
   /* public void runNotification(){
    	memasukan nilai duration ke this.duration
    	
    	
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
                Notification_Long_Break sw = new Notification_Long_Break();

                // Set the window to 85% translucency, if supported.
                if (isTranslucencySupported) {
                    sw.setOpacity(0.85f);
                }

                // Display the window.
                sw.setVisible(true);
            }
        });
    }*/
    
    private void setTimeToClose(int milisecond){
   	 timer = new Timer(milisecond, new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               
               dispose();
              //I want to place my code here so then this class will close, and then the other class will open
           }
       });
       timer.setRepeats(false);
       timer.start();
   }
      
    public static void main(String[] args) {
    	Notification_Long_Break swd = new Notification_Long_Break();
    	swd.runNotification();	
    }
}
