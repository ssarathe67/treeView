package com.tree.project.dao;

import com.tree.project.model.Node;

public class NodeDAO extends MongoDAO<Node>{

	public NodeDAO() {
		super(Node.class);
	}
	
	
}
