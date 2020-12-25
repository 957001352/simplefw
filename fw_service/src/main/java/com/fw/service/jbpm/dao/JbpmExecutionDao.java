package com.fw.service.jbpm.dao;

import com.fw.entity.jbpm.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 审核任务办理
 * @author lpsong
 * @since 2020-10-22
 */
@Repository
public interface JbpmExecutionDao {

    /**
     *
     *实例化流程实例
     * @param jbpmExecution
     * @return
     */
    Integer insertExecution(JbpmExecution jbpmExecution);



    /**
     *
     *更新流程任务状态
     * @param jbpmExecution
     * @return
     */
    Integer updateExecution(JbpmExecution jbpmExecution);
    /**
     *插入待办任务
     * @param jbpmTask
     * @return
     */
    Integer insertTask(JbpmTask jbpmTask);


    /**
     * 删除待办任务
     * @param executionId
     * @return
     */
    Integer deleteWaitTask(Integer executionId);

    /**
     * 任务办理
     *
     * @param jbpmHistTask
     * @return
     */
    Integer insertHistHask(JbpmHistTask jbpmHistTask);

    /**
    * 查询第一个流程审核节点
     * @param jbpmId
    * @return
    */
    JbpmDeployprop findFirstJbpmNode(@Param("jbpmId") Integer jbpmId);

    /**
    * 查询下一个流程审核节点
     * @param jbpmId
     * @param noteId
    * @return
    */
    JbpmDeployprop findNextJbpmNode(@Param("jbpmId") Integer jbpmId,@Param("noteId") Integer noteId);


    /**
    * 待办任务列表查询
     * @param auditUser
     * @param jbpmNo
     * @param userIds
     * @param startTime
     * @param endTime
    * @return
    */
    List<JbpmExecution> findWaitTaskList(@Param("auditUser") Integer auditUser,
                                         @Param("jbpmNo") String jbpmNo,
                                         @Param("userIds") List<Integer> userIds,
                                         @Param("startTime") String startTime,
                                         @Param("endTime") String endTime);


    /**
     * 已办任务列表查询
     * @param auditUser
     * @param jbpmNo
     * @param userIds
     * @param startTime
     * @param endTime
     * @return
     */
    List<JbpmExecution> findHistTaskList(@Param("auditUser") Integer auditUser,
                                         @Param("jbpmNo") String jbpmNo,
                                         @Param("userIds") List<Integer> userIds,
                                         @Param("startTime") String startTime,
                                         @Param("endTime") String endTime);
    /**
    * 已办任务明细查询
     * @param executionId
    * @return
    */
    List<JbpmHistTask> findHistTaskDeatilList(@Param("executionId") Integer executionId);

    /**
     * 查询各类正在审核的审核单
     * @param formCode
     * @return
     */
    List<JbpmExecution> findChecking(@Param("formCode") String formCode);
    /**
     * 编码查询
     * @param
     * @return
     */
    String findCode(String  code);

}
