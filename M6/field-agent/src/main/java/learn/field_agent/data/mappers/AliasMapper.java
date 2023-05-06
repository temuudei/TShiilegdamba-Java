package learn.field_agent.data.mappers;

import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AliasMapper implements RowMapper<Alias> {
    @Override
    public Alias mapRow(ResultSet resultSet, int i) throws SQLException {
        Alias alias = new Alias();
        alias.setAlias_id(resultSet.getInt("alias_id"));
        alias.setName(resultSet.getString("alias_name"));
        alias.setPersona(resultSet.getString("persona"));
        alias.setAgent_id(resultSet.getInt("agent_id"));
        return alias;
    }
}