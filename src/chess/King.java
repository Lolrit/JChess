package chess;

import java.util.ArrayList;

public class King extends ChessPiece implements ChessPieceMovement{



	public King(boolean isBlack)
	{
		super(4, 7, isBlack); 
		
		if(isBlack) 
			setCoord(3,0); 
		else 
			setCoord(4,7); 
		
		onBoard = true; 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY()+1;
		ArrayList<Coord> coords= new ArrayList<Coord>();
		
		// Down
		y0 = getY()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up
		y0 = getY()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}		
		
		// Right
		x0 = getX()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}			
		
		// Left
		x0 = getX()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down Right
		x0 = getX()+1;
		y0 = getY()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Down Left
		x0 = getX()-1;
		y0 = getY()+1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		// Up Right
		x0 = getX()+1;
		y0 = getY()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		
		//Up Left
		x0 = getX()-1;
		y0 = getY()-1;
		
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		return coords;
	}
	
}
