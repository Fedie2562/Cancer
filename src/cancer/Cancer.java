/*
 * Ferris D.
 * 31/10/2018
 * Reads a text file containing a 15 x 15 grid
 */

package cancer;

import java.io.*;

/**
 * 
 * @author fedie2562
 */
public class Cancer {
  //Make global variables (grid&blobSize) which are accessible
  //from anywhere inside the class FloodIntro
  public static String grid[][];
  public static int blobSize;

  public static void main(String[] args) {
    int row, col;
    //Create 2D array size 15 x 15
    grid = new String[15][15];

    //Fill the array with plus
    for (row = 0; row < 15; row++) {
      for (col = 0; col < 15; col++) {
        grid[row][col] = "+";
      }
    }

    //Fill 70 random elements in the array with an asterisk
    //Do no choose an element along the border
    //The border will always contain elements with spaces
    //(blank border)
    for (int i = 0; i < 70; i++) {
      row = (int) (Math.random() * 13 + 1);
      col = (int) (Math.random() * 13 + 1);
      grid[row][col] = "-";
    }

    //Print out the current grid
    displayGrid();

    //variable to determine the size of the blob
    blobSize = 0;

    //Pick one random element in the array that is not along the
    //border and remove the blob at that location
    //NOTE: if a blank is chosen, the blob size is 0
    //and nothing is removed
    int blobRow = (int) (Math.random() * 13 + 1);
    int blobCol = (int) (Math.random() * 13 + 1);

    System.out.println("The blob at " + blobRow + "," + blobCol
      + " will be removed.");
    floodFill(blobRow, blobCol);
    System.out.println("The blob had " + blobSize +
      " items in it");
    System.out.println("The new grid is:");
    //Print out the new grid
    displayGrid();
  }

  public static void floodFill(int row, int col) {
    if (grid[row][col].equals("*")) {
      grid[row][col] = " ";
      blobSize++;
      floodFill(row - 1, col - 1);
      floodFill(row - 1, col);
      floodFill(row - 1, col + 1);
      floodFill(row, col - 1);
      floodFill(row, col + 1);
      floodFill(row + 1, col - 1);
      floodFill(row + 1, col);
      floodFill(row + 1, col + 1);
    }
  }

  public static void displayGrid() {
    String output = "";
    for (int row = 0; row <= 14; row++) {
      //if ((row > 0) && (row < 14)) {
      //  output += (row % 14);
      //}
      for (int col = 0; col <= 14; col++) {
        output += grid[row][col];
      }
      output += "\n";
    }
    System.out.println(output);
    try{
        PrintWriter fileOut = new PrintWriter(new FileWriter("cancer.txt", true));
        fileOut.print(output);
        fileOut.close();
    }catch(IOException IOEx){}
  }
}