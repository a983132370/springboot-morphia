package com.zhu.dao.baseconfig;

import com.mongodb.MongoClient;
import com.zhu.entity.query.base.Condition;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.perf4j.aop.Profiled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by zhu
 */
public abstract class MongoDAO<T,K,C extends Condition> extends BasicDAO<T, K> {
	private static Logger log = LoggerFactory.getLogger(MongoDAO.class);

    protected Query<T> query;

    static{
        MorphiaLoggerFactory.reset();
        //MorphiaLoggerFactory.registerLogger(SLF4JLoggerImplFactory.class);
    }

    protected MongoDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
        super(mongoClient, morphia, dbName);
        ensureIndexes();
    }

    /**
     * common query
     * @param c
     * @return
     */
    protected abstract Query<T> getCommonCondQuery(C c);

    /**
     * query by condition
     * @param c
     * @return
     */
    public abstract List<T> query(C c);


    protected Query<T> createConditionQuery(Condition cond){
        query = createQuery();
        if(cond!=null){
            if(cond.getRetrievedFields()!=null){
                query.retrievedFields(cond.isFieldsIncluded(), cond.getRetrievedFields());
            }
            if(cond.getStatus()!=null){
                query.field("status").equal(cond.getStatus());
            }
        }
        return query;

    }

    /**
     * 添加实体
     * @param t
     */
    @Profiled
    public String add(T t) {
        Key<T> save = save(t);
        return save.getId().toString();
    }


    /**
     * 根据ID获得实体详情
     * @param id
     * @return
     */
	@Profiled
	@Cacheable(value = "entity", key = "#id", unless = "#result == null")
    public T getById(String id) {
        //query Id
        final Query<T> queryById = createQuery()
                .field("id").equal(new ObjectId(id));
        return findOne(queryById);
    }


	/**
	 * 根据ID获得实体详情，无缓存版
	 * @author luyu
	 * @param id
	 * @return
	 */
	@Profiled
	public T getByIdWithOutCache(String id) {
		//query Id
		final Query<T> queryById = createQuery()
				.field("id").equal(new ObjectId(id));
		return findOne(queryById);
	}
	/**
	 * 批量添加
	 * @param ts
	 * @return
	 */
	public List<String> add(T... ts) {
		Iterable<Key<T>> keyIt = getDatastore().save(ts);
		List<String> list = new ArrayList<String>(ts.length);
		for (Key<T> key : keyIt) {
			list.add(key.toString());
		}
		return list;
	}

	/**
	 * 根据指定ID删除文档
	 * @param id
	 */
	@Profiled
	@CacheEvict(value="entity", key="#id")
	public void remove(String id) {
		// query Id
		final Query<T> circlesById = createQuery().field("id").equal(new ObjectId(id));
		deleteByQuery(circlesById);
	}

    /**
     * 更新状态
     */
	@Profiled
	@CacheEvict(value="entity", key="#id")
    public void updateStatus(String id, Integer status){
        final Query<T> query = createQuery().field("id").equal(new ObjectId(id));
        UpdateOperations<T> updateOperations = createUpdateOperations().set("status", status);
        update(query, updateOperations);
    }

	/**
	 * 按指定ID查询实体详情
	 * @param ids
	 */
	@Profiled
	public List<T> queryByIds(List<String> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.<T>emptyList();
		}
		// id map to ObjectId
		List<ObjectId> objIds = new ArrayList<ObjectId>();
		for (String id : ids) {
			objIds.add(new ObjectId(id));
		}
		final Query<T> circleByIds = createQuery().field("id").in(objIds);
		return this.find(circleByIds).asList();
	}
	
	@Profiled
	public <S> List<S> queryByIds(List<String> ids, Class<S> entityClass) {
		if (ids == null || ids.isEmpty()) {
			return Collections.<S>emptyList();
		}
		// id map to ObjectId
		List<ObjectId> objIds = new ArrayList<ObjectId>();
		for (String id : ids) {
			objIds.add(new ObjectId(id));
		}
		return getDs().createQuery(entityClass).field("id").in(objIds).asList();
	}

}
