package com.kuangke.dao;
import org.springframework.stereotype.Service;
import java.util.List;
import com.kuangke.domain.Student;
import com.kuangke.domain.User;

/***
 * 旷课网学生系统-学生dao层
 * @author 老彭 
 */
@Service
public interface StudentDao {
	public Student findById();
	public List<Student> find();
	public void doInsert(Student student);
	public void doUpdate(Student student);
	public void delById(int id);
}
