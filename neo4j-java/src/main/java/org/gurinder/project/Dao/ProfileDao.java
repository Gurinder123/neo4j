package org.gurinder.project.Dao;

import org.gurinder.project.Entity.Profile;
import org.gurinder.project.Entity.User;
import org.neo4j.graphdb.Result;

import java.util.List;

/**
 * Created by gurinder on 20/5/15.
 */
public interface ProfileDao {

    public boolean createUser(Profile profile);

    public List<Profile> getBlocked(String profileId);

    public List<Profile> getBlockedBy(String profileId);

    public List<Profile> getFavorite(String profileId);

    public List<Profile> getFavoriteBy(String profileId);

}
