package com.semantyca.util;


import com.semantyca.model.Assignee;
import com.semantyca.model.user.AnonymousUser;
import com.semantyca.model.user.User;
import com.semantyca.server.EnvConst;
import com.semantyca.dao.AssigneeRepository;
import com.semantyca.dao.UserRepository;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EntityGenerator {
    private static final String FIRST_NAME_SOURCE = "Roman.txt";
    private static final String LAST_NAME_SOURCE = "Simple.txt";
    private UserRepository userRepository;
    private AssigneeRepository assigneeRepository;

    public EntityGenerator(UserRepository userRepository, AssigneeRepository assigneeRepository) {
        this.userRepository = userRepository;
        this.assigneeRepository = assigneeRepository;
    }

    public List<User> generateUsers() {
        List users = new ArrayList();
        String[] data = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"};
        for (int i = 0; i < data.length; i++) {
            Optional<User> userEntity = userRepository.findByLogin(data[i]);
            if (userEntity.isEmpty()) {
                User entity = new User();
                ZonedDateTime currentMoment = ZonedDateTime.now();
                entity.setRegDate(currentMoment);
                entity.setLogin(data[i]);
                entity.setPwd(EnvConst.INITIAL_PWD);
                entity.setLastModifiedDate(currentMoment);
                entity.setAuthor(AnonymousUser.ID);
                entity.setLastModifier(AnonymousUser.ID);
                entity.setTitle(entity.getLogin());
                ArrayList<String> roles = new ArrayList();
                roles.add("supervisor");
                roles.add("manager");
                entity.setRoles(roles);
                users.add(entity);
            }
        }
        return users;
    }

    public List<Assignee> generateAssignees() throws IOException {
        NameGenerator nameGeneratorLastName = new NameGenerator(LAST_NAME_SOURCE);
        NameGenerator nameGeneratorFirstName = new NameGenerator(FIRST_NAME_SOURCE);
        List entities = new ArrayList();
        List<User> users = userRepository.findAllUnrestricted(100, 0);
        for (User user : users) {
            Optional<Assignee> assigneeOptional = assigneeRepository.findByLogin(user.getLogin());
            if (assigneeOptional.isEmpty()) {
                Assignee entity = new Assignee();
                ZonedDateTime currentMoment = ZonedDateTime.now();
                entity.setRegDate(currentMoment);
                entity.setLastModifiedDate(currentMoment);
                entity.setAuthor(AnonymousUser.ID);
                entity.setLastModifier(AnonymousUser.ID);
                entity.setRank(NumberUtil.getRandomNumber(1, 10));
                entity.setName(nameGeneratorLastName.compose(3) + " " + nameGeneratorFirstName.compose(3));
                entity.setTitle(entity.getName());
                entity.setUser(user);
                entities.add(entity);
            }
        }
        return entities;
    }


}
