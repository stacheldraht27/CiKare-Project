

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.eyecare.controller.SystemPreferences;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Notification_Short_Break extends JFrame {
	
	private final String MOVE_LEFT_AND_RIGHT ="/images/GIF/move_left_and_right.gif";
	private final String MOVE_UP_AND_DOWN ="/images/GIF/move_up_and_down.gif";
	private final String CLOSE_EYES ="/images/GIF/close_eyes_tight.gif";
	private final String ROLL_EYES="/images/GIF/rolling_eyes.gif";
	private final String BLINK="/images/GIF/blink_eyes.gif";
	
	private final String MESSAGE_LEFT_AND_RIGHT = "Move your eye left & right";
	private final String MESSAGE_UP_AND_DOWN = "Move eyes up & down";
	private final String MESSAGE_CLOSE_TIGHTLY = "Close your eyes tightly";
	private final String MESSAGE_ROLL_EYES = "Roll your eyes";
	private final String MESSAGE_BLINK_EYES = "Blink your eyes";
	
	private final String MESSAGE_SHORT_BREAK = "Time for short Break";
	
	private final String VOICE_LEFT_AND_RIGHT="/sound/time_for_short_break,_move_your_eye_left_and_right.wav";
	private final String VOICE_UP_AND_DOWN="/sound/time_for_short_break,_move_your_eyes_up_and_down.wav";
	private final String VOICE_CLOSE_TIGHTLY = "/sound/time_for_short_break,__close_your_eyes_tightly.wav";
	private final String VOICE_ROLL_EYES="/sound/time_for_short_break,_roll_your_eyes.wav";
	private final String VOICE_BLINK_EYES="/sound/time_for_short_break,_blinking_your_eyes.wav";
		
	String[] MESSAGES = {MESSAGE_LEFT_AND_RIGHT, MESSAGE_UP_AND_DOWN, MESSAGE_CLOSE_TIGHTLY, MESSAGE_ROLL_EYES, MESSAGE_BLINK_EYES};
	String[] MOVES = {MOVE_LEFT_AND_RIGHT, MOVE_UP_AND_DOWN, CLOSE_EYES, ROLL_EYES, BLINK};
	String[] VOICES = {VOICE_LEFT_AND_RIGHT, VOICE_UP_AND_DOWN, VOICE_CLOSE_TIGHTLY, VOICE_ROLL_EYES, VOICE_BLINK_EYES};
	
	static Timer timer; 
	SystemPreferences sPreference = new SystemPreferences();
	Settings_Page settingsPage = new Settings_Page();
	int randomInt;
	
	
    public int getRandomInt() {
		return randomInt;
	}

	public void setRandomInt(int randomInt) {
		this.randomInt = randomInt;
	}

	public Notification_Short_Break() {
        super("ShapedWindow");
        getContentPane().setBackground(Color.WHITE);

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
                   
        setTimeToClose(10000);
    }

    private void handleIcon(int num){
        /*buat nge handle gambar*/
        JLabel lblPictureHere= new JLabel();
        lblPictureHere.setIcon(new ImageIcon(Notification_Short_Break.class.getResource(MOVES[num])));
        lblPictureHere.setForeground(Color.WHITE);
        lblPictureHere.setBounds(0, 60, 133, 95);
        getContentPane().add(lblPictureHere);
    }
    
    private void handleVoice(int num){
    	if(sPreference.getBooleanSystemPreferences(settingsPage.ENABLE_SOUND, settingsPage.ENABLE_SOUND_VALUE)){
    		Clip voice = new Clip();
            voice.play(VOICES[num]);
            System.out.println("index of the clip is-"+num+" and the value is "+VOICES[num] );
    	}   	
    }
    
    public void handleTitle(){
    	   /*buat nge handle Opening*/
        JLabel lblTimeForShort = new JLabel(MESSAGE_SHORT_BREAK);
        lblTimeForShort.setBackground(Color.BLACK);
        lblTimeForShort.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTimeForShort.setForeground(Color.BLACK);
        lblTimeForShort.setBounds(163, 75, 185, 26);
        getContentPane().add(lblTimeForShort);
    }
    
    public void handleMessage(int num){
        /*buat nge handle pesan*/
        JLabel lblNewLabel = new JLabel(MESSAGES[num]);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(172, 112, 176, 19);
        getContentPane().add(lblNewLabel);    
        
        JLabel lblCikare = new JLabel("");
        lblCikare.setIcon(new ImageIcon(Notification_Short_Break.class.getResource("/images/icons/Title.png")));
        lblCikare.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblCikare.setBounds(23, 10, 93, 39);
        getContentPane().add(lblCikare);
        {
        	JLabel lblNewLabel_1 = new JLabel("New label");
        	lblNewLabel_1.setIcon(new ImageIcon(Notification_Short_Break.class.getResource("/images/icons/TitleBar.png")));
        	lblNewLabel_1.setBounds(-56, 10, 221, 66);
        	getContentPane().add(lblNewLabel_1);
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
            	setRandomInt(randInt(0,4));
                Notification_Short_Break sw = new Notification_Short_Break();
                sw.handleIcon(getRandomInt());
                sw.handleTitle();
                sw.handleMessage(getRandomInt());
                sw.handleVoice(getRandomInt());
                // Set the window to 70% translucency, if supported.
                if (isTranslucencySupported) {
                    sw.setOpacity(1.f);
                }
                // Display the window.
                sw.setVisible(true);
            }
        });
    }
    
   /* public static void main(String[] args) {
    	Notification_Short_Break swd = new Notification_Short_Break();
    	swd.runNotification();	
    }*/
}
