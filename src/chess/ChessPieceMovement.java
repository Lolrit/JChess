package chess;

import java.util.ArrayList;

import javax.swing.JButton;

public interface ChessPieceMovement
{  
	public ArrayList<Coord>  availableMoves (ChessPiece pieces[][]);
	
}

