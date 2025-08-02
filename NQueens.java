// TC n! - If one row is choosen next row will have n - 2 choices. then next row will have n - 4  choices. 
// So n * (n - 2) * (n - 4) ....gives n!
// SC - O(n^2) as we use grid and O(n) for recursion stack (max n row is put in stack at once)

class Solution {
    List<List<String>> result;
    boolean[][] grid;

    public List<List<String>> solveNQueens(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        result = new ArrayList<>();
        grid = new boolean[n][n];

        backtrack(0, n);
        return result;
    }

    private void backtrack(int row, int n) {

        if (row == n) {
            List<String> ans = new ArrayList<>();
            
            for(int i = 0; i < n; ++i) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == true) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                ans.add(sb.toString());
            }

            result.add(ans);
            return;
        }

        for (int c = 0; c < n; ++c) {
            if (isSafe(row, c)) {
                grid[row][c] = true;
                backtrack(row + 1, n);
                grid[row][c] = false;
            }
        }
    }

    private boolean isSafe(int row, int col) {

        int i = row;
        int j = col;

        // same col
        while (i >= 0) {
            if (grid[i][col] == true) {
                return false;
            }
            i--;
        }

        i = row;
        j = col;

        // upper left diagonal
        while (i >= 0 && j >= 0) {
            if (grid[i][j] == true) {
                return false;
            }
            --i;
            --j;
        }
        
        i = row;
        j = col;

        // upper right diagonal
        while (i >= 0 && j < grid.length) {
            if (grid[i][j] == true) {
                return false;
            }
            --i;
            ++j;
        }

        return true;
    }
}