/***
 * Represents a bishop piece from the Japanese game of Shogi.
 * 
 * The shogi bishop moves in a similar fashion to the chess bishop, able to
 * move diagonally freely as long as there is no piece blocking its path.
 * - When promoted, this piece gains the option to move one square
 *   vertically or horizontally instead of moving diagonally.
 ***/
public class Bishop extends Piece
{
    public Bishop(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "bishop";
        pieceName = "角行";
    }

    public Bishop()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        // Promoted bishop movement (regular + one space horizontal or vertical)
        if(upgrade && (colDistance == 0 || rowDistance == 0)) {
            return colDistance == 0
                ? rowDistance == 1 || rowDistance == -1
                : colDistance == 1 || colDistance == -1;

        // Regular bishop movement
        } else {
            // If there is both a column and row change...
            return rowDistance != 0 && colDistance != 0
                // ...check if the target destination is along a diagonal.
                ? rowDistance / colDistance == -1 || rowDistance / colDistance == 1
                : false;
        }
    }

    public Piece promote()
    {
        upgrade = true;
        pieceName = "龍王";
        return this;
    }

    public Piece reset()
    {
        upgrade = false;
        pieceName = "角行";
        return this;
    }
}