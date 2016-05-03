package com.xiechur.service.impl;

import com.xiechur.dao.StudentDao;
import com.xiechur.model.Student;
import com.xiechur.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/5/2.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;
    @Override
    public int add(Student student) {
        return studentDao.insertSelective(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public List<Student> find(Map<String, Object> map) {
        return studentDao.find(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return studentDao.getTotal(map);
    }
    @Override
    public int delete(Integer id) {
        return studentDao.delete(id);
    }
}
