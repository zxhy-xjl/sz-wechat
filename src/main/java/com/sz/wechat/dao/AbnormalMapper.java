package com.sz.wechat.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sz.wechat.entity.Abnormal;
import com.sz.wechat.entity.CodeDict;
/**
 * 异常信息
 * @author li.lisheng
 *
 */
@Repository
public interface AbnormalMapper {
	

public List<Abnormal> getAll();
public void insert(Abnormal abnormal);
public void close(@Param("pid") String pid, @Param("closeDate") String closeDate);

}
