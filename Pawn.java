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
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Promoted pawn movement (look to GoldG class for description) */
        if(upgrade) {
            if(colDist == direction || colDist == direction * -1) {
                return rowDist == 0 || rowDist == direction;
            } else {
                return rowDist == direction || rowDist == direction * -1;
            }
        /* Regular pawn movement */
        } else {
            return direction == rowDist && colDist == 0;
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceType = "gold";
        pieceName = "と　";
    }

    public void reset()
    {
        upgrade = false;
        pieceType = "pawn";
        pieceName = "歩兵";
    }
}