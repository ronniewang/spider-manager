package com.spider.manager.service;

import com.spider.manager.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubLookupService {

    RestTemplate restTemplate = new RestTemplate();

    public User findUser(String user) throws InterruptedException {

        System.out.println("Looking up " + user);
        User results = restTemplate.getForObject("https://api.github.com/users/" + user, User.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(5000L);
        return results;
    }

}