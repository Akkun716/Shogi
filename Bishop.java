public class Bishop extends Piece
{
    public Bishop(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "bishop";
        pieceName = "角行";
    }

    public Bishop()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Promoted bishop movement (regular bishop + one space diagonal) */
        if(upgrade && (colDist == 0 || rowDist == 0)) {
            return colDist == 0
                ? rowDist == 1 || rowDist == -1
                : colDist == 1 || colDist == -1;
        /* Regular bishop movement */
        } else {
            return rowDist != 0 && colDist != 0
                ? rowDist / colDist == -1 || rowDist / colDist == 1
                : false;
        }
    }

    public void promote()
    {
        upgrade = true;
        pieceName = "龍王";
    }

    public void reset()
    {
        upgrade = false;
        pieceName = "角行";
    }
}