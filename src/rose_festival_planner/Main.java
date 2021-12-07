/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4/5 - Fair Management
 File: Main.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Main.java has the purpose of defining the Main class, its fields, and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Main extends Util {

    //Main function used to initialize program.
    public static void main(String[] args) {
        Park park = new Park(8);
    }
}
