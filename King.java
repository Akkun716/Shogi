
/***
 * Represents a king piece from the Japanese game of Shogi.
 * 
 * The shogi king moves in the same fashion to the chess king, able to move one
 * square in any direction.
 * - This piece does not have a promotion option.
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
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        // Regular king movement
        // If there is a change in position...
        return rowDistance != 0 && colDistance != 0
            // ...check if it is one space in a direction.
            ? colDistance >= -1 && colDistance <= 1 && rowDistance >= -1 && rowDistance <= 1
            : false;
    }

    public void promote()
    {}
    
    public void reset()
    {}
}