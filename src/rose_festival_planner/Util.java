/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4/5 - Fair Management
 File: Util.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Util.java has the purpose of defining the Util Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

import java.util.Scanner;

public class Util {

    static public Scanner sc = null;

    //Default Constructor
    public Util() {
        sc = new Scanner(System.in);
    }

    //Function used to decide whether to repeat an action, returns a boolean.
    static public boolean Again() {
        System.out.print("Would you like to enter another? ");
        String response = new String();
        response = sc.next();
        sc.nextLine();

        if(response.toUpperCase().equals("YES"))
        {
            return true;
        }
        return false;
    }
}
