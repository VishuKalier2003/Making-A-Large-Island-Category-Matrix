/* You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1. Return the size of 
the largest island in grid after applying this operation. An island is a 4-directionally connected group of 1s.
* Eg 1 : grid = [[0,1,1,0,0],[0,1,1,0,0],[0,0,0,0,0],[1,1,0,0,0],[1,1,1,1,0]]     Output = 11 
* Eg 2 : grid = [[0,1,1,0,1],[0,1,1,1,1],[0,0,0,0,0],[1,1,0,0,0],[1,1,1,1,0]]     Output = 14 
* Eg 3 : grid = [[1,0],[0,1]]                                                     Output = 3  
* Eg 4 : grid = [[1,0],[1,1]]                                                     Output = 4  
*/
import java.util.*;
public class LargeIsland
{
      public int MakeAnIslandLarger(int grid[][])
      {
            int water[][] = new int[grid.length][grid[0].length];    //* Matrix of Water Cells -> O(N x M)
            for(int i = 0; i < grid.length; i++)     //! Grid Traversal -> O(N x M)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {     // Ternary Operator to check if current cell is water or not...
                        water[i][j] = (grid[i][j] == 0) ? AdjacentLand(i, j, grid) : 0;
                  }
            }
            Vector<int[]> vector = new Vector<int[]>();    //* Vector for storing water cell locations -> O(N x M)
            for(int i = 0; i < water.length; i++)     //! Grid Traversal -> O(N x M)
            {
                  for(int j = 0; j < water[0].length; j++)
                  {
                        if(water[i][j] > 0)     // If the water cell is connected to land, in atleast one direction...
                              vector.add(new int[]{i, j});
                  }
            }
            boolean visited[][] = new boolean[grid.length][grid[0].length];     //* Visited Cell Matrix -> O(N x M)
            for(int i = 0; i < grid.length; i++)       //! Initialising the Matrix -> O(N x M)
                  Arrays.fill(visited[i], false);
            int area = 0;     // Variable to store Maximum area...
            for(int i = 0; i < vector.size(); i++)     //! Vector Traversal -> O(N/2)
            {
                  int currentArea = 0;   // Variable to check current area...
                  grid[vector.get(i)[0]][vector.get(i)[1]] = 1;   // Current index is change to land, and the area
                  // is checked for the formed Island...
                  Queue<int[]> queue = new LinkedList<int[]>();      //* Queue Declaration -> O(N x M)
                  queue.add(new int[]{vector.get(i)[0], vector.get(i)[1]});
                  while(!queue.isEmpty())    //! Breadth First Search -> O(N x M)
                  {
                        for(int j = 0; j < queue.size(); j++)
                        {
                              int temp[] = queue.poll();     // Getting the front element...
                              if((temp[0] > 0) && (grid[temp[0] - 1][temp[1]] == 1) && (visited[temp[0] - 1]
                              [temp[1]] == false))
                              {     // For upwards...
                                    queue.add(new int[]{temp[0] - 1, temp[1]});
                                    visited[temp[0] - 1][temp[1]] = true;
                                    currentArea++;
                              }
                              if((temp[0] < grid.length - 1) && (grid[temp[0] + 1][temp[1]] == 1) && 
                              (visited[temp[0] + 1][temp[1]] == false))
                              {     // For downwards...
                                    queue.add(new int[]{temp[0] + 1, temp[1]});
                                    visited[temp[0] + 1][temp[1]] = true;
                                    currentArea++;
                              }
                              if((temp[1] > 0) && (grid[temp[0]][temp[1] - 1] == 1) && (visited[temp[0]]
                              [temp[1] - 1] == false))
                              {     // For leftwards...
                                    queue.add(new int[]{temp[0], temp[1] - 1});
                                    visited[temp[0]][temp[1] - 1] = true;
                                    currentArea++;
                              }
                              if((temp[1] < grid[0].length - 1) && (grid[temp[0]][temp[1] + 1] == 1) && 
                              (visited[temp[0]][temp[1] + 1] == false))
                              {     // For rightwards...
                                    queue.add(new int[]{temp[0], temp[1] + 1});
                                    visited[temp[0]][temp[1] + 1] = true;
                                    currentArea++;
                              }
                        }
                  }
                  area = (currentArea >= area) ? currentArea : area;   // Ternary Operation to get Area...
                  grid[vector.get(i)[0]][vector.get(i)[1]] = 0;    // Making the changed cell to water cell...
                  for(int j = 0; j < visited.length; j++)      //! Filling the Matrix -> O(N x M)
                        Arrays.fill(visited[j], false);
            }
            return area;     // Returning the Maximum Area...
      }
      public int AdjacentLand(int row, int col, int grid[][])    //! Checking Adjacent Cells -> O(1)
      {
            int adjacent = 0;   // Checking adjacent cells...
            adjacent = ((row > 0) && (grid[row - 1][col] == 1)) ? adjacent + 1 : adjacent;
            adjacent = ((row < grid.length - 1) && (grid[row + 1][col] == 1)) ? adjacent + 1 : adjacent;
            adjacent = ((col > 0) && (grid[row][col - 1] == 1)) ? adjacent + 1 : adjacent;
            adjacent = ((col < grid[0].length - 1) && (grid[row][col + 1] == 1)) ? adjacent + 1 : adjacent;
            return adjacent;
      }
      public void DisplayGrid(int grid[][])     //! Grid Traversal -> O(N x M)
      {
            System.out.println("Grid !!");
            for(int i = 0; i < grid.length; i++)
            {
                  for(int j = 0; j < grid[0].length; j++)
                        System.out.print(grid[i][j]+", ");
                  System.out.println();
            }
      }
      public static void main(String args[])
      {
            //? Test Case - I
            int mat1[][] = {{1,0}, {0,1}};
            //? Test Case - II
            int mat2[][] = {{0,1,1,0,0}, {0,1,1,0,0}, {0,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,0}};
            //? Test Case - III
            int mat3[][] = {{0,1,1,0,1}, {0,1,1,1,1}, {0,0,0,0,0}, {1,1,0,0,0}, {1,1,1,1,0}};
            //? Test Case - IV
            int mat4[][] = {{1,1}, {1,0}};
            LargeIsland largeIsland = new LargeIsland();   // Object creation...
            System.out.println("The Maximum Area : "+largeIsland.MakeAnIslandLarger(mat1));
            System.out.println("The Maximum Area : "+largeIsland.MakeAnIslandLarger(mat2));
            System.out.println("The Maximum Area : "+largeIsland.MakeAnIslandLarger(mat3));
            System.out.println("The Maximum Area : "+largeIsland.MakeAnIslandLarger(mat4));
      }
}



//! Time Complexity -> O(N x N x M)
//* Space Complexity -> O(N x M)