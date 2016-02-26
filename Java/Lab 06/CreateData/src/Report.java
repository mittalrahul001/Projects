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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
 
/*
 * This method will first display the list of employees
 * in sorted manner and then the user need to enter the 
 * name of the employee, if that employee name exists
 * then it will generate a payroll report of the employee
 * else it will display a message saying that the name is invalid
 */
		public class Report { //Rahul Student, Programmer
			String firstLine = "", secondLine = "", thirdLine = "";
		    double hours = 0.00, wages = 0.00;
		    double grossPay,overTime,overTimeWage = 0.00;
		    DecimalFormat twoDecimal = new DecimalFormat("0.00");
		    ArrayList<String> emp = new ArrayList<String>();
		    
		    public Report() throws Exception
		    {
		    	 FileReader file = new FileReader("payroll.txt");
		         BufferedReader buffer = new BufferedReader(file);
		         String line;
		         while ((line = buffer.readLine()) != null) {
		             firstLine = line;
		             secondLine = buffer.readLine();
		             thirdLine  = buffer.readLine();
		             emp.add(line);
		         }//end while
		         
		         buffer.close();
		         file.close();
		         String empList = "";

		         Collections.sort(emp);
		         for (String str : emp) {
		             empList += str + "\n";
		         }
               
		         JOptionPane.showMessageDialog(null, "Name:\n" + empList, "List of Employees",
		                 JOptionPane.PLAIN_MESSAGE);


		         //Get input then of desired employee name to save employee data to a file
		         String userInput = "";

		         while (userInput == null || userInput.equals("")) {

		             userInput = JOptionPane.showInputDialog("Enter an employee name from the list");
		         }

		         if (empList.toLowerCase().contains(userInput.toLowerCase())) {
		        	 FileReader file2 = new FileReader("payroll.txt");
			         BufferedReader buffer2 = new BufferedReader(file2);
			         String input;
			       //Loop the file
		        	 while ((line = buffer2.readLine()) != null) {
		        		 firstLine  = line;	 
		        		 secondLine = buffer2.readLine();
			             thirdLine  = buffer2.readLine();
			             input      = userInput.toLowerCase();
			             if(firstLine.equalsIgnoreCase(input))
		        		 {
			            	 generate_Report(firstLine,secondLine,thirdLine);
				             
		        		 }
		        	 }
		        	 buffer2.close();
			         file2.close();
		        	 
		             JOptionPane.showMessageDialog(null, "Report Generated.", "Result", JOptionPane.PLAIN_MESSAGE);
		         }

		         //Error Message
		         else {
		             JOptionPane.showMessageDialog(null, "Please enter a valid name");
		         }

		         System.exit(0);
		     } 
		    
		 /*
		  * This method will generate a report   
		  */
		 public void generate_Report(String name,String lv_hours,String lv_wages)
		 {
			 hours = Double.parseDouble(lv_hours); 
             wages = Double.parseDouble(lv_wages);
             if(hours <= 40)
             {
             	grossPay = ( hours * wages );
             }
             else
             {
             	overTimeWage = 1.5  * wages;
             	overTime = (hours - 40) * overTimeWage;
                twoDecimal.format(overTime);
             	grossPay = ((40 * wages) + overTime); 
             }
               String split[]    = name.split(" ");
               char name_First   = Character.toUpperCase(split[0].charAt(0));
               String last_Name  = split[1];
               String file_Name  = (name_First + last_Name + ".txt");
               File check_Report = new File(file_Name);  
               FileWriter file_Report = null;
               try
               {
	             if(check_Report.exists()) 
	               file_Report = new FileWriter(file_Name, true);
	             else
	               file_Report = new FileWriter(file_Name); 
               }
               catch(Exception e)
               {
            	   System.out.println(e);
               }
             BufferedWriter buffer_Rep = new BufferedWriter(file_Report);
             try
             {
	             for(int i = 1; i <= 20; i++)
	             {
	            	 buffer_Rep.write("*");
	             }
	             buffer_Rep.write("Payroll Report");
	             for(int i = 1; i <= 20; i++)
	             {
	            	 buffer_Rep.write("*");
	             }
	             buffer_Rep.newLine();
	             buffer_Rep.write("Employee Name:		   	");
	             buffer_Rep.write(name);
	             buffer_Rep.newLine();
	             buffer_Rep.write("Hours:   			");
	             buffer_Rep.write(secondLine);
	             buffer_Rep.newLine();
	             buffer_Rep.write("Wages: 			 	");
	             buffer_Rep.write("$" + thirdLine);
	             buffer_Rep.newLine();
	             buffer_Rep.write("Gross Pay:			");
	             String gross_String = ("$" + Double.toString(grossPay));
	             buffer_Rep.write(gross_String);
	             buffer_Rep.newLine();
	             buffer_Rep.write("Overtime Pay  			");
	             String overtime_String = ("$" + String.format( "%.2f", overTime));
	             buffer_Rep.write(overtime_String);
	             buffer_Rep.newLine();
	             for(int i = 1; i <=54 ; i++)
	             {
	            	 buffer_Rep.write("*");
	             }
	             buffer_Rep.close();
             }
             catch(Exception e)
             {
            	 System.out.println(e);
             } 
		 }


		public static void main(String[] args) throws Exception  
		{
			new Report();
		}
		}
