
public class Interactive
{
    // This is the main terminal interface for the user to interact with the program.
    private final static UserInput USER = new UserInput();

    /**
     * Sets a piece on the board at the specified location.
     * 
     * @param board The board on which to set the piece.
     * @param autoSet If true, use the provided row and column; otherwise,
     * prompt the user for the location.
     * @param setRow The row where the piece should be set if autoSet is true.
     * @param setCol The column where the piece should be set if autoSet is true.
     */
    private void setPiece(Board board, boolean autoSet, int setRow, int setCol) {
        // If the piece it to be automatically set on the board...
        if(autoSet) {
            board.createPiece(
                USER.choiceInput("Select the piece you wish to place: ", board.validPieces()),
                setRow,
                setCol, 
                USER.booleanInput("Set as gote (defending)?", "True", "False"));

        // Else, the location must also be set.
        } else {
            board.createPiece(
                USER.choiceInput("Select the piece you wish to place: ", board.validPieces()),
                USER.intInput("Enter row number: ", 0, board.size()),
                USER.intInput("Enter column number: ", 0, board.size()),
                USER.booleanInput("Set as gote (defending)?", "True", "False"));
        }
    }

    /**
     * Starts the terminal Shogi interaction.
     */
    public void start()
    {
        int output = 0;
        int currRow = 4;
        int currCol = 4;
        int tarRow = 0;
        int tarCol = 0;
        Board board = new Board();

        // Auto setup for default board.
        if(USER.booleanInput("Fully set up board?", "True", "False")) {
            board.createDefaultBoard();

        // Manually set pieces.
        } else {
            System.out.printf("\n%s\n", board);
            setPiece(
                board,
                USER.booleanInput("Default piece location (4,4)?", "True", "False"),
                currRow,
                currCol);
            
            while(USER.booleanInput("Set another piece?", "Yes", "No")) {
                System.out.printf("\n%s\n", board);
                setPiece(board, false, 0, 0);
            }
        }

        // Select and move piece loop.
        while(true) {
            System.out.printf("%s", board.printAll());
            currRow = USER.intInput("Select piece row number: ", 0, board.size());
            if(currRow < 0)
            { break; }
            
            currCol = USER.intInput("Select piece column number: ", 0, board.size());
            if(currCol < 0)
            { break; }

            if(board.getPiece(currRow, currCol) == null) {
                System.out.printf("There is no piece located there...\n");
                continue;
            }
            if(!board.getPiece(currRow, currCol).isPromoted()) {
                if(USER.booleanInput("Promote piece?", "True", "False")) {
                    board.promote(currRow, currCol);
                    System.out.printf("%s", board.printAll());
                }
            }

            tarRow = USER.intInput("Enter target row number: ", 0, board.size());
            if(tarRow < 0)
            { break; }

            tarCol = USER.intInput("Enter target column number: ", 0, board.size());    
            if(tarCol < 0)
            { break; }

            output = board.movePiece(currRow, currCol, tarRow, tarCol);
            // Check if the piece prematurely collides with an opposing piece.
            if(output > 0) {
                if(output != 1) {
                    System.out.printf("Opposing piece found at (%d,%d).\n",
                        output / 10, output % 10);
                    if(USER.booleanInput("Capture piece?", "Yes", "No"))
                    {
                        tarRow = output / 10;
                        tarCol = output % 10;
                        board.movePiece(currRow, currCol, tarRow, tarCol);
                    }
                }

                currRow = tarRow;
                currCol = tarCol;
            }
        }
    }
}