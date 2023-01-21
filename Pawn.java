public class Pawn extends Piece
{
    public Pawn(int direction)
    {
        this.direction = direction;
        upgrade = false;
        pieceName = "歩兵";
    }

    public Pawn()
    { this(1); }

    public boolean isValid(Tile curr, Tile target)
    {
        if(target.getLocation() > 100 || target.getLocation() < 0) {
            return false;
        }

        int rowDist = curr.getLocation() / 10 - target.getLocation() / 10;
        int colDist = curr.getLocation() % 10 - target.getLocation() % 10;

        /* Promoted pawn movement */
        if(upgrade) {
            /* Checks if column changes first to determine simply vertical or horizontal movement included */
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
        pieceName = " と ";
    }
}