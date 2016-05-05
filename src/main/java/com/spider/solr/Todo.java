package com.spider.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

/**
 * Created on 2016/3/23-9:26.
 *
 * @author ronnie
 */
public class Todo {

    @Id
    @Field
    private String id;

    @Field
    private String description;

    @Field
    private String title;

    public Todo() {

    }

    public Todo(String id, String description, String title) {

        this.id = id;
        this.description = description;
        this.title = title;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }
}
