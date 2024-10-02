import java.util.LinkedList;

/***
 * Represents an individual player and pieces they have captured from the game.
 * 
 * The player has a selected direction and a collection of captured pieces from
 * their opponent. The player also has the option of using those pieces against
 * the opponent.
 ***/
public class Player {
    LinkedList<Piece> collection;
    int direction;

    public Player(int direction) {
        collection = new LinkedList<>();
        this.direction = direction;
    }

    public Player() {
        this(1);
    }

    /**
     * Adds a piece to the player's captured collection.
     * 
     * @param piece Piece object representing the captured piece.
     */
    public void addPiece(Piece piece) {
        for(int i = 0; i < collection.size(); i++) {
            if(collection.get(i).pieceType().equals(piece.pieceType())) {
                collection.add(i + 1, piece);
                return;
            }
        }

        collection.add(piece);
    }

    /**
     * Removes a piece from the Player's captured piece collection.
     * 
     * @param piece A string representing the piece that needs to be retrieved.
     * @return the Piece object to removed from the collection.
     */
    public Piece removePiece(String piece) {
        for(int i = 0; i < collection.size(); i++) {
            if(collection.get(i).pieceType().equals(piece)) {
                Piece output = collection.get(i);
                collection.remove(i);
                return output;
            }
        }

        return null;
    }

    /**
     * Returns a string representation of the player's captured pieces.
     * 
     * The format includes the player number and a list of captured pieces,
     * grouped by type and quantity.
     * @return A string representing the player's captured pieces.
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        String pieceType = "";

        output.append(String.format("=== Player %d Captured Pieces ===\n", direction == 1 ? 2 : 1));
        
        // Prints out the collection in a certain format.
        for(int i = 0; i < collection.size(); i++) {
            // If the piece type is not the same anymore...
            if(pieceType == collection.get(i).pieceType()) {
                counter++;

            // If the piece type is NOT the same...
            } else {
                // Prints out the currently built counter.
                if(counter > 1)
                { output.append(String.format(" x%d", counter)); }
                counter = 1;
                // Only prints comma after first element.
                if(i > 0)
                { output.append(", "); }

                pieceType = collection.get(i).pieceType();
                output.append(pieceType);
            }
        }
        if(counter > 1)
        { output.append(String.format(" x%d", counter)); }
        output.append("\n========\n");
        return output.toString();
    }
}
