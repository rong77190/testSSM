package com.xiechur.dao;

import com.xiechur.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    /**
     * 修改用户
     * @param student
     * @return影响的记录数
     */
    public int update(Student student);
    /**
     * 用户查询
     * @param map
     * @return用户集合
     */
    public List<Student> find(Map<String, Object> map);

    /**
     * 获取总记录数
     * @param map
     * @return获取的total数
     */
    public Long getTotal(Map<String, Object> map);
    /**
     * 删除用户
     * @param id
     * @return
     */
    public int delete(Integer id);
}