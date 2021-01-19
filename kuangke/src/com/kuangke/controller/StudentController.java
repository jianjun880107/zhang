package com.kuangke.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.kuangke.dao.StudentDao;
import com.kuangke.domain.Student;


@Controller
@RequestMapping(value="student")
/***
 * 旷课网学生系统-学生控制器
 * @author 
 */
public class StudentController {
	@Resource StudentDao dao;
	//查询学生信息
	@RequestMapping(value="query")
	public ModelAndView query(){
		System.out.println("-------查询开始------");
		List<Student> student = dao.find();
		ModelAndView model = new ModelAndView();
		model.addObject("student",student);
		model.setViewName("student");
		return model;
	}
	//插入
	@RequestMapping(value="insert")
	public String insert(Student student){
		System.out.println("-------新增-----");
		dao.doInsert(student);
		return "redirect:/student/query";
		//return new ModelAndView("redirect:/query");
	}
	//删除
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(@RequestParam(value="id")String id){
		dao.delById(Integer.parseInt(id));
		return "ok";
	}
	//更新
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ResponseBody
	public String modify(@RequestBody Map map){
		System.out.println("------进入修改----");
		System.out.println(map.get("id"));
		Student student=new Student();
		String id = (String)map.get("id");
		student.setId(id);
		student.setAge((String)map.get("age"));
		student.setName((String)map.get("name"));
		student.setSex((String)map.get("sex"));
		student.setMemo((String)map.get("memo"));
		dao.doUpdate(student);
		//return "redirect:/student/query";

		return "ok";
	}


}
