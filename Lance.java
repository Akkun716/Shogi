/***
 * Represents a lance piece from the Japanese game of Shogi.
 * 
 * The lance is a unique to shogi with a distinct movement style. This piece
 * is in essence a pawn that moves forward like a rook. It can only move
 * forward, but it can move forward unrestricted unless there is an obstacle in
 * its path.
 * - When promoted, this piece turns into a gold general piece.
 ***/
public class Lance extends Piece
{
    public Lance(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "lance";
        pieceName = "香車";
    }

    public Lance()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        int rowDistance = currLocation / 10 - targetLocation / 10;
        int colDistance = currLocation % 10 - targetLocation % 10;

        // Promoted lance movement (look to GoldG class for description).
        if(upgrade) {
            return colDistance == 1 || colDistance == -1
                ? rowDistance == 0 || rowDistance == direction
                : rowDistance == 1 || rowDistance == -1;

        // Regular lance movement
        } else {
            // If there is no column change, yet there is a row change...
            if(colDistance == 0 && rowDistance != 0) {
                // If the piece is meant to move up...
                return direction == 1
                    // ...check if the row change moves the piece up.
                    ? rowDistance > 0
                    // Else, check if the row change moves the piece down.
                    : rowDistance < 0;
            } else {
                return false;
            }
        }
    }

    public Piece promote()
    {
        upgrade = true;
        pieceType = "gold";
        pieceName = "杏　";
        return this;
    }

    public Piece reset()
    {
        upgrade = false;
        pieceType = "lance";
        pieceName = "香車";
        return this;
    }
}