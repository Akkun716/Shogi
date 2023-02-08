public class Interactive
{
    private final static UserInput USER = new UserInput();

    public static void main(String[] args)
    {
        int currRow = 4;
        int currCol = 4;
        int tarRow = 0;
        int tarCol = 0;
        Board board = new Board();
        if(USER.trueFalseInput("Fully set up board? [T]rue or [F]alse: ")) {
            board.createDefaultBoard();
        } else {
            if(USER.trueFalseInput("Default piece location? [T]rue or [F]alse: ")) {
            board.createPiece(
                USER.choiceInput("Select the piece you wish to place: ", board.validPieces()),
                currRow,
                currCol, 
                USER.trueFalseInput("Start as gote (attacking)? [T]rue or [F]alse: "));
            } else {
                board.createPiece(
                    USER.choiceInput("Select the piece you wish to place: ", board.validPieces()),
                    USER.intInput("Enter row number: ", 0, board.size()),
                    USER.intInput("Enter column number: ", 0, board.size()),
                    USER.trueFalseInput("Start as gote (attacking)? [T]rue or [F]alse: "));
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
                if(USER.trueFalseInput("Promote piece? [T]rue or [F]alse: ")) {
                    board.promote(currRow, currCol);
                    System.out.printf("%s", board.toString());
                }
            }
            tarRow = USER.intInput("Enter target row number: ", 0, board.size());
            tarCol = USER.intInput("Enter target column number: ", 0, board.size());    
            if(tarRow < 0 || tarCol < 0) { break; }
            if(board.movePiece(currRow, currCol, tarRow, tarCol)) {
                currRow = tarRow;
                currCol = tarCol;
            }
        }
    }
}