package com.driving.notification.mapper;

import com.driving.notification.entity.MessageRef;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author YueLiMin
 */
@Repository
public class MessageRefMapper {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String insert(MessageRef entity) {
        entity = mongoTemplate.save(entity);
        return entity.get_id();
    }

    public long searchUnreadCount(long userId, String identity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("readFlag").is(false).and("receiverId").is(userId).and("receiverIdentity").is(identity));
        return mongoTemplate.count(query, MessageRef.class);
    }

    public long searchLastCount(long userId, String identity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastFlag").is(true).and("receiverId").is(userId).and("receiverIdentity").is(identity));
        Update update = new Update();
        update.set("lastFlag", false);
        UpdateResult result = mongoTemplate.updateMulti(query, update, "message_ref");
        return result.getModifiedCount();
    }

    public long updateUnreadMessage(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("readFlag", true);
        UpdateResult result = mongoTemplate.updateFirst(query, update, "message_ref");
        return result.getModifiedCount();
    }

    public long deleteMessageRefById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, "message_ref");
        return result.getDeletedCount();
    }

    public long deleteUserMessageRef(long userId, String identity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("receiverId").is(userId).and("receiverIdentity").is(identity));
        DeleteResult result = mongoTemplate.remove(query, "message_ref");
        return result.getDeletedCount();
    }
}
