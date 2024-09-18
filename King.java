
/***
 * Represents a king piece from the Japanese game of Shogi.
 * 
 * The shogi king moves in the same fashion to the chess king, able to move one square in any direction.
 * This piece does not have a promotion option.
 ***/
public class King extends Piece
{
    public King(int direction)
    {
        this.direction = direction;
        upgrade = true;
        pieceType = "king";
        // In shogi, the opposing king pieces have different written names.
        if(direction == -1) {
            pieceName = "玉将";
        } else {
            pieceName = "王将";
        }
    }

    public King()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the 1st int
        // being the row and the second as the column.
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        // Regular king movement
        return rowDist == 0 && colDist == 0
            ? false
            : colDist >= -1 && colDist <= 1 && rowDist >= -1 && rowDist <= 1;
    }

    public void promote()
    {}
    
    public void reset()
    {}
}