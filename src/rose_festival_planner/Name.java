/******************************************************************************************
Program by: Adam LaFleur
Date: June 3, 2019
Class: CS202 - Programming Systems
Program: #4/5 - Fair Management
File: Name.java
Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
Name.java has the purpose of defining the Name class, its fields and methods.
******************************************************************************************/

package rose_festival_planner;

public class Name {

    private String name;

    //Default Constructor.
    public Name()
    {
        this.name = null;
    }

    //Assignment Constructor, takes a String as input.
    public Name(String name)
    {
        this.name = name;
    }

    //Copy Constructor, takes a Name reference as input.
    public Name(Name to_copy)
    {
        this.name = to_copy.name;
    }

    //Function used to send of copy of the name out of the object. Returns a String.
    public String getName() {
        return name;
    }

    //Function used to change the name after the object has been created, takes a String as input.
    public void setName(String name) {
        this.name = name;
    }

    //Function used to compare two names, takes a String as input and returns a boolean.
    public boolean compareName(String to_compare)
    {
        return this.name.equals(to_compare);
    }

    //Function used to determine if the name contains the argument String, takes a String as input and returns a boolean.
    public boolean containsString(String to_compare) {
        if (name.contains(to_compare)) return true;
        else {
            return containsString(to_compare.toUpperCase().split("\\s+"), 0);
        }
    }

    //Function used to convert a string into an array of strings using space as regex, returns a String array.
    public String [] string_toArray()
    {
        return this.name.split("\\s+");
    }

    //Recursive Function for public method, takes a array of strings and an int as arguments and returns a boolean.
    private boolean containsString(String [] array, int num)
    {
        if(num == array.length)
        {
            return false;
        }
        if(name.toUpperCase().contains(array[num]))
        {
            return true;
        }
        return containsString(array, ++num);
    }

    //Function used to print the name field.
    public void printName()
    {
        System.out.println("Name: " + this.name);
    }
}
