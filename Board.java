import java.util.ArrayList;

public class Board
{
    private final String[] PIECES = {"pawn", "lance", "knight", "silver", "gold", "bishop", "rook", "king"};
    private final int BOARD_MAX = 9;
    private Piece board[][];
    private ArrayList<Piece> player1, player2;

    public Board()
    {
        //board = new Tile[9][9];
        board = new Piece[BOARD_MAX][BOARD_MAX];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                //board[row][col] = new Tile(row * 10 + col);
                board[row][col] = null;
            }
        }
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
    }

    public int size()
    { return BOARD_MAX; }

    public String[] validPieces()
    { return PIECES; }

    /** 
     * Removes the piece on the board at the specified loaction
     * @param row int representing row val of piece
     * @param col int representing col val of piece
     */
    public void removePiece(int row, int col)
    {
        //board[row][col].clear();
        board[row][col] = null;
    }
   
    /**
     * Clears the board of pieces in the specified row
     * @param row int representing row to clear
     */
    public void clearRow(int row)
    {
        for(int col = 0; col < board[0].length; col++) {
            removePiece(row, col);
        }
    }

    /**
     * Clears the board of pieces in the specified column
     * @param col int representing column to clear
     */
    public void clearCol(int col)
    {
        for(int row = 0; row < board.length; row++) {
            removePiece(row, col);
        }
    }

    /**
     * Clears the board of all pieces
     */
    public void clearBoard()
    {
        for(int row = 0; row < board.length; row++) {
            clearRow(row);
        }
    }

    /** 
     * Creates the specified piece on the board at the specified loaction
     * @param row int representing row val of piece to place
     * @param col int representing col val of piece to place
     * @param piece the piece type to be placed
     * @param gote boolean represnting player with "white" pieces (moves Top to Bottom)
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
     * Set all tiles the specified row with specified pieces
     * @param row int representing row to clear
     * @param piece String of the name of the piece to be populated within the row
     * @param gote boolean represnting player with "white" pieces (moves Top to Bottom)
     */
    public void createRow(String piece, int row, boolean gote)
    {
        for(int col = 0; col < board[0].length; col++) {
            createPiece(piece, row, col, gote);
        }
    }

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
     * Sets the specified piece on the board at the specified loaction
     * @param currRow int representing original row of piece to be set
     * @param currCol int representing original col of piece to be set
     * @param tarRow int representing target row of piece to be set 
     * @param tarCol int representing target col of piece to be set
     */
    public void setPiece(int currRow, int currCol, int tarRow, int tarCol)
    {
        board[tarRow][tarCol] = board[currRow][currCol];
        board[currRow][currCol] = null;
    }

    /**
     * Set the target row to all tiles the current row
     * @param currRow int representing the curr row to be set
     * @param tarRow int representing the target row to set
     */
    public void setRow(int currRow, int tarRow)
    {
        for(int col = 0; col < board[0].length; col++) {
            setPiece(currRow, col, tarRow, col);
        }
    }

    public Piece getPiece(int row, int col)
    { return board[row][col]; }
    
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

    public boolean isValid(int currLocation, int targetLocation)
    {
        Piece currPiece = board[currLocation / 10][currLocation % 10];
        if(currPiece.isValid(currLocation, targetLocation)) {
            System.out.printf("The location is valid!\n");
            /* This "rearranges" the current and target rows and columns for simpler loop propogation */
            int currRow, currCol, tarRow, tarCol;
            if(currLocation / 10 > targetLocation / 10 || currLocation % 10 > targetLocation % 10) {
                currRow =  targetLocation / 10;
                currCol =  targetLocation % 10;
                tarRow = currLocation / 10;
                tarCol = currLocation % 10;
            } else {
                currRow =  currLocation / 10;
                currCol =  currLocation % 10;
                tarRow = targetLocation / 10;
                tarCol = targetLocation % 10;
            }

            switch(currPiece.pieceType()) {
                case "lance":
                    for(int i = currRow; i <= tarRow; i++) {
                        if(board[i][tarCol] != null && board[i][tarCol] != currPiece) {
                            if(board[i][tarCol].getDirection() == currPiece.getDirection()) { return false; }
                        }
                    }
                    break;
                case "bishop":
                    if(tarCol - currCol > 0 && tarRow - currRow > 0) {
                        for(int i = currRow, j = currCol; i <= tarRow; i++ , j++ ) {
                            if(board[i][j] != null && board[i][j] != currPiece) {
                                if(board[i][j].getDirection() == currPiece.getDirection()) { return false; }
                            }
                        }
                    } else {
                        for(int i = currRow, j = currCol; i >= tarRow; i-- , j++ ) {
                            if(board[i][j] != null && board[i][j] != currPiece) {
                                if(board[i][j].getDirection() == currPiece.getDirection()) { return false; }
                            }
                        }
                    }
                    break;
                case "rook":
                    if(currRow == tarRow) {
                        for(int i = currCol; i <= tarCol; i++) {
                            if(board[tarRow][i] != null && board[tarRow][i] != currPiece) {
                                if(board[tarRow][i].getDirection() == currPiece.getDirection()) { return false; }
                            }
                        }
                    } else {
                        for(int i = currRow; i <= tarRow; i++) {
                            if(board[i][tarCol] != null && board[i][tarCol] != currPiece) {
                                if(board[i][tarCol].getDirection() == currPiece.getDirection()) { return false; }
                            }
                        }
                    }
                    break;
                default:
                    tarRow = targetLocation / 10;
                    tarCol = targetLocation % 10;
                    if(board[tarRow][tarCol] != null) {
                        if(board[tarRow][tarCol].getDirection() == currPiece.getDirection()) { return false; }
                    } 
            }
            return true;
        }

        return false;
    }

    public boolean movePiece(int currRow, int currCol, int tarRow, int tarCol)
    {
        if(isValid(currRow * 10 + currCol, tarRow * 10 + tarCol)) {
            setPiece(currRow, currCol, tarRow, tarCol);
            return true;
        } else {
            System.out.println("That move is not valid! ");
        }
        return false;

    }

    public void promote(int row, int col)
    {
        if(board[row][col] != null) {
            board[row][col].promote();
        }
    }

    public String printAll()
    {
        StringBuilder output = new StringBuilder();
        output.append("===Top Graveyard===\n");
        output.append(player1);
        output.append("\n========\n");
        output.append(board.toString());
        output.append("   ");
        output.append("\n===Bottom Graveyard===\n");
        output.append(player2);
        output.append("\n========\n");
        return output.toString();
        
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for(int row = 0; row < board.length; row++) {
            /* Row number marker */
            output.append(row);
            output.append(" ");
            /* Tiles (and pieces if applicable) */
            // for(int col = 0; col < board.length; col++) {
            //     output.append(board[row][col]);
            // }
            for(int col = 0; col < board.length; col++) {
                output.append("[");
                if(board[row][col] == null) {
                    output.append(" 　　 ");
                } else {
                    if(board[row][col].getDirection() == -1) {
                        output.append("=");
                        output.append(board[row][col]);
                        output.append("=");
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