// 611. Valid Triangle Number
// Solved
// Medium
// Topics
// premium lock icon
// Companies
// Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

// Example 1:

// Input: nums = [2,2,3,4]
// Output: 3
// Explanation: Valid combinations are: 
// 2,3,4 (using the first 2)
// 2,3,4 (using the second 2)
// 2,2,3
// Example 2:

// Input: nums = [4,2,3,4]
// Output: 4

// Constraints:

// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 1000

class Solution {
  public int triangleNumber(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int total = 0;

    for (int k = n - 1; k >= 2; k--) {
      int i = 0, j = k - 1;
      while (i < j) {
        if (nums[i] + nums[j] > nums[k]) {
          total += (j - i); // all pairs between i and j are valid
          j--;
        } else {
          i++;
        }
      }
    }
    return total;
  }
}
