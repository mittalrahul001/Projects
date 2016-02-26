/**
 * Name:         Rahul Mittal
 * Lab Name:     ITMD_510_LAB_06
 * Current Date: 11/01/2015
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;


public class Summary {

	/*
	 * This method will display the summary of overtime
	 * pay of employees starting from A to F,G to L and
	 * M to Z and the total overtime salary  
	 */
public Summary()
{
	try
	{
	  String firstLine, secondLine = "";
	  double a_to_f_Subtotal = 0.00,
			 g_to_l_Subtotal = 0.00,
			 m_to_z_Subtotal = 0.00,
			 total_Overtime  = 0.00,
			 overTime 		 = 0.00;
	  DecimalFormat twoDecimal = new DecimalFormat("#.00");
	  FileReader file = new FileReader("overtime.txt");
	  BufferedReader buffer = new BufferedReader(file);
	  String line;
	  while((line = buffer.readLine()) != null)
	  {
	    firstLine  = line.toLowerCase();
	    secondLine = buffer.readLine();
	    overTime   = Double.parseDouble(secondLine.substring(1,secondLine.length()));
	    char first_Char = firstLine.charAt(0);
	    if(first_Char >= 'a' && first_Char <= 'f')
	    {
	    	a_to_f_Subtotal = a_to_f_Subtotal + overTime;
	    }
	    else if(first_Char >= 'g' && first_Char <= 'l' )
	    {
	    	g_to_l_Subtotal = g_to_l_Subtotal + overTime;
	    }
	    else if(first_Char >= 'm' && first_Char <= 'z')
	    {
	    	m_to_z_Subtotal = m_to_z_Subtotal + overTime;
	    }
	   
	  }
	  total_Overtime = a_to_f_Subtotal + g_to_l_Subtotal + m_to_z_Subtotal;
	  JOptionPane.showMessageDialog(null,"A to F Subtotal: " + "\t" + "$"
			     + twoDecimal.format(a_to_f_Subtotal) + "\n" + "G to L Subtotal: " +
			    "\t" + "$" + twoDecimal.format(g_to_l_Subtotal) + "\n" + "M to Z Subtotal: " + 
			     "\t" + "$" + twoDecimal.format(m_to_z_Subtotal) + "\n"
			    + "Total OverTime: " + "\t" + "$" + twoDecimal.format(total_Overtime), "Over Time",
			     JOptionPane.PLAIN_MESSAGE );
	}
	catch(IOException e)
	{
		System.out.println(e);
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}
	
	public static void main(String[] args)  
	{
		new Summary();
	}
}
