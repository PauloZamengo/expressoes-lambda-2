import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter full file path: ");
        String arquivo = sc.nextLine();

        try {
            FileReader file = new FileReader(arquivo);
            BufferedReader readFile = new BufferedReader(file);

            String line = readFile.readLine();
            List<Product> products = new ArrayList<>();

            while (line != null) {
                System.out.printf("%s\n", line);
                String[] fields = line.split(",");

                products.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = readFile.readLine();

            }

            double priceAverage = products.stream()
                    .map(p -> p.getPrice())
                    .reduce(0.0, (x,y) -> x + y / products.size());

            System.out.println("Average price: " + priceAverage);

            List<String> nameDescresc = products.stream()
                    .map(p -> p.getName())
                    .sorted()
                    .collect(Collectors.toList());

            nameDescresc.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
