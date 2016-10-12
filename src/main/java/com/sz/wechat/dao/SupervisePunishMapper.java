package com.sz.wechat.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.sz.wechat.entity.SupervisePunish;
/**
 * 监管评分
 * @author sway
 */
@Repository
public interface SupervisePunishMapper {

	/**
	 * 通过企业名称获取监管信息
	 * @param caseName
	 * @return
	 */
	public List<SupervisePunish> getSuperviseLikeCompanyName(SupervisePunish supervisePunish);
}
