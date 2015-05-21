package org.gurinder.project.Services;

import org.gurinder.project.Dao.ProfileDao;
import org.gurinder.project.DaoImpl.ProfileDaoImpl;
import org.gurinder.project.Entity.Profile;

import java.util.List;

/**
 * Created by gurinder on 20/5/15.
 */
public class ProfileService {
    ProfileDao profieDaoImpl;

    public ProfileService(String rootUrl) {
        profieDaoImpl = new ProfileDaoImpl(rootUrl);

    }

    public boolean addUserNode(Profile profile) {

        return profieDaoImpl.createUser(profile);
    }

    public List<Profile> getBlocked(String profileId) {
        return profieDaoImpl.getBlocked(profileId);
    }

    public List<Profile> getBlockedBy(String profileId) {
        return profieDaoImpl.getBlockedBy(profileId);
    }

    public List<Profile> getFavorite(String profileId) {
        return profieDaoImpl.getFavorite(profileId);
    }

    public List<Profile> geFavoriteBy(String profileId) {
        return profieDaoImpl.getFavoriteBy(profileId);
    }

}
