import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogonGui extends JPanel
{
	private int width, height;
	private JTextField userName, passWord;

	public LogonGui(int width, int height)
	{
		this.width = width;
		this.height = height;

		this.setSize(width,height);
		this.setLocation(0,0);

		setupInputFields();
	}

	private void setupInputFields()
	{
		this.setLayout(new GridLayout(3,2));

		JLabel nameLabel = new JLabel("Username: ");
		JLabel passLabel = new JLabel("Password: ");

		userName = new JTextField("");
		passWord = new JTextField("");

		nameLabel.setSize(width/2,40);
		passLabel.setSize(width/2,40);
		userName.setSize(width/2,40);
		passWord.setSize(width/2,40);

		this.add(nameLabel);
		this.add(userName);
		this.add(passLabel);
		this.add(passWord);

		JButton logonButton = new JButton("Logon");
		logonButton.addActionListener(new ButtonListener());
		this.add(logonButton);
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String text = ((JButton)e.getSource()).getText();

			if(text.equals("Logon"))
			{
				System.out.println("ping");
			}
		}
	}
}