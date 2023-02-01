public class GoldG extends Piece {
    public GoldG(int direction)
    {
        this.direction = direction;
        upgrade = true;
        pieceName = "金将";
    }

    public GoldG()
    { this(1); }

    public boolean isValid(int currLocation, int targetLocation)
    {
        int rowDist = currLocation / 10 - targetLocation / 10;
        int colDist = currLocation % 10 - targetLocation % 10;

        /* Regular gold general movement:
         * 1) First checks if column changes to determine if horizontal movement included. Then checks target row value
         * 2) Otherwise, check if target row is one above or below gold general
         */
        if(colDist == direction || colDist == direction * -1) {
            return rowDist == 0 || rowDist == direction;
        } else {
            return rowDist == direction || rowDist == direction * -1;
        }
    }

    public void promote()
    {}
}