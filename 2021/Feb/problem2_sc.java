import java.util.*;
import java.io.*;

public class problem2_sc
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner( System.in );
        int n = in.nextInt();
        
        int[][] pasture = new int[1001][1001];
        int[] ans = new int[n];

        for (int i = 0; i < n; i++)
        {
            int x = in.nextInt();
            int y = in.nextInt();
            int before = checkComf(pasture, x, y);
            pasture[x][y] = 1;
            int after = checkComf(pasture, x, y);
            int diff = after - before;
            ans[i] = i == 0 ? diff : ans[i-1] + diff;
            /*if ((i >= 19)&&(i <= 22)) {
                System.out.printf("%d %d %d\n", after, before, ans[i]);
            }*/
        }
        in.close();

        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }
    
    private static int checkComf(int[][] p, int x, int y)
    {
        int n = 0;

        n += y-1 >= 0 ? isComf(p, x, y-1) : 0;
        n += x+1 <= 1001 ? isComf(p, x+1, y) : 0;
        n += y+1 <= 1001 ? isComf(p, x, y+1) : 0;
        n += x-1 >= 0 ? isComf(p, x-1, y) : 0;
        n += isComf(p, x, y);

/*        if ((x == 4)&&(y == 7)) {
            System.out.println(isComf(p, x, y-1));
            System.out.println(isComf(p, x+1, y));
            System.out.println(isComf(p, x, y+1));
            System.out.println(isComf(p, x-1, y));
            System.out.println(isComf(p, x, y));
        } */

        return n;
    }

    private static int isComf(int[][] p, int x, int y) {
        int n = 0;

        if (p[x][y] == 1) {
            n += y-1 >= 0 ? p[x][y-1] : 0;
            n += x+1 <= 1001 ? p[x+1][y] : 0;
            n += y+1 <= 1001 ? p[x][y+1] : 0;
            n += x-1 >= 0 ? p[x-1][y] : 0;
        }

        return n == 3 ? 1 : 0;
    }
}