package com.shijing.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shijing.bean.TbPackage;
import com.shijing.service.PackageManager;

//套餐相关操作
public class PackageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 定义request,response对象
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	// packageManager注入
	private PackageManager packageManager;
	// 保存查询的套餐数据
	private List<TbPackage> packageList;

	public List<TbPackage> getPackageList() {
		return packageList;
	}

	public void setPackageManager(PackageManager packageManager) {
		this.packageManager = packageManager;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	// 套餐列表
	public void packageList() throws IOException {
		packageList = packageManager.getPackageList();
		response = (HttpServletResponse) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		JSONArray jsonArray = JSONArray.fromObject(packageList);
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	// 添加套餐
	public void packageAdd() throws IOException {
		request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    response = (HttpServletResponse) ActionContext
				.getContext().get(
						org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		String packageNameString=request.getParameter("tcmc");
		String priceOriginalString = request.getParameter("tcyj");
		String priceCurrentString=request.getParameter("tcxj");
		String infoString = request.getParameter("xxjs");
//		String realpath = request.getServletContext().getRealPath("/images");
		String str = packageManager.addPackage(packageNameString,priceOriginalString,priceCurrentString, infoString);

//		if (photoUrl != null) {
//			File savefile = new File(new File(realpath), photoName);
//			if (!savefile.getParentFile().exists())
//				savefile.getParentFile().mkdirs();
//			FileUtils.copyFile(photoUrl, savefile);
//			ActionContext.getContext().put("message", "文件上传成功");
//		}
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().print(str);
	}

	// 根据套餐Id查询套餐信息
	public void packageQueryTC() throws IOException {
		request = (HttpServletRequest) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		response = (HttpServletResponse) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		Integer pcId = Integer.parseInt(request.getParameter("pid"));
		packageList = packageManager.queryPackage(pcId);
		JSONArray jsonArray = JSONArray.fromObject(packageList);
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().print(jsonArray);
	}

	// 根据套餐Id删除套餐信息
	public void packageDeleteTc() throws IOException {
		request = (HttpServletRequest) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		response = (HttpServletResponse) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		Integer pcId = Integer.parseInt(request.getParameter("id"));
		String str = packageManager.deletePackage(pcId);
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().print(str);
	}

	// 修改套餐信息
	public void packageUpdate() throws IOException {
		request = (HttpServletRequest) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		response = (HttpServletResponse) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		Integer pcId = Integer.parseInt(request.getParameter("id"));
		String priceString = request.getParameter("tcyj");
		String currentPriceString = request.getParameter("tcxj");
		String infoString = request.getParameter("xxjs");
		String packageName=request.getParameter("tcmc");
		String str = packageManager.updatePacekage(pcId, priceString,
				currentPriceString, infoString, packageName);

		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getWriter().print(str);
	}
}
