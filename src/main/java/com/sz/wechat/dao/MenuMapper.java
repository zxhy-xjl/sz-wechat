package com.sz.wechat.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.sz.wechat.entity.Menu;
/**
 * 菜单服务操作接口
 * @author sway
 *
 */
@Repository
public interface MenuMapper {
	
	/**
	 * 获取所有菜单信息
	 * @return
	 */
	public List<Menu> getMenu();

}
