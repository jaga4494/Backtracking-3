// TC: O(m * n * 4^L)
// We try to start the search from each cell → O(m * n)
// Imagine a 3-letter word like "ABC".
// At level 0: we try the first letter from some cell (i, j)
// From there, we try 4 directions → up to 4 recursive calls for second letter ("B")
// Each of those can lead to 4 more for third letter ("C")

// SC: Max depth that is put inside the stack = length of word → O(L)
class Solution {
    int m, n;
    int[][] dirs;
    public boolean exist(char[][] board, String word) {
        // can use additional boolean variable or mutate the input (put some invalid character) and backtrack.

        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        m = board.length;
        n = board[0].length;

        dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //UDLR

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if(board[i][j] == word.charAt(0)) {
                    if(backtrack(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, int row, int col, int index) {

        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row == m || col < 0 || col == n || board[row][col] == '#') {
            return false;
        }

        if(board[row][col] == word.charAt(index)) {
            char temp = board[row][col];
            board[row][col] = '#';
            for (int dir[] : dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                if(backtrack(board, word, nr, nc, index + 1)) {
                    return true;
                }

            }
            board[row][col] = temp;
        }

        return false;
    }
}