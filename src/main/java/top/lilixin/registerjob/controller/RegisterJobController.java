package top.lilixin.registerjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.lilixin.registerjob.service.RegisterJobService;

/**
 * 查询   通过消费消息插入到数据库中的数据  的接口
 */
/**
*@Author: lilixin
*@Date: 2016年10月18日
*/
@Controller
@RequestMapping("reg")
public class RegisterJobController {
	@Autowired
	private RegisterJobService registerJobService;

	@RequestMapping("data")
	@ResponseBody
	public Object regData(){
		return registerJobService.getRegisterDataByParam();
	}
}
