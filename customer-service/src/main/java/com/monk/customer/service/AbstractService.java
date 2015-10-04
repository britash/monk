package com.monk.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.monk.customer.service.dal.Mapper;
import com.monk.customer.service.dal.entity.AbstractEntity;

public abstract class AbstractService<T extends AbstractEntity,V>{
	private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

	private Mapper<T,V> mapper;
	
	public void setMapper(Mapper<T, V> mapper) {
		this.mapper = mapper;
	}
	
	public T get(Long id) {
		log.debug("Enter (id[{}])", id);
		T entity = mapper.get(id);
		log.debug("Exit ({})", entity);
		return entity;
	}
	
	
	public T getDetail(V v) {
		log.debug("Enter ]", v);
		
		T entity = mapper.getDetailByExample(v);

		log.debug("Exit ({})", entity);
		return entity;
	}
	
	
	
	public T getByEntity(T entity) {
		log.debug("Enter (entity[{}])", entity);
		
		T result = mapper.getByEntity(entity);

		log.debug("Exit ({})", result);
		return result;
	}
	
	@Transactional
	public int delete(Long id) {
		log.debug("Enter (id[{}])", id);
		int num = mapper.delete(id);
		log.debug("Exit ({})", num);
		return num;
	}
	
	@Transactional
	public int deleteEntity(T entity,String userId) {
		log.debug("Enter (id[{}])", entity);
		int num = 0;
		if(entity != null){
			num = mapper.deleteEntity(entity);
		}
		log.debug("Exit ({})", num);
		return num;
	}
	
	@Transactional
	public int save(T entity) {
		log.debug("Enter ([{}])", entity.toString());
		int num = mapper.insert(entity);
		log.debug("Exit({})",  num);
		return num;
	}
	@Transactional
	public int save(T entity,String userId) {
		log.debug("Enter ([{}])", entity.toString());
		int num = 	mapper.insert(entity);
		log.debug("Exit({})",  num);
		return num;
	}
	@Transactional
	public int update(T entity) {
		log.debug("Enter ([{}])", entity.toString());
		int num = mapper.update(entity);
		log.debug("Exit ({})",  num);
		return num;
	}
	@Transactional
	public int update(T entity,String userId) {
		log.debug("Enter ([{}])", entity.toString());
		int num = 0;
		num = mapper.update(entity);
		log.debug("Exit ({})",  num);
		return num;
	}
	
	public List<T> findByExample(V v){
		log.debug("Enter ([{}])", v.toString());
		List<T> list = mapper.findByExample(v);
		list = (list == null ? new ArrayList<T>(0) : list);
		log.debug("Exit(list.size {},{})",list.size(),v.toString());
		return list;
	}
	
	public Integer countByExample(V v){
		log.debug("Enter ([{}])", v.toString());
		Integer cnt = mapper.countByExample(v);
		cnt = (cnt == null ? 0 : cnt);
		log.debug("Exit(cnt {},v{})", cnt,v.toString());
		return cnt;
	}
}
