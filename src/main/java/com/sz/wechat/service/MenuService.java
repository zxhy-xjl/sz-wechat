package com.sz.wechat.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.sz.wechat.dao.MenuMapper;
import com.sz.wechat.entity.Menu;

/**
 * 菜单数据逻辑层
 * @author sway
 */
@Service
public class MenuService {

	/**
	 * 菜单服务接口
	 */
	@Autowired
	private MenuMapper menuMapper;
	
	/**
	 * 菜单服务接口
	 * @return
	 */
	public List<Menu> getMenu(String companyCode){
		return this.menuMapper.getMenu(companyCode);
	}
	
	/**
	 * 根据主键编号获取菜单信息
	 * @param menuId
	 * @return
	 */
	public Menu getMenuByMenuId(String menuId){
		return this.menuMapper.getMenuByMenuId(menuId);
	}
	
	/**
	 * 执行插入
	 * @param menu
	 * @return
	 */
	@Async
	public int doInsertBlob(Menu menu){
		return this.menuMapper.doInsertBlob(menu);
	}
}
