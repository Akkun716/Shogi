/***
 * The shogi bishop moves in a similar fashion to the chess bishop, able to move diagonally
 * freely as long as there is no piece blocking its path. Once the piece is promoted, it gains
 * the option to move one square vertically or horizontally.
 ***/
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

    // Recall the locations are represented as a 2-digit integer with the 1st int
    // being the row and the secodn as the column.
    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        // Promoted bishop movement (regular + one space horizontal or vertical)
        if(upgrade && (colDist == 0 || rowDist == 0)) {
            return colDist == 0
                ? rowDist == 1 || rowDist == -1
                : colDist == 1 || colDist == -1;
        // Regular bishop movement
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