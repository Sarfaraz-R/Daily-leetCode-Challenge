// 417. Pacific Atlantic Water Flow
// Solved
// Medium
// Topics
// premium lock icon
// Companies
// There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

// The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

// The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

// Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

// Example 1:

// Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
// Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
// Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
// [0,4]: [0,4] -> Pacific Ocean 
//        [0,4] -> Atlantic Ocean
// [1,3]: [1,3] -> [0,3] -> Pacific Ocean 
//        [1,3] -> [1,4] -> Atlantic Ocean
// [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
//        [1,4] -> Atlantic Ocean
// [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
//        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
// [3,0]: [3,0] -> Pacific Ocean 
//        [3,0] -> [4,0] -> Atlantic Ocean
// [3,1]: [3,1] -> [3,0] -> Pacific Ocean 
//        [3,1] -> [4,1] -> Atlantic Ocean
// [4,0]: [4,0] -> Pacific Ocean 
//        [4,0] -> Atlantic Ocean
// Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
// Example 2:

// Input: heights = [[1]]
// Output: [[0,0]]
// Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.

// Constraints:

// m == heights.length
// n == heights[r].length
// 1 <= m, n <= 200
// 0 <= heights[r][c] <= 10^5

class Solution {
  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int n = heights.length;
    int m = heights[0].length;
    List<List<Integer>> ans = new ArrayList<>();
    boolean[][] canFlowToAtlantic = new boolean[n][m];
    boolean[][] canFlowToPacific = new boolean[n][m];
    Queue<int[]> q = new LinkedList<>();
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    // pacific
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (i == 0 || j == 0) {
          q.offer(new int[] { i, j });
          canFlowToPacific[i][j] = true;
        }

      }
    }
    // pacific
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int[] cell = q.poll();
        int row = cell[0];
        int col = cell[1];
        for (int k = 0; k < 4; k++) {
          int nr = row + dx[k];
          int nc = col + dy[k];
          if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
            if (!canFlowToPacific[nr][nc] && heights[nr][nc] >= heights[row][col]) {
              q.offer(new int[] { nr, nc });
              canFlowToPacific[nr][nc] = true;
            }
          }
        }
      }
    }

    // Atlantic
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (i == n - 1 || j == m - 1) {
          q.offer(new int[] { i, j });
          canFlowToAtlantic[i][j] = true;
        }

      }
    }
    // Atlantic
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int[] cell = q.poll();
        int row = cell[0];
        int col = cell[1];
        for (int k = 0; k < 4; k++) {
          int nr = row + dx[k];
          int nc = col + dy[k];
          if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
            if (!canFlowToAtlantic[nr][nc] && heights[nr][nc] >= heights[row][col]) {
              q.offer(new int[] { nr, nc });
              canFlowToAtlantic[nr][nc] = true;
            }
          }
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (canFlowToAtlantic[i][j] && canFlowToPacific[i][j]) {
          ans.add(new ArrayList<>());
          ans.get(ans.size() - 1).add(i);
          ans.get(ans.size() - 1).add(j);
        }
      }
    }
    return ans;

  }

}