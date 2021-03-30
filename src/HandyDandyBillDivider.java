/***********************************************************************************************************************
 * Author: Anne Jetton
 * Title: Handy-Dandy Bill Divider
 * Description: This program was created for the purpose of calculating Any bills that need to be split between multiple people.
 ***********************************************************************************************************************
 * Pseudocode
 *
 * Check if previous configuration exists
 * if so,
 *      ask if user wants to use previous or new configuration
 *      if previous,
 *          load file into list of names
 *      else,
 *          gather new list of names
 * else,
 *      gather new list of names
 *
 *
 * while user still has bills to add,
 *      ask who owns the debt
 *      ask if it is distributed to all, some, or all except one.
 *      if all,
 *          divide by amount of names
 *          add amount to every total, except the owner of the bill
 *      else if some,
 *          while there is still names to collect,
 *              ask user for another name
 *          divide the debt by the amount of temporary names
 *          add the divided amount to those peoples totals
 *      else,
 *          ask for the one excluded name
 *          divide total by number of names minus 1
 *          add to all totals except the specified name.
 *
 * if two names owe each other money
 *      subtract the smaller total from the larger total
 *      set the larger total to that new number
 *      set the smaller total to zero
 *
 * return totals to user
 *
 * ask if the user would like to calculate again
 * if so, call calculate function again
 * else, return to start function
 *
 * **in progress**
 *
 * if new configuration was set,
 *      ask if user would like to override the previous file
 *      if yes,
 *          overwrite the previous file
 *
 * **current issues**
 *
 * -debts should be able to cancel each other out
 * -in the 'some' implementation, need to only ask for the amount of names specified
 *
 ***********************************************************************************************************************/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HandyDandyBillDivider {

    /*constructor*/
    public HandyDandyBillDivider(){

    }

//______________________________________________________________________________________________________________________

    /*main program*/
    public static void main(String[] args) throws IOException {
        String response = "previous";

        while (!response.equals("exit")) {
            Scanner obj = new Scanner(System.in); /* create a object */
            ArrayList<Name> nameList = new ArrayList<Name>();
            boolean newNames = false;

            newOrTemporary(response, obj, nameList);

            nameList = bills(nameList, response, obj);

            totals(nameList);

            newDefault(nameList);

            System.out.println("Type continue to make a new calculation, else type exit to exit.");
            response = obj.nextLine();
        }

    }

//______________________________________________________________________________________________________________________

    private static ArrayList<Name> newOrTemporary(String response, Scanner obj, ArrayList<Name> nameList) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("default.txt"));
        String name = reader.readLine();

        if (name != null){
            //while no response
            System.out.println("If you would like to use your previous names, type previous. If you would like to use new names, type new.");
            response = obj.nextLine();

            if (response.equals("previous")){
                nameList = loadPrevious(nameList);
                return nameList;
            }

        }

        nameList = loadNew(nameList, response, obj);
        return nameList;
    }

//______________________________________________________________________________________________________________________

    private static ArrayList<Name> loadPrevious(ArrayList<Name> nameList) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("default.txt"));
        String name = reader.readLine();

        while (name != null){
            Name addname = new Name(name);
            nameList.add(addname);
            name = reader.readLine();
        }
        
        return nameList;
    }

//______________________________________________________________________________________________________________________

    private static ArrayList<Name> loadNew(ArrayList<Name> nameList, String response, Scanner obj) {
        
        while(!response.equals("no")) {
            System.out.println("Please enter a name:");
            response = obj.nextLine();

            Name addname = new Name(response);
            nameList.add(addname);
            
            System.out.println("If you have another name, type yes. Otherwise, type no.");
            response = obj.nextLine();
        }

        response = "";
        return nameList;
    }

//______________________________________________________________________________________________________________________

    private static ArrayList<Name> bills(ArrayList<Name> nameList, String response, Scanner obj){
        float debt;
        String owner;

        while (!response.equals("no")){
            System.out.println("Please enter the bill amount in the format dollars.cents:");
            debt = Float.parseFloat(obj.nextLine());
            System.out.println("Who owns this Debt?");
            owner = obj.nextLine();
            String finalOwner = owner;
            System.out.println("Is this debt distributed to all, some, or all but one?");
            response = obj.nextLine();

            if (response.equals("all")){
                float Divider = (nameList.size());
                debt = debt/Divider;
                float finalDebt = debt;
                nameList.forEach((n) -> n.addDebt(finalDebt, finalOwner, "", ""));
            }
            else if(response.equals("some")){
                System.out.println("how many names are we distributing between?");
                float Divider = Float.parseFloat(obj.nextLine());
                debt = debt/Divider;
                float finalDebt = debt;

                while(!response.equals("no")) {
                    System.out.println("Please give me one of the names, or type no if finished:");
                    response = obj.nextLine();
                    String curName = response;
                    nameList.forEach((n) -> n.addDebt(finalDebt, finalOwner, curName, ""));
                }
            }
            else{ //response = all but one
                float Divider = (nameList.size() - 1);
                debt = debt/Divider;
                float finalDebt = debt;
                System.out.println("Who are we excluding?");
                String curNameNot = obj.nextLine();
                nameList.forEach((n) -> n.addDebt(finalDebt, finalOwner, "", curNameNot));
            }

            System.out.println("Would you like to add another debt?");
            response = obj.nextLine();
        }

        return nameList;
    }

//______________________________________________________________________________________________________________________

    private static void totals(ArrayList<Name> nameList){
        nameList.forEach((n) -> n.printTotals());
    }

//______________________________________________________________________________________________________________________

    //method in progress
    private static void newDefault(ArrayList<Name> nameList){

    }

//______________________________________________________________________________________________________________________

}
