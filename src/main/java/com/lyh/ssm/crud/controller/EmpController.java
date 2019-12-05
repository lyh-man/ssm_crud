package com.lyh.ssm.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.ssm.crud.bean.Employee;
import com.lyh.ssm.crud.model.BaseModel;
import com.lyh.ssm.crud.model.EmpModel;
import com.lyh.ssm.crud.model.EmpPageModel;
import com.lyh.ssm.crud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * ��ȡ��ҳ������
     * @param pn ��ȡ�ڼ�ҳ������
     * @return ��������
     */
    @GetMapping("/emps/{pn}")
    public EmpPageModel getAllEmp(@PathVariable Integer pn) {
        // step1:�����ҳ���(PageHelper)
        // step2:ÿ�β�ѯǰ�����ò�ѯ��ҳ���Լ���ѯ��������ÿ�λ�ȡ5������
        PageHelper.startPage(pn, 5);
        // step3:ִ�з�ҳ��ѯ
        List<Employee> employeeList = empService.getAllEmp();
        // step4:��װ��ѯ�������
        PageInfo pageInfo = new PageInfo(employeeList);
        EmpPageModel empPageModel = new EmpPageModel();
        if (employeeList.size() <= 0) {
            empPageModel.addMessage("��ȡ��ҳ����ʧ��");
            empPageModel.setSuccess(false);
            empPageModel.setLevel(BaseModel.Level.error);
            return empPageModel;
        }
        empPageModel.addMessage("��ȡ��ҳ���ݳɹ�");
        empPageModel.setSuccess(true);
        empPageModel.setLevel(BaseModel.Level.info);
        empPageModel.setPageInfo(pageInfo);
        return empPageModel;
    }

    /**
     * ��ȡĳ��Ա������Ϣ
     * REST -- GET
     * @param id Ա����id
     * @return ��������
     */
    @GetMapping("/emp/{id}")
    public EmpModel getEmpById(@PathVariable Integer id) {
        EmpModel empModel = new EmpModel();
        if (empService.getEmpById(id) == null) {
            empModel.addMessage("��ȡԱ����Ϣʧ��");
            empModel.setSuccess(false);
            empModel.setLevel(BaseModel.Level.error);
            return empModel;
        }
        empModel.addMessage("��ȡԱ����Ϣ�ɹ�");
        empModel.setSuccess(true);
        empModel.setLevel(BaseModel.Level.info);
        empModel.addEmployee(empService.getEmpById(id));
        return empModel;
    }

    /**
     * ɾ��ĳ��Ա������Ϣ
     * REST -- DELETE
     * @param id Ա����id
     * @return ��������
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public BaseModel deleteEmpById(@PathVariable Integer id) {
        BaseModel baseModel = new BaseModel();
        if (empService.deleteEmpById(id) != 0) {
            baseModel.addMessage("ɾ��Ա����Ϣ�ɹ�");
            baseModel.setSuccess(true);
            baseModel.setLevel(BaseModel.Level.info);
            return baseModel;
        }
        baseModel.addMessage("ɾ��Ա����Ϣʧ��");
        baseModel.setSuccess(false);
        baseModel.setLevel(BaseModel.Level.error);
        return baseModel;
    }

    /**
     * ��Ա�����в���Ա����Ϣ
     * REST -- POST
     * ʹ�� @RequestBody ��ע�⣬ǰ̨���ݵĲ�����Ҫ�� Employee ��Ĳ�������Ӧ��������ղ���ֵ
     * @param employee Ա����Ϣ
     * @return ��������
     */
    @PostMapping("/emp")
    public BaseModel insertEmp(@RequestBody Employee employee) {
        BaseModel baseModel = new BaseModel();
        if (empService.insertEmp(employee) != 0) {
            baseModel.addMessage("����Ա����Ϣ�ɹ�");
            baseModel.setSuccess(true);
            baseModel.setLevel(BaseModel.Level.info);
            return baseModel;
        }
        baseModel.addMessage("����Ա����Ϣʧ��");
        baseModel.setSuccess(false);
        baseModel.setLevel(BaseModel.Level.error);
        return baseModel;
    }

    /**
     * ����Ա����Ϣ
     * REST -- PUT
     * @param employee Ա����Ϣ
     * @return ��������
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public BaseModel updateEmp(@RequestBody Employee employee) {
        BaseModel baseModel = new BaseModel();
        if (empService.updateEmp(employee) != 0) {
            baseModel.addMessage("����Ա����Ϣ�ɹ�");
            baseModel.setSuccess(true);
            baseModel.setLevel(BaseModel.Level.info);
            return baseModel;
        }
        baseModel.addMessage("����Ա����Ϣʧ��");
        baseModel.setSuccess(false);
        baseModel.setLevel(BaseModel.Level.error);
        return baseModel;
    }
}
