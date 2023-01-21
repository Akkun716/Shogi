public class Tile
{
    /* Stored as a two digit number: tens place = row; ones place = column */
    private int location;
    private Piece piece;
    
    public Tile(int location)
    {
        this.location = location;
        this.piece = null;
    }
    
    public Tile(int location, Piece piece)
    {
        this.piece = piece;
        this.location = location;
    }

    public int getLocation()
    { return location; }

    public Piece getPiece()
    { return piece; }

    public void setPiece(Piece piece)
    { this.piece = piece; }

    public void clear()
    { piece = null; }

    public int isOccupied(Piece checkPiece)
    {
        if(checkPiece == null) {
            return piece == null
                ? 0
                : 1;
        } else {
            return piece == null
                ? 0
                : piece.getTeam() == checkPiece.getTeam()
                    ? -1
                    : 1;
        }
    }

    public String toString()
    {
        return piece == null
            ? "[      ]"
            : "[ " + piece + " ]";
    }
}