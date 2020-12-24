package com.kuangke.dao;
import org.springframework.stereotype.Service;
import java.util.List;
import com.kuangke.domain.Student;
import com.kuangke.domain.User;

/***
 * ������ѧ��ϵͳ-ѧ��dao��
 * @author ���� 
 */
@Service
public interface StudentDao {
	public Student findById();
	public List<Student> find();
	public void doInsert(Student student);
	public void doUpdate(Student student);
	public void delById(int id);
}
