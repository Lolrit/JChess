package chess;

import java.util.ArrayList; 

public interface ChessPieceMovement
{  
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][]);
	
}

