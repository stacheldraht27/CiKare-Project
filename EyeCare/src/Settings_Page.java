import java.awt.AWTException;
import java.awt.Composite;
import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.Timer;

import java.awt.Button;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import com.eyecare.controller.Time;
import com.eyecare.controller.SystemPreferences;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;


public class Settings_Page extends JFrame{
	
	/*key start here*/
	private final String ENABLE_LONG_BREAK= "enableLongBreak";
	private final String NOTIFY_LONG_BREAK="notifyLongBreak";
	private final String ENABLE_SHORT_BREAK="enableShortBreak";
	public final String ENABLE_SOUND="enableSound";
	public final String ENABLE_STRICT_MODE="enableStrictMode";
	public final String HAVE_WINDOW_NEARBY="haveWindowNearby";
	
	private final String CB_LONG_BREAK = "cbLongBreak";
	public final String CB_LONG_BREAK_DURATION = "cbLongBreakDuration";
	private final String CB_NOTIFY_LONG_BREAK = "cbNotifyLongBreak";
	private final String CB_SHORT_BREAK="cbShortBreak";
	/*key ends here*/
	
	/*value start here*/
	private final Boolean ENABLE_LONG_BREAK_VALUE = false;
	private final Boolean NOTIFY_LONG_BREAK_VALUE = false;
	private final Boolean ENABLE_SHORT_BREAK_VALUE = false;
	public final Boolean ENABLE_SOUND_VALUE = false;
	public final Boolean ENABLE_STRICT_MODE_VALUE = false;
	public final Boolean HAVE_WINDOW_NEARBY_VALUE = false;
	
	private final int CB_LONG_BREAK_VALUE = 0;
	public final int CB_LONG_BREAK_DURATION_VALUE = 0;
	private final int CB_NOTIFY_LONG_BREAK_VALUE = 0;
	private final int CB_SHORT_BREAK_VALUE =0;
	/*value ends here*/
	
	String[] comboBoxLongBreakContent = {"30 minutes","40 minutes","50 minutes","60 minutes","90 minutes"};
	String[] comboBoxTimeOffContent = {"2 minutes", "3 minutes","4 minutes","5 minutes","7 minutes"};
	String[] comboBoxNotifyContent = {"30 second","1 minutes","2 minutes","3 minutes","5 minutes"};
	String[] comboBoxShortBreakContent = {"3 minutes","5 minutes","8 minutes","10 minutes","15 minutes"};
	
	SystemPreferences sPreference = new SystemPreferences();
	static Settings_Page settings_Page = new Settings_Page();
	
	/*create 3 different instances*/
	Timer timerShortBreak, timerLongBreak, timerNotifyLongBreak;
	Time systemTime = new Time();
	TraySystem traySystem = new TraySystem();
	
	JCheckBox chckbxTakeALong, chckbxNotifiyLongBreak,chckbxTakeAShort,chckbxStrictMode,chckbxEnableSounds,chckbxIHaveA;
	JComboBox cbLongBreak, cbLongBreakDuration, cbNotifyLongBreak,cbShortBreak;
	
	int xMouse;
	int yMouse;
	private JLabel lblBackground;
	private JLabel lblDragJFrame;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	/*nanti di hapus yaa*/
	int loopShortBreak, loopLongBreak, loopLongBreakNotification;
	
	boolean isThisClassAlreadyRun =false;
	boolean isTimerAlreadyRun = false;
	boolean isTimerLongBreakRun = false;
	boolean isTimerShortBreakRun = false;
	boolean isTimerNotifyLongBreakRun =false;
	
	
	
	public Settings_Page() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Settings_Page.class.getResource("/images/icons/Cika_ChibiIcon.png")));
		setResizable(false);
		setTitle("CiKare");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		/*this.setLocationRelativeTo(null);*/
		getContentPane().setLayout(null);
		setUndecorated(true);
		
		/*Jcombobox start here*/
		cbLongBreak = new JComboBox(comboBoxLongBreakContent);
		cbLongBreak.setFont(new Font("Serif", Font.PLAIN, 13));
		cbLongBreak.setBounds(211, 59, 96, 20);
		getContentPane().add(cbLongBreak);
		
		cbLongBreakDuration = new JComboBox(comboBoxTimeOffContent);
		cbLongBreakDuration.setFont(new Font("Serif", Font.PLAIN, 13));
		cbLongBreakDuration.setBounds(351, 59, 96, 20);
		getContentPane().add(cbLongBreakDuration);
		
		cbNotifyLongBreak = new JComboBox(comboBoxNotifyContent);
		cbNotifyLongBreak.setFont(new Font("Serif", Font.PLAIN, 13));
		cbNotifyLongBreak.setBounds(274, 96, 96, 20);
		getContentPane().add(cbNotifyLongBreak);
		
		cbShortBreak = new JComboBox(comboBoxShortBreakContent);
		cbShortBreak.setFont(new Font("Serif", Font.PLAIN, 13));
		cbShortBreak.setBounds(221, 132, 96, 20);
		getContentPane().add(cbShortBreak);
		/*Jcombobox ends hre*/
		
		
		/*button starts here*/
		final JButton btnTryShortBreak = new JButton("Try short break");
		btnTryShortBreak.setFont(new Font("Serif", Font.PLAIN, 13));
		btnTryShortBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification_Short_Break notif_short = new Notification_Short_Break();
				notif_short.runNotification();
			}
		});
		btnTryShortBreak.setBounds(10, 295, 138, 23);
		getContentPane().add(btnTryShortBreak);
		
		final JButton btnTryLongBreak = new JButton("Try long break");
		btnTryLongBreak.setFont(new Font("Serif", Font.PLAIN, 13));
		btnTryLongBreak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notification_Long_Break notif_long = new Notification_Long_Break();
				notif_long.setDuration(systemTime.getLongBreakDuration());
				notif_long.runNotification();
			}
		});
		btnTryLongBreak.setBounds(169, 295, 138, 23);
		getContentPane().add(btnTryLongBreak);
		
		final JButton btnSaveAndClose = new JButton("Save and Close");
		btnSaveAndClose.setFont(new Font("Serif", Font.PLAIN, 13));
		btnSaveAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				stopAllTimer();
				settings_Page.timeSettings();
				saveCurrentSettings();
				settings_Page.dispose();
			}
		});
		btnSaveAndClose.setBounds(98, 331, 138, 23);
		getContentPane().add(btnSaveAndClose);
		/*button ends here*/
		
		
		/*JCheckBox starts here*/
		chckbxTakeALong = new JCheckBox("Take a long break every");
		chckbxTakeALong.setFont(new Font("Serif", Font.PLAIN, 13));
		chckbxTakeALong.setOpaque(false);
		chckbxTakeALong.setBounds(44, 58, 161, 23);
		getContentPane().add(chckbxTakeALong);
		
		chckbxNotifiyLongBreak = new JCheckBox("Notifiy long break coming before ");
		chckbxNotifiyLongBreak.setFont(new Font("Serif", Font.PLAIN, 13));
		chckbxNotifiyLongBreak.setOpaque(false);
		chckbxNotifiyLongBreak.setBounds(44, 95, 213, 23);
		getContentPane().add(chckbxNotifiyLongBreak);
		
		chckbxTakeAShort = new JCheckBox("Take a short break every");
		chckbxTakeAShort.setFont(new Font("Serif", Font.PLAIN, 13));
		chckbxTakeAShort.setOpaque(false);
		chckbxTakeAShort.setBounds(44, 131, 171, 23);
		getContentPane().add(chckbxTakeAShort);
		
		chckbxStrictMode = new JCheckBox("Enable strict mode");
		chckbxStrictMode.setFont(new Font("Serif", Font.PLAIN, 13));
		chckbxStrictMode.setOpaque(false);
		chckbxStrictMode.setBounds(44, 204, 147, 23);
		getContentPane().add(chckbxStrictMode);
		
		chckbxEnableSounds = new JCheckBox("Enable sounds");
		chckbxEnableSounds.setFont(new Font("Serif", Font.PLAIN, 13));
		chckbxEnableSounds.setOpaque(false);
		chckbxEnableSounds.setBounds(44, 168, 147, 23);
		getContentPane().add(chckbxEnableSounds);
		
		chckbxIHaveA = new JCheckBox("I have a window nearby");
		chckbxIHaveA.setFont(new Font("Serif", Font.PLAIN, 13));
		chckbxIHaveA.setOpaque(false);
		chckbxIHaveA.setBounds(44, 245, 161, 23);
		getContentPane().add(chckbxIHaveA);
		/*Jcheckbox ends here*/
		
		
		/*label start here*/
		JLabel lblButtonExit = new JLabel("");
		lblButtonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settings_Page.dispose();
			}
		});
		lblButtonExit.setBounds(455, 6, 19, 19);
		lblButtonExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(lblButtonExit);
		
		JLabel lblButtonMinimize = new JLabel("");
		lblButtonMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Settings_Page.ICONIFIED);
			}
		});
		lblButtonMinimize.setBounds(425, 6, 19, 19);
		lblButtonMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(lblButtonMinimize);
		
		lblDragJFrame = new JLabel("");
		lblDragJFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		lblDragJFrame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				
				setLocation(x - xMouse, y - yMouse);
			}
		});
		lblDragJFrame.setBounds(0, 0, 481, 25);
		getContentPane().add(lblDragJFrame);
		
		JLabel lblFor = new JLabel("for");
		lblFor.setFont(new Font("Serif", Font.PLAIN, 13));
		lblFor.setBounds(321, 62, 20, 14);
		getContentPane().add(lblFor);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/alarm.png")));
		lblNewLabel.setBounds(10, 51, 28, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/chat.png")));
		label.setBounds(9, 88, 28, 36);
		getContentPane().add(label);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/alarm.png")));
		lblNewLabel_1.setBounds(10, 124, 28, 36);
		getContentPane().add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/sound.png")));
		label_1.setBounds(10, 165, 28, 36);
		getContentPane().add(label_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/eye_close.png")));
		lblNewLabel_2.setBounds(6, 202, 38, 30);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/window.png")));
		lblNewLabel_3.setBounds(9, 238, 38, 35);
		getContentPane().add(lblNewLabel_3);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(Settings_Page.class.getResource("/images/icons/Background_settings_page.png")));
		lblBackground.setBounds(0, 0, 481, 389);
		getContentPane().add(lblBackground);
		/*label ends here*/		
	}

	
	/*ini method untuk memanggil activity short break*/
	private void callShortBreak(){	
		System.out.println("Start SHORT BREAK...");
		timerShortBreak = new Timer(systemTime.getShortBreakTime(), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            		Notification_Short_Break notif_short = new Notification_Short_Break();
    				notif_short.runNotification(); 
    				
                	System.out.println("SHORT BREAK-"+loopShortBreak+" : "+ systemTime.getShortBreakTime() +" milisecond");
                	loopShortBreak++;
         	
            }
        });
        timerShortBreak.setRepeats(true);
        timerShortBreak.start();
	}
	
	
	/*ini method untuk memanggil activity long break*/
	private void callLongBreak(){	
		System.out.println("Start LONG BREAK...");	
		timerLongBreak = new Timer(systemTime.getLongBreakTime(), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            		Notification_Long_Break notif_long = new Notification_Long_Break();
            	  	notif_long.setDuration(systemTime.getLongBreakDuration());
    				notif_long.runNotification();
            	
            		System.out.println("LONG BREAK-"+loopLongBreak+" : "+ systemTime.getLongBreakTime() +" milisecond"+
    				" with duration-"+systemTime.getLongBreakDuration());
                	loopLongBreak++;
            	     	
            }
        });
        timerLongBreak.setRepeats(true);
        timerLongBreak.start();
	}
		
	/*ini method untuk memanggil activity long break notify*/
	private void callLongBreakNotify(){
		System.out.println("Start Notification LONG BREAK...");
		
		timerNotifyLongBreak = new Timer(systemTime.getLongBreakTime()-systemTime.getLongBreakNotifyTime(), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            		Notification_Before_Long_Break notif_before_long = new Notification_Before_Long_Break();
    				notif_before_long.runNotification();
    				
                	System.out.println("NOTIFICATION LONG BREAK-"+loopLongBreakNotification+" : "+ (systemTime.getLongBreakTime()-systemTime.getLongBreakNotifyTime()) +" milisecond");
                	loopLongBreakNotification++;
            	
            }
        });
        timerNotifyLongBreak.setRepeats(true);
        timerNotifyLongBreak.start();
	}
	
	/*ini untuk me run seluruh timer*/
	public void timeSettings(){
		
		isTimerAlreadyRun = true;
		
		if(chckbxTakeALong.isSelected()){
			isTimerLongBreakRun=true;
			systemTime.setLongBreakTime(cbLongBreak.getSelectedIndex());
			systemTime.setLongBreakDuration(cbLongBreakDuration.getSelectedIndex());
			callLongBreak();
		} 
		
		if(chckbxNotifiyLongBreak.isSelected() && chckbxTakeALong.isSelected()){
			isTimerNotifyLongBreakRun=true;
			systemTime.setLongBreakNotifyTime(cbNotifyLongBreak.getSelectedIndex());
			callLongBreakNotify();
		}
		if(chckbxTakeAShort.isSelected()){
			isTimerShortBreakRun=true;
			systemTime.setShortBreakTime(cbShortBreak.getSelectedIndex());
			callShortBreak();	
		}
		if(chckbxEnableSounds.isSelected()){
			/*handled by each activity*/
		}
		if(chckbxStrictMode.isSelected()){
			
		}
		if(chckbxIHaveA.isSelected()){
			
		}
	}
	
	/*untuk menghentikan semua timer yang berjalan*/
	public void stopAllTimer(){	
		System.out.println("Stop All timer...");
		if(isTimerShortBreakRun){
			timerShortBreak.stop();
		}
		if(isTimerLongBreakRun){
			timerLongBreak.stop();
		}
		if(isTimerNotifyLongBreakRun){
			timerNotifyLongBreak.stop();
		}
	}
	
	private void saveCurrentSettings(){
		sPreference.setBooleanSystemPreferences(ENABLE_LONG_BREAK, chckbxTakeALong.isSelected());
		sPreference.setBooleanSystemPreferences(NOTIFY_LONG_BREAK, chckbxNotifiyLongBreak.isSelected());
		sPreference.setBooleanSystemPreferences(ENABLE_SHORT_BREAK, chckbxTakeAShort.isSelected());
		sPreference.setBooleanSystemPreferences(ENABLE_STRICT_MODE, chckbxStrictMode.isSelected());
		sPreference.setBooleanSystemPreferences(ENABLE_SOUND, chckbxEnableSounds.isSelected());
		sPreference.setBooleanSystemPreferences(HAVE_WINDOW_NEARBY, chckbxIHaveA.isSelected());
		
		sPreference.setIntSystemPreferences(CB_LONG_BREAK, cbLongBreak.getSelectedIndex());
		sPreference.setIntSystemPreferences(CB_LONG_BREAK_DURATION, cbLongBreakDuration.getSelectedIndex());
		sPreference.setIntSystemPreferences(CB_NOTIFY_LONG_BREAK, cbNotifyLongBreak.getSelectedIndex());
		sPreference.setIntSystemPreferences(CB_SHORT_BREAK, cbShortBreak.getSelectedIndex());
		
		systemTime.setLongBreakTime(cbLongBreak.getSelectedIndex());
		systemTime.setLongBreakDuration(cbLongBreakDuration.getSelectedIndex());
		
		systemTime.setLongBreakNotifyTime(cbNotifyLongBreak.getSelectedIndex());
		
		systemTime.setShortBreakTime(cbShortBreak.getSelectedIndex());
	}
	
	private void loadCurrentSettings(){
		chckbxTakeALong.setSelected(sPreference.getBooleanSystemPreferences(ENABLE_LONG_BREAK, ENABLE_LONG_BREAK_VALUE));
		chckbxNotifiyLongBreak.setSelected(sPreference.getBooleanSystemPreferences(NOTIFY_LONG_BREAK, NOTIFY_LONG_BREAK_VALUE ));
		chckbxTakeAShort.setSelected(sPreference.getBooleanSystemPreferences(ENABLE_SHORT_BREAK, ENABLE_SHORT_BREAK_VALUE));
		chckbxStrictMode.setSelected(sPreference.getBooleanSystemPreferences(ENABLE_STRICT_MODE, ENABLE_STRICT_MODE_VALUE));
		chckbxEnableSounds.setSelected(sPreference.getBooleanSystemPreferences(ENABLE_SOUND, ENABLE_SOUND_VALUE));
		chckbxIHaveA.setSelected(sPreference.getBooleanSystemPreferences(HAVE_WINDOW_NEARBY, HAVE_WINDOW_NEARBY_VALUE));
		
		cbLongBreak.setSelectedIndex(sPreference.getIntSytemPreferences(CB_LONG_BREAK, CB_LONG_BREAK_VALUE));
		cbLongBreakDuration.setSelectedIndex(sPreference.getIntSytemPreferences(CB_LONG_BREAK_DURATION, CB_LONG_BREAK_DURATION_VALUE));
		cbNotifyLongBreak.setSelectedIndex(sPreference.getIntSytemPreferences(CB_NOTIFY_LONG_BREAK, CB_NOTIFY_LONG_BREAK_VALUE));
		cbShortBreak.setSelectedIndex(sPreference.getIntSytemPreferences(CB_SHORT_BREAK, CB_SHORT_BREAK_VALUE));
	}
	
	public void runSettingsPage(){
		settings_Page.setDefaultCloseOperation(JFrame.ICONIFIED);
		settings_Page.setSize(481,386);
		settings_Page.setVisible(true);
		settings_Page.loadCurrentSettings();
		if(isThisClassAlreadyRun!=true){
			settings_Page.timeSettings();
			isThisClassAlreadyRun=true;
		}	
	}
		
	public static void main(String [] args){
		settings_Page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settings_Page.setSize(481,386);
		settings_Page.setVisible(true);
		settings_Page.loadCurrentSettings();
		/*settings_Page.timeSettings();*/
	}
}

