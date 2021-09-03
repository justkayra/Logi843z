package com.semantyca.configuration;


import com.semantyca.dao.IAssigneeDAO;
import com.semantyca.dao.IUserDAO;
import com.semantyca.dao.system.IRLSEntryDAO;
import com.semantyca.model.IUser;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class DbConfiguration {


    public IUserDAO userDao(Jdbi jdbi) {
        return jdbi.onDemand(IUserDAO.class);
    }


    Map<Integer, IUser> allUsers (IUserDAO userDAO) {
        Map<Integer, IUser> users = new HashMap();
        for (IUser user : userDAO.findAllUnrestricted(999, 0)) {
            users.put(user.getId(), user);
        }
        return users;
    }

    public IRLSEntryDAO getRlsEntryDao(Jdbi jdbi) {
        return jdbi.onDemand(IRLSEntryDAO.class);
    }

    public IAssigneeDAO assigneeDAO(Jdbi jdbi) {
        return jdbi.onDemand(IAssigneeDAO.class);
    }


}
