package org.gurinder.project.Dao;

import org.neo4j.graphdb.Result;
import org.gurinder.project.Entity.User;

import java.util.List;

public interface UserDao {
	public User getUserByUserName(String userName);

	public Result createUser(User user);

	public Result updateUser(User user);

	public List<User> getBlocked(String userName);

	public List<User> getFavorite(String userName);

	public List<User> getBlockedBy(String userName);

	public List<User> getFavoriteBy(String userName);

}
