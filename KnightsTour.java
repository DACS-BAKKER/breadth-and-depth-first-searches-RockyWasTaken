import edu.princeton.cs.algs4.StdIn;

//Author: Rocky Xia
public class KnightsTour {

    public static ChessBoard board;
    public static Stack stackSolver;
    public static Queue queueSolver;

    //Console UI
    public static void main(String[] args){
        System.out.println("Knight's Tour Solver");
        System.out.println("Enter board size: ");
        int inputSize = StdIn.readInt();
        System.out.println("Enter starting row: ");
        int inputRow = StdIn.readInt();
        System.out.println("Enter starting col: ");
        int inputCol = StdIn.readInt();
        setUp(inputSize, new Coord(inputRow, inputCol));
        System.out.println("Select search");
        System.out.println("1) Stack");
        System.out.println("2) Queue");
        int inputSolve = StdIn.readInt();
        System.out.println();
        if(inputSolve == 1){
            solveStack();
        }
        else if(inputSolve == 2){
            solveQueue();
        }
    }

    //Set up initial board, stack, and queue
    public static void setUp(int size, Coord start){
        board = new ChessBoard(size, start);
        stackSolver = new Stack();
        queueSolver = new Queue();
    }

    //Solve the problem with a stack
    public static void solveStack(){
        stackSolver.push(new StorageNode(new KTNode(board, new KTNode(new ChessBoard(board.nullBoard()), null))));
        while(!stackSolver.top.ktNode.chessBoard.isSolved()){
            if(stackSolver.top.ktNode.chessBoard.next <= 8){
                ChessBoard temp = stackSolver.top.ktNode.chessBoard.getNext();
                if(!isNullBoard(temp)){
                    stackSolver.push(new StorageNode(new KTNode(temp, stackSolver.top.ktNode)));
                }
            }
            else{
                stackSolver.pop();
            }
        }

        if(stackSolver.top.ktNode.chessBoard.isSolved()){
            System.out.println("Solution: \n");
            KTNode temp = stackSolver.pop().ktNode;
            Stack solution = new Stack();

            while(!isNullBoard(temp.chessBoard)){
                solution.push(new StorageNode(temp));
                temp = temp.previous;
            }

            while(!solution.isEmpty()){
                System.out.println(solution.pop().ktNode.chessBoard);
            }
        }
        else if(stackSolver.isEmpty()){
            System.out.println("No solution");
        }
    }

    //Solves the problem with a queue
    public static void solveQueue(){
        queueSolver.enqueue(new StorageNode(new KTNode(board, new KTNode(new ChessBoard(board.nullBoard()), null))));
        StorageNode tempNode = queueSolver.dequeue();
        for(int i = 0; i < 8; i++){
            ChessBoard tempBoard = tempNode.ktNode.chessBoard;
            if(!isNullBoard(tempBoard)){
                queueSolver.enqueue(new StorageNode(new KTNode(tempBoard, tempNode.ktNode)));
            }
        }

        while(!queueSolver.first.ktNode.chessBoard.isSolved() && !queueSolver.isEmpty()){
            StorageNode count = queueSolver.dequeue();
            while(count.ktNode.chessBoard.next <= 8){
                ChessBoard temp = count.ktNode.chessBoard.getNext();
                if(!isNullBoard(temp)){
                    queueSolver.enqueue(new StorageNode(new KTNode(temp, count.ktNode)));
                }
            }
        }

        if(queueSolver.first.ktNode.chessBoard.isSolved()){
            System.out.println("Solution: ");
            KTNode temp = queueSolver.dequeue().ktNode;
            Stack solution = new Stack();

            while(!isNullBoard(temp.chessBoard)){
                solution.push(new StorageNode(temp));
                temp = temp.previous;
            }

            while(!solution.isEmpty()){
                System.out.println(solution.pop().ktNode.chessBoard);
            }
        }
        else if(queueSolver.isEmpty()){
            System.out.println("No solution");
        }
    }

    //Checks for a null board
    public static boolean isNullBoard(ChessBoard chessBoard){
        if(chessBoard.board[0][0] == 3){
            return true;
        }
        return false;
    }

    //Node classes and linked data structures
    private static class KTNode{

        private ChessBoard chessBoard;
        private KTNode previous;

        private KTNode(ChessBoard chessBoard, KTNode previous){
            this.chessBoard = chessBoard;
            this.previous = previous;
        }
    }

    private static class StorageNode{

        private KTNode ktNode;
        private StorageNode next;

        private StorageNode(KTNode ktNode){
            this.ktNode = ktNode;
            this.next = null;
        }
    }

    private static class Stack{

        private StorageNode top;

        public Stack() {
            top = null;
        }

        public void push(StorageNode storageNode) {
            if(top == null) {
                top = storageNode;
            }
            else {
                StorageNode temp = top;
                top = storageNode;
                top.next = temp;
            }
        }

        public StorageNode pop() {
            StorageNode temp = top;
            top = top.next;
            return temp;
        }

        public boolean isEmpty() {
            if(top == null) {
                return true;
            }
            return false;
        }
    }

    private static class Queue{

        private StorageNode first;
        private StorageNode last;

        public Queue() {
            first = null;
            last = null;
        }

        public void enqueue(StorageNode storageNode) {
            if(first == null) {
                last = storageNode;
                first = last;
            }
            else {
                StorageNode temp = storageNode;
                last.next = temp;
                last = temp;
            }
        }

        public StorageNode dequeue() {
            StorageNode temp = first;
            first = first.next;
            return temp;
        }

        public boolean isEmpty() {
            if(first == null) {
                return true;
            }
            return false;
        }
    }

}
