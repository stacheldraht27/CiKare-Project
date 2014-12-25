/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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


/*
 * TrayIconDemo.java
 */

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class TraySystem {
	   final static PopupMenu popup = new PopupMenu();
       final static TrayIcon trayIcon = new TrayIcon(createImage("images/icons/Cika_ChibiIcon.png", "tray icon"));
       final static SystemTray tray = SystemTray.getSystemTray();
       static Settings_Page settingsPage = new Settings_Page();

       static MenuItem pauseItem;
    public static void main(String[] args) {
    	
    	settingsPage.runSettingsPage();
  
        /* Use an appropriate Look and Feel */
        try {
        	for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
        		if("Windows".equals(info.getName())){
        			javax.swing.UIManager.setLookAndFeel(info.getClassName());
        			break;
            	}
        	} 
        	/*UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");*/
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            /*UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());*/
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event-dispatching thread:
        //adding TrayIcon.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    
    private static void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
     
        
        // Create a popup menu components
        MenuItem settingsItem = new MenuItem("Settings");
        pauseItem = new MenuItem();
        pauseItem.setLabel("Pause");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");
        
        //Add components to popup menu
        popup.add(settingsItem);
        popup.add(pauseItem);
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(exitItem);
        
        trayIcon.setPopupMenu(popup);
        trayIcon.setToolTip("Version 1.0\nCiKare");
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
        
     
        settingsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                settingsPage.runSettingsPage();
            }
        });

        pauseItem.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(settingsPage.isTimerAlreadyRun){
					settingsPage.stopAllTimer();
					settingsPage.isTimerAlreadyRun=false;
					
					System.out.println("timer telah di pause");
					pauseItem.setLabel("Start");
				}else{
					settingsPage.timeSettings();					
					settingsPage.isTimerAlreadyRun=true;
					
					System.out.println("timer telah di start lagi");
					pauseItem.setLabel("Pause");
				}
			}
		});
        
        aboutItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(null,
	                    "CiKare created by Azura Akbar Faraday ©2014");			
			}
		});
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        
        
    }
    
    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = TraySystem.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}