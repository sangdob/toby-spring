package toby.spring.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
public class DBConnection {
    private final DataSource dataSource;
    private Connection con = null;

    public Connection getConnection() {
        try {
            this.con = DataSourceUtils.getConnection(dataSource);
            return this.con;
        } catch (CannotGetJdbcConnectionException e) {
            log.info("connection Error = {}", e);
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            log.info("illegal Error = {}", e);
            throw new IllegalStateException(e);
        }
    }

    public void close() {
        try {
            this.con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
