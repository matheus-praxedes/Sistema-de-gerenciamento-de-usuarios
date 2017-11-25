package view;

import business.control.AccessControl;
import business.control.Facade;
import business.control.FacadeSales;
import business.control.ProductControl;
import business.control.UserControl;
import business.control.Command;
import business.control.AddCommand;
import business.control.UpdateCommand;
import business.control.SearchCommand;
import business.model.Order;
import business.model.User;
import business.model.Product;
import business.model.memento.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import util.ControlException;
import util.LoginException;
import util.PasswordException;
import util.UserException;

public class UserForm {
    
    private Facade  facade;
    private FacadeSales  facade_sales;
    private Scanner input;
    
    
    public UserForm(){
        
        input = new Scanner(System.in);
        input.useLocale(Locale.US);
        
        try {
            facade = new Facade();
            facade_sales = new FacadeSales();
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to load data into the system. Search for an administrator for support");
            System.exit(0);
        }    
    }
    
    public void mainMenu(){
        
        System.out.println("\n\n#######################################\n");
        System.out.println("Welcome!");
        System.out.println("User registration system");
        
        while(true){
       
            System.out.println("\n\n#######################################\n");
            System.out.println("Choose one of the options below: \n" +
                          " 1  - Login\n" +
                          " 2  - Logout\n" +
                          " ------------------------\n" +
                          " 10 - Register user\n" +
                          " 11 - Delete user\n" + 
                          " 12 - List user\n" +
                          " 13 - List all users\n" +
                          " ------------------------\n" +
                          " 20 - Register product\n" +
                          " 21 - Delete product\n" + 
                          " 22 - List product\n" +
                          " 23 - Show menu\n" +
                          " ------------------------\n" +
                          " 30 - Make order\n" +
                          " ------------------------\n" +
                          " 40 - Add sale\n" +
                          " 41 - Change sale\n" +
                          " 42 - Search for sale\n" +
                          " 43 - Undo last sale action\n" +
                          " 44 - Redo last sale action\n" +
                          " ------------------------\n" +
                          " 50 - Show orders report\n" +
                          " 51 - Show products report\n" +
                          " 52 - Show clients report\n" +
                          " ------------------------\n" +
                          " 0  - Exit system");
       
            int choice = input.nextInt();
            input.nextLine();
            System.out.println();
           
            
            if(choice == 0){
                break;
            }
            
            switch(choice){
                case 1:
                    loginMenu();
                    break;
                case 2:
                    logoutMenu();
                    break;
                case 10:
                    addUserMenu();
                    break;
                case 11:
                    deleteUserMenu();
                    break;
                case 12:
                    listUserMenu();
                    break;
                case 13:
                    listAllUserMenu();
                    break;
                case 20:
                    addProductMenu();
                    break;
                case 21:
                    deleteProductMenu();
                    break;
                case 22:
                    listProductMenu();
                    break;
                case 23:
                    listAllProductMenu();
                    break;
                case 30:
                    makeOrderMenu();
                    break;
                case 40:
                    registerSaleMenu();
                    break;
                case 41:
                    updateSaleMenu();
                    break;
                case 42:
                    searchSaleMenu();
                    break;
                case 43:
                    undoSaleMenu();
                    break;
                case 44:
                    redoSaleMenu();
                    break;
                case 50:
                    showOrderReportMenu();
                    break;
                case 51:
                    showProductReportMenu();
                    break;
                case 52:
                    showClientReportMenu();
                    break;

            }
        }
        System.out.println("Thank you for using the system!");
    }

    private void loginMenu(){
    
        System.out.print("Enter login: ");
        String login = input.next();

        System.out.print("Enter password: ");
        String password = input.next();

        System.out.println();
        
        try{
            facade.loginSystem(login, password);
            System.out.println("Successfully logged as " + login + "!");
            System.out.println();
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }

    private void addUserMenu(){
        
        while(true){
            
            System.out.print("Enter your login: ");
            String login = input.next();

            System.out.print("Enter your password: ");
            String password = input.next();
            
            System.out.print("Enter your email: ");
            String email = input.next();
            
            System.out.print("Enter your phone number: ");
            String phone = input.next();

            System.out.println();

            try{
                facade.addUser(login, password, (email.equals("0") ? "" : email), (phone.equals("0") ? "" : phone));
                System.out.println("User successfully registered!");
                break;
            }
            catch(LoginException | PasswordException e){

                System.out.println( e.getMessage());
                System.out.println("Try again");
                System.out.println();
                
            } catch (ControlException ex) {
                System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
                // ex.printStackTrace(System.out);
            }
        }
    }

    private void deleteUserMenu(){
    
        System.out.print("Enter login to delete: ");
        String login = input.next();
        System.out.println();

        try{
            facade.deleteUser(login);
            System.out.print(login + " deleted successfully!\n");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        } catch (ControlException ex) {
            System.out.println("Internal error. Unable to save user data into the system. Search for an administrator for support");
        }
    }

    private void listUserMenu(){
    
        System.out.print("Enter login to display: ");
        String login = input.next();
        System.out.println();

        try{
            facade.listUser(login);
            System.out.println(String.format("%-20s%-20s%-30s%-13s" , "Login", "Password", "Email", "Phone" ));
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(facade.listUser(login) + "\n");
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllUserMenu(){
    
        try{
            List<User> all_users = facade.listAllUsers();
            System.out.println(String.format("%-20s%-20s%-30s%-13s" , "Login", "Password", "Email", "Phone" ));
            System.out.println("-----------------------------------------------------------------------------------");
            
            for(User u : all_users)
                System.out.println(u);
        }
        catch(UserException e){
            System.out.println( e.getMessage());
        }
    }
    
    private void addProductMenu(){
        
        while(true){
            
            System.out.print("Enter product name: ");
            String name = input.nextLine();

            System.out.print("Enter product price: R$ ");
            float price = input.nextFloat();
            input.nextLine();

            try{
                facade.addProduct(name, price);
                break;
            }catch (ControlException ex) {
                System.out.println("Internal error. Unable to save product data into the system. Search for an administrator for support");
            }
        }
    }

    private void deleteProductMenu(){
    
        System.out.print("Enter product name to delete: ");
        String name = input.nextLine();
        System.out.println();

        try{
            facade.deleteProduct(name);
            System.out.print("Deleted product\n");
        }catch (ControlException ex) {
            System.out.println( ex.getMessage());
        }
    }

    private void listProductMenu(){
    
        System.out.print("Enter product name: ");
        String name = input.nextLine();
        System.out.println();

        try{
            facade.listProduct(name);
            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            System.out.println(facade.listProduct(name) + "\n");
        }
        catch(ControlException e){
            System.out.println( e.getMessage());
        }
    }

    private void listAllProductMenu(){

        System.out.println();
    
        try{
            List<Product> all_products = facade.listAllProducts();
            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            
            for(Product p : all_products)
                System.out.println(p);
        }
        catch(ControlException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void makeOrderMenu(){
        
        List<String> orders = new ArrayList<>();
            
        while(true){
            
            System.out.print("Enter product name or type '0' to exit: ");
            String name = input.nextLine();

            if(!name.equals("0")){ 
               
            }
            else if(orders.size() > 0){
                break;
            }
            else{
               return;
            }
            
            orders.add(name);      
        }

        System.out.println();
        
        try{
            facade.newOrder(orders);      
            System.out.print("Order made successfully!\n");
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    private void logoutMenu(){
        System.out.println("Successfully logged out!");
        facade.logoutSystem();
    }

    private void registerSaleMenu(){
        
        List<String> sale_products = new ArrayList<>();
            
        System.out.print("Enter sale name: ");
        String name = input.nextLine();

        while(true){
            
            System.out.print("Enter product name or type '0' to exit: ");
            String product_name = input.nextLine();

            if(!product_name.equals("0")){ 
               
            }
            else if(sale_products.size() > 0){
                break;
            }
            else{
               return;
            }
            
            sale_products.add(product_name);   
        }

        System.out.print("Enter discount(%): ");
        float discount = input.nextFloat();
        input.nextLine();
        System.out.println();
        
        try{
            Command cmd = new AddCommand(facade_sales.getSaleControl(), name, discount/100.0f, sale_products);
            facade_sales.service(cmd);      
            System.out.print("Sale registered successfully!\n");
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void searchSaleMenu(){
        
        Sale sale;

        System.out.print("Enter sale name: ");
        String name = input.nextLine();
        System.out.println();

        try{
            Command cmd = new SearchCommand(facade_sales.getSaleControl(), name);
            sale = facade_sales.service(cmd);

            System.out.println(String.format("%-20s%-7s" , "Name", "Price" ));
            System.out.println("---------------------------");
            System.out.println(sale + "\n");
        }
        catch(ControlException e){
            System.out.println( e.getMessage());
        }
    }

    private void updateSaleMenu(){
        
        List<String> sale_products = new ArrayList<>();
            
        System.out.print("Enter sale name: ");
        String name = input.nextLine();

        while(true){
            
            System.out.print("Enter new products name or type '0' to exit: ");
            String product_name = input.nextLine();

            if(product_name.equals("0")){ 
               break;
            }
            
            sale_products.add(product_name);   
        }

        System.out.print("Enter new discount(%): ");
        float discount = input.nextFloat();
        input.nextLine();
        System.out.println();
        
        try{
            Command cmd = new UpdateCommand(facade_sales.getSaleControl(), name, discount/100.0f, sale_products);
            facade_sales.service(cmd);      
            System.out.print("Sale modified successfully!\n");
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void undoSaleMenu(){
        
        try{
            facade_sales.undo();      
            System.out.print("Last actions was undo successfully!\n");
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void redoSaleMenu(){
        
        try{
            facade_sales.redo();      
            System.out.print("Last actions was undo successfully!\n");
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showOrderReportMenu(){
        
        try{
            System.out.println(facade.getOrderReport());
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showProductReportMenu(){
        
        try{
            System.out.println(facade.getProductReport());
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void showClientReportMenu(){
        
        try{
            System.out.println(facade.getClientReport());
        }catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }
 }
