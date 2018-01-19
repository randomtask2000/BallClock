using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
class Solution {

    static int simpleArraySum(int n, int[] ar) {
        // Complete this function
        int size = 0;
        for (int i=0;i<n;i++){
            size = size + ar[i];
        }
        return size;
    }

    static void Main(String[] args) {
        int n = Convert.ToInt32(Console.ReadLine());
        string[] ar_temp = Console.ReadLine().Split(' ');
        int[] ar = Array.ConvertAll(ar_temp,Int32.Parse);
        int result = simpleArraySum(n, ar);
        Console.WriteLine(result);
    }
}
