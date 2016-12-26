package com.zhu.dao;

import com.mongodb.MongoClient;
import com.zhu.dao.baseconfig.MongoDAO;
import com.zhu.entity.User;
import com.zhu.entity.query.UserCondition;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhu on 2016/11/24.
 */
@Repository
public class AppUserDao extends MongoDAO<User, String, UserCondition> {
    @Autowired
    protected AppUserDao(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
        ensureIndexes();
    }

    @Override
    protected Query<User> getCommonCondQuery(UserCondition userCondition) {
        return null;
    }

    @Override
    public List<User> query(UserCondition userCondition) {
        Query<User> query = createConditionQuery(userCondition);
        if (userCondition.getId() != null) {
            query.field("id").equal(userCondition.getId());
        }
        return query.asList();
    }

    @Profiled
    public User getUserById(String id){
        return getById(id);
    }

    @Profiled
    public User queryByEmail(String email) {
        User user = getDatastore().createQuery(User.class).field("email").equal(email).get();
        return user;
    }
}
