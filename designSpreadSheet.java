/*
 * Design Spreadsheet
Solved
Medium
Topics
premium lock icon
Companies
Hint
A spreadsheet is a grid with 26 columns (labeled from 'A' to 'Z') and a given number of rows. Each cell in the spreadsheet can hold an integer value between 0 and 105.

Implement the Spreadsheet class:

Spreadsheet(int rows) Initializes a spreadsheet with 26 columns (labeled 'A' to 'Z') and the specified number of rows. All cells are initially set to 0.
void setCell(String cell, int value) Sets the value of the specified cell. The cell reference is provided in the format "AX" (e.g., "A1", "B10"), where the letter represents the column (from 'A' to 'Z') and the number represents a 1-indexed row.
void resetCell(String cell) Resets the specified cell to 0.
int getValue(String formula) Evaluates a formula of the form "=X+Y", where X and Y are either cell references or non-negative integers, and returns the computed sum.
Note: If getValue references a cell that has not been explicitly set using setCell, its value is considered 0.

 

Example 1:

Input:
["Spreadsheet", "getValue", "setCell", "getValue", "setCell", "getValue", "resetCell", "getValue"]
[[3], ["=5+7"], ["A1", 10], ["=A1+6"], ["B2", 15], ["=A1+B2"], ["A1"], ["=A1+B2"]]

Output:
[null, 12, null, 16, null, 25, null, 15]

Explanation

Spreadsheet spreadsheet = new Spreadsheet(3); // Initializes a spreadsheet with 3 rows and 26 columns
spreadsheet.getValue("=5+7"); // returns 12 (5+7)
spreadsheet.setCell("A1", 10); // sets A1 to 10
spreadsheet.getValue("=A1+6"); // returns 16 (10+6)
spreadsheet.setCell("B2", 15); // sets B2 to 15
spreadsheet.getValue("=A1+B2"); // returns 25 (10+15)
spreadsheet.resetCell("A1"); // resets A1 to 0
spreadsheet.getValue("=A1+B2"); // returns 15 (0+15)
 

Constraints:

1 <= rows <= 10^3
0 <= value <= 10^5
The formula is always in the format "=X+Y", where X and Y are either valid cell references or non-negative integers with values less than or equal to 105.
Each cell reference consists of a capital letter from 'A' to 'Z' followed by a row number between 1 and rows.
At most 104 calls will be made in total to setCell, resetCell, and getValue.
 */

import java.util.HashMap;
import java.util.Map;

class Spreadsheet {
  // Stores cell values, where key = cell name (e.g., "A1"), value = integer value
  private Map<String, Integer> cellValues;

  // Constructor initializes the spreadsheet
  public Spreadsheet(int rows) {
    cellValues = new HashMap<>();
  }

  /**
   * Sets a value for a given cell.
   * 
   * @param cell  - cell name (e.g., "A1")
   * @param value - integer value to assign
   */
  public void setCell(String cell, int value) {
    cellValues.put(cell, value);
  }

  /**
   * Resets (removes) a cell value.
   * 
   * @param cell - cell name to reset
   */
  public void resetCell(String cell) {
    cellValues.remove(cell);
  }

  /**
   * Evaluates a formula in the form "X+Y" where X and Y
   * can be either cell references (e.g., "A1") or integer values.
   * 
   * @param formula - string formula (example: "A1+10")
   * @return result of evaluating the formula
   */
  public int getValue(String formula) {
    StringBuilder leftOperand = new StringBuilder();
    StringBuilder rightOperand = new StringBuilder();

    int n = formula.length();
    int i = 1; // Start from index 1 (assuming formula always starts with "=" like Excel)

    // Extract left operand until '+'
    for (; i < n; i++) {
      char ch = formula.charAt(i);
      if (ch == '+')
        break;
      leftOperand.append(ch);
    }

    i++; // Move past '+'

    // Extract right operand
    for (; i < n; i++) {
      char ch = formula.charAt(i);
      rightOperand.append(ch);
    }

    // Convert operands into actual integer values
    int leftValue = isCellReference(leftOperand.toString())
        ? cellValues.getOrDefault(leftOperand.toString(), 0)
        : Integer.parseInt(leftOperand.toString());

    int rightValue = isCellReference(rightOperand.toString())
        ? cellValues.getOrDefault(rightOperand.toString(), 0)
        : Integer.parseInt(rightOperand.toString());

    return leftValue + rightValue;
  }

  /**
   * Checks if a given string is a cell reference
   * (contains at least one uppercase letter).
   * 
   * @param s - input string
   * @return true if it's a cell reference, false otherwise
   */
  private boolean isCellReference(String s) {
    for (char ch : s.toCharArray()) {
      if (ch >= 'A' && ch <= 'Z')
        return true;
    }
    return false;
  }
}

/**
 * Usage Example:
 * Spreadsheet sheet = new Spreadsheet(5);
 * sheet.setCell("A1", 10);
 * sheet.setCell("B1", 20);
 * System.out.println(sheet.getValue("=A1+B1")); // Output: 30
 * System.out.println(sheet.getValue("=A1+50")); // Output: 60
 */
