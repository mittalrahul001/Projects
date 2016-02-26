/**
 * Name:         Rahul Mittal
 * Lab Name:     ITMD_510_LAB_07
 * Current Date: 11/07/2015
 */


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Arrays;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class LIFO <T>
{	  
      private static Connection connect = null;
	  private static Statement statement = null;
      private static ResultSet resultSet = null;
      
      private static int count;
      private T[] data;
 
    public LIFO()
    {
        data = (T[]) new Object[5];
        count = 0;
    }
 
    void expandCapacity()
    {
        data = Arrays.copyOf(data, data.length * 2);
    }
 
    void push(T e)
    {
        if (count == data.length)
            expandCapacity();
        data[count++] = e;
    }
 
    T pop() throws Exception
    {
        if (count <= 0)
        {
            throw new Exception("stack empty");
        }
        count--;
        return data[count];
    }
 
    boolean isEmpty()
    {
        return count == 0;
    }
 
    static int size() 
    {
        return count;
    }
 
    public static void main(String[] args) throws Exception
    {
    	LIFO<Integer> s      = new LIFO<Integer>();
        LIFO<Integer> cogs   = new LIFO<Integer>();
        LIFO<Integer> endInv = new LIFO<Integer>();
        int total = 0;
        try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = (Connection) DriverManager
                                .getConnection("jdbc:mysql://www.papademas.net/Inventory?"
                                                + "user=dbuser&password=db1");
                // Statements allow to issue SQL queries to the database
                statement = (Statement) connect.createStatement();
                // Result set gets the result of the SQL query
                resultSet = statement
                                .executeQuery("select cost from rmittInventory");
                // ResultSet is initially before the first data set
                while (resultSet.next()) {
                        /*
                         * column data may be retrieved via name e.g.
                         * resultSet.getString("name"); or via the column number which
                         * starts at 1 e.g. resultSet.getString(1);
                         */
                        int cost = resultSet.getInt(1); // retrieve cost
                        total+= cost; 
                        s.push(cost); // push cost value onto stack

                }
                
                count = 5;
                cogs.push(s.pop() + s.pop() + s.pop());
                int goods_sold = cogs.pop();

                endInv.push(s.pop() + s.pop());
                int endInv_val = endInv.pop();
                
                int sold_cost = 800 * 3;

                int profit = sold_cost - goods_sold;
                int invturn = goods_sold / ((total + endInv_val) / 2);
                
                System.out.println("*************************************");
                System.out.println("Cost of Goods Sold: \t$" + goods_sold);
                System.out.println("Profit: \t\t$" + profit);
                System.out.println("Ending Inventory: \t$" + endInv_val);
                System.out.println("Inv Turnover Ratio: \t" + invturn);
                System.out.println("*************************************");

        } catch (Exception e) {
                System.out.println(e.getMessage());
        }

}
}