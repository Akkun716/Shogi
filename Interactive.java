public class Interactive
{
    private final static UserInput USER = new UserInput();

    public static void main(String[] args)
    {
        int output = 0;
        int currRow = 4;
        int currCol = 4;
        int tarRow = 0;
        int tarCol = 0;
        Board board = new Board();
        if(USER.booleanInput("Fully set up board?", "True", "False")) {
            board.createDefaultBoard();
        } else {
            if(USER.booleanInput("Default piece location?", "True", "False")) {
            board.createPiece(
                USER.choiceInput("Select the piece you wish to place: ", board.validPieces()),
                currRow,
                currCol, 
                USER.booleanInput("Start as gote (attacking)?", "True", "False"));
            } else {
                board.createPiece(
                    USER.choiceInput("Select the piece you wish to place: ", board.validPieces()),
                    USER.intInput("Enter row number: ", 0, board.size()),
                    USER.intInput("Enter column number: ", 0, board.size()),
                    USER.booleanInput("Start as gote (attacking)?", "True", "False"));
            }
        }

        while(tarRow >= 0 && tarCol >= 0) {
            System.out.printf("%s", board.toString());
            currRow = USER.intInput("Select piece row number: ", 0, board.size());
            currCol = USER.intInput("Select piece column number: ", 0, board.size());
            if(currRow < 0 || currCol < 0) { break; }
            if(board.getPiece(currRow, currCol) == null) {
                System.out.printf("There is no piece located there...\n");
                continue;
            }
            if(!board.getPiece(currRow, currCol).isPromoted()) {
                if(USER.booleanInput("Promote piece?", "True", "False")) {
                    board.promote(currRow, currCol);
                    System.out.printf("%s", board.toString());
                }
            }
            tarRow = USER.intInput("Enter target row number: ", 0, board.size());
            tarCol = USER.intInput("Enter target column number: ", 0, board.size());    
            if(tarRow < 0 || tarCol < 0) { break; }

            output = board.movePiece(currRow, currCol, tarRow, tarCol);
            if(output > 0) {
                currRow = tarRow;
                currCol = tarCol;
            }
        }
    }
}