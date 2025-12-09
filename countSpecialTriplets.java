/*

You are given an integer array nums.

A special triplet is defined as a triplet of indices (i, j, k) such that:

0 <= i < j < k < n, where n = nums.length
nums[i] == nums[j] * 2
nums[k] == nums[j] * 2
Return the total number of special triplets in the array.

Since the answer may be large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [6,3,6]

Output: 1

Explanation:

The only special triplet is (i, j, k) = (0, 1, 2), where:

nums[0] = 6, nums[1] = 3, nums[2] = 6
nums[0] = nums[1] * 2 = 3 * 2 = 6
nums[2] = nums[1] * 2 = 3 * 2 = 6
Example 2:

Input: nums = [0,1,0,0]

Output: 1

Explanation:

The only special triplet is (i, j, k) = (0, 2, 3), where:

nums[0] = 0, nums[2] = 0, nums[3] = 0
nums[0] = nums[2] * 2 = 0 * 2 = 0
nums[3] = nums[2] * 2 = 0 * 2 = 0
Example 3:

Input: nums = [8,4,2,8,4]

Output: 2

Explanation:

There are exactly two special triplets:

(i, j, k) = (0, 1, 3)
nums[0] = 8, nums[1] = 4, nums[3] = 8
nums[0] = nums[1] * 2 = 4 * 2 = 8
nums[3] = nums[1] * 2 = 4 * 2 = 8
(i, j, k) = (1, 2, 4)
nums[1] = 4, nums[2] = 2, nums[4] = 4
nums[1] = nums[2] * 2 = 2 * 2 = 4
nums[4] = nums[2] * 2 = 2 * 2 = 4
 

Constraints:

3 <= n == nums.length <= 10^5
0 <= nums[i] <= 10^5
 */

class Solution {
    public int specialTriplets(int[] nums) {
       Map<Integer,Integer> left=new HashMap<>();
       Map<Integer,Integer> right=new HashMap<>();
       int n=nums.length;
       long ans=0;
       int mod=(int)1e9+7;
       for(int i=0;i<n;i++){
        right.putIfAbsent(nums[i],0);
        right.put(nums[i],right.get(nums[i])+1);
       }
       for(int i=0;i<n;i++){
         right.put(nums[i],right.get(nums[i])-1);
         long leftChildrens=left.getOrDefault(nums[i]*2,0);
         long rightChildrens=right.getOrDefault(nums[i]*2,0);
         ans=(ans+(leftChildrens*rightChildrens)%mod)%mod;
         left.putIfAbsent(nums[i],0);
         left.put(nums[i],left.getOrDefault(nums[i],0)+1);
       }
       return (int)ans;
    }
}