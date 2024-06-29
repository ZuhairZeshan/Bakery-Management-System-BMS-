import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Console;


class Main{
    public static int to_show(){
        Scanner input= new Scanner(System.in);
        Menu obj1=new Menu();
        
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Press Item Code to Continue:");
        obj1.show_menu();
        System.out.println("");
        int i=input.nextInt();
        return i;
    }

    public static void admin_portal(){
        Scanner input= new Scanner(System.in);
        while(true){
            System.out.print("Enter User_Name = ");        
            String user=input.nextLine();
            Console console = System.console();
            char[] passwordArray = console.readPassword("Enter your password: ");
            String password = new String(passwordArray);
            //System.out.println(password);
            //System.out.println(user + " " + password);
            if(user.equals("Zuhair_Zeshan") && password.equals("pass@123")){
                System.out.println("");
                System.out.println("Press Any Key To Continue....");
                input.nextLine();
                break;
            }else{
                System.out.println("You Have Entered a Wrong User_Name or Password");
                System.out.println("please Try Again");
                System.out.println("");
                System.out.println("Press Any Key To Continue....");
                input.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void add_item(String files_name){
        Scanner input= new Scanner(System.in);

        System.out.print("Enter New Item Details (Complete) = ");
        String new_item=input.nextLine();

        try {
            FileWriter fw = new FileWriter(files_name, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(new_item);
            bw.newLine(); 
            bw.close();
            fw.close();
            System.out.println("Data appended to the file successfully.");
        } catch (IOException e) {
            System.err.println("Error appending data to the file: " + e.getMessage());
        }


    } 

    public static void delete_item(String file_name2){
        Scanner input= new Scanner(System.in);
        System.out.print("Enter Item Details (Complete) = ");
        String del_item=input.nextLine();

        int i=0;
        int n=line_counter(file_name2);
        String[] line=new String[n]; 
        
        try {//////////////////////////////////////////// Read
            File file = new File(file_name2); 
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                line[i] = scanner.nextLine();
                i++;
            }
            scanner.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        try {//////////////////////////////////////////////////////////////Write
            PrintWriter pw = new PrintWriter(file_name2);
            for(int k=0;k<n;k++){
                if(del_item.equals(line[k])){
                    continue;
                }else{
                    pw.println(line[k]);
                }
            }
            pw.close(); 
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

    }

    public static void update_item(String file_name5){
        Scanner input= new Scanner(System.in);
        System.out.print("Enter Details (Complete) to Update = ");
        String upt_item=input.nextLine();

        int i=0;
        int n=line_counter(file_name2);
        String[] line=new String[n]; 
        
        try {//////////////////////////////////////////// Read
            File file = new File(file_name2); 
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                line[i] = scanner.nextLine();
                i++;
            }
            scanner.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        try {//////////////////////////////////////////////////////////////Write
            PrintWriter pw = new PrintWriter(file_name2);
            for(int k=0;k<n;k++){
                if(upt_item.equals(line[k])){
                    pw.println(upt_item);
                }else{
                    pw.println(line[k]);
                }
            }
            pw.close(); 
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }


    }


    public static int line_counter(String filePath){
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return lineCount;
    }

    public static void selection(int num,int[] itemcode,int[] quantity){

       Scanner input= new Scanner(System.in);

        for(int i=0;i<num;i++){
            System.out.print("Enter Item Code = ");
            itemcode[i]=input.nextInt();
            System.out.print("Enter Item Quantity = ");
            quantity[i]=input.nextInt();
        }
    }



    public static int billing(int n,int[] ite,int[] q,int count,String file_name){
       int b=0;
        try {
            for(int j=0;j<n;j++){
                File file = new File(file_name);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(ite[j] == count){
                        String[] lin=line.split(" ");
                        int number = Integer.parseInt(lin[2]);
                        number=number*q[j];
                        b+=number;
                        break;
                    }else{
                        count++;
                    }
                }
                scanner.close(); 
             }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return b;
    }

    public static void displaying(int a,int[] items,int[] qua,int count2,String na,String customer_name){
        try {
            for(int h=0;h<a;h++){
                File file = new File(na); 
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(items[h] == count2){
                        System.out.print(line);
                        System.out.print(" ");
                        System.out.print(qua[h]);
                       String[] lin=line.split(" ");
                       int num2= Integer.parseInt(lin[2]);
                       System.out.print(" ");
                       System.out.println(num2 * qua[h]);
                       file_writing(customer_name,line,qua[h],num2*qua[h]);
                       break;   
                    }else{
                        count2++;
                    }
                }
                scanner.close(); 
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }



    public static void file_writing(String n,String file_line,int q,int a){
        try {
            FileWriter fw = new FileWriter("customer_order.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(n + " " + file_line + " " + q  + " " + a);
            bw.newLine();

            bw.close();
            fw.close();

            //System.out.println("Order Written ..... ");

        } catch (IOException e) {
            System.err.println("Error appending data to the file: " + e.getMessage());
        }
    }


    public static void show_order(){
        try {
            File file = new File("customer_order.txt"); 
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

    public static void update_order(String str){
        int i=0;
        int n=line_counter("customer_order.txt");
        String[] line=new String[n]; 
        
        try {//////////////////////////////////////////// Read
            File file = new File("customer_order.txt"); 
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                line[i] = scanner.nextLine();
                i++;
            }
            scanner.close(); 
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        try {//////////////////////////////////////////////////////////////Write
            PrintWriter pw = new PrintWriter("customer_order.txt");
            for(int k=0;k<n;k++){
                if(str.equals(line[k])){
                    continue;
                }else{
                    pw.println(line[k]);
                }
            }
            pw.close(); 
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

////////////////////////////////////////////////////////////////////////////////////main //////////////////////////////
    public static void main(String[] args) {

        Scanner input= new Scanner(System.in);
        
        String name;

        while(true){

            int bill1=0,bill2=0,bill3=0,bill4=0,bill5=0,bill=0;
            int num1=0,num2=0,num3=0,num4=0,num5=0;
            int counting1=1,counting2=1,counting3=1,counting4=1,counting5=1;

            int[] itemcode1=new int[num1];
            int[] itemcode2=new int[num2];
            int[] itemcode3=new int[num3];
            int[] itemcode4=new int[num4];
            int[] itemcode5=new int[num5];
            
            int[] quantity1=new int[num1];
            int[] quantity2=new int[num2];
            int[] quantity3=new int[num3];
            int[] quantity4=new int[num4];
            int[] quantity5=new int[num5];


            System.out.println("Choose Your Panel:");
            System.out.println("1 -> Admin Panel");
            System.out.println("2 -> Customer Panel");
            System.out.println("0 -> EXIT");
            
            int panel=input.nextInt();
            input.nextLine();

            if(panel == 1){///////////////////////////////////////////////////Admin Portal
                admin_portal();
                Menu obj2=new Menu();

                while(true){
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Please Select Any Option! ");
                    System.out.println("Press 1 to Add New Item.");
                    System.out.println("Press 2 to Delete Item.");
                    System.out.println("Press 3 to Update Item.");
                    System.out.println("Press 5 when Done");
                    int option=input.nextInt();

                    if(option == 1){ //////////////////////////////////////////////Adding a New Item
            
                        while(true){
                            int category_code=to_show();

                            if(category_code == 1){
                                obj2.show_fry_items();
                                System.out.println("");
                                System.out.println("");
                                add_item("fry_items.txt");
                                //counting1++;
                            }else if(category_code == 2){
                                obj2.show_frozen_items();
                                System.out.println("");
                                System.out.println("");
                                add_item("frozen_items.txt");
                                //counting2++;
                            }else if(category_code == 3){
                                obj2.show_cakes();
                                System.out.println("");
                                System.out.println("");
                                add_item("cakes.txt");
                                //counting3++;
                            }else if(category_code == 4){
                                obj2.show_sweets();
                                System.out.println("");
                                System.out.println("");
                                add_item("sweets.txt");
                                //counting4++;
                            }else if(category_code == 5){
                                obj2.show_nimco();
                                System.out.println("");
                                System.out.println("");
                                add_item("nimco.txt");
                                //counting5++;
                            }else if(category_code == 0){
                                break;
                            }else{
                                System.out.println("You have Entered a Wrong option");
                                System.out.println("Please Try Again");
                                System.out.println("");
                                System.out.println("Press Any Key To Continue....");
                                input.nextLine();
                                input.nextLine();
                            }
                        }

                    }else if(option == 2){/////////////////////////////////// Deleting a Item
                        while(true){
                            int category_code=to_show();

                            if(category_code == 1){
                                obj2.show_fry_items();
                                System.out.println("");
                                System.out.println("");
                                delete_item("fry_items.txt");
                            }else if(category_code == 2){
                                obj2.show_frozen_items();
                                System.out.println("");
                                System.out.println("");
                                delete_item("frozen_items.txt");
                            }else if(category_code == 3){
                                obj2.show_cakes();
                                System.out.println("");
                                System.out.println("");
                                delete_item("cakes.txt");
                            }else if(category_code == 4){
                                obj2.show_sweets();
                                System.out.println("");
                                System.out.println("");
                                delete_item("sweets.txt");
                            }else if(category_code == 5){
                                obj2.show_nimco();
                                System.out.println("");
                                System.out.println("");
                                delete_item("nimco.txt");
                            }else if(category_code == 0){
                                break;
                            }else{
                                System.out.println("You have Entered a Wrong option");
                                System.out.println("Please Try Again");
                                System.out.println("");
                                System.out.println("Press Any Key To Continue....");
                                input.nextLine();
                                input.nextLine();
                            }
                        }
                    }else if(option == 3){//////////////////////////////////  updating Item
                        while(true){
                            int category_code=to_show();

                            if(category_code == 1){
                                obj2.show_fry_items();
                                System.out.println("");
                                System.out.println("");
                                update_item("fry_items.txt");
                            }else if(category_code == 2){
                                obj2.show_frozen_items();
                                System.out.println("");
                                System.out.println("");
                                update_item("frozen_items.txt");
                            }else if(category_code == 3){
                                obj2.show_cakes();
                                System.out.println("");
                                System.out.println("");
                                update_item("cakes.txt");
                            }else if(category_code == 4){
                                obj2.show_sweets();
                                System.out.println("");
                                System.out.println("");
                                update_item("sweets.txt");
                            }else if(category_code == 5){
                                obj2.show_nimco();
                                System.out.println("");
                                System.out.println("");
                                update_item("nimco.txt");
                            }else if(category_code == 0){
                                break;
                            }else{
                                System.out.println("You have Entered a Wrong option");
                                System.out.println("Please Try Again");
                                System.out.println("");
                                System.out.println("Press Any Key To Continue....");
                                input.nextLine();
                                input.nextLine();
                            }
                        }
                    }else if(option == 5){/////////////////////////////////    Break
                        break;
                    }else{////////////////////////////////////////////////     Wrong Option
                        System.out.println("You have Entered a Wrong option");
                        System.out.println("Please Try Again");
                        System.out.println("");
                        System.out.println("Press Any Key To Continue....");
                        input.nextLine();
                        input.nextLine();
                        
                    }
                }


            }else if(panel == 2){         ///////////////////////////////////Customer_Portal
                System.out.println("");
                System.out.println("");
                Menu obj2=new Menu();
                
                while(true){
                    int item=to_show();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    if(item == 1){
                        obj2.show_fry_items();
                        System.out.println("");
                        System.out.print("Enter Number of Items you want to Order or Else Press 0 = ");
                        num1=input.nextInt();
                        if(num1 == 0){
                            continue;
                        }
                        System.out.println("");

                        itemcode1=new int[num1];
                        quantity1=new int[num1];
                
                        selection(num1,itemcode1,quantity1);
                        bill1=billing(num1,itemcode1,quantity1,counting1,"fry_items.txt");
                    }else if(item == 2){
                        obj2.show_frozen_items();
                        System.out.println("");
                        System.out.print("Enter Number of Items you want to Order or Else Press 0 = ");
                        num2=input.nextInt();
                        if(num2 == 0){
                            continue;
                        }
                        System.out.println("");

                        itemcode2=new int[num2];
                        quantity2=new int[num2];
                        selection(num2,itemcode2,quantity2);
                        bill2=billing(num2,itemcode2,quantity2,counting2,"frozen_items.txt");
                    }else if(item == 3){
                        obj2.show_cakes();
                        System.out.println("");
                        System.out.print("Enter Number of Items you want to Order or Else Press 0 = ");
                        num3=input.nextInt();
                        if(num3 == 0){
                            continue;
                        }
                        System.out.println("");

                        itemcode3=new int[num3];
                        quantity3=new int[num3];
                        selection(num3,itemcode3,quantity3);
                        bill3=billing(num3,itemcode3,quantity3,counting3,"cakes.txt");
                    }else if(item == 4){
                        obj2.show_sweets();;
                        System.out.println("");
                        System.out.print("Enter Number of Items you want to Order or Else Press 0 = ");
                        num4=input.nextInt();
                        if(num4 == 0){
                            continue;
                        }
                        System.out.println("");

                        itemcode4=new int[num4];
                        quantity4=new int[num4];
                        selection(num4,itemcode4,quantity4);
                        bill4=billing(num4,itemcode4,quantity4,counting4,"sweets.txt");
                    }else if(item == 5){
                        obj2.show_nimco();;
                        System.out.println("");
                        System.out.print("Enter Number of Items you want to Order or Else Press 0 = ");
                        num5=input.nextInt();
                        if(num5 == 0){
                            continue;
                        }
                        System.out.println("");

                        itemcode5=new int[num5];
                        quantity5=new int[num5];
                        selection(num5,itemcode5,quantity5);
                        bill5=billing(num5,itemcode5,quantity5,counting5,"nimco.txt");
                    }else if(item == 00){///////////////////////////////////////////////////////////update
                        while(true){
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Please Select Any One Option: ");
                            System.out.println("Press 1 for Removing");
                            System.out.println("Press 5 for Done");
                            int opt=input.nextInt();

                            

                            if(opt == 1){

                                    System.out.println("Enter Customer Name = ");
                                    name=input.nextLine();
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();

                                    show_order();

                                    System.out.println(" ");
                                    System.out.println(" ");
                                    
                                    System.out.print("Enter Your Full Details = ");
                                    String str=input.nextLine();

                                    update_order(str);                                    

                                

                            }else if(opt == 5){
                                break;
                            }else{
                                System.out.println("You have Entered a Wrong option");
                                System.out.println("Please Try Again");
                                System.out.println("");
                                System.out.println("Press Any Key To Continue....");
                                input.nextLine();
                            }
                        }

                    }else if(item == 0){/////////////////////////////////////////////////////checkout
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.print("Please Enter Your Name (Ex: Affan_Ahmed) = ");
                        name=input.nextLine();
                        System.out.println("Press Any Key To Continue....");
                        input.nextLine();

                        System.out.println("You Have Ordered! ");
                        System.out.println("");

                        if(num1 > 0){
                            displaying(num1,itemcode1,quantity1,counting1,"fry_items.txt",name);
                        }
                        if(num2 > 0){
                            displaying(num2,itemcode2,quantity2,counting2,"frozen_items.txt",name);
                        }
                        if(num3 > 0){
                            displaying(num3,itemcode3,quantity3,counting3,"cakes.txt",name);
                        }
                        if(num4 > 0){
                            displaying(num4,itemcode4,quantity4,counting4,"sweets.txt",name);
                        }
                        if(num5 > 0){
                            displaying(num5,itemcode5,quantity5,counting5,"nimco.txt",name);
                        }
                        
                        bill=bill1+bill2+bill3+bill4+bill5;
                        System.out.println("");
                        System.out.println("");
                        
                        System.out.println("TOTAL BILL = " + bill);
                        try {
                            FileWriter fw = new FileWriter("customer_order.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                
                            bw.write("TOTAL BILL = " + bill);
                            bw.newLine();
                            bw.newLine();
                
                            bw.close();
                            fw.close();

                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println("Order Stored ..... ");
                
                        } catch (IOException e) {
                            System.err.println("Error appending data to the file: " + e.getMessage());
                        }
                        break;
                    }else{
                        System.out.println("You have Entered a Wrong option");
                        System.out.println("Please Try Again");
                        System.out.println("");
                        System.out.println("Press Any Key To Continue....");
                        input.nextLine();
                    }
                    System.out.println("Press Any Key To Continue....");
                    input.nextLine();
                    input.nextLine();
                }

            }else if(panel == 0){          //////////////////////////////////EXIT
                break;
            }else{                          ////////////////////////////////Wrong_option
                System.out.println("You have Entered a Wrong option");
                System.out.println("Please Try Again");
                System.out.println("");
                System.out.println("Press Any Key To Continue....");
                input.nextLine();
            }
            System.out.println("");
            System.out.println("Press Any Key To Continue....");
            input.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
      
    }

}







