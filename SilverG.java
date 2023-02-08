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
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Promoted silver movement (look to GoldG class for description) */
        if(upgrade) {
            if(colDist == direction || colDist == direction * -1) {
                return rowDist == 0 || rowDist == direction;
            } else {
                return rowDist == direction || rowDist == direction * -1;
            }
        /* Regular silver general movement */
        } else {
            if(colDist == direction || colDist == direction * -1) {
                return rowDist == -1 || rowDist == 1;
            } else {
                return rowDist == direction;
            } 
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