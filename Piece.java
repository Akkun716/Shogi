public abstract class Piece
{
    // Represent the direction and implicity the team the piece is on.
    protected int direction;
    // Indicates whether the piece is currently promoted or "upgraded".
    protected boolean upgrade;
    // Holds what type of piece the object represents.
    protected String pieceType;
    // The string to be displayed in print statements.
    protected String pieceName;

    /**
     * Checks if the specified location is within the valid paths of the piece.
     *
     * @param currLocation A 2-digit integer representing the current row and the current
     *  col (respectively).
     * @param targetLocation A 2-digit integer representing the target row and target col
     *  (respectively).
     * @return boolean declaring if path is obstructed.
     */
    public abstract boolean isValid(int currLocation, int targetLocation);
    
    /**
     * Updates the name of the piece and enables horizontal and vertical movement of one space.
     */
    public abstract void promote();
    
    /**
     * The piece is returned to its original form (removes promotion).
     */
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