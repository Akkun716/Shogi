/***
 * Represents a board from the Japanese game of Shogi.
 * 
 * The board holds all the pieces in a 2D array and the class contains methods 
 * to set, edit, or check the pieces on the board.
 ***/
public class Board
{
    private final String[] PIECES =
        {"pawn", "lance", "knight", "silver", "gold", "bishop", "rook", "king"};
    // Represents the maximum number of spaces in a row or column.
    private final int BOARD_MAX = 9;
    private Piece board[][];
    // Serves as the graveyard for pieces each player captured.
    private Player player1, player2;

    public Board()
    {
        board = new Piece[BOARD_MAX][BOARD_MAX];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                board[row][col] = null;
            }
        }
        player1 = new Player(1);
        player2 = new Player(-1);
    }

    /**
     * Returns the number of squares in a row and column of the board.
     * 
     * @return The number of squares in the rows and columns.
     */
    public int size()
    { 
        return board.length; 
    }

    /**
     * Returns an array of the names of valid pieces in the game of Shogi.
     * 
     * @return A string array of the names of usable pieces within Shogi.
     */
    public String[] validPieces()
    { 
        return PIECES; 
    }

    /** 
     * Removes the piece on the board at the specified location.
     * 
     * @param row int representing row val of piece.
     * @param col int representing col val of piece.
     */
    public void removePiece(int row, int col)
    { board[row][col] = null; }
   
    /**
     * Clears the board of pieces in the specified row.
     * 
     * @param row int representing row to clear.
     */
    public void clearRow(int row)
    {
        for(int col = 0; col < board[0].length; col++) {
            removePiece(row, col);
        }
    }

    /**
     * Clears the board of pieces in the specified column.
     * 
     * @param col int representing column to clear.
     */
    public void clearCol(int col)
    {
        for(int row = 0; row < board.length; row++) {
            removePiece(row, col);
        }
    }

    /**
     * Clears the board of all pieces.
     */
    public void clearBoard()
    {
        for(int row = 0; row < board.length; row++) {
            clearRow(row);
        }
    }

    /** 
     * Creates the specified piece on the board at the specified location.
     * 
     * @param row int representing row val of piece to place.
     * @param col int representing col val of piece to place.
     * @param piece the piece type to be placed.
     * @param gote boolean representing the "defending" player pieces (moves 
     * top to bottom).
     */
    public void createPiece(String piece, int row, int col, boolean gote)
    {
        switch(piece) {
            case "lance":
                board[row][col] = new Lance(gote ? -1 : 1);
                break;
            case "knight":
                board[row][col] = new Knight(gote ? -1 : 1);
                break;
            case "silver":
                board[row][col] = new SilverG(gote ? -1 : 1);
                break;
            case "gold":
                board[row][col] = new GoldG(gote ? -1 : 1);
                break;
            case "bishop":
                board[row][col] = new Bishop(gote ? -1 : 1);
                break;
            case "rook":
                board[row][col] = new Rook(gote ? -1 : 1);
                break;
            case "king":
                board[row][col] = new King(gote ? -1 : 1);
                break;
            default:
                board[row][col] = new Pawn(gote ? -1 : 1);
        }
    }

    /**
     * Set all tiles the specified row with specified pieces.
     * 
     * @param row int representing row to clear.
     * @param piece String of the name of the piece to be populated within the 
     * row.
     * @param gote boolean representing the "defending" player pieces (moves 
     * top to bottom).
     */
    public void createRow(String piece, int row, boolean gote)
    {
        for(int col = 0; col < board[0].length; col++) {
            createPiece(piece, row, col, gote);
        }
    }

    /**
     * Set all tiles in the specified column with specified pieces.
     * 
     * @param col int representing column to populate.
     * @param piece String of the name of the piece to be populated within the 
     * column.
     * @param gote boolean representing the "defending" player pieces (moves 
     * top to bottom).
     */
    public void createCol(String piece, int col, boolean gote)
    {
        for(int row = 0; row < board.length; row++) {
            createPiece(piece, row, col, gote);
        }
    }

    /**
     * Set the row as initially set in a typical shogi game.
     * 
     * @param row int representing the row to set.
     * @param gote boolean representing the "defending" player pieces (moves 
     * top to bottom).
     */
    public void createDefaultKingRow(int row, boolean gote)
    {
        createPiece("lance", row, 0, gote);
        createPiece("lance", row, 8, gote);
        createPiece("knight", row, 1, gote);
        createPiece("knight", row, 7, gote);
        createPiece("silver", row, 2, gote);
        createPiece("silver", row, 6, gote);
        createPiece("gold", row, 3, gote);
        createPiece("gold", row, 5, gote);
        createPiece("king", row, 4, gote);
    }

    /** 
     * Sets the specified piece on the board at the specified location.
     * 
     * @param currRow int representing original row of piece to be set.
     * @param currCol int representing original col of piece to be set.
     * @param tarRow int representing target row of piece to be set.
     * @param tarCol int representing target col of piece to be set.
     */
    public void setPiece(int currRow, int currCol, int tarRow, int tarCol)
    {
        board[tarRow][tarCol] = board[currRow][currCol];
        board[currRow][currCol] = null;
    }

    /**
     * 
     * @param player
     * @param tarRow
     * @param tarCol
     */
    public void takePiece(Player player, int tarRow, int tarCol) {
        if(board[tarRow][tarCol] != null) {
            player.addPiece(board[tarRow][tarCol].reset());
        }
    }

    /**
     * Set the target row to all tiles the current row.
     * 
     * @param currRow int representing the curr row to be set.
     * @param tarRow int representing the target row to set.
     */
    public void setRow(int currRow, int tarRow)
    {
        for(int col = 0; col < board[0].length; col++) {
            setPiece(currRow, col, tarRow, col);
        }
    }

    /**
     * Retrieves the piece at a given location.
     * 
     * @param row int representing the row of the piece.
     * @param col int representing the column of the piece.
     * @return The piece at the specified location, or null if the location is 
     * empty.
     */
    public Piece getPiece(int row, int col)
    { return board[row][col]; }
    
    /**
     * Set the board in the default layout of a shogi game.
     */
    public void createDefaultBoard()
    {
        createDefaultKingRow(0, true);
        createPiece("rook", 1, 1, true);
        createPiece("bishop", 1, 7, true);
        createRow("pawn", 2, true);
        createRow("pawm", 6, false);
        createPiece("bishop", 7, 1, false);
        createPiece("rook", 7, 7, false);
        createDefaultKingRow(8, false);
    }

    /**
     * Checks if the specified location is within the bounds of the board.
     * 
     * @param row int representing the row to check.
     * @param col int representing the column to check.
     * @return int indicating confirmation status: 0 for friendly obstruction,
     * 1 for clear, 2-digit number for a premature opposing piece location.
     */
    private int locationCheck(int row, int col, int direction, boolean ignore) {
        if(board[row][col] != null) {
            // If the checked piece is on the same "side"...
            if(board[row][col].getDirection() == direction)
            { return 0; }
            // Else if an opposing piece should NOT be ignored...
            else if(!ignore)
            { return row * 10 + col; }
        }

        return 1;
    }

    /**
     * Check if the path provided is not interrupted from friendly and opposing
     * pieces.
     * 
     * @param currLocation int representing the current location of the piece.
     * @param targetLocation int representing the target location of the piece.
     * @return int indicating path status: 0 for friendly obstruction, 1 for
     * clear, 2-digit number for a premature opposing piece obstruction.
     */
    public int isValid(int currLocation, int targetLocation)
    {
        // Recall the locations are represented as a 2-digit integer with the
        // 1st int being the row and the second as the column.
        Piece currPiece = board[currLocation / 10][currLocation % 10];
        int direction = currPiece.getDirection();
        int outputVal = 0;

        // If the target location is a vaild move for the piece...
        if(currPiece.isValid(currLocation, targetLocation)) {
            int currRow =  currLocation / 10;
            int currCol =  currLocation % 10;
            int tarRow = targetLocation / 10;
            int tarCol = targetLocation % 10;

            // Choose move direction based on the target location.
            int rowMove;
            if(tarRow == currRow)
            { rowMove = 0; }
            else 
            { rowMove = tarRow > currRow ? 1 : -1; }

            int colMove;
            if(tarCol == currCol)
            { colMove = 0; }
            else
            { colMove = tarCol > currCol ? 1 : -1; }

            switch(currPiece.pieceType()) {
                case "lance":
                    // Check the spaces "in front" of the lance.
                    for(int i = currRow; i <= tarRow; i+=direction) {
                        outputVal = locationCheck(i, tarCol, direction, i == tarRow);
                        if(outputVal != 1)
                        { return outputVal; }
                    }
                    break;

                case "bishop":                    
                    // If the bishop is promoted and there is no column or row
                    // change...
                    if(currPiece.isPromoted() && (colMove == 0 || rowMove == 0))
                    { return locationCheck(tarRow, tarCol, direction, true); }

                    // Regular bishop movement: Start check from the next space.
                    for(int i = currRow + rowMove, j = currCol + colMove;
                            // Check if the bishop is moving "down" or "up"
                            rowMove == 1 ? i <= tarRow : i >= tarRow
                            ; i+=rowMove, j+=colMove) {
                        outputVal = locationCheck(i, j, direction, i == tarRow);
                        if(outputVal != 1)
                        { return outputVal; }
                    }
                    break;

                case "rook":
                    // If the rook is promoted and there is both a column 
                    // AND row change...
                    if(currPiece.isPromoted() && (colMove != 0 && rowMove != 0))
                    { return locationCheck(tarRow, tarCol, direction, true); }

                    // If there is no row change...
                    if(currRow == tarRow) {
                        // ...check the horizontal spaces to traverse.
                        for(int j = currCol + colMove;
                                // Check if the rook is moving "right" or "left"
                                colMove == 1 ? j <= tarCol : j >= tarCol;
                                j+=colMove) {
                            outputVal = locationCheck(tarRow, j, direction, j == tarCol);
                            if(outputVal != 1)
                            { return outputVal; }
                        }

                    // Else, check the vertical spaces to traverse.
                    } else {
                        for(int i = currRow + rowMove;
                                // Check if the rook is moving "down" or "up"
                                rowMove == 1 ? i <= tarRow : i >= tarRow;
                                i+=rowMove) {
                            outputVal = locationCheck(i, tarCol, direction, i == tarRow);
                            if(outputVal != 1)
                            { return outputVal; }
                        }
                    }
                    break;

                default:
                    outputVal = locationCheck(tarRow, tarCol, direction, true);
            }
            return outputVal;
        }

        return 0;
    }

    /**
     * Moves the piece from the specified current location to the target
     * location (if valid).
     * 
     * @param currRow int representing the current row of the piece.
     * @param currCol int representing the current column of the piece.
     * @param tarRow int representing the target row of the piece.
     * @param tarCol int representing the target column of the piece.
     * @return int indicating move status: 0 for failed, 1 for success,
     * and a 2-digit int for a premature opposing piece obstruction.
     */
    public int movePiece(int currRow, int currCol, int tarRow, int tarCol)
    {
        int valid = isValid(currRow * 10 + currCol, tarRow * 10 + tarCol);
        switch(valid) {
            case 0:
                System.out.println("That move is not valid! ");
                break;
            case 1:
                // Identify which direction the piece is moving and 
                Player currPlayer =
                    board[currRow][currCol].getDirection() == 1 ? player1 : player2;
                takePiece(currPlayer, tarRow, tarCol);
                setPiece(currRow, currCol, tarRow, tarCol);
                break;
        }

        return valid;
    }

    /**
     * Promote the piece at the given location (if valid).
     * 
     * @param row
     * @param col
     */
    public void promote(int row, int col)
    {
        if(board[row][col] != null)
        { board[row][col].promote(); }
    }

    /**
     * Returns a string representation of the board, including the pieces on 
     * the board and the captured pieces for both players.
     *
     * @return String format of the board.
     */
    public String printAll()
    {
        StringBuilder output = new StringBuilder();
        output.append(player2.toString());
        output.append("\n");
        output.append(toString());
        output.append(player1.toString());
        return output.toString();
        
    }

    /**
     * Returns a string representation of the board, including the pieces on 
     * the board.
     * 
     * Each row is represented with its row number, and each column is 
     * represented with its column number. Pieces belonging to the "attacking" 
     * player are enclosed in "-", and pieces belonging to the 
     * "defending" player are enclosed in "=".
     *
     * @return A string representation of the board.
     */
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for(int row = 0; row < board.length; row++) {
            // Row number marker.
            output.append(row);
            output.append(" ");
            for(int col = 0; col < board.length; col++) {
                output.append("[");
                // Empty board space.
                if(board[row][col] == null) {
                    output.append(" 　　 ");
                   
                // Existing piece.
                } else {
                    // Defending piece.
                    if(board[row][col].getDirection() == -1) {
                        output.append("=");
                        output.append(board[row][col]);
                        output.append("=");

                    // Attacking piece.
                    } else {
                        output.append("-");
                        output.append(board[row][col]);
                        output.append("-");
                    }
                }
                output.append("]");
            }
            output.append("\n");
        }

        // Column marker row.
        output.append("   ");
        for(int col = 0; col < board.length; col++) {
            output.append(" 　");
            output.append(col);
            output.append("  　");
        }
        output.append("\n");
        return output.toString();
    }
}