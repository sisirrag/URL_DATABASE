import java.io.*;
import java.util.*;
import java.sql.*;

class urldatabase
{
    public static void main(String args[])throws Exception
    {
        String command,url;
        int count=0;        

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","mathew");
        Statement st=con.createStatement();

        Scanner sc=new Scanner(System.in);
        int a=0;

        do{
        command=sc.next();
        url=sc.next();
        int hash = url.hashCode();        

        if(command.equals("exit"))
        {
            a=1;
        }        

        if(command.equals("urlstore"))
        {
            st.executeUpdate("insert into urldatabase values('"+url+"','"+hash+"',"+count+")");
        }
        else if(command.equals("get"))
        {
            ResultSet rs=st.executeQuery("select key,count from urldatabase where url='"+url+"'");
            while(rs.next())
            {
                System.out.println(rs.getString("key"));
                count=rs.getInt("count");
            }
            count=count+1;
            st.executeUpdate("update urldatabase set count="+count+"where url='"+url+"'");

        }
        else if(command.equals("count"))
        {
            ResultSet rs=st.executeQuery("select count from urldatabase where url='"+url+"'");
            while(rs.next())
            {
                System.out.println(rs.getInt("count"));
            }

        }
        else if(command.equals("list"))
        {
            ResultSet rs=st.executeQuery("select * from urldatabase");
            while(rs.next())
            {
                System.out.println(rs.getString("url")+" "+rs.getString("key")+" "+rs.getInt("count"));
            }
            
        }
		else if(command.equals("exit"))
        {
            System.exit(0);
        }
    }while(a!=1);
    }
}