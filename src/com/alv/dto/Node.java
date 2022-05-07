package com.alv.dto;

import java.util.List;

public class Node {

	private String node;
	private String content;
	private Node parent;
	private String rawContent;
	
	public Node(String node, String content) {
		this.node = node;
		this.content = content;
	}
	
	private List<Node> children;
	
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public String getRawContent() {
		return rawContent;
	}
	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}
	
	
}
