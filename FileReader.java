import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String [][] info;
    private String [][] Reservations;

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

public String [][] readRes() throws FileNotFoundException{
    java.io.File file = new java.io.File(".../Reservations.csv");
    Scanner count = new Scanner(file);
    Scanner input = new Scanner(file);

    String line ;
    String c;
    int z = 0;

    // Row Count
    while(count.hasNext()){
       c = count.nextLine();
       z++;
    }
    count.close();

    this.Reservations =new String [z][9];

    while(input.hasNext()){
        line = input.nextLine();
        this.Reservations[z] = line.split(",");
        z++;
    }
    input.close();
    return Reservations;



}

}
