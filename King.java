public class King extends Piece
{
    public King(int direction)
    {
        this.direction = direction;
        upgrade = true;
        pieceType = "king";
        if(direction == -1) {
            pieceName = "玉将";
        } else {
            pieceName = "王将";
        }
    }

    public King()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Regular king movement */
        return rowDist == 0 && colDist == 0
            ? false
            : colDist >= -1 && colDist <= 1 && rowDist >= -1 && rowDist <= 1;
    }

    public void promote()
    {}
    
    public void reset()
    {}
}