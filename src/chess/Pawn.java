package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece implements ChessPieceMovement  { 
	
	public Pawn(int count, boolean isBlack)
	{
		super(count, 6, isBlack); 
		
		if(isBlack) 
			setCoord(count,1); 
		else 
			setCoord(count,6);
		
		System.out.println(coord.toString() + " is "+ isBlack);
		
		onBoard = true; 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>(); 
		if(isBlack)
		{
			y0 = getY()+1;
			if(!outOfRange(x0, y0))
			{
				if (!unitHere(pieces,x0,y0)) //check
					coords.add(new Coord(x0,y0));
			
				if(x0<7)
				{
					if(enemyHere(pieces[x0+1][y0]))
					{	
						coords.add(new Coord(x0+1,y0));
					}
				}
				if(x0>1)
				{
					if(enemyHere(pieces[x0-1][y0]))
					{
						coords.add(new Coord(x0-1,y0));
					}
				}
				
			}
			if(!hasMoved)
			{
				x0 = getX();
				y0 = getY();
				if(!outOfRange(x0, y0+2))
					coords.add(new Coord(x0,y0+2));
			}
		}
		else
		{
			y0 = getY()-1;
			if(!outOfRange(x0, y0)){
				if (!unitHere(pieces,x0,y0)) 
					coords.add(new Coord(x0,y0));
			
				if(x0<7){
					if(enemyHere(pieces[x0+1][y0]))
						coords.add(new Coord(x0+1,y0));}
				
				if(x0>1){
					if(enemyHere(pieces[x0-1][y0]))
						coords.add(new Coord(x0-1,y0));}
				
			}
			if(!hasMoved)
			{
				x0 = getX();
				y0 = getY();
				if(!outOfRange(x0, y0-2))
					coords.add(new Coord(x0,y0-2));
			}
		}  
		return coords;
	}
}
