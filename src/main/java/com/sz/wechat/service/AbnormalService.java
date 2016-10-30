package com.sz.wechat.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sz.wechat.dao.AbnormalMapper;
import com.sz.wechat.entity.Abnormal;
/**
 * 异常信息管理
 * @author li.lisheng
 *
 */
@Service
public class AbnormalService {
	@Autowired
	private AbnormalMapper abnormalMapper;
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Abnormal> getAll(){
		return this.abnormalMapper.getAll();
	}
	/**
	 * 插入一条记录
	 * @param abnormal
	 */
	public void insert(Abnormal abnormal){
		abnormal.setCloseDate(null);
		this.abnormalMapper.insert(abnormal);
	}
	public void findAbnormal(String openId, String companyCode, String companyName, boolean noRecode,boolean noLicence){
		Abnormal abnormal=new Abnormal();
		abnormal.setPid(UUID.randomUUID().toString());
		abnormal.setOpenId(openId);
		abnormal.setCompanyCode(companyCode);
		abnormal.setCompanyName(companyName);
		abnormal.setNoRecode(noRecode?"1":"0");
		abnormal.setNoLicence(noLicence?"1":"0");
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		String abnormalDate = dateFormater.format(date);
		abnormal.setAbnormalDate(abnormalDate);
		this.insert(abnormal);
	}
	/**
	 * 关闭某个异常记录，代表政府已经知道了这个事情
	 * @param pid
	 */
	public void close(String pid){
		String closeDate = "";
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		closeDate = dateFormater.format(date);
		this.abnormalMapper.close(pid, closeDate);
	}
	

}
