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
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Promoted lance movement (look to GoldG class for description) */
        if(upgrade) {
            if(colDist == direction || colDist == direction * -1) {
                return rowDist == 0 || rowDist == direction;
            } else {
                return rowDist == direction || rowDist == direction * -1;
            }
        /* Regular lance movement */
        } else {
            if(colDist == 0 && rowDist != 0) {
                return direction == 1
                    ? rowDist > 0
                    : rowDist < 0;
            } else {
                return false;
            }
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceName = "杏　";
    }
}