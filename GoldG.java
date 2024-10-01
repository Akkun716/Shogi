/***
 * Represent a gold general piece from the Japanese game of Shogi.
 * 
 * The gold general is unique to shogi with a distinct movement style. This piece
 * moves similar to the king piece: one space in every direction, EXCEPT the
 * "backward" diagonals. (NOTE: The silver and gold generals compliment each other,
 * covering moves the other can't make.)
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

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the 1st int
        // being the row and the second as the column.
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        // Regular gold general movement:
        // If there is a column change (one space left or right)...
        return colDistance == 1 || colDistance == -1
            // ...check if the piece stays in the same row or moves one space
            // "forward".
            ? rowDistance == 0 || rowDistance == direction
            // Else, check if the piece moves one space "forward" or "backward"
            : rowDistance == 1 || rowDistance == -1;
    }

    public Piece promote()
    { return this; }

    public Piece reset()
    { return this; }
}