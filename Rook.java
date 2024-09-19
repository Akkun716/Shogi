/***
 * Represents a rook piece from the Japanese game of Shogi.
 * 
 * The shogi rook moves in a similar fashion to the chess rook, able to move
 * vertically and horizontally freely as long as there is no piece blocking its
 * path.
 * - When promoted, this piece gains the option to move one square
 *   diagonally instead of moving vertically or horizontally.
 ***/
public class Rook extends Piece
{
    public Rook(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "rook";
        pieceName = "飛車";
    }

    public Rook()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        /* Promoted rook movement (regular rook + one space vertical or horizontal) */
        if(upgrade && colDistance != 0 && rowDistance != 0) {
            return (colDistance == -1 || colDistance == 1) && (rowDistance == -1 || rowDistance == 1);
        /* Regular rook movement */
        } else {
            return colDistance == 0
                ? rowDistance != 0
                : rowDistance == 0;
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceName = "龍王";
    }

    public void reset()
    {
        upgrade = false;
        pieceName = "飛車";
    }
}