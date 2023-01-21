import java.util.Scanner;

public class Interactive {
    public static void main(String[] args)
    {
        Board board = new Board();
        board.setDefaultBoard();
        // board.setPiece(4, 4, "pawn", false);
        Scanner user = new Scanner(System.in);
        String input;
        // while((input = user.next("Enter target location (e.g. 12): ")).compareToIgnoreCase("exit") != 0) {
            
            System.out.print(board);
            // board.promote(4, 4);
            // board.movePiece(4, 4, 4, 3);
            // System.out.println(board);        
        // }
    }
}