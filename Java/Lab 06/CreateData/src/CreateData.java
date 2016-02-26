
/**
 * Name:         Rahul Mittal
 * Lab Name:     ITMD_510_LAB_06
 * Current Date: 11/01/2015
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 
import javax.swing.JOptionPane;

public class CreateData {
public static void main(String[] args)
	{
		  new CreateData();	
		}
		public CreateData()
		{
		  int repeat = 1;
		  String answer;
			
		  do 
		  {
		    Write();
		    answer = JOptionPane.showInputDialog ("write payroll " + 
		          "data?\n" + "enter 1 to continue or 0 to exit");

		    repeat = Integer.parseInt(answer);

		  }while(repeat == 1);
		  
		  System.exit(1);
		}

/*
 * Method to write the data into the file
 * This method validate each and every value and
 * convert every string value to either integer,double
 * and  again convert it to String to write it in a file
 */
static void Write()
{
	try {  
	      String firstLine  = "",
	    		 secondLine = "",
	    		 thirdLine  = "",
	    		 number     = "";
	      Double hours = 0.0,
	    		  wage = 0.0;
	      File check = new File("payroll.txt");  
	      FileWriter file;
	      if(check.exists()) 
	        //allows appending of data to file
	        file = new FileWriter("payroll.txt", true);
	      else
	        file = new FileWriter("payroll.txt"); 
	      BufferedWriter buffer = new BufferedWriter(file);
	      int size = 0, count = 1;
	      
	      number = JOptionPane.showInputDialog(null,"how many records?");
	      //Check for null and blank value of number of records
	      if(number == null)
	      {
	    	  System.exit(0);
	      }
	      else if( number.equalsIgnoreCase(""));
	      {
	    	  while(number.equalsIgnoreCase(""))
		      {
		   number = JOptionPane.showInputDialog(null,"how many records?");
		       if(number == null)
		       {
		    	   System.exit(0);
		       }
		      }
		      
	      }
	      //Check for string value in number of records
	      String errorMessage = "";
	      do
	      {
	    	  try
		      {
		    	  size = Math.abs(Integer.parseInt(number));
		    	  break;
		      }
		      catch(NumberFormatException e)
		      {
		   number = JOptionPane.showInputDialog(null,"how many records?");
		    	  if(number == null)
		    	  {
		    		  System.exit(0);
		    	  }
		    	  errorMessage = "Error";
		      }
	      }while (!errorMessage.isEmpty());
	     
	      // Check for null and blank value for name
	       do 
	       {
		      firstLine = JOptionPane.showInputDialog(null,"Enter name");
		         
		         if(firstLine == null)
			      {
			    	  System.exit(0);
			      }
			      else if( firstLine.equalsIgnoreCase(""));
			      {
			    	  while(firstLine.equalsIgnoreCase(""))
				      {
			 firstLine = JOptionPane.showInputDialog(null,"Enter name");
				       if(firstLine == null)
				       {
				    	   System.exit(0);
				       }
				      }
				      
			      }
		    	//Check for null value of hours
		    secondLine = JOptionPane.showInputDialog(null,"Enter hours");
		    		if(secondLine == null)
				      {
				    	  System.exit(0);
				      }
				      else if( secondLine.equalsIgnoreCase(""));
				      {
				    	  while(secondLine.equalsIgnoreCase(""))
					      {
		secondLine = JOptionPane.showInputDialog(null,"Enter hours");
					       if(secondLine == null)
					       {
					    	   System.exit(0);
					       }
					      }
					      
				      }
		    		
		    	//Check for string value in number of hours
		    	do
			      {
			    	  try
				      {
			    	  hours = Math.abs(Double.parseDouble(secondLine));
				    	  break;
				      }
				      catch(NumberFormatException e)
				      {
		secondLine = JOptionPane.showInputDialog(null,"Enter hours?");
				    	  if(secondLine == null)
				    	  {
				    		  System.exit(0);
				    	  }
				    	  errorMessage = "Error";
				      }
			      }while (!errorMessage.isEmpty());
		        
		    	//convert double to string for writing the data to file
		    	String hours_string = Double.toString(hours);
		    	
		    	//Check for null and blank value for wage
		    	thirdLine = JOptionPane.showInputDialog(null,"Enter wage");
		    	
		    	if(thirdLine == null)
			      {
			    	  System.exit(0);
			      }
			      else if( thirdLine.equalsIgnoreCase(""));
			      {
			    	  while(thirdLine.equalsIgnoreCase(""))
				      {
		  thirdLine = JOptionPane.showInputDialog(null,"Enter wage");
				       if(thirdLine == null)
				       {
				    	   System.exit(0);
				       }
				      }
				      
			      }
		    	
		    	//check for string value in wage
		    	do
		    	{
		    		try{
					wage = Math.abs(Double.parseDouble(thirdLine));
		    		}
		    		catch(NumberFormatException e)
		    		{
			thirdLine = JOptionPane.showInputDialog(null,"Enter wage");
		    			if(thirdLine == null)
		    			{
		    				System.exit(0);
		    			}
		    			errorMessage = "Error";
		    		}
		    	} while(!errorMessage.isEmpty());
		    	
		    	//convert to string value for writing it to file
		    	String wage_string = Double.toString(wage);
		    	
		    	//write all the data to file
		        buffer.write(firstLine);
		        buffer.newLine();
		        buffer.write(hours_string);
		        buffer.newLine();
		        buffer.write(wage_string);
		        buffer.newLine();
		        count++;
		        
		       firstLine  = null;
		       secondLine = null;
		       thirdLine  = null;
	
	      }while(count <= size);
	      

   buffer.close();

   JOptionPane.showMessageDialog(null, "data processed",
   "Result", JOptionPane.PLAIN_MESSAGE );
    
 }

 catch (IOException e) { System.out.println(e); }  
	
}
}
