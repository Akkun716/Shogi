import java.util.LinkedList;

public class Player {
    LinkedList<Piece> collection;
    int direction;

    public Player() {
        collection = new LinkedList<>();
        this.direction = 1;
    }

    public Player(int direction) {
        collection = new LinkedList<>();
        this.direction = direction;
    }

    public void addPiece(Piece piece) {
        for(int i = 0; i < collection.size(); i++) {
            if(collection.get(i).pieceType().equals(piece.pieceType())) {
                collection.add(i + 1, piece);
                return;
            }
        }

        collection.add(piece);
    }

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
