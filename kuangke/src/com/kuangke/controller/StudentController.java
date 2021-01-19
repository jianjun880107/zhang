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
 * ������ѧ��ϵͳ-ѧ��������
 * @author 
 */
public class StudentController {
	@Resource StudentDao dao;
	//��ѯѧ����Ϣ
	@RequestMapping(value="query")
	public ModelAndView query(){
		System.out.println("-------��ѯ��ʼ------");
		List<Student> student = dao.find();
		ModelAndView model = new ModelAndView();
		model.addObject("student",student);
		model.setViewName("student");
		return model;
	}
	//����
	@RequestMapping(value="insert")
	public String insert(Student student){
		System.out.println("-------����-----");
		dao.doInsert(student);
		return "redirect:/student/query";
		//return new ModelAndView("redirect:/query");
	}
	//ɾ��
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(@RequestParam(value="id")String id){
		dao.delById(Integer.parseInt(id));
		return "ok";
	}
	//����
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	@ResponseBody
	public String modify(@RequestBody Map map){
		System.out.println("------�����޸�----");
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
