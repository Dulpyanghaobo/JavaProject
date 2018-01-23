package utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class JDBCUtils {
	private static String url = "jdbc:mysql://localhost:3307/regist_web";
    private static String user = "root";
    private static String passwd = "19971104yhb";
    private static String driver = "com.mysql.jdbc.Driver";
   
	public static ComboPooledDataSource getDataSource() throws Exception{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( driver);
        cpds.setJdbcUrl( url );
        cpds.setUser(user);                                  
        cpds.setPassword(passwd);  
        cpds.setMinPoolSize(5);                                     
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(30);
        cpds.setMaxIdleTime(60);
		return cpds;
	}
}
