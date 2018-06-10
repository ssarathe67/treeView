package com.tree.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tree.project.dao.NodeDAO;
import com.tree.project.model.Node;

@RestController
@RequestMapping("/treeview")
public class TreeController {
	
	@Autowired
	NodeDAO nodeDAO;
	
	@RequestMapping(value = "/getTreeNodes", method = RequestMethod.GET)
	public Map<String, Object> getOneStepPayInvoice() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Node> nodeList = nodeDAO.findAll();
			map.put("value", nodeList);
			map.put("code", "success");
		} catch (Exception ex) {
			map.put("code", "error");
			map.put("errorResponse", ex.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/addUpdateNode", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addNode(@RequestBody Node node) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Integer> childrens = new ArrayList<Integer>();
			for(int i=1;i<=node.getChildcount();i++) {
				int random = ThreadLocalRandom.current().nextInt(node.getUpperBound(), node.getLowerBound() + 1);
				childrens.add(random);
			}
			node.setChildren(childrens);
			if(StringUtils.isEmpty(node.getId())) {
				node.setCreatedDate(new Date());
			}
			nodeDAO.save(node);
			map.put("code", "success");
		} catch (Exception ex) {
			map.put("code", "error");
			map.put("errorResponse", ex.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value = "/deleteNode", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteNode(@RequestBody String nodeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Node node = nodeDAO.findById(nodeId);
			nodeDAO.delete(node);
			map.put("code", "success");
		} catch (Exception ex) {
			map.put("code", "error");
			map.put("errorResponse", ex.getMessage());
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
}
