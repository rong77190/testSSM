package com.xiechur.controller;

import com.xiechur.model.PageBean;
import com.xiechur.model.Student;
import com.xiechur.service.StudentService;
import com.xiechur.util.ResponseUtil;
import com.xiechur.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/5/2.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Resource
    private StudentService studentService;


    /**
     * 删除用户
     * @param ids
     * @param res
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="ids") String ids,HttpServletResponse res) throws Exception{
        String[] idStr = ids.split(",");
        JSONObject jsonObject = new JSONObject();
        for (String id : idStr) {
            studentService.delete(Integer.parseInt(id));
        }
        jsonObject.put("success", true);
        ResponseUtil.write(res, jsonObject);
        return null;
    }

    @RequestMapping("/save")
    public String save(Student student,HttpServletResponse res) throws Exception{
        //操作记录条数，初始化为0
        int resultTotal = 0;
        if (student.getId() == null) {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            String date = format.format(now);
            student.setCreatedate(date);
            resultTotal = studentService.add(student);
        }else{
            resultTotal = studentService.update(student);
        }
        JSONObject jsonObject = new JSONObject();
        if(resultTotal > 0){   //说明修改或添加成功
            jsonObject.put("success", true);
        }else{
            jsonObject.put("success", false);
        }
        ResponseUtil.write(res, jsonObject);
        return null;
    }


    /*
    * 分页查询
    * */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page",required = false)String page,@RequestParam(value = "rows",required = false)String rows,Student s_student,HttpServletResponse response)throws Exception{
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_student.getName()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Student> studentList=studentService.find(map);
        Long total=studentService.getTotal(map);
        JSONObject result=new JSONObject();
        JSONArray jsonArray= JSONArray.fromObject(studentList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }
}
