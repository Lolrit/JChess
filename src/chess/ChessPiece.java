package chess;

import java.util.ArrayList;

public class ChessPiece { 
	public Coord coord;
	public boolean isBlack;
	public boolean onBoard;
	public boolean hasMoved; 

	public ChessPiece(int x, int y, boolean isBlack)
	{
		setIsBlack(isBlack);
		coord = new Coord(0,0);
		hasMoved = false;
		setCoord(x,y); 
	}
	
	
	public void setX(int x1)
	{
		coord.setX(x1);
	}
	
	public int getX()
	{
		return coord.getX();
	}
	
	public void setY(int y1)
	{
		coord.setY(y1);
	}
	
	public int getY()
	{
		return coord.getY();
	}
	
	public void setCoord(int x1 ,int y1)
	{
		setX(x1);
		setY(y1);
	}

	public void setCoord(Coord enteredCoord) 
	{

		setX(enteredCoord.getX());
		setY(enteredCoord.getY());
		
	}
	public Coord getCoord()
	{
		return coord;
	}
	
	public void setIsBlack(boolean black)
	{
		isBlack = black;
	}
	   
	public boolean getIsBlack()
	{
		return isBlack;
	}
	
	public void move(char x1, int y1)
	{
		coord=new Coord(x1,y1);
	}
	
	public void offBoard()
	{
		onBoard = false;
	}
	
	public boolean getOnBoard()
	{
		return onBoard;
	}
	
	public String toString()
	{
		return "position is "+getX()+getY();
	}
	
	public boolean outOfRange(int x0, int y0)
	{
		boolean range = false;
		if(x0<0||x0>7)
		{
			System.out.println("x out of bounds");
			range = true;
		}
		else if(y0<0||y0>7)
		{
			System.out.println("y out of bounds");
			range = true;
		}		
		return range;
	}		
	
	// return true if the piece given is an enemy piece
	public boolean enemyHere(ChessPiece piece)
	{
		if( piece == null || piece instanceof EmptyPiece )
			return false;
		
		boolean isEnemy;
		boolean otherIsBlack = piece.isBlack; 
		
		if( (isBlack && !otherIsBlack) || ( !isBlack && otherIsBlack) )
			isEnemy = true;
		else
			isEnemy = false;
		
		return isEnemy;

	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ChessPiece))
			return false;
		
		ChessPiece piece = (ChessPiece) obj; 
		
		if(coord.getX() == piece.getX() && coord.getY() == piece.getY())
			return true;
		
		return false;	
	}
	
	// return true if the coordinate given contains a piece that is not empty
	public boolean unitHere(ChessPiece pieces[][],int x0,int y0)
	{
		//System.out.println(x0+", "+y0);
		boolean unit = true;
		if(pieces[x0][y0] instanceof EmptyPiece)
			unit = false;
		else
			System.out.println("moveadded: "+x0+", "+y0);
		return unit;	
	}
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{ 
		ArrayList<Coord> coords= new ArrayList<Coord>(); 
		return coords;
	}
}
