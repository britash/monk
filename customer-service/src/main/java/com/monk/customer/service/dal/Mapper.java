package com.monk.customer.service.dal;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Mapper<T, V> {

	int insert(T entity);

	int update(T entity);

	int delete(Long id);

	public T get(@Param("id") Long id);

	public Integer countByExample(@Param("example") V entityExample);

	public List<T> findByExample(@Param("example") V entityExample);
}