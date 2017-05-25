package org.v1.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BatchController<T, ID extends Serializable> {
	
	protected JpaRepository<T, ID> rep;
	
	@Autowired
	public BatchController(JpaRepository<T, ID> rep) {
        this.rep = rep;
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public T findById(@PathVariable("id") ID id) {
		T t = rep.findOne(id);
		return t;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<T> all() {
		return  rep.findAll();
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public Map<String, Object> save(@RequestBody T entity) {
		rep.save(entity);	
		return getSucessMessage();
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") ID id) {
		rep.delete(id);
		return getSucessMessage();
	}
	
	protected Map<String, Object> getSucessMessage(){
		Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", true);
        return m;
	}
	
	protected Map<String, Object> getErrorMessage() {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", false);
        return m;
	}
}
