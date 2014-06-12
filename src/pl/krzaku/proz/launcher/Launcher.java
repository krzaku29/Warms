package pl.krzaku.proz.launcher;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import pl.krzaku.proz.Game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Launcher implements ActionListener
{
	private JList<Resolution> list;
	private JFrame frmWarmsLauncher;
	private JTextField seedTextField;
	private JTextField towerNumberTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Launcher window = new Launcher();
					window.frmWarmsLauncher.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launcher()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmWarmsLauncher = new JFrame();
		frmWarmsLauncher.setTitle("Warms Launcher");
		frmWarmsLauncher.setBounds(100, 100, 236, 260);
		frmWarmsLauncher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWarmsLauncher.getContentPane().setLayout(null);
		
		JButton btnCredits = new JButton("Credits");
		btnCredits.setBounds(10, 168, 107, 42);
		frmWarmsLauncher.getContentPane().add(btnCredits);
		btnCredits.setActionCommand("credits");
		btnCredits.addActionListener(this);
		
		JButton btnStart = new JButton("START");
		btnStart.setBounds(10, 115, 107, 42);
		frmWarmsLauncher.getContentPane().add(btnStart);
		btnStart.setActionCommand("start");
		btnStart.addActionListener(this);
		
		DefaultListModel<Resolution> listModel = new DefaultListModel<Resolution>();
		listModel.addElement(new Resolution(960,540));
		listModel.addElement(new Resolution(1024,576));
		listModel.addElement(new Resolution(1280,720));
		listModel.addElement(new Resolution(1366,768));
		listModel.addElement(new Resolution(1400,900));
		list = new JList<Resolution>(listModel);
		list.setBounds(127, 75, 86, 133);
		list.setSelectedIndex(3);
		frmWarmsLauncher.getContentPane().add(list);
		
		seedTextField = new JTextField("1234");
		seedTextField.setBounds(127, 9, 86, 20);
		frmWarmsLauncher.getContentPane().add(seedTextField);
		seedTextField.setColumns(10);
		
		towerNumberTextField = new JTextField("4");
		towerNumberTextField.setBounds(127, 37, 86, 20);
		frmWarmsLauncher.getContentPane().add(towerNumberTextField);
		towerNumberTextField.setColumns(10);
		
		JLabel lblMapSeed = new JLabel("Map seed");
		lblMapSeed.setBounds(10, 12, 63, 14);
		frmWarmsLauncher.getContentPane().add(lblMapSeed);
		
		JLabel lblNumberOfTowers = new JLabel("Number of towers");
		lblNumberOfTowers.setBounds(10, 40, 107, 14);
		frmWarmsLauncher.getContentPane().add(lblNumberOfTowers);
		
		JLabel lblScreenResolution = new JLabel("Screen resolution");
		lblScreenResolution.setBounds(10, 76, 113, 14);
		frmWarmsLauncher.getContentPane().add(lblScreenResolution);
	}

	/**
	 * Listener for frame component events
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("credits"))
		{
			 JOptionPane.showMessageDialog(null, "Programming by: Patryk Majkrzak\nGraphics by: Monika Domaszewska & Patryk Majrzak\nMusic by: Kevin McLeod www.incompetech.com");
		}
		else if(event.getActionCommand().equals("start"))
		{
			int resX = list.getSelectedValue().getWidth();
			int resY = list.getSelectedValue().getHeight();
			int seed;
			int num;
			try
			{
				seed = Integer.parseInt(seedTextField.getText());
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "B³êdnie wprowadzony seed mapy!");
				return;
			}
			
			try
			{
				num = Integer.parseInt(towerNumberTextField.getText());
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "B³êdnie wprowadzona iloœæ wie¿!");
				return;
			}
			
			if(num < 1 || num > 8) 
			{
				JOptionPane.showMessageDialog(null, "Iloœæ wie¿ musi byæ w przedziale (1-8)!");
				return;
			}
			
			Game a = new Game("Warms", resX, resY, seed, num);
			a.start();
		}
	}
	
	
}
