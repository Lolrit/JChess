package chess; 

public class EmptyPiece extends ChessPiece { 
	
	public EmptyPiece(int x, int y){
		super(x, y, false);  
		onBoard = true; 
	}	  
	
}
