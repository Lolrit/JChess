package chess;

import java.util.ArrayList;

public class ChessPiece { 
	public Coord coord;
	public boolean isBlack;
	public boolean onBoard;
	public boolean hasMoved;
	public String name;

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
	
	public boolean isEqual(ChessPiece piece)
	{
		boolean equals = false;
		if(coord.getX() == piece.getX() && coord.getY() == piece.getY())
			equals = true;
		return equals;	
	}
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
		int x0 = getX();
		int y0 = getY();
		ArrayList<Coord> coords= new ArrayList<Coord>();
		//System.out.println("x"); 
		switch(name.substring(5))
		{
//========================================================
		case "queen": 
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
			}
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
			}
			break;
//========================================================
		case "king":
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
			break;
//========================================================
		case "bishop":
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
			}
			break;
//========================================================
		case "knight":
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
			break;
//========================================================
		case "rook":
			//System.out.println("Moving up");
			y0 = getY()+1;
			//System.out.println(x0+", "+y0);
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
			}
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
			}
			break;
//========================================================
		case "pawn":
			if(!isBlack)
			{
				y0 = getY()+1;
				if(!outOfRange(x0, y0))
				{
					if (!unitHere(pieces,x0,y0)) 
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
			break;
//========================================================
		default: 
			break;		
		}
		return coords;
	}
}
