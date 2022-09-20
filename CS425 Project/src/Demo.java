import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Demo {
    public static void main(String args[]) {
        System.out.println("Connect to DB");
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "HR", "oracle");
            	System.out.println("Database successfully connected");
            
//step3 create the statement object
            Statement st = conn.createStatement();

//step4 execute query

            String dept = "Biology";
            
            ResultSet rs = st.executeQuery("select * from ADDRESS where Zipcode=123");

            while (rs.next()) {
                System.out.println(rs.getString("City"));
            }
/*
            Statement st2 = conn.createStatement();
            st2.executeUpdate("insert into instructor values('11341', 'Perry', 'Physics', 98000)");


            //resultset metadata

            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println("This gives us the metadata for the resultset\n");

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + "  " + rsmd.getColumnTypeName(i));
//		System.out.println(rsmd.getColumnTypeName(i));
            }

            //database metadata


            DatabaseMetaData dbmd = conn.getMetaData();
            //ResultSet rs2 = dbmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern)
            ResultSet rs2 = dbmd.getColumns(null, "%", "INSTRUCTOR", "%");

            System.out.println("\nThis gives us the metadata for the database\n");

            while (rs2.next()) {
                System.out.println(rs2.getString("COLUMN_NAME") + "  " + rs2.getString("TYPE_NAME") + "  " + rs2.getString("COLUMN_SIZE"));

*/
//step5 close the connection object
                st.close();
            	conn.close();

            //}

        }catch(Exception e){System.out.println(e); }
        }
    }