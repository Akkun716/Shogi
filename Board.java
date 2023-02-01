import java.util.ArrayList;

import javax.xml.namespace.QName;

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
    
    public void setDefaultBoard()
    {
        createPiece("lance", 0, 0, true);
        createPiece("lance", 8, 0, true);
        createRow("pawn", 2, true);
        createRow("pawm", 6, false);
    }

    public boolean isValid(int currLocation, int targetLocation)
    {
        Piece currPiece = board[currLocation / 10][currLocation % 10];
        if(currPiece.isValid(currLocation, targetLocation)) {
            System.out.printf("The location is valid!\n");
            int currRow = currLocation / 10 > targetLocation / 10 ? targetLocation / 10 : currLocation / 10;
            int currCol = currLocation % 10 > targetLocation % 10 ? targetLocation % 10 : currLocation % 10;
            int tarRow = currRow == currLocation / 10 ? targetLocation / 10 : currLocation / 10;
            int tarCol = currCol == currLocation % 10 ? targetLocation % 10 : currLocation % 10;

            switch(currPiece.pieceType()) {
                case "lance":
                    for(int i = currRow; i <= tarRow; i++) {
                        if(board[i][tarCol] != null && board[i][tarCol] != currPiece) {
                            if(board[i][tarCol].getDirection() == currPiece.getDirection()) { return false; }
                        }
                    }
                    break;
                case "bishop":
                    break;
                case "rook":
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
                output.append("[ ");
                if(board[row][col] == null) {
                    output.append("　　");
                } else {
                    output.append(board[row][col]);
                }
                output.append(" ]");
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