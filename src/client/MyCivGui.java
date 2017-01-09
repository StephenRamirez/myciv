package client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.MapGui;

public class MyCivGui extends JFrame
{
	private JPanel view;
	private int width, height;

	public static void main(String[] args)
	{
		MyCivGui gui = new MyCivGui();
		gui.setVisible(true);
	}

	public MyCivGui()
	{
		width = 1000;
		height = 1000;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width,height);
		this.setLocation(100,100);

		this.setLayout(null);
		setView(new LogonGui(width,height));
	}

	private void setView(JPanel nextView)
	{
		if(view != null)
			remove(view);

		view = nextView;
		add(view);
		repaint();
		validate();
	}
}