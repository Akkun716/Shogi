/***
 * Represent a gold general piece from the Japanese game of Shogi.
 * 
 * The gold piece is unique to shogi with a distinct movement style. This piece
 * moves similar to the king piece: one space in every direction, EXCEPT the
 * "backward" diagonals.
 * - This piece does not have a promotion option.
 ***/
public class GoldG extends Piece
{
    public GoldG(int direction)
    {
        this.direction = direction;
        upgrade = true;
        pieceType = "gold";
        pieceName = "金将";
    }

    public GoldG()
    { this(1); }

    // Recall the locations are represented as a 2-digit integer with the 1st int
    // being the row and the second as the column.
    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        // Regular gold general movement:
        // If there is a column change (one space left or right)...
        return colDist == 1 || colDist == -1
            // ...check if the piece stays in the same row or moves one space
            // "forward"
            ? rowDist == 0 || rowDist == direction
            // Else, check if the piece moves one space "forward" or "backward"
            : rowDist == 1 || rowDist == -1;
    }

    public void promote()
    {}

    public void reset()
    {}
}