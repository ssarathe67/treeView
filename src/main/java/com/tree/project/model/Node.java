package com.tree.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Node implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    private String id;
	private String nodeName;
	private List<Integer> children;
	private Integer childcount;
	private Integer upperBound;
	private Integer lowerBound;
	private Date createdDate;
	
	public Node() {}
    public Node(String nodeName, List<Integer> children, Integer upperBound, Integer lowerBound) {
        this.nodeName = nodeName;
        this.children = children;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public List<Integer> getChildren() {
		return children;
	}
	public void setChildren(List<Integer> children) {
		this.children = children;
	}
	public Integer getChildcount() {
		return childcount;
	}
	public void setChildcount(Integer childcount) {
		this.childcount = childcount;
	}
	public Integer getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(Integer upperBound) {
		this.upperBound = upperBound;
	}
	public Integer getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(Integer lowerBound) {
		this.lowerBound = lowerBound;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
