package striker.studing.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionPool {
    private static DataSource source;

    static {
        try {
            Context context = new InitialContext();
            source = (DataSource) context.lookup("java:comp/env/connectionPoolDataSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    private ConnectionPool(){}

    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
