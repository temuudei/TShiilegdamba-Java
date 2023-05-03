package org.example.dal;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DbTestConfig {
    @Bean
    public DataSource getDataSource() {
        MysqlDataSource result = new MysqlDataSource();
        result.setUrl("jdbc:mysql://localhost:3306/contactdb_test");
        result.setUser("root");
        result.setPassword("top-secret-password");
        return result;
    }

    @Bean
    JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
