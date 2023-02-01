public class Knight extends Piece
{
    public Knight(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "knight";
        pieceName = "桂馬";
    }

    public Knight()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Promoted knight movement (look to GoldG class for description) */
        if(upgrade) {
            if(colDist == direction || colDist == direction * -1) {
                return rowDist == 0 || rowDist == direction;
            } else {
                return rowDist == direction || rowDist == direction * -1;
            }
        /* Regular knight movement */
        } else {
            if(colDist == -1 || colDist == 1) {
                return direction == -1
                    ? rowDist == -2
                    : rowDist == 2;
            } else {
                return false;
            }
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceName = "金　";
    }
}