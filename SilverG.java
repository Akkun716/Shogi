/***
 * Represent a silver general piece from the Japanese game of Shogi.
 * 
 * The silver general is unique to shogi with a distinct movement style. This piece
 * moves similar to the king piece: one space in every direction, EXCEPT directly to
 * the sides and directly backwards. (NOTE: The silver and gold generals compliment each other,
 * covering moves the other can't make.)
 * - When promoted, this piece turns into a gold general piece.
 ***/
public class SilverG extends Piece
{
    public SilverG(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "silver";
        pieceName = "銀将";
    }

    public SilverG()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        // Promoted silver movement (look to GoldG class for description)
        if(upgrade) {
            return colDistance == 1 || colDistance == -1
                ? rowDistance == 0 || rowDistance == direction
                : rowDistance == 1 || rowDistance == -1;
        // Regular silver general movement
        } else {
            // If there is a column change...
            return colDistance == 1 || colDistance == -1
                // ...check if there is also a row change.
                ? rowDistance == -1 || rowDistance == 1
                // Else, check if the piece moves one space "forward".
                : rowDistance == direction;
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
        pieceType = "silver";
        pieceName = "銀将";
    }
}