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
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        // Promoted lance movement (look to GoldG class for description).
        if(upgrade) {
            return colDist == 1 || colDist == -1
                ? rowDist == 0 || rowDist == direction
                : rowDist == 1 || rowDist == -1;

        // Regular lance movement
        } else {
            // If there is no column change, yet there is a row change...
            if(colDist == 0 && rowDist != 0) {
                // If the piece is meant to move up...
                return direction == 1
                    // ...check if the row change moves the piece up.
                    ? rowDist > 0
                    // Else, check if the row change moves the piece down.
                    : rowDist < 0;
            } else {
                return false;
            }
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceType = "gold";
        pieceName = "杏　";
    }

    public void reset()
    {
        upgrade = false;
        pieceType = "lance";
        pieceName = "香車";
    }
}