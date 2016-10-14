package com.sz.wechat.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Menu> getMenu(){
		return this.menuMapper.getMenu();
	}
}
