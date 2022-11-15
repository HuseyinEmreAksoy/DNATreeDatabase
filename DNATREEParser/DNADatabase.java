package DNATREEParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class DNADatabase {
    public static void main(String[] args) {
        DNATree <String> tree = new DNATree<>();
        //String fileName = args[0];
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new File("directivesFile.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
        while (inputStream.hasNext())
        {
            String operation = inputStream.next();
            if (operation.equals("insert"))
                tree.insert(inputStream.next());
            else if (operation.equals("remove"))
                tree.remove(inputStream.next());
            else if (operation.equals("display"))
                tree.display();
            else if (operation.equals("display-lengths"))
                tree.displayLengths();
            else
                tree.search(inputStream.next());
        }
        inputStream.close();
    }
}
