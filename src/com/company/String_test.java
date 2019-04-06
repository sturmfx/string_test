package string_test;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class String_test
{

    public static void main(String[] args)
    {
        try {
            writeToFile(listWordsOccurency(getWords(getString())));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getString()
    {

        String content = null;
        Scanner sc = new Scanner(System.in);
        boolean a = true;
        while(a)
        {
            String s = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\";
            s += sc.nextLine();
            try
            {
                content = new String(Files.readAllBytes(Paths.get(s)), "UTF-8");
                content = content.toLowerCase();
                a = false;
            }
            catch (IOException e)
            {
                System.out.println("Incorrect path!");
            }

        }
        return content;
    }

    public static String[] getWords(String s)
    {
        return s.split(" ");
    }

    public static String listWordsOccurency(String[] s)
    {
        String result = "";
        Set<String> temp = new LinkedHashSet<String>(Arrays.asList(s));
        String[] unique = temp.toArray(new String[temp.size()]);
        int[] number_of_word = new int[unique.length];
        wordnumber[] list = new wordnumber[unique.length];
        for(int i = 0; i < unique.length; i++)
        {
            for(int j = 0; j < s.length; j++)
            {
                if(unique[i].equals(s[j]))number_of_word[i]++;
            }
        }
        for(int i = 0; i < unique.length; i++)
        {
            list[i] = new wordnumber(unique[i], number_of_word[i]);
        }
        Arrays.sort(list);
        for(int i = 0; i < list.length; i++)
        {
            result += i+ ".) " + list[i].toString() + System.lineSeparator();
        }
        return result;
    }

    public static void writeToFile(String s) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/" + "result.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (writer != null) {
            writer.write(s);
        }
        writer.close();
    }


}
class wordnumber implements Comparable<wordnumber>
{
    public String word;
    public int number;
    public wordnumber(String s, int i)
    {
        word = s;
        number = i;
    }
    @Override
    public int compareTo(wordnumber o) 
    {

        if (this.number == o.number)
        {
            return 0;
        } else if (this.number < o.number)
        {
            return 1;
        } else
        {
            return -1;
        }
    }

    @Override
    public String toString() {
        return word + " : " + number;
    }
}
