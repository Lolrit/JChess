package chess;

import java.awt.Color; 
import java.awt.GridLayout; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard extends JPanel
{ 
	private static final long serialVersionUID = 1L;
	
	public ImageIcon blankIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	
	public ImageIcon B_ROOK_ICON 	= new ImageIcon("BrownRook.png");
	public ImageIcon W_ROOK_ICON 	= new ImageIcon("WhiteRook.png");
	public ImageIcon B_KNIGHT_ICON 	= new ImageIcon("BrownKnight.png");
	public ImageIcon W_KNIGHT_ICON 	= new ImageIcon("WhiteKnight.png");
	public ImageIcon B_BISHOP_ICON 	= new ImageIcon("BrownBishop.png");
	public ImageIcon W_BISHOP_ICON 	= new ImageIcon("WhiteBishop.png");
	public ImageIcon B_KING_ICON 	= new ImageIcon("BrownKing.png");
	public ImageIcon W_KING_ICON 	= new ImageIcon("WhiteKing.png");
	public ImageIcon B_QUEEN_ICON 	= new ImageIcon("BrownQueen.png");
	public ImageIcon W_QUEEN_ICON 	= new ImageIcon("WhiteQueen.png");
	public ImageIcon B_PAWN_ICON 	= new ImageIcon("BrownPawn.png");
	public ImageIcon W_PAWN_ICON 	= new ImageIcon("WhitePawn.png"); 

    public String WHITE_TURN_MESSAGE = "White's Turn!";
    public String BLACK_TURN_MESSAGE = "Brown's Turn!";
    
    public JLabel turnMessage = new JLabel(WHITE_TURN_MESSAGE); 

    public Color TILE_COLOR_DARK 	= Color.BLACK; 
    public Color TILE_COLOR_LIGHT 	= Color.WHITE; 
    public Color TILE_COLOR_HOVER 	= Color.GRAY;
    
    public Color AVAILABLE_MOVE_COLOR_DARK 	= new Color(200, 200, 0); 
    public Color AVAILABLE_MOVE_COLOR_LIGHT = new Color(255, 255, 155);
     
	private ArrayList<Coord> availableMoves;  
	private Coord fromCoord, enteredCoord;  
	private boolean pressed = false,
					blackTurn = false; 
	
	public ChessPiece [][] pieces 	= new ChessPiece [8][8];
	public JButton [][] buttons 	= new JButton [8][8];  
	
	public ChessBoard()
	{
		super(new GridLayout(0,8));
		// Create the Chesspieces and populate the pieces array
    	setPieces();
    	// Create the buttons and add the appropriate icons
    	setButtons();
    	// Add handlers for dealing with mouse actions for playing the game
    	setMouseActions();
    	// Add the JButtons to the ChessBoard Panel;
    	for(int i = 0; i<8;i++)
			for(int c = 0; c<8;c++)
			{ 
				add(buttons[c][i]); 
			}
    	
	} 
	// add mouse action handlers for when a mouse presses on, leaves, enteres, and releases on a button
	private void setMouseActions()
	{
		for(int i = 0; i<8;i++)
        	for(int c = 0; c<8; c++)
        	{
        		buttons[i][c].addMouseListener(new MouseAdapter()
        		{ 
        			public void mousePressed(MouseEvent evt)
        			{  
        				// If the the user is not already in the middle of a click, record the coordinates of the click
        				if(!pressed)
        				{
        					fromCoord = new Coord((JButton) evt.getSource()); 
							enteredCoord = fromCoord; 
							// if it is the turn of the piece that was clicked on 
        					if((pieces[fromCoord.getX()][fromCoord.getY()].getIsBlack() &&  blackTurn) || ((!pieces[fromCoord.getX()][fromCoord.getY()].getIsBlack()) && (!blackTurn))) 
        					{
        						//it the piece isn't empty
								if( !(pieces[fromCoord.getX()][fromCoord.getY()] instanceof EmptyPiece) )
								{  
		        					showAvailableMoves();
									pressed = true;
	        					}
        					}
						}    					
        			}
        			// if the mouse is pressed, record it, and change the color of the tile
        			public void mouseEntered(MouseEvent evt)
        			{  
        				if(pressed)
        				{    
            				enteredCoord = new Coord((JButton) evt.getSource());     
							buttons[enteredCoord.getX()][enteredCoord.getY()].setBackground(TILE_COLOR_HOVER); 
    					} 
        			}
        			// 
        			public void mouseReleased(MouseEvent e)
        			{
        				if(pressed)
        				{  
        					//if the button we're on is empty
	        				if((pieces[enteredCoord.getX()][enteredCoord.getY()] instanceof EmptyPiece))
	        					// if this is a legal move make it
	        					if(availableMoves.contains(enteredCoord))  
	        						makeMove();   
	        					//otherwise reset the color of the hovered tile
	        					else
	        						chooseColor(enteredCoord, false); 
	        				// empty out the available moves list and change the colors back
	    					while(!availableMoves.isEmpty())
	    					{
	        					int x = Character.getNumericValue(availableMoves.get(0).toString().charAt(0));
								int y = Character.getNumericValue(availableMoves.get(0).toString().charAt(1)); 
								chooseColor( x, y, false );
								availableMoves.remove(0);
							}     
	        			} 		 
        				pressed = false; 
        			}
        			//====================================
        			public void mouseExited(MouseEvent evt)
        			{
        				//if you're making the tiles gray, make sure to set them back to their original color
    					if(pressed)
    					{  
							chooseColor(enteredCoord, availableMoves.contains(enteredCoord));  
						}
					}
				});
        	}
	} 
	
	// change the turn and the message displayed
	private void toggleTurn() 
	{ 
		blackTurn = !blackTurn;
		
		if(blackTurn) 
			turnMessage.setText(BLACK_TURN_MESSAGE);
		else 
			turnMessage.setText(WHITE_TURN_MESSAGE);
		
	} 
	
	//get the available moves for the selected piece and change the color of the tiles that the piece can move to
	public void showAvailableMoves()
	{
		availableMoves = pieces[fromCoord.getX()][fromCoord.getY()].availableMoves(pieces); 
		if (availableMoves.size()!=0)
		{
			for(int z = 0; z<availableMoves.size();z++)
			{ 
				int x = Character.getNumericValue(availableMoves.get(z).toString().charAt(0));
				int y = Character.getNumericValue(availableMoves.get(z).toString().charAt(1));
				chooseColor(x, y, true); 
				repaint();
			}
		}
	}
	
	//overloaded method that takes x and y instead of a Coord
	public void chooseColor(int x, int y, boolean availMove) {
		chooseColor(new Coord(x,y), availMove);
	}
	
	// Chooses the color for the tile
	public void chooseColor(Coord coord, boolean availMove)
	{
		int x = coord.getX();
		int y = coord.getY();

    	buttons[x][y].setOpaque(true);
    	buttons[x][y].setBorderPainted(false);
    	
		if((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1))
		{   
			if(availMove) 
				buttons[x][y].setBackground(AVAILABLE_MOVE_COLOR_LIGHT);
			else
				buttons[x][y].setBackground(TILE_COLOR_LIGHT);
		}
		else
		{   
			if(availMove) 
				buttons[x][y].setBackground(AVAILABLE_MOVE_COLOR_DARK);
			else 
				buttons[x][y].setBackground(TILE_COLOR_DARK);
		}
	}
	
	// populate pieces with the piece given
	public void setPiece(ChessPiece piece)
	{
		pieces[piece.getX()][piece.getY()] = piece;
	} 
	
	//perform the move that has already been verified legal
	public void makeMove()
	{	    
		//swap icons
		buttons[enteredCoord.getX()][enteredCoord.getY()].setIcon(buttons[fromCoord.getX()][fromCoord.getY()].getIcon());   
		buttons[fromCoord.getX()][fromCoord.getY()].setIcon(blankIcon); 

		pieces[enteredCoord.getX()][enteredCoord.getY()] = pieces[fromCoord.getX()][fromCoord.getY()];
		pieces[enteredCoord.getX()][enteredCoord.getY()].setCoord(enteredCoord); 
		pieces[enteredCoord.getX()][enteredCoord.getY()].hasMoved = true; 
		pieces[fromCoord.getX()][fromCoord.getY()] = new EmptyPiece(fromCoord.getX(), fromCoord.getY());

		toggleTurn(); 
		
	}

	// perform initial population of all the chessPieces
	public void setPieces()
	{
		pieces = new ChessPiece [8][8];
		
		for(int i = 0; i<8;i++)
			for(int c = 2; c<6;c++)
				pieces[i][c] = new EmptyPiece( i, c );
		
		pieces[4][0] = new EmptyPiece(4,0);
		pieces[4][7] = new EmptyPiece(4,7);
		
		setPiece( new Queen( true ));
		setPiece( new Queen( false ));

		setPiece( new King( true ));
		setPiece( new King( false ));

		setPiece( new Bishop( 0, true ));
		setPiece( new Bishop( 1, true ));
		setPiece( new Bishop( 0, false ));
		setPiece( new Bishop( 1, false ));

		setPiece( new Knight( 0, true ) );
		setPiece( new Knight( 1, true ) );
		setPiece( new Knight( 0, false ) );
		setPiece( new Knight( 1, false ) );

		setPiece( new Rook( 0, true ) );
		setPiece( new Rook( 1, true ) );
		setPiece( new Rook( 0, false ) );
		setPiece( new Rook( 1, false ) );

		for(int i = 0; i<8;i++)
			setPiece(new Pawn( i, true ));

		for(int i = 0; i<8;i++)
			setPiece(new Pawn( i, false ));
	}

	// perform the initial population of the buttons
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
		setTileColors();
		setIcons();
	}
	
	// iterates through the whole board and sets the tile color for each square
	public void setTileColors()
	{
		for(int i = 0; i < 8; i++) 
		{  
            for(int j = 0; j < 8; j++)
            {  
        		chooseColor(i,j,false);
            }
		}
	}

	// make the initial placement of icons
	public void setIcons()
	{
		
		buttons[0][0].setIcon(B_ROOK_ICON);
		buttons[1][0].setIcon(B_KNIGHT_ICON);
		buttons[2][0].setIcon(B_BISHOP_ICON);
		buttons[3][0].setIcon(B_QUEEN_ICON);
		buttons[4][0].setIcon(B_KING_ICON);
		buttons[5][0].setIcon(B_BISHOP_ICON);
		buttons[6][0].setIcon(B_KNIGHT_ICON);
		buttons[7][0].setIcon(B_ROOK_ICON);
		
		for(int i = 0; i < 8; ++i) {
			buttons[i][1].setIcon(B_PAWN_ICON);
		}

		for(int i = 0; i < 8; ++i) {
			buttons[i][6].setIcon(W_PAWN_ICON);
		}
		 
        buttons[0][7].setIcon(W_ROOK_ICON);
        buttons[1][7].setIcon(W_KNIGHT_ICON);
        buttons[2][7].setIcon(W_BISHOP_ICON);
        buttons[3][7].setIcon(W_KING_ICON);
        buttons[4][7].setIcon(W_QUEEN_ICON);
        buttons[5][7].setIcon(W_BISHOP_ICON);
        buttons[6][7].setIcon(W_KNIGHT_ICON);
        buttons[7][7].setIcon(W_ROOK_ICON);
        
	}
	
	// open a file choose and save the game to a .jc file (just a special txt file)
	public void saveGame() {
		JFileChooser fileChooser = new JFileChooser();  
		fileChooser.showSaveDialog(this);
		
		File file = fileChooser.getSelectedFile(); 
		
		if (file.getName().length() <= 3 || ! file.getName().substring(file.getName().length()-3).equalsIgnoreCase(".jc")) 
		{
		    file = new File(file.toString() + ".jc");    
		} 
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()), StandardCharsets.UTF_8))) 
		{
		    writer.write(getGameString());
		} 
		catch (IOException ex) 
		{
			turnMessage.setText("Could not save file! Please try again later");
		}   
	}
	
	// open file choose and get file to load game from
	public void loadGame() {

		JFileChooser fileChooser = new JFileChooser();  
		fileChooser.showOpenDialog(this);
		
		loadFromGameFile(fileChooser.getSelectedFile()); 
	}
	
	// parse file and populate board accordingly
	private boolean loadFromGameFile(File file) {
		try(Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), StandardCharsets.UTF_8)))
		{ 
	        char[] chars = new char[(int) file.length()]; 
			reader.read(chars);
			
			String gameString = new String(chars);
			String[] gameStringLines = gameString.split("\n");
			
			char turnChar = gameStringLines[0].charAt(0);
			if (!((turnChar == 'b' && blackTurn) || (turnChar == 'w' && !blackTurn)))
				toggleTurn(); 
			
			for(int i = 1; i < gameStringLines.length; ++i ) {
				
				String[] line = gameStringLines[i].split(",");
				
				for(int c = 0; c < line.length; ++c){
					
					if(line[c].length() != 2)
						throw new IOException("File is Corrupted");
					
					boolean pieceIsBlack = true;
					if(line[c].charAt(0) == 'w') {
						pieceIsBlack = false;
					}
					
					switch(line[c].charAt(1)) {
					
					case 'k':
						pieces[c][i] = new King(pieceIsBlack);
						pieces[c][i].setCoord(c, i); 

						if(pieceIsBlack)
							buttons[c][i].setIcon(new ImageIcon("BrownKing.png"));
						else
							buttons[c][i].setIcon(new ImageIcon("WhiteKing.png"));
						
						break;
						
					case 'q':
						pieces[c][i] = new Queen(pieceIsBlack);
						pieces[c][i].setCoord(c, i); 

						if(pieceIsBlack)
							buttons[c][i].setIcon(new ImageIcon("BrownQueen.png"));
						else
							buttons[c][i].setIcon(new ImageIcon("WhiteQueen.png"));
						
						break;
						
					case 'n':
						pieces[c][i] = new Knight(0, pieceIsBlack);
						pieces[c][i].setCoord(c, i); 

						if(pieceIsBlack)
							buttons[c][i].setIcon(new ImageIcon("BrownKnight.png"));
						else
							buttons[c][i].setIcon(new ImageIcon("WhiteKnight.png"));
						
						break;
						
					case 'b':
						pieces[c][i] = new Bishop(0, pieceIsBlack);
						pieces[c][i].setCoord(c, i); 

						if(pieceIsBlack)
							buttons[c][i].setIcon(new ImageIcon("BrownBishop.png"));
						else
							buttons[c][i].setIcon(new ImageIcon("WhiteBishop.png"));
						
						break;
						
					case 'r':
						pieces[c][i] = new Rook(0, pieceIsBlack);
						pieces[c][i].setCoord(c, i); 
						
						if(pieceIsBlack)
							buttons[c][i].setIcon(new ImageIcon("BrownRook.png"));
						else
							buttons[c][i].setIcon(new ImageIcon("WhiteRook.png"));
						
						break;
						
					case 'p':
						pieces[c][i] = new Pawn(0, pieceIsBlack);
						pieces[c][i].setCoord(c, i); 

						if(pieceIsBlack)
							buttons[c][i].setIcon(new ImageIcon("BrownPawn.png"));
						else
							buttons[c][i].setIcon(new ImageIcon("WhitePawn.png")); 
						
						break;
						
					default:
						pieces[c][i] = new EmptyPiece(c,i);  
						buttons[c][i].setIcon(blankIcon);
						break;
							
					}
				}
			}
		}
		catch(IOException ex) {
			turnMessage.setText("Cannot load file! Please try again later");
			return false;
		}
		return true;
	}
	// create a string to represent the current state of the game
	private String getGameString() {
		String gameString = "";
		
		if(blackTurn)
			gameString += "b";
		else
			gameString += "w";
		
		for(int i = 0; i < 8; ++i) {
			for(int c = 0; c < 8; ++c) { 
				if(pieces[c][i].isBlack) {
					gameString += "b";
				}
				else {
					gameString += "w";
				}
				switch(pieces[c][i].getClass().getSimpleName()) {
				case "King":
					gameString += "k,";
					break;
				case "Queen":
					gameString += "q,";
					break;
				case "Knight":
					gameString += "n,";
					break;
				case "Bishop":
					gameString += "b,";
					break;
				case "Rook":
					gameString += "r,";
					break;
				case "Pawn":
					gameString += "p,";
					break;
				default:
					gameString += "e,";
					break; 
				}
			}
			gameString += "\n";
		}
		return gameString;
	}
	
	//do a complete reset of the game, recreate all the JButtons and pieces
	public void reset() { 

    	for(int i = 0; i<8;i++)
			for(int c = 0; c<8;c++)
			{
				remove(buttons[c][i]); 
				buttons[c][i] = new JButton();
			}
    	
    	setPieces();
    	setButtons();  
    	setMouseActions();
    	
    	for(int i = 0; i<8;i++)
			for(int c = 0; c<8;c++)
			{
				add(buttons[c][i]);  
			}
    	
		turnMessage.setText("New Game! Whites Turn!");
		blackTurn = false;
		repaint();
	}
}
