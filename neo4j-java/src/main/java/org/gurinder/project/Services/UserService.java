package org.gurinder.project.Services;

import org.gurinder.project.Dao.UserDao;
import org.gurinder.project.DaoImpl.UserDaoImpl;
import org.gurinder.project.Entity.User;
import org.neo4j.graphdb.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserDao userDaoImpl = new UserDaoImpl();


    public Result addUserNode(User user) {

        return userDaoImpl.createUser(user);
    }

    public Result updateUserNode(User user) {

        return userDaoImpl.updateUser(user);
    }

    public User getUserByUserName(String userName) {
        return userDaoImpl.getUserByUserName(userName);
    }

    public List<User> getBlocked(String userName) {
        return userDaoImpl.getBlocked(userName);
    }

    public List<User> getFavorite(String userName) {
        return userDaoImpl.getFavorite(userName);
    }

    public List<User> getBlockedBy(String userName) {
        return userDaoImpl.getBlockedBy(userName);
    }

    public List<User> getFavoriteBy(String userName) {
        return userDaoImpl.getFavoriteBy(userName);
    }
}
