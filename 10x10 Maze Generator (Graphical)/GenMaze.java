//Muhammad Ismail 1922235
//CSCI 1301 Section 2
//Semester 1 2020/2021

package assignments;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GenMaze extends JFrame
{
	   private JPanel panel;
	   public GenMaze()
	   {
		   setTitle("10x10 Maze Generator");
		   panel = new NewPanel();
		   panel.setBackground(Color.BLACK);
		   add(panel, BorderLayout.CENTER);
		   setSize(600, 600);
		   setLocationRelativeTo(null);
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setVisible(true);
	   }
	   
	   public static void main(String args[])
	   {
		   new GenMaze();
	   }
}

class NewPanel extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		int[][] Visited = new int[14][14];
		int[] Track = new int[100];
		int cordX = 51, cordY = 51;
		int i = 2, j = 2, k = 0;
		int index = k;
		
		for(int a = 0; a < 14; a++)
		{
			for(int b = 0; b < 14; b++)
			{
				Visited[a][b] = 1;
			}
		}
		
		for(int a = 2; a < 12; a++)
		{
			for(int b = 2; b < 12; b++)
			{
				Visited[a][b] = 0;
			}
		}
		
		for(int a = 0; a < 100; a++)
		{
			Track[a] = 0;
		}
		
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(51, 51, 49, 49);
		Visited[2][2] = 1;
		g.setColor(Color.YELLOW);
		
		for(int y = cordY - 1; y <= 500; y += 50)
		{
			for(int x = cordX - 1; x <= 500; x += 50)
			{
				g.drawRect(x, y, 50, 50);	
			}
		}
		
		for (int fill = 0; fill < 99; fill++)
		{		
			while(Visited[i][j-1] == 1 && Visited[i+1][j] == 1 && Visited[i][j+1] == 1 && Visited[i-1][j] == 1)
			{
				g.setColor(Color.DARK_GRAY);
				switch(Track[index])
				{
					case 0: break;
					case 1: g.fillRect(cordX, cordY, 49, 50); cordY += 50; ++j; --k; break; //Backtrack Down
					case 2: g.fillRect(cordX - 1, cordY, 50, 49); cordX -= 50; --i; --k; break; //Backtrack Left
					case 3: g.fillRect(cordX, cordY - 1, 49, 50); cordY -= 50; --j; --k; break; //Backtrack Up
					case 4: g.fillRect(cordX, cordY, 50, 49); cordX += 50; ++i; --k; break; //Backtrack Right
				}
				--index;
				
				switch(Track[index])//Paint current position
				{
					case 0: break;
					case 1: g.fillRect(cordX, cordY, 49, 50); break;
					case 2: g.fillRect(cordX - 1, cordY, 50, 49); break;
					case 3: g.fillRect(cordX, cordY - 1, 49, 50); break;
					case 4: g.fillRect(cordX, cordY, 50, 49); break;
				}				
			}

			int Direction = RandNum1to4();
			if(!(Visited[i][j-1] == 1 && Visited[i+1][j] == 1 && Visited[i][j+1] == 1 && Visited[i-1][j] == 1))
			{
				switch(Direction)
				{
					case 1: cordY -= 50; --j; break; //Forward Up
					case 2: cordX += 50; ++i; break; //Forward Right
					case 3: cordY += 50; ++j; break; //Forward Down
					case 4: cordX -= 50; --i; break; //Forward Left
				}
			}
			
			if(Visited[i][j] == 0)
			{
				g.setColor(Color.GRAY);
				switch(Direction)
				{
					case 1: g.fillRect(cordX, cordY, 49, 50); break; //Carve Up
					case 2: g.fillRect(cordX - 1, cordY, 50, 49); break; //Carve Right
					case 3: g.fillRect(cordX, cordY - 1, 49, 50); break; //Carve Down
					case 4: g.fillRect(cordX, cordY, 50, 49); break; //Carve Left
				}
				Visited[i][j] = 1;
				Track[k] = Direction;
				index = k;
				k++;
			}
			
			else
			{
				--fill;
				switch(Direction)
				{
					case 1: cordY += 50; ++j; break; //Back Down
					case 2: cordX -= 50; --i; break; //Back Left
					case 3: cordY -= 50; --j; break; //Back Up
					case 4: cordX += 50; ++i; break; //Back Right
				}
			}
		}
		
		g.setColor(Color.DARK_GRAY);
		while(k != 0)
		{			
			switch(Track[k - 1])
			{
				case 0: break;
				case 1: g.fillRect(cordX, cordY, 49, 50); cordY += 50; ++j; --k; break; //Back Down
				case 2: g.fillRect(cordX - 1, cordY, 50, 49); cordX -= 50; --i; --k; break; //Back Left
				case 3: g.fillRect(cordX, cordY - 1, 49, 50); cordY -= 50; --j; --k; break; //Back Up
				case 4: g.fillRect(cordX, cordY, 50, 49); cordX += 50; ++i; --k; break; //Back Right
			}
		}
		g.fillRect(51, 51, 49, 49);
	}
	
	public static Random numGen = new Random();

    public static int RandNum1to4()
    {
    	int rand = Math.abs((1)+numGen.nextInt(4));
    	return rand;
    }
}