import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Primary
    @Bean
    public DataSource getDataSource() {
        PoolDataSource pds = null;
        try {
            pds = PoolDataSourceFactory.getPoolDataSource();
            pds.setConnectionFactoryClassName(driverClassName);
            pds.setURL(url);
            pds.setUser(userName);
            pds.setPassword(password);
        } catch (SQLException ex) {
            System.err.println("Error connection to database: " + ex.getMessage());
        }
        return pds;
    }
}
