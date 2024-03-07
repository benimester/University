import java.util.ArrayList;
import java.util.Random;

public class Champion {
    UIFrame frame;
    Boxes boxes;

    ArrayList<String>board;
    ArrayList<Integer>moves;
    public Champion(UIFrame frame) {
        this.frame = frame;
        boxes = new Boxes();
        board = new ArrayList<String>(0);
        moves = new ArrayList<Integer>(0);
    }

    public int getNextStep(String matrix, Integer[] signs){

        Integer[] emptyTiles = signs;
        int emptyTilesCount = 9;
        for(int i = 0; i < 9; ++i){
            if(matrix.charAt(i) != ' '){
                emptyTiles[i] = 0;
                --emptyTilesCount;
            }
        }

        // Generate cumulative distribution
        int[] cumulativeDistribution = new int[emptyTiles.length];
        int cumulativeSum = 0;
        for (int i = 0; i < emptyTiles.length; i++) {
            cumulativeSum += emptyTiles[i];
            cumulativeDistribution[i] = cumulativeSum;
        }

        // Generate random number
        Random random = new Random();
        int randNum = random.nextInt(cumulativeSum) + 1;

        // Binary search to find corresponding type
        int low = 0;
        int high = cumulativeDistribution.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (randNum > cumulativeDistribution[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public void play(String matrix){
        if(frame.checkBoard()){
            return;
        }

        Integer[] signs = boxes.getBox(matrix);
        int myStep = getNextStep(matrix, signs);
        frame.playground.setValue(myStep / 3, myStep % 3, 'o');
        board.add(frame.playground.getBoard());
        moves.add(myStep);

        frame.checkBoard();
    }

    public void newGame(Character outcome){
        if(outcome == 'x'){
            for(int i = 0; i < board.size(); ++i) {
                boxes.throw1(board.get(i), moves.get(i));
            }
        } else if (outcome == 'd') {
            for(int i = 0; i < board.size(); ++i) {
                boxes.add1(board.get(i), moves.get(i));
            }
        } else{
            for(int i = 0; i < board.size(); ++i) {
                boxes.add2(board.get(i), moves.get(i));
            }
        }

        board.clear();
        moves.clear();
    }

    private class Boxes {
        ArrayList<String> matrices;
        ArrayList<Integer[]> signs;
        public Boxes() {
            matrices = new ArrayList<String>(0);
            signs = new ArrayList<Integer[]>(0);
        }

        public void addBox(String matrix){
            matrices.add(matrix);
            signs.add(new Integer[]{150, 150, 150, 150, 150, 150, 150, 150, 150});
        }

        public Integer[] getBox(String matrix){
            if(!matrices.contains(matrix)){
                addBox(matrix);
            }
            return signs.get(matrices.indexOf(matrix));
        }

        public void add2(String matrix, int i){
            getBox(matrix)[i] += 2;
        }

        public void add1(String matrix, int i){
            getBox(matrix)[i] += 1;
        }

        public void throw1(String matrix, int i){
            getBox(matrix)[i] -= 1;
        }
    }
}
