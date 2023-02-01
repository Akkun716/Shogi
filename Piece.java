public abstract class Piece
{
    protected int direction;
    protected boolean upgrade;
    protected String pieceType;
    protected String pieceName;

    public abstract boolean isValid(int currLocation, int targetLocation);
    public abstract void promote();

    public String pieceType()
    { return pieceType; }

    public String toString()
    { return pieceName; }

    public int getDirection()
    { return direction; }
}