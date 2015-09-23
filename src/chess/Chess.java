package chess;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Chess extends JFrame
{
	final JPanel gui = new JPanel(new BorderLayout(3, 3));
	//ChessSquare fromSquare;
	ChessBoard board;
	JPanel panel;
	JFrame test;
	public Chess(String title)
	{
		super(title);
		initialize();
	}
	public void initialize()
	{
		board = new ChessBoard();
		add(gui);
		gui.add(board);
		validate();
		setLocationByPlatform(true);
		pack();
		setMinimumSize(getSize());
		setVisible(true);
		
	}
	private static final long serialVersionUID = -5185475584729272657L;
	public static void main(String args[] ) 
	{
		
		Runnable r = new Runnable() {

            @Override
            public void run() {
            	Chess chess = new Chess("Chess");
        		chess.setDefaultCloseOperation(EXIT_ON_CLOSE);
        		


            }
        };
        SwingUtilities.invokeLater(r);
	}
//=============================================================================================================================
}
