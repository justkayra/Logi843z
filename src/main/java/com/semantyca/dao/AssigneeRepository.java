package com.semantyca.dao;

import com.semantyca.model.Assignee;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;

public class AssigneeRepository {

    private Jdbi jdbi;

    private  UserRepository userRepository;

    public AssigneeRepository(Jdbi jdbi, UserRepository userRepository) {
        this.jdbi = jdbi;
        this.userRepository = userRepository;
    }

    public Optional<Assignee> findByLogin(String login) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM assignees, users WHERE assignees.user_id = users.id AND users.login = '" + login + "'")
                        .map(new AssigneesMapper(userRepository)).findFirst());
    }


    public List<Assignee> findAllUnrestricted(int limit, int offset) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM assignees LIMIT " + limit + " OFFSET " + offset)
                        .map(new AssigneesMapper(userRepository)).list());
    }

    public void bareInsert(Assignee assignee) {
        jdbi.useHandle(handle -> {
            handle.registerArgument(new RolesArgumentFactory());
            handle.createUpdate("INSERT INTO assignees (reg_date, title, author, last_mod_date, last_mod_user, rank, name, user_id)" +
                    "VALUES (:regDate, :title, :author, :lastModifiedDate, :lastModifier, :rank, :name, :userId )")
                    .bindBean(assignee)
                    .execute();
        });
    }

    public void bareDelete(Assignee assignee) {
        jdbi.useHandle(handle -> {
            //handle.registerArgument(new UUIDArgumentFactory());
            handle.createUpdate("DELETE FROM assignees WHERE id = :id")
                    .bind("id", assignee.getId())
                    .execute();
        });
    }


}
