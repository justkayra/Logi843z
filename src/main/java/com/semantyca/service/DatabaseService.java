package com.semantyca.service;

import com.semantyca.dao.AssigneeRepository;
import com.semantyca.dao.UserRepository;
import com.semantyca.model.Assignee;
import com.semantyca.model.user.User;
import com.semantyca.util.EntityGenerator;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class DatabaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger("DatabaseService");
    private EntityGenerator generator;

    private UserRepository userRepository;
    private AssigneeRepository assigneeRepository;

    @Inject
    public DatabaseService(Jdbi jdbi) {
        userRepository = new UserRepository(jdbi);
        assigneeRepository = new AssigneeRepository(jdbi, userRepository);
        generator = new EntityGenerator(userRepository, assigneeRepository);
    }


    public List<User> getUsers() {
        return userRepository.findAllUnrestricted(100, 0);
    }


    public boolean populateUsers() {
        try {
            generator.generateUsers().forEach(user -> {
                userRepository.bareInsert(user);
            });
        } catch (UnableToExecuteStatementException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }


    public boolean populateAssignees() {
        try {
            generator.generateAssignees().forEach(assignee -> {
                System.out.println(assignee.getName());
                assigneeRepository.bareInsert(assignee);
            });

        } catch (UnableToExecuteStatementException | IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean purgeUsers() {
        List<User> userList = userRepository.findAllUnrestricted(999
                , 0);
        for (User user: userList) {
            userRepository.bareDelete(user);
        }
        return true;
    }

    public boolean purgeAssignees() {
        List<Assignee> assigneeList = assigneeRepository.findAllUnrestricted(999
                , 0);
        for (Assignee assignee: assigneeList) {
            assigneeRepository.bareDelete(assignee);
        }
        return true;
    }

}
