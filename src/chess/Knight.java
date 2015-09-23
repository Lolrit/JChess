package chess;

import java.util.ArrayList;

public class Knight extends ChessPiece implements ChessPieceMovement 
{

	public Knight(int count, boolean isBlack)
	{
		super(count*7, 7, isBlack); 
		
		if(isBlack) 
			setCoord(count*6,0); 
		else 
			setCoord(count*6,7);
		
		System.out.println(coord.toString() + " is "+ isBlack);
		
		onBoard = true; 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();
		//System.out.println("Moving up1 Right2");
		x0 = getX()+2;
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving down1 Right2");
		x0 = getX()+2;
		y0 = getY()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving up1 left2");
		x0 = getX()-2;
		y0 = getY()+1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		};
		//===========================================			
		//System.out.println("Moving down1 left2");
		x0 = getX()-2;
		y0 = getY()-1;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving up2 Right1");
		x0 = getX()+1;
		y0 = getY()+2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving down2 Right1");
		x0 = getX()+1;
		y0 = getY()-2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving up2 left1");
		x0 = getX()-1;
		y0 = getY()+2;
		if(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
				coords.add(new Coord(x0,y0));
		}
		//===========================================			
		//System.out.println("Moving down2 left1");
		x0 = getX()-1;
		y0 = getY()-2;
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
