import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu{
    public static void reading(String filename){
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void show_menu(){
       reading("menu_categories.txt");
    }


    public void show_fry_items(){
        reading("fry_items.txt");
    }

    public void show_frozen_items(){
        reading("frozen_items.txt");
    }

    public void show_cakes(){
        reading("cakes.txt");
    }
    
    public void show_sweets(){
        reading("sweets.txt");
    }
    
    public void show_nimco(){
        reading("nimco.txt");
    }
    
}
