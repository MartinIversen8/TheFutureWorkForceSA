
    import org.junit.Test;

import java.sql.*;
import java.util.Properties;
import static org.junit.Assert.*;

    public class DBTest {
        public static Connection con;
        private static PreparedStatement ps;
        private static ResultSet rs;
        private static String port;
        private static String databaseName;
        private static String userName;
        private static String password;
        @org.junit.Before
        public void setUp() throws Exception {
            Properties props = new Properties();
            port = props.getProperty("port","1433");
            databaseName = props.getProperty("databaseName","DatabaseTesting");
            userName=props.getProperty("userName", "sa");
            password=props.getProperty("password","12345");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, userName, password);
            System.out.println("Database Ready");

        }

        @Test
        public void testInsert() throws Exception {

            String insert = ("23");
            con.prepareStatement("insert into tbl_Testing values("+insert+",'testing')").execute();
            assertTrue(con.prepareStatement("select * from tbl_Testing where fld_TestID ="+insert).executeQuery().next());
        }
        @Test
        public void testDelete() throws Exception {

            String delete = ("from tbl_Testing where fld_TestID = 16");
            con.prepareStatement("delete " + delete).execute();
            assertFalse(con.prepareStatement("select * "+ delete).executeQuery().next());
        }
        @Test
        public void testColumnChanges() throws Exception {

            String insert =("24");
            con.prepareStatement("insert into tbl_Testing values("+insert+",'testing')").execute();
            DB.selectSQL("Select count(fld_TestID) from tbl_Testing");
            String count =(DB.getData());
            assertEquals(15,count);
        }
    }

