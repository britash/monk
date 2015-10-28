package com.monk.customer.service.dal;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper<T, V> extends Mapper<T, V>{

	
	public Integer countCustomerForAdmin(@Param("example") V entityExample);

	public List<T> findCustomerForAdmin(@Param("example") V entityExample);
}
