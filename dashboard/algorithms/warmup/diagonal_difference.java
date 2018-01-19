import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int diagonalDifference(int[][] a, int n) {
        // Complete this function
        int diag1 = 0, diag2 = 0;
        for (int x = 0; x < n; x++)
        {
            for (int z = 0; z < n; z++)
            {
                // finding sum of primary diagonal
                if (x == z)
                    diag1 += a[x][z];

                // finding sum of secondary diagonal
                if (x == n - z - 1)
                    diag2 += a[x][z];
            }
        }
        return Math.abs(diag1 - diag2);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for(int a_i = 0; a_i < n; a_i++){
            for(int a_j = 0; a_j < n; a_j++){
                a[a_i][a_j] = in.nextInt();
            }
        }
        int result = diagonalDifference(a, n);
        System.out.println(result);
        in.close();
    }
}
