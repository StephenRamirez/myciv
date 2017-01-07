package client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.MapGui;

public class MyCivGui extends JFrame
{
	private JPanel view;
	private int width, height;

	public MyCivGui()
	{
		width = 1000;
		height = 1000;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width,height);
		this.setLocation(100,100);

		setView(new MapGui(width,height));
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