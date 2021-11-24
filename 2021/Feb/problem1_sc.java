import java.util.*;
import java.io.*;

public class problem1_sc
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner( System.in );
        int n = Integer.parseInt(in.nextLine());
        
        String[][] sent = new String[n][8];
        for (int i = 0; i < n; i++)
        {
            String[] temp = new String[8];
            temp = in.nextLine().split(" ", 0);
            for (int j = 0; j < 8; j++)
            {
                sent[i][j] = temp[j];
            }
        }
        in.close();

        //Arrays.sort(names);
        //System.out.println(Arrays.toString(names));

        String[] dictionary = new String[12];
        String d = "Ox, Tiger, Rabbit, Dragon, Snake, Horse, Goat, Monkey, Rooster, Dog, Pig, Rat";
        dictionary = d.split(", ", 0);

        String[] names = new String[n+1];
        int[] ani_years = new int[n+1];
        int[] dirs = new int[n+1];
        int[] froms = new int[n+1];
        int[] ydiffs = new int[n+1];

        // Bessie
        names[0] = "Bessie";
        ani_years[0] = 0;
        dirs[0] = 0;
        froms[0] = 0;
        for (int i = 0; i < n; i++)
        {
            names[i+1] = sent[i][0];
        }
        for (int i = 0; i < n; i++)
        {
            ani_years[i+1] = animalTOyear(sent[i][4], dictionary);
            dirs[i+1] = sent[i][3].equals("previous") ? -1 : 1;
            froms[i+1] = nameTOindex(sent[i][7], names);
            ydiffs[i+1] = year_diff(ani_years[i+1], ani_years[froms[i+1]], dirs[i+1]);
            //System.out.printf("%s, %d, %d, %d, %d\n", names[i+1], ani_years[i+1], dirs[i+1], froms[i+1], ydiffs[i+1]);
        }
        int ptr = nameTOindex("Elsie", names);
        int ydiff_total = 0;
        for (int i = 0; i < n; i++) {
            //System.out.printf("%s %d\n", names[ptr], ydiffs[ptr]);
            ydiff_total += ydiffs[ptr];
            ptr = froms[ptr];
        }
        
        System.out.println(Math.abs(ydiff_total));
        
    }

    private static int nameTOindex(String name, String[] names)
    {
        int i = 0;
        int len = names.length;
        for (i = 0; i < len; i++)
        {
            if (name.equals(names[i]))
            {
                break;
            }
        }
        return i;
    }

    private static int animalTOyear(String animal, String[] dictionary)
    {
        int i = 0;
        int len = dictionary.length;
        for (i = 0; i < len; i++)
        {
            if (animal.equals(dictionary[i]))
            {
                break;
            }
        }
        return i;
    }

    private static int year_diff(int a, int b, int dir) {
        int diff = 0;

        //System.out.printf("%d %d %d\n", a, b, dir);

        if (dir > 0) {
            diff = a - b;
            diff = diff <= 0 ? diff+12 : diff;
        } else {
            diff = a - b;
            diff = diff >= 0 ? diff-12 : diff;
        }
        return diff;
    }
}