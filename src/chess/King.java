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
		
		System.out.println(coord.toString() + " is "+ isBlack);
		
		onBoard = true; 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();
		//System.out.println("Moving up");
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving Down");
		y0 = getY()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}			
		//===========================================			
		//System.out.println("Moving Right");
		x0 = getX()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}			
		//===========================================			
		//System.out.println("Moving Left");
		x0 = getX()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving upRight");
		x0 = getX()+1;
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving UpLeft");
		x0 = getX()-1;
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving DownRight");
		x0 = getX()+1;
		y0 = getY()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving DownLeft");
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
