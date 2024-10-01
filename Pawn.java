/***
 * Represents a pawn piece from the Japanese game of Shogi.
 * 
 * The shogi pawn is the same as the chess pawn except for two things. This
 * piece captures the piece in front of it, not to its forward diagonal. Along
 * with that, it does not have an optional starting movement of two spaces. It
 * always only moves one space at a time.
 * - When promoted, this piece turns into a gold general piece.
 ***/
public class Pawn extends Piece
{
    public Pawn(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "pawn";
        pieceName = "歩兵";
    }

    public Pawn()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        // Promoted pawn movement (look to GoldG class for description).
        if(upgrade) {
            return colDistance == 1 || colDistance == -1
                ? rowDistance == 0 || rowDistance == direction
                : rowDistance == 1 || rowDistance == -1;
        // Regular pawn movement
        } else {
            // Check if the pawn moves only one space forward and not to the side.
            return direction == rowDistance && colDistance == 0;
        }
    }

    public Piece promote()
    {
        upgrade = true;
        pieceType = "gold";
        pieceName = "と　";
        return this;
    }

    public Piece reset()
    {
        upgrade = false;
        pieceType = "pawn";
        pieceName = "歩兵";
        return this;
    }
}