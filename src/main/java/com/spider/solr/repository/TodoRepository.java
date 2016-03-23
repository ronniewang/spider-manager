package com.spider.solr.repository;

import com.spider.solr.Todo;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface TodoRepository extends SolrCrudRepository<Todo, String> {

}