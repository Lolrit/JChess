package chess;

import java.util.ArrayList;

public class Queen extends ChessPiece implements ChessPieceMovement{


	public Queen(boolean isBlack)
	{
		super(3, 7, isBlack); 
		
		if(isBlack) 
			setCoord(3,0); 
		else 
			setCoord(3,7);
		
		System.out.println(coord.toString() + " is "+ isBlack);
		
		onBoard = true; 
	}	
	
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][])
	{
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();
		
		System.out.println("Moving up");
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
			y0++;
		}
		//===========================================			
		//System.out.println("Moving Down");
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
			y0--;
		}
		y0 = getY();
		//===========================================			
		//System.out.println("Moving Right");
		x0 = getX()+1;
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
		}
		//===========================================			
		//System.out.println("Moving Right");
		x0 = getX()-1;
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
		}
		//===========================================			
		//System.out.println("Moving Left");
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
		//===========================================			
		//System.out.println("Moving upRight");
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
		return coords;
	}
	
}
