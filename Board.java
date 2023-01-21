import java.util.ArrayList;

public class Board
{
    private Tile board[][];
    private ArrayList<Piece> player1, player2;

    public Board()
    {
        board = new Tile[9][9];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                board[row][col] = new Tile(row * 10 + col);
            }
        }
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
    }

    /** 
     * Removes the piece on the board at the specified loaction
     * @param row int representing row val of piece
     * @param col int representing col val of piece
     */
    public void removePiece(int row, int col)
    { board[row][col].clear(); }
   
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
     * Sets the specified piece on the board at the specified loaction
     * @param row int representing row val of piece to place
     * @param col int representing col val of piece to place
     * @param piece the piece type to be placed
     * @param gote boolean represnting player with "white" pieces (moves Top to Bottom)
     */
    public void setPiece(int row, int col, String piece, boolean gote)
    {
        switch(piece) {
            case "pawn":
                board[row][col].setPiece(new Pawn(gote ? -1 : 1));
                break;
        }
    }

    /**
     * Set all tiles the specified row with specified pieces
     * @param row int representing row to clear
     * @param piece String of the name of the piece to be populated within the row
     * @param gote boolean represnting player with "white" pieces (moves Top to Bottom)
     */
    public void setRow(int row, String piece, boolean gote)
    {
        for(int col = 0; col < board[0].length; col++) {
            setPiece(row, col, piece, gote);
        }
    }
    
    public void setDefaultBoard()
    {
        setRow(2, "pawn", true);
        setRow(6, "pawn", false);
    }

    public boolean isValid(Tile curr, Tile target)
    { return curr.getPiece().isValid(curr, target); }

    public boolean movePiece(int currRow, int currCol, int tarRow, int tarCol)
    {
        if(isValid(board[currRow][currCol], board[tarRow][tarCol])) {
            board[tarRow][tarCol].setPiece(board[currRow][currCol].getPiece());
            board[currRow][currCol].clear();
            return true;
        } else {
            System.out.println("That move is not valid! ");
        }
        return false;
    }

    public void promote(int row, int col)
    {
        if(board[row][col].isOccupied(null) != 0) {
            board[row][col].getPiece().promote();
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
            output.append(row);
            output.append(" ");
            for(int col = 0; col < board.length; col++) {
                output.append(board[row][col]);
            }
            output.append("\n");
        }

        output.append("   ");
        for(int col = 0; col < board.length; col++) {
            output.append("   ");
            output.append(col);
            output.append("    ");
        }
        output.append("\n");
        return output.toString();
    }
}