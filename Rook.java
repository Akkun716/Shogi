public class Rook extends Piece
{
    public Rook(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceType = "rook";
        pieceName = "飛車";
    }

    public Rook()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Promoted rook movement (regular rook + one space vertical or horizontal) */
        if(upgrade && colDist != 0 && rowDist != 0) {
            return (colDist == -1 || colDist == 1) && (rowDist == -1 || rowDist == 1);
        /* Regular rook movement */
        } else {
            return colDist == 0
                ? rowDist != 0
                : rowDist == 0;
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
        pieceName = "飛車";
    }
}