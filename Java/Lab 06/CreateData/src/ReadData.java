/**
 * Name:         Rahul Mittal
 * Lab Name:     ITMD_510_LAB_06
 * Current Date: 11/01/2015
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
 
public class ReadData { //Rahul Student, Programmer
/*
 * This method will read the data from the file payroll.txt
 * and calculate gross pay, overtime pay and display the 
 * payroll details of each and every employee
 */
public ReadData ()
{
try {  
  String[] firstLine  = new String[100],
           secondLine = new String[100],
           thirdLine  = new String[100];
   
  double hours[] = new double[100], wages[] = new double[100];
  
  int index;
  double overTimeWage, overTimePay = 0.00;
  for (index = 0; index < 100; index++) {
  firstLine[index]  = "";
  secondLine[index] = "";
  thirdLine[index ] = "";
  hours[index]      = 0.0;
  wages[index]      = 0.0;
}
  FileReader file = new FileReader("payroll.txt");
  BufferedReader buffer = new BufferedReader(file);
  index = 0;
  String line;
  DecimalFormat twoDecimal = new DecimalFormat("#.00");
   
  while((line = buffer.readLine()) != null)
  {
    firstLine [index]  = line;
    secondLine[index]  = buffer.readLine();
    thirdLine [index ] = buffer.readLine();
    
    hours[index] = Double.parseDouble(secondLine[index]); 
    wages[index] = Double.parseDouble(thirdLine[index]);
    twoDecimal.format(wages[index]);
    double grossPay;
    if(hours[index] <= 40)
    {
    	grossPay = ( hours[index] * wages[index] );
    }
    else
    {
    	overTimeWage = 1.5  * wages[index];
    	overTimePay = (hours[index] - 40) * overTimeWage;
        twoDecimal.format(overTimePay);
    	grossPay = ((40 * wages[index]) + overTimePay); 
    }
    BigDecimal result     = new BigDecimal(grossPay).setScale(2, BigDecimal.ROUND_HALF_UP);
    String stringName     = ("Name:   "  + firstLine[index]);
    String stringHours    = ("Hours:  "  + secondLine[index]);
    String stringWages    = ("Wages: "   + "$" + wages[index]);
    String stringGrossPay = ("Gross Pay: " + "$" + result); 
    JOptionPane.showMessageDialog(null,stringName + "\n"
     + stringHours + "\n" + stringWages + "\n" + stringGrossPay, "Result",
     JOptionPane.PLAIN_MESSAGE );
    overTimeWrite(firstLine[index],overTimePay);
 
    index++;
  }
  buffer.close();
  System.exit(0);
}
catch (IOException e ) { System.out.println(e); }        
 }

/*
 * This will create a overtime.txt file having
 * overtime pay information for every employee
 */
static void overTimeWrite(String name,double overPay)
{
	{
		try { 

		  String firstLine, secondLine = ""; 
		  File check = new File("overtime.txt");  
		  FileWriter file;
		  if(check.exists()) 
		    file = new FileWriter("overtime.txt", true);
		  else
		    file = new FileWriter("overtime.txt"); 
		  BufferedWriter buffer = new BufferedWriter(file);
		 
		    firstLine = name;
		    secondLine = ("$" + String.format( "%.2f", overPay));
		    buffer.write(firstLine);
		    buffer.newLine();
		    buffer.write(secondLine);
		    buffer.newLine();
			buffer.close();
		}
			catch (IOException e) { System.out.println(e); }  
		}
}
public static void main(String[] args)  
{
	new ReadData();
}
}


