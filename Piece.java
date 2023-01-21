public abstract class Piece
{
    protected int direction;
    protected boolean upgrade;
    protected String pieceName;

    public abstract boolean isValid(Tile curr, Tile target);
    public abstract void promote();

    public String toString()
    { return pieceName; }

    public int getTeam()
    {
        return direction == 1
            ? 1
            : 2;
    }
}