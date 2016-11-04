package com.sz.wechat.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.sz.wechat.entity.CodeDict;
/**
 * 字典服务接口
 * @author sway
 *
 */
@Repository
public interface CodeDictMapper {
	
	/**
	 * 通过字典类型获取字典信息
	 * @return
	 */
	public List<CodeDict> getDictByType(String type);
	
	
	/**
	 * 获取所有字典信息
	 * @return
	 */
	public  List<CodeDict> getAllDict();

}
