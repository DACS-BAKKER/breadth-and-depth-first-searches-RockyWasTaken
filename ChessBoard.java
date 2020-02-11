//Author: Rocky Xia
public class ChessBoard {

    public int[][] board;
    public int next;

    /**
     * Empty space: 0
     * Visited: 1
     * Current: 2
     */
    public ChessBoard(int size, Coord start){
        this.next = 1;
        this.board = new int[size][size];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                if(row == start.row && col == start.col){
                    board[row][col] = 2;
                }
                else{
                    board[row][col] = 0;
                }
            }
        }
    }

    public ChessBoard(int[][] board){
        this.board = board;
        this.next = 1;
    }

    //Get current coordinates
    public Coord getCurrent(){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                if(board[row][col] == 2){
                    return new Coord(row, col);
                }
            }
        }
        return null;
    }

    //Generate next possible move
    public ChessBoard getNext(){
        ChessBoard chessBoard = new ChessBoard(copyBoard(board));
        int rowCurrent = chessBoard.getCurrent().row;
        int colCurrent = chessBoard.getCurrent().col;
        if(next == 1){
            next++;
            if(rowCurrent > 1 && colCurrent > 0){
                if(chessBoard.board[rowCurrent - 2][colCurrent - 1] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent - 2][colCurrent - 1] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 2){
            next++;
            if(rowCurrent > 1 && colCurrent < board.length-1){
                if(chessBoard.board[rowCurrent - 2][colCurrent + 1] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent - 2][colCurrent + 1] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 3){
            next++;
            if(rowCurrent > 0 && colCurrent < board.length-2){
                if(chessBoard.board[rowCurrent - 1][colCurrent + 2] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent - 1][colCurrent + 2] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 4){
            next++;
            if(rowCurrent < board.length-1 && colCurrent < board.length-2){
                if(chessBoard.board[rowCurrent + 1][colCurrent + 2] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent + 1][colCurrent + 2] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 5){
            next++;
            if(rowCurrent < board.length-2 && colCurrent < board.length-1){
                if(chessBoard.board[rowCurrent + 2][colCurrent + 1] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent + 2][colCurrent + 1] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 6){
            next++;
            if(rowCurrent < board.length-2 && colCurrent > 0){
                if(chessBoard.board[rowCurrent + 2][colCurrent - 1] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent + 2][colCurrent - 1] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 7){
            next++;
            if(rowCurrent < board.length-1 && colCurrent > 1){
                if(chessBoard.board[rowCurrent + 1][colCurrent - 2] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent + 1][colCurrent - 2] = 2;
                    return chessBoard;
                }
            }
        }
        else if(next == 8){
            next++;
            if(rowCurrent > 0 && colCurrent > 1){
                if(chessBoard.board[rowCurrent - 1][colCurrent - 2] == 0){
                    chessBoard.board[rowCurrent][colCurrent] = 1;
                    chessBoard.board[rowCurrent - 1][colCurrent - 2] = 2;
                    return chessBoard;
                }
            }
        }
        return new ChessBoard(nullBoard());
    }

    public int[][] nullBoard(){
        int[][] nullBoardReturn = new int[8][8];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                nullBoardReturn[row][col] = 3;
            }
        }
        return nullBoardReturn;
    }

    //Checks if a board is solved
    public boolean isSolved(){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                if(!(row == getCurrent().row && col == getCurrent().col) && board[row][col] != 1){
                    return false;
                }
            }
        }

        return true;
    }

    //Make a copy of the board to prevent pointer conflicts
    public int[][] copyBoard(int[][] board){
        int[][] copied = new int[board.length][board.length];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                copied[row][col] = board[row][col];
            }
        }
        return copied;
    }

    //String output
    public String toString(){
        String boardString = "";
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                boardString += board[row][col] + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }
}
