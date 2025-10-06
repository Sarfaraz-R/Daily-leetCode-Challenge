// 407. Trapping Rain Water II
// Solved
// Hard
// Topics
// premium lock icon
// Companies
// Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.

// Example 1:

// Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
// Output: 4
// Explanation: After the rain, water is trapped between the blocks.
// We have two small ponds 1 and 3 units trapped.
// The total volume of water trapped is 4.
// Example 2:

// Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
// Output: 10

// Constraints:

// m == heightMap.length
// n == heightMap[i].length
// 1 <= m, n <= 200
// 0 <= heightMap[i][j] <= 2 * 10^4

class Solution {
  class Cell {
    int height;
    int row;
    int col;

    public Cell(int height, int row, int col) {
      this.height = height;
      this.col = col;
      this.row = row;
    }
  }

  public int trapRainWater(int[][] heightMap) {
    int n = heightMap.length;
    int m = heightMap[0].length;
    boolean[][] vis = new boolean[n][m];
    PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.height - b.height);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
          pq.offer(new Cell(heightMap[i][j], i, j));
          vis[i][j] = true;
        }
      }
    }
    int waterStored = 0;
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    while (!pq.isEmpty()) {
      Cell c = pq.poll();
      int height = c.height;
      int row = c.row;
      int col = c.col;
      for (int i = 0; i < 4; i++) {
        int nr = row + dx[i];
        int nj = col + dy[i];
        if (nr >= 0 && nr < n && nj >= 0 && nj < m && !vis[nr][nj]) {
          if (heightMap[nr][nj] < height) {
            waterStored += (height - heightMap[nr][nj]);
            pq.offer(new Cell(height, nr, nj));
          } else {
            pq.offer(new Cell(heightMap[nr][nj], nr, nj));
          }
          vis[nr][nj] = true;
        }
      }
    }
    return waterStored;
  }
}
