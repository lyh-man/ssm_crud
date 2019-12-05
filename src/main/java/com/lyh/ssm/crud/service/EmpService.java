package com.lyh.ssm.crud.service;

import com.lyh.ssm.crud.bean.Employee;
import com.lyh.ssm.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * ��ȡĳ��Ա������Ϣ
     *
     * @param id ĳ��Ա����id
     * @return ĳ��Ա������Ϣ
     */
    public Employee getEmpById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * ��ȡ����Ա������Ϣ
     *
     * @return ����Ա������Ϣ
     */
    public List<Employee> getAllEmp() {
        return employeeMapper.selectAll();
    }

    /**
     * ����ĳ��Ա������Ϣ
     *
     * @param emp ĳԱ������Ϣ
     * @return ���ز���Ӱ�������
     */
    public Integer insertEmp(Employee emp) {
        return employeeMapper.insertSelective(emp);
    }

    /**
     * ɾ��ĳ��Ա������Ϣ
     *
     * @param id Ա����id
     * @return ����ɾ��Ӱ�������
     */
    public Integer deleteEmpById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * ����ĳ��Ա������Ϣ
     * @param employee Ա����Ϣ
     * @return �����޸�Ӱ�������
     */
    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }
}
