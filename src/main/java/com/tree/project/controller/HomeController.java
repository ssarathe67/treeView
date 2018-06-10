package com.tree.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tree.project.dao.NodeDAO;
import com.tree.project.model.Node;

@Controller
public class HomeController {
	
	@Autowired
	NodeDAO nodeDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "treeView";
	}
	
	@MessageMapping("/treeApp")
    @SendTo("/topic/getFactories")
    public List<Node> greeting(Node message) throws Exception {
        Thread.sleep(3000); // simulated delay
        List<Node> nodeList = nodeDAO.findAll();
        return nodeList;
    }
	
}