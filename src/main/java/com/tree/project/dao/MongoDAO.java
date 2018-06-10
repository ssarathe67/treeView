package com.tree.project.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public abstract class MongoDAO<E> {
	private MongoTemplate mongoTemplate;

	Class<E> type = null;

	public MongoDAO(Class<E> type) {
		this.type = type;
	}

	public List<E> findAll() {
		return mongoTemplate.findAll(type);
	}

	public E findById(String id) {
		return mongoTemplate.findById(id, type);
	}

	public void save(E obj) {
		mongoTemplate.save(obj);
	}

	public void delete(E obj) {
		mongoTemplate.remove(obj);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
