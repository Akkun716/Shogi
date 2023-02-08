public abstract class Piece
{
    protected int direction;
    protected boolean upgrade;
    protected String pieceType;
    protected String pieceName;

    public abstract boolean isValid(int currLocation, int targetLocation);
    public abstract void promote();
    public abstract void reset();

    public String pieceType()
    { return pieceType; }

    public String toString()
    { return pieceName; }

    public boolean isPromoted()
    { return upgrade; }

    public int getDirection()
    { return direction; }
}