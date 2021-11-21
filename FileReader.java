import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String [][] info;

public String [][] readHotels() throws FileNotFoundException{
    java.io.File file = new java.io.File(".../l4Hotels.csv");
    Scanner input = new Scanner(file);

    String line;
    int z = 0;
    this.info = new String [12][12];

    while(input.hasNext()){
        line = input.nextLine();
        this.info[z] = line.split(",");
        z++;
    }
    input.close();
    return info;
}

}
