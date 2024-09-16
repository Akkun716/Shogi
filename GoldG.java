/***
 * The gold piece is unique to shogi with a distinct movement style. This piece can move
 * one space vertically, horizontally, and the diagonals in the direction it is facing.
 * This is to say it can't move to the "backward" diagonals. This is a piece that can NOT
 * be promoted.
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

        /* Regular gold general movement:
         * 1) First checks if column changes to determine if horizontal movement included.
         *    Then checks target row value
         * 2) Otherwise, check if target row is one above or below gold general */
        if(colDist == direction || colDist == direction * -1) {
            return rowDist == 0 || rowDist == direction;
        } else {
            return rowDist == direction || rowDist == direction * -1;
        }
    }

    public void promote()
    {}

    public void reset()
    {}
}