package org.gurinder.project.DaoImpl;

import org.gurinder.project.Dbfactory.DatabaseNeo4j;
import org.gurinder.project.Entity.User;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.gurinder.project.Dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {


    @Override
    public User getUserByUserName(String userName) {
        GraphDatabaseService db = DatabaseNeo4j.getDatabase();
        User user = new User();
        try {

            Result result = db.execute("match (n) where n.userName = \"" + userName + "\" return n");
            Iterator<Node> n_column = result.columnAs("n");

            if (!n_column.hasNext())
                return null;
            Node node = n_column.next();
            user.setAge(node.getProperty("age").toString());
            user.setCity(node.getProperty("city").toString());
            user.setSurname(node.getProperty("userName").toString());
            return user;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }

    @Override
    public Result createUser(User user) {

        GraphDatabaseService db = DatabaseNeo4j.getDatabase();


        try (Transaction tx = db.beginTx();) {
            Result result = db.execute("merge (me: User {userName:'" + user.getSurname() + "', age:'" + user.getAge() + "',city:'" + user.getCity() + "'})"
                    + " return me");
            tx.success();
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }

    @Override
    public Result updateUser(User user) {
        GraphDatabaseService db = DatabaseNeo4j.getDatabase();


        try (Transaction tx = db.beginTx();) {
            Result result = db.execute("MATCH (n { userName: '" + user.getSurname() + "' SET n.age = '" + user.getAge() + "', n.city = '" + user.getCity() + "'  RETURN n })");

            tx.success();
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }

    @Override
    public List<User> getFavorite(String userName) {
        GraphDatabaseService db = DatabaseNeo4j.getDatabase();
        List<User> list = new ArrayList<User>();

        try (Transaction tx = db.beginTx();) {

            Result result = db.execute("MATCH (me:User {surname:'" + userName + "'})-[b:FAVORITE]->(other:User) RETURN other");
            Iterator<Node> n_column = result.columnAs("other");

            if (!n_column.hasNext())
                return null;
            while (n_column.hasNext()) {
                Node node = n_column.next();
                User user = new User();
                user.setAge(String.valueOf(node.getProperty("age")));
                user.setCity(String.valueOf((node.getProperty("city"))));
                user.setSurname(String.valueOf(node.getProperty("surname")));
                list.add(user);
                tx.success();

            }
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }

    @Override
    public List<User> getBlocked(String userName) {
        GraphDatabaseService db = DatabaseNeo4j.getDatabase();
        List<User> list = new ArrayList<User>();

        try (Transaction tx = db.beginTx();) {

            Result result = db.execute("MATCH (me:User {surname:'" + userName + "'})-[b:BLOCKED]->(other:User) RETURN other");
            Iterator<Node> n_column = result.columnAs("other");

            if (!n_column.hasNext())
                return null;
            while (n_column.hasNext()) {
                Node node = n_column.next();
                User user = new User();
                user.setAge(String.valueOf(node.getProperty("age")));
                user.setCity(String.valueOf((node.getProperty("city"))));
                user.setSurname(String.valueOf(node.getProperty("surname")));
                list.add(user);
                tx.success();

            }
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }

    @Override
    public List<User> getBlockedBy(String userName) {
        GraphDatabaseService db = DatabaseNeo4j.getDatabase();
        List<User> list = new ArrayList<User>();

        try (Transaction tx = db.beginTx();) {

            Result result = db.execute("MATCH (me:User)-[b:BLOCKED]->(other:User {surname:'" + userName + "'}) RETURN me");
            Iterator<Node> n_column = result.columnAs("me");

            if (!n_column.hasNext())
                return null;
            while (n_column.hasNext()) {
                Node node = n_column.next();
                User user = new User();
                user.setAge(String.valueOf(node.getProperty("age")));
                user.setCity(String.valueOf((node.getProperty("city"))));
                user.setSurname(String.valueOf(node.getProperty("surname")));
                list.add(user);
                tx.success();

            }
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }

    @Override
    public List<User> getFavoriteBy(String userName) {
        GraphDatabaseService db = DatabaseNeo4j.getDatabase();
        List<User> list = new ArrayList<User>();

        try (Transaction tx = db.beginTx();) {

            Result result = db.execute("MATCH (me:User)-[b:FAVORITE]->(other:User {surname:'" + userName + "'}) RETURN me");
            Iterator<Node> n_column = result.columnAs("me");

            if (!n_column.hasNext())
                return null;
            while (n_column.hasNext()) {
                Node node = n_column.next();
                User user = new User();
                user.setAge(String.valueOf(node.getProperty("age")));
                user.setCity(String.valueOf((node.getProperty("city"))));
                user.setSurname(String.valueOf(node.getProperty("surname")));
                list.add(user);
                tx.success();

            }
            return list;
        } catch (Exception e) {
            return null;
        } finally {
            db.shutdown();
        }
    }
}

