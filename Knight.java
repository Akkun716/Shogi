/***
 * Represents a knight piece from the Japanese game of Shogi.
 * 
 * The shogi knight moves differently to the chess knight. This knight only
 * moves two spaces forward and one space to the left or right. It can NOT move
 * two spaces to the side and one space forward and does NOT have backward
 * movement.
 * - When promoted, this piece turns into a gold general piece.
 ***/
public class Knight extends Piece
{
    public Knight(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "knight";
        pieceName = "桂馬";
    }

    public Knight()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        // Promoted knight movement (look to GoldG class for description).
        if(upgrade) {
            return colDist == 1 || colDist == -1
                ? rowDist == 0 || rowDist == direction
                : rowDist == 1 || rowDist == -1;
        
        // Regular knight movement
        } else {
            // If the column distance is one space to the right or left...
            return colDist == -1 || colDist == 1
                // ...check if the piece is moving two spaces "forward"
                ? rowDist == direction * 2
                : false;
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceType = "gold";
        pieceName = "金　";
    }

    public void reset()
    {
        upgrade = false;
        pieceType = "knight";
        pieceName = "桂馬";
    }
}