package chess;

import java.awt.Color; 
import java.awt.GridLayout; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ChessBoard extends JPanel
{ 
	private static final long serialVersionUID = 1L;
	public ImageIcon blankIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
//====================================
	private JButton fromSquare; 
	private Color fromColor;
//====================================
	private int x0,y0;
	private ArrayList<Coord> availableMoves; 
//====================================
	private JButton enteredSquare; 
	private Color enteredColor; 
//====================================
	private boolean makeGray = false,
					haveSetColor = false;
//====================================
	public ChessPiece [][] squares = new ChessPiece [8][8];
	public JButton [][] buttons = new JButton [8][8];
//====================================
	public boolean pressed = false,
					blackTurn = false;
//======================================================================================
	public ChessBoard()
	{
		super(new GridLayout(0,8));
    	setPieces();
    	setButtons();
    	setMouseActions();
    	for(int i = 0; i<8;i++)
			for(int c = 0; c<8;c++)
			{
				add(buttons[c][i]); 
			}
    	
	}
//======================================================================================
	private void setMouseActions()
	{
		for(int i = 0; i<8;i++)
        	for(int c = 0; c<8; c++)
        	{
        		buttons[i][c].addMouseListener(new MouseAdapter()
        		{
				//====================================
        			public void mousePressed(MouseEvent evt)
        			{  
        				
        				if(!pressed){
        					Coord coord = new Coord((JButton) evt.getSource());
        					makeGray = true;
        					setFrom(coord);  
	        				System.out.println(blackTurn);
	        				System.out.println(squares[coord.getX()][coord.getY()].getIsBlack());
        					if((squares[coord.getX()][coord.getY()].getIsBlack() &&  blackTurn) || ((!squares[coord.getX()][coord.getY()].getIsBlack()) && (!blackTurn))) {
								if( !(squares[coord.getX()][coord.getY()] instanceof EmptyPiece) )
								{ 
		        					setEntered(coord.getX(),coord.getY());
		        					showAvailableMoves();
									pressed = true;
	        					}
        					}
						}    					
        			}
        			//====================================
        			public void mouseEntered(MouseEvent evt)
        			{
        				for(int x = 0; x < 8; x++){
        					for(int y = 0; y < 8; y++){
        						System.out.print(buttons[x][y].getName() + " | ");
        					}
        					System.out.println();
        				}
        				Coord coord = new Coord((JButton) evt.getSource());  
        				System.out.println(coord);
        				if(coord.getX() <= 7 || coord.getY() <= 7) {
	        				if(pressed)
	        				{  
	        					setEntered(coord.getX(),coord.getY());
	    						if(makeGray)
	    						{ 
	    							if(!(enteredColor.equals(Color.YELLOW)))
	    							{ 
	    								enteredSquare.setBackground(Color.GRAY);
	    								repaint();
	    							} 
	    						}
	    					}
        				}
        			}
        			//====================================
        			public void mouseReleased(MouseEvent e)
        			{
        				if(pressed){
    						Coord fromCoord = new Coord(fromSquare);
            				Coord enteredCoord = new Coord(enteredSquare);
	        				if(!enteredSquare.getName().equalsIgnoreCase("empty"))
	        					if(enteredSquare.getBackground() == Color.YELLOW) {
	        						makeMove(); 
	        						//fromSquare.setName("empty");
	        						blackTurn = !blackTurn;
	        					}
	        				if(makeGray)
        						chooseColor(enteredCoord);
	        				System.out.println(enteredSquare.getName());

	    					while(!availableMoves.isEmpty())
	    					{
	        					x0 = Character.getNumericValue(availableMoves.get(0).toString().charAt(0));
								y0 = Character.getNumericValue(availableMoves.get(0).toString().charAt(1));
								System.out.println("removing: "+x0+", "+y0);
								chooseColor(new Coord(x0,y0));
								availableMoves.remove(0);
							}        	
	        				System.out.println("From Coord: "+fromCoord.getX()+", "+fromCoord.getY());
	        				buttons[fromCoord.getX()][fromCoord.getY()].setBackground(Color.BLACK);	
	        				fromSquare.setBackground(fromColor);
	        			} 		
        				makeGray = false;
        				pressed = false; 
        			}
        			//====================================
        			public void mouseExited(MouseEvent evt)
        			{
    					if(makeGray && enteredSquare != null){
    						System.out.print(" MakeGray"); 
							System.out.print(" HasSquare");
							System.out.println(enteredSquare.getBackground());
							if(!enteredColor.equals(Color.YELLOW))
								chooseColor(new Coord(enteredSquare)); 
						}
					}
				});
        	}
	} 
	public void setEntered(int x, int y)
	{
		Coord entered = new Coord(x,y); 
		enteredSquare = buttons[entered.getX()][entered.getY()];
		enteredColor = buttons[entered.getX()][entered.getY()].getBackground(); 
	}
	public void showAvailableMoves()
	{
		Coord coord = new Coord(fromSquare);
		availableMoves = squares[coord.getX()][coord.getY()].availableMoves(squares);
		System.out.println(availableMoves);
		if (availableMoves.size()!=0)
		{
			for(int z = 0; z<availableMoves.size();z++)
			{
				System.out.println(availableMoves.get(z).toString());
				x0 = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
				y0 = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
				buttons[x0][y0].setBackground(Color.YELLOW);
				repaint();
			}
		}
	}
	public void chooseColor(Coord coord)
	{
		int x = coord.getX();
		int y = coord.getY();
		if((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1))
		{ 
			if(!haveSetColor)
			{
            	buttons[x][y].setOpaque(true);
            	buttons[x][y].setBorderPainted(false);
			}
			buttons[x][y].setBackground(Color.WHITE);
		}
		else
		{
			if(!haveSetColor)
			{
            	buttons[x][y].setOpaque(true);
            	buttons[x][y].setBorderPainted(false);
			}
			System.out.println("SETBLACK");
			buttons[x][y].setBackground(Color.BLACK);
		}
	}
	public void setFrom(Coord coord)
	{ 
		fromSquare = buttons[coord.getX()][coord.getY()]; 
		fromColor = fromSquare.getBackground(); 
	}
	public void setSquare(ChessPiece piece)
	{
		squares[piece.getX()][piece.getY()] = piece;
	} 
	public void makeMove()
	{	 
		Coord entered = new Coord(enteredSquare); 

		Coord from = new Coord(fromSquare); 
 
		System.out.println(String.format("Entered (%d,%d) From (%d,%d)", entered.getX(), entered.getY(), from.getX(), from.getY()));
		
		
		buttons[entered.getX()][entered.getY()].setIcon(buttons[from.getX()][from.getY()].getIcon()); 
		
		if((from.getX()%2 == 0 &&from.getY()%2 == 0) ||(from.getX()%2 == 1 && from.getY()%2 == 1))
			createEmptyWhiteSquare(buttons[from.getX()][from.getY()]);
		else
			createEmptyBlackSquare(buttons[from.getX()][from.getY()]); 
		 
		buttons[from.getX()][from.getY()].setIcon(blankIcon); 

		squares[entered.getX()][entered.getY()] = squares[from.getX()][from.getY()];
		//String name = squares[from.getX()][from.getY()].getName();
		//squares[entered.getX()][entered.getY()].setName(name);
		squares[entered.getX()][entered.getY()].setCoord(entered); 
		squares[entered.getX()][entered.getY()].hasMoved = true;
		squares[entered.getX()][entered.getY()].setIsBlack( squares[from.getX()][from.getY()].getIsBlack() ); 
		squares[from.getX()][from.getY()] = new EmptyPiece(from.getX(), from.getY());
		
	}
//======================================================================================
	public void setPieces()
	{
		squares = new ChessPiece [8][8];
		
		for(int i = 0; i<8;i++)
			for(int c = 2; c<6;c++)
				squares[i][c] = new EmptyPiece( i, c );
		
		squares[4][0] = new EmptyPiece(4,0);
		squares[4][7] = new EmptyPiece(4,7);
		setSquare( new Queen( true ));
		//====================================		
		setSquare( new Queen( false ));
		//====================================			
		setSquare( new King( true ));
		//====================================	
		setSquare( new King( false ));
		//====================================	
		setSquare( new Bishop( 0, true ));
		//====================================	
		setSquare( new Bishop( 1, true ));
		//====================================	
		setSquare( new Bishop( 0, false ));
		//====================================	
		setSquare( new Bishop( 1, false ));
		//====================================	
		setSquare( new Knight( 0, true ) );
		//====================================	
		setSquare( new Knight( 1, true ) );
		//====================================	
		setSquare( new Knight( 0, false ) );
		//====================================	
		setSquare( new Knight( 1, false ) );
		//====================================	
		setSquare( new Rook( 0, true ) );
		//====================================	
		setSquare( new Rook( 1, true ) );
		//====================================	
		setSquare( new Rook( 0, false ) );
		//====================================	
		setSquare( new Rook( 1, false ) );
		//====================================	
		for(int i = 0; i<8;i++)
			setSquare(new Pawn( i, true ));
		//====================================	
		for(int i = 0; i<8;i++)
			setSquare(new Pawn( i, false ));
	}
//======================================================================================
	public void setButtons()
	{
		for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
            {
            	int name = i*10+j;
            	buttons[i][j] = new JButton();
            	buttons[i][j].setOpaque(true);
            	buttons[i][j].setBorderPainted(false);
            	buttons[i][j].setName(Integer.toString(name));
            }
		setEmptyBoard();
		setIcons();
	}
	public void setEmptyBoard()
	{
		for(int i = 0; i < 8; i++) {  
            for(int j = 0; j < 8; j++)
            {  
            	if((i%2==0 && j%2==0) || (i%2==1 && j%2==1))
            		createEmptyWhiteSquare(buttons[i][j]);
            	else
            		createEmptyBlackSquare(buttons[i][j]); 
            }
		}
	}
	public JButton createEmptyWhiteSquare(JButton jb) {
  
		jb.setBackground(Color.WHITE);
		return jb;
	}
	public JButton createEmptyBlackSquare(JButton jb) {
 
		jb.setBackground(Color.black);
		return jb;
	}
	public void setIcons()
	{
		buttons[0][0].setIcon(new ImageIcon("BrownRook.png"));
		buttons[1][0].setIcon(new ImageIcon("BrownKnight.png"));
		buttons[2][0].setIcon(new ImageIcon("BrownBishop.png"));
		buttons[3][0].setIcon(new ImageIcon("BrownKing.png"));
		buttons[4][0].setIcon(new ImageIcon("BrownQueen.png"));
		buttons[5][0].setIcon(new ImageIcon("BrownBishop.png"));
		buttons[6][0].setIcon(new ImageIcon("BrownKnight.png"));
		buttons[7][0].setIcon(new ImageIcon("BrownRook.png"));
        buttons[0][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[1][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[2][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[3][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[4][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[5][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[6][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[7][1].setIcon(new ImageIcon("BrownPawn.png"));
        buttons[0][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[1][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[2][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[3][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[4][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[5][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[6][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[7][6].setIcon(new ImageIcon("WhitePawn.png"));
        buttons[0][7].setIcon(new ImageIcon("WhiteRook.png"));
        buttons[1][7].setIcon(new ImageIcon("WhiteKnight.png"));
        buttons[2][7].setIcon(new ImageIcon("WhiteBishop.png"));
        buttons[3][7].setIcon(new ImageIcon("WhiteKing.png"));
        buttons[4][7].setIcon(new ImageIcon("WhiteQueen.png"));
        buttons[5][7].setIcon(new ImageIcon("WhiteBishop.png"));
        buttons[6][7].setIcon(new ImageIcon("WhiteKnight.png"));
        buttons[7][7].setIcon(new ImageIcon("WhiteRook.png"));
	}
}
