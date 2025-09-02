/* 3025. Find the Number of Ways to Place People I

You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].

Count the number of pairs of points (A, B), where

A is on the upper left side of B, and
there are no other points in the rectangle (or line) they make (including the border).
Return the count 
Example 1:

Input: points = [[1,1],[2,2],[3,3]]

Output: 0

Explanation:



There is no way to choose A and B so A is on the upper left side of B.

Example 2:

Input: points = [[6,2],[4,4],[2,6]]

Output: 2

Explanation:



The left one is the pair (points[1], points[0]), where points[1] is on the upper left side of points[0] and the rectangle is empty.
The middle one is the pair (points[2], points[1]), same as the left one it is a valid pair.
The right one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0], but points[1] is inside the rectangle so it's not a valid pair.
Example 3:

Input: points = [[3,1],[1,3],[1,1]]

Output: 2

Explanation:



The left one is the pair (points[2], points[0]), where points[2] is on the upper left side of points[0] and there are no other points on the line they form. Note that it is a valid state when the two points form a line.
The middle one is the pair (points[1], points[2]), it is a valid pair same as the left one.
The right one is the pair (points[1], points[0]), it is not a valid pair as points[2] is on the border of the rectangle.
 

Constraints:

2 <= n <= 50
points[i].length == 2
0 <= points[i][0], points[i][1] <= 50
All points[i] are distinct.
*/


//Intution
//my intution was pick up two points check wheater the one is upperleft of another 
// if yes find ou is there any other point which comes in the rectangle formed by the two points


//code
class Solution {
    public int numberOfPairs(int[][] points) {
        //1.A is upper left of B
        //2.there is no point inside recatngle formed by A,B
        int count=0;
        for(int i=0;i<points.length;i++){
            for(int j=0;j<points.length;j++){
                if(i==j)continue;
                if(isUpperLeft(points[i],points[j])){
                    if(!containsAnotherPoint(points,i,j))count++;
                }
            }

        }
        return count;

    }
    private boolean isUpperLeft(int[]x,int[]y){
        int xi=x[0];
        int xj=x[1];
        int yi=y[0];
        int yj=y[1];
        if(xi<=yi&&xj>=yj)return true;
        return false;
    }
    private boolean containsAnotherPoint(int[][] points,int i, int j){
        int xi=points[i][0];
        int xj=points[i][1];
        int yi=points[j][0];
        int yj=points[j][1];
        for(int k=0;k<points.length;k++){
            if(k==i||k==j)continue;
            int zi=points[k][0];
            int zj=points[k][1];
            if(zj>=yj&&zj<=xj&&zi>=xi&&zi<=yi)return true;
        }
        return false;
    }
}

