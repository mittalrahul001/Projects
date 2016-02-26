/**
 * Name:         Rahul Mittal
 * Lab Name:     ITMD_510_LAB_06
 * Current Date: 11/01/2015
 */

import javax.swing.JOptionPane;
//programmer: Rahul Student 
public class Menu {
public Menu()
{
String message = "welcome" + "\n", response;

  message += "\n" + "enter...";
  message += "\n" + "  1 to enter payroll data";
  message += "\n" + "  2 to view payroll data";
  message += "\n" + "  3 to generate report by employee";
  message += "\n" + "  4 to check the subtotal and total overtime";
  message += "\n" + "  5 to exit" + "\n" + " ";

  char answer = 'Y';

  do {
	
   try {  
  
    response  = JOptionPane.showInputDialog(message);

    int choice = Integer.parseInt(response);
	
    switch (choice) {
    //This will call CreateData.java
	case 1:  CreateData cd = new CreateData();
	         answer = 'N'; System.exit(1);
	         break;
	//This will call ReadData
	case 2:  ReadData rd = new ReadData();
 	         answer = 'N';  System.exit(1);
 	         break;
 	//This will call Report.java which will generate a report for an employee 
	case 3:  Report  rpt = new Report();
	         answer = 'N';  System.exit(1);
	         break;
	//This will call Summary.java which will display the summary         
	case 4:  Summary sum = new Summary();
			 answer = 'N';  System.exit(1);
	         break;
	//This will exit the program
	case 5:  answer = 'N';  System.exit(1);
    		 break;
	default: { answer = 'Y'; choice = 0;
	JOptionPane.showMessageDialog(null,"enter a number: 1 - 5");
	} 
    }//end switch
   }//end try
   catch (Exception e ) 
   { 
	   JOptionPane.showMessageDialog(null,"Please enter only a number: 1 - 5");
   }  
  }while(answer == 'Y' || answer == 'y');  

}
public static void main(String[] args)  
{ 
 new Menu();
}//end main
}//end class 
