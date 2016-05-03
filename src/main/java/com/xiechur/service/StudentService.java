package com.xiechur.service;

import com.xiechur.model.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/5/2.
 */
public interface StudentService {
    /**
     * 添加用户
     * @param student
     * @return影响的记录数
     */
    public int add(Student student);
    /**
     * 修改用户
     * @param student
     * @return影响的记录数
     */
    public int update(Student student);
    /**
     * 用户查询
     * @param map
     * @return
     */
    public List<Student> find(Map<String, Object> map);

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String, Object> map);
    /**
     * 删除用户
     * @param id
     * @return
     */
    public int delete(Integer id);

}
