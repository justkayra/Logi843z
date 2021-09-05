package com.semantyca.dao;

import com.semantyca.model.Assignee;
import com.semantyca.model.user.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AssigneesMapper extends AbstractMapper<Assignee> {

    UserRepository userRepository;

    public AssigneesMapper(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Assignee map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Assignee entity = new Assignee();
        transferIdUUID(entity, rs);
        transferCommonData(entity, rs);
        entity.setName(rs.getString("name"));
        entity.setRank(rs.getInt("rank"));
        Optional<User> userOptional = userRepository.findById(rs.getInt("user_id"));
        if (userOptional.isEmpty()) {
            entity.setUser(userOptional.get());
        }
        return entity;
    }
}
