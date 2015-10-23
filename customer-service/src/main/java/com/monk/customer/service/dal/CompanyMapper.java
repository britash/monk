package com.monk.customer.service.dal;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMapper<T, V> extends Mapper<T, V>{

	
	public int insertUser(@Param("userId") Long userId,@Param("companyId") Long companyId);
	public int updateUser(@Param("userId") Long userId,@Param("companyId") Long companyId,@Param("userOldId") Long userOldId,@Param("companyOldId") Long companyOldId);
	public int deleteUser(@Param("userId") Long userId,@Param("companyId") Long companyId);
}
