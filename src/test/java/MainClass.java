
        import java.io.IOException;
        import java.sql.*;
        import java.util.concurrent.TimeUnit;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;
       // import com.mysql.jdbc.ResultSetMetaData;

public class MainClass {
    public static WebDriver driver;
    public static Enter enter;
    @BeforeClass
    void setUp() throws IOException {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "\"D:\\chromedriver.exe\"");
        driver.manage().window().maximize();
        driver.get("https://uk-ua.facebook.com/" );
    }
    @BeforeMethod
    public void sestUp() {
        enter = new Enter(driver);
        driver.get("https://uk-ua.facebook.com/");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test(dataProvider="DP")
    public void login(String login,String password,String xpass,String number) throws InterruptedException {
        enter.login(login);
        enter.password(password);
        enter.eye();
        enter.enterBtn();
        enter.checkError(xpass);
        enter.nummberPeople(number);
    }
    @DataProvider(name="DP")
    public String[][] feedDP()
    {
        String data[][]=readDB();
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {

            }
        }
        return data;
    }


    private static String url = "jdbc:mysql://127.0.0.1:3306/testingbd?useSSL=false";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    private static Connection con;
    private String[][] inputArr;

    public String[][]  readDB( )
    {
        try {
            Class.forName(driverName);
            try {
                //Create a connection to DB by passing Url,Username,Password as parameters
                con = DriverManager.getConnection(url, username, password);
                Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                //Executing the Queries
                //stmt.executeUpdate("INSERT INTO testDB.employee VALUES ('john',2000)");
                //stmt.executeUpdate("truncate table testDB.employee");
                ResultSet rs = stmt.executeQuery("SELECT * FROM `dataproviderwithdb`");
                rs.last();
                int rows = rs.getRow();

                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                int cols = rsmd.getColumnCount();
                System.out.println(rows +"--" + cols);
                inputArr= new String[rows][cols];

                int i =0;
                rs.beforeFirst();
                //Iterating the data in the Table
                while (rs.next())
                {
                    for(int j=0;j<cols;j++)
                    {
                        inputArr[i][j]=rs.getString(j+1);

                    }
                    System.out.println();
                    i++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Driver not found.");
        }
        return inputArr;

    }
}