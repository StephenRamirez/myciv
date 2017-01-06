package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color;

import javax.swing.JPanel;

public class MapGui extends JPanel
{
	private int width, heigth;

	public MapGui(int width, int heigth)
	{
		this.width = width;
		this.heigth = heigth;
		this.setSize(width,heigth);
		this.setLocation(0,0);
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;

		g2.setPaint(Color.BLACK);
		g2.fillRect(0,0,width,heigth);
	}
}