package com.sz.wechat.pagination;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.ui.ModelMap;

import com.github.pagehelper.PageInfo;
import com.sz.wechat.vo.DataGridData;

/**
 * 分页处理帮助类
 * @author sway
 *
 */
public class DataGridHepler {
	/**
	 * 处理分页
	 * @param list
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ModelMap addDataGrid(List list,ModelMap modelMap){
		return addDataGrid(list, new PageInfo(list).getTotal(), modelMap);
	}
	/**
	 * 增加分页
	 * @param list
	 * @param total
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ModelMap addDataGrid(List list, long total,ModelMap modelMap){
		DataGridData dgd = new DataGridData();
		dgd.setRows(list);
		dgd.setTotal(total);
		if (modelMap == null){
			modelMap = new ModelMap();
		}
		modelMap.addAttribute("result", dgd);
		return modelMap;
	}
	/**
	 * 从request中获取分页参数
	 * @param request
	 * @return
	 */
	public static PageParam parseRequest(HttpServletRequest request){
		PageParam pageParam = new PageParam();
		pageParam.setPage(NumberUtils.toInt(request.getParameter("page"),1));
		pageParam.setPageSize(NumberUtils.toInt(request.getParameter("rows"),10));
		return pageParam;
	}
}
