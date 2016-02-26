/**
 * Name:         Rahul Mittal
 * Lab Name:     ITMD_510_LAB_06
 * Current Date: 11/01/2015
 */

import javax.swing.JOptionPane;
//programmer: Rahul Student

public class Login {
public static void main(String[] args)  
{ 
  boolean access = false;	
  String message = "welcome" + "\n", response;
  int count = 0;
  message += "enter your name";
  message += "\n" + " ";
  do
  {
  String name = JOptionPane.showInputDialog(message);
  String password;
  name = name.trim();
  name = name.toUpperCase();
  if (name.equalsIgnoreCase("Admin")) 
  { 
	count = 0;
    JOptionPane.showMessageDialog(null,"hello " + name);
    message = "enter your password";
    message += "\n" + " ";
    password = JOptionPane.showInputDialog(message);
    password = password.trim();
    password = password.toUpperCase();

    if (password.equals("ADMIN")) 
    { 
      access = true;
      break;
    }
  else
    JOptionPane.showMessageDialog(null, "incorrect password");
    count++;
  }
  else
  {
    JOptionPane.showMessageDialog(null, "incorrect login name");
    count++;
    //System.exit(1);
  }
  }
  while(count < 3);
 if(access == true)
  {
    try {
 	Menu m = new Menu(); 
	System.exit(1);
    }
    catch (Exception e) { System.out.println(e);} 
  }
 else
 {
	 JOptionPane.showMessageDialog(null, "You have attempted 3 times with failure");
	 System.exit(0);
 }
}//end main
}//end class
