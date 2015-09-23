package chess;

import java.util.ArrayList;

public class Bishop extends ChessPiece implements ChessPieceMovement {

	public Bishop(int count, boolean isBlack)
	{
		super(2, 7, isBlack); 
		
		if(isBlack) 
			setCoord( 2 + (count * 3), 0 ); 
		else 
			setCoord( 2 + (count * 3), 7 );
		
		System.out.println(coord.toString() + " is "+ isBlack);
		
		onBoard = true; 
	}	

	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>(); 
		//System.out.println("Moving downRight");
		x0 = getX()+1;
		y0 = getY()+1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0++;
			y0++;
		}
		//===========================================			
		//System.out.println("Moving UpLeft");
		x0 = getX()-1;
		y0 = getY()+1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0--;
			y0++;
		}
		//===========================================			
		//System.out.println("Moving DownRight");
		x0 = getX()+1;
		y0 = getY()-1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0++;
			y0--;
		}
		//===========================================			
		//System.out.println("Moving DownLeft");
		x0 = getX()-1;
		y0 = getY()-1;
		while(!outOfRange(x0, y0))
		{
			if (!unitHere(pieces,x0,y0)) 
				coords.add(new Coord(x0,y0));
			else if(enemyHere(pieces[x0][y0]))
			{
				coords.add(new Coord(x0,y0));
				break;
			}
			else
				break;
			x0--;
			y0--;
		}
		return coords;
	}
}
