// // 165. Compare Version Numbers
// Solved
// Medium
// Topics
// premium lock icon
// Companies
// Hint
// Given two version strings, version1 and version2, compare them. A version
// string consists of revisions separated by dots '.'. The value of the revision
// is its integer conversion ignoring leading zeros.

// To compare version strings, compare their revision values in left-to-right
// order. If one of the version strings has fewer revisions, treat the missing
// revision values as 0.

// Return the following:

// If version1 < version2, return -1.
// If version1 > version2, return 1.
// Otherwise, return 0.

// Example 1:

// Input: version1 = "1.2", version2 = "1.10"

// Output: -1

// Explanation:

// version1's second revision is "2" and version2's second revision is "10": 2 <
// 10, so version1 < version2.

// Example 2:

// Input: version1 = "1.01", version2 = "1.001"

// Output: 0

// Explanation:

// Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

// Example 3:

// Input: version1 = "1.0", version2 = "1.0.0.0"

// Output: 0

// Explanation:

// version1 has less revisions, which means every missing revision are treated
// as "0".

class Solution {
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    int j = 0;
    int i = 0;
    // System.out.println(Arrays.toString(v1));
    // System.out.println(Arrays.toString(v2));
    for (; i < v1.length || j < v2.length; i++, j++) {
      int num1 = 0;
      if (i < v1.length)
        num1 = Integer.valueOf(v1[i]);
      int num2 = 0;

      if (j < v2.length)
        num2 = Integer.valueOf(v2[j]);

      if (num1 < num2)
        return -1;
      else if (num1 > num2)
        return 1;

    }
    return 0;
  }
}