package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.PageDTO;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.log.annotation.Log;
import com.huacainfo.ace.common.plugin.sms.hxrt.HxrtApi;
import com.huacainfo.ace.common.plugin.sms.hxrt.HxrtHttpKit;
import com.huacainfo.ace.common.threadpool.RunThread;
import com.huacainfo.ace.common.threadpool.ThreadPool;
import com.huacainfo.ace.common.threadpool.ThreadProcess;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.vo.UserProp;
import com.huacainfo.ace.portal.dao.QueueCmccHisMapper;
import com.huacainfo.ace.portal.dao.QueueCmccWaitMapper;
import com.huacainfo.ace.portal.dao.TaskCmccMapper;
import com.huacainfo.ace.portal.model.QueueCmccHis;
import com.huacainfo.ace.portal.model.QueueCmccWait;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service("taskCmccService")
public class TaskCmccServiceImpl implements TaskCmccService, ThreadProcess {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private TaskCmccMapper taskCmccMapper;
    @Autowired
    private QueueCmccWaitMapper queueCmccWaitMapper;
    @Autowired
    private QueueCmccHisMapper queueCmccHisMapper;


    @Value("${sms.hxrt.req}")
    private String req;

    @Value("${sms.hxrt.pwd}")
    private String pwd;

    @Value("${sms.hxrt.sourceadd}")
    private String sourceadd;

    @Value("${sms.hxrt.url}")
    private String url;

    @Value("${sms.hxrt.suffix}")
    private String suffix;


    private ThreadPool threadPool;

    public TaskCmccServiceImpl() {
        logger.info("启动线程池->初始化 " + 5);
        threadPool = new ThreadPool(this, 5, 20, 1000 * 10);
        threadPool.start();
    }

    @Override
    public PageDTO<TaskCmccVo> findTaskCmccList(TaskCmccQVo condition, int start, int limit, String orderBy) {
        PageDTO<TaskCmccVo> rst = new PageDTO<>();
        List<TaskCmccVo> list = this.taskCmccMapper.findList(condition, start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.taskCmccMapper.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    @Transactional
    @Log(operationObj = "添加短信任务", operationType = "短信任务", detail = "创建短信发送任务")
    public ResponseDTO insertTaskCmcc(TaskCmcc o, UserProp userProp) {
        o.setCreateTime(new Date());
        o.setCreateUserId(userProp.getUserId());
        o.setStatus("0");
        o.setTaskId(System.currentTimeMillis() + "");
        if (CommonUtils.isBlank(o.getTaskId())) {
            return new ResponseDTO(ResultCode.FAIL, "任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new ResponseDTO(ResultCode.FAIL, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new ResponseDTO(ResultCode.FAIL, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsg())) {
            return new ResponseDTO(ResultCode.FAIL, "短信不能为空！");
        }

        this.taskCmccMapper.insert(o);
        return new ResponseDTO(ResultCode.SUCCESS, "添加短信任务完成！");
    }

    @Override
    public ResponseDTO insertTaskCmcc(TaskCmcc o) {
        o.setCreateTime(new Date());
        o.setStatus("0");
        o.setTaskId(String.valueOf(System.currentTimeMillis()));
        if (CommonUtils.isBlank(o.getTaskId())) {
            return new ResponseDTO(ResultCode.FAIL, "任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new ResponseDTO(ResultCode.FAIL, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new ResponseDTO(ResultCode.FAIL, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsg())) {
            return new ResponseDTO(ResultCode.FAIL, "短信不能为空！");
        }

        String[] telArr = o.getTel().split(";");
        for (int i = 0; i < telArr.length; i++) {
            if (telArr[i] != null && telArr[i].indexOf(",") != -1) {
                QueueCmccWait q = new QueueCmccWait();
                q.setCreateTime(new Date());
                q.setMsg(o.getMsg());
                q.setQueueId(UUID.randomUUID().toString());
                q.setName(telArr[i].split(",")[1]);
                q.setTel(telArr[i].split(",")[0]);
                if (!threadPool.dataQueue.contains(q)) {
                    threadPool.addData(q);
                    this.logger.info("====================短信通道==================");
                    logger.info("add new task 1");
                    logger.info("dataQueue " + threadPool.dataQueue.procSize());
                    logger.info("threadSize " + threadPool.getThreadList().size());
                }
            }

        }

        return new ResponseDTO(ResultCode.SUCCESS, "短信已发送，请注意查收！");
    }

    @Override
    @Log(operationObj = "变更短信任务", operationType = "短信任务", detail = "变更短信任务")
    public ResponseDTO updateTaskCmcc(TaskCmcc o, UserProp userProp) {
        o.setCreateTime(new Date());
        o.setCreateUserId(userProp.getUserId());
        o.setStatus("0");
        if (CommonUtils.isBlank(o.getTaskId())) {
            return new ResponseDTO(ResultCode.FAIL, "任务编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getTaskName())) {
            return new ResponseDTO(ResultCode.FAIL, "任务名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getTel())) {
            return new ResponseDTO(ResultCode.FAIL, "手机号不能为空！");
        }
        if (CommonUtils.isBlank(o.getMsg())) {
            return new ResponseDTO(ResultCode.FAIL, "短信不能为空！");
        }
        this.taskCmccMapper.updateByPrimaryKey(o);
        return new ResponseDTO(ResultCode.SUCCESS, "变更短信任务完成！");
    }

    @Override
    @Log(operationObj = "删除短信任务", operationType = "短信任务", detail = "删除短信任务")
    public ResponseDTO deleteTaskCmccByTaskCmccId(String id, UserProp userProp) {
        ResponseDTO rst = new ResponseDTO();
        this.taskCmccMapper.deleteByPrimaryKey(id);
        return rst;
    }

    @Override
    @Log(operationObj = "重新发送短信", operationType = "短信任务", detail = "重新发送短信")
    public ResponseDTO updateTaskStatusCmccByTaskCmccId(String id, UserProp userProp) {
        ResponseDTO rst = new ResponseDTO();
        this.taskCmccMapper.updateStatusByPrimaryKey(id, "0");
        return rst;
    }

    @Override
    @Scheduled(fixedDelay = 5000)
    public void queueTask() {
        this.logger.info("queueTask executed");
        List<TaskCmcc> list = this.taskCmccMapper.selectByTask();
        for (TaskCmcc o : list) {
            String[] telArr = o.getTel().split(";");
            for (int i = 0; i < telArr.length; i++) {
                if (telArr[i] != null && telArr[i].indexOf(",") != -1) {
                    QueueCmccWait q = new QueueCmccWait();
                    q.setCreateTime(new Date());
                    q.setMsg(o.getMsg());
                    q.setQueueId(GUIDUtil.getGUID());
                    q.setName(telArr[i].split(",")[1]);
                    q.setTel(telArr[i].split(",")[0]);
                    queueCmccWaitMapper.insert(q);
                }

            }
            this.taskCmccMapper.updateStatusByPrimaryKey(o.getTaskId(), "1");
        }
    }

    @Override
    @Scheduled(fixedDelay = 5000)
    public void queueTaskDetail() {
        this.logger.info("===============================start dispatch for task queueTaskDetail=================================");
        this.logger.info("threadPool.dataSize:" + threadPool.dataSize());
        if (threadPool.dataSize() < 1) {
            List<QueueCmccWait> list = this.taskCmccMapper.selectQueueByTask();
            for (QueueCmccWait o : list) {
                if (!threadPool.dataQueue.contains(o)) {
                    threadPool.addData(o);
                }
            }
            logger.info("add new task " + list.size());
            logger.info("dataQueue " + threadPool.dataQueue.procSize());
            logger.info("threadSize " + threadPool.getThreadList().size());
        }

    }

    /**
     * 业务处理回调方法
     *
     * @param rt
     * @param obj
     * @throws UnsupportedEncodingException
     */
    @Override
    public void execute(RunThread rt, Object obj) {
        this.logger.info("execute start");
        QueueCmccWait o = (QueueCmccWait) obj;
        QueueCmccHis record = new QueueCmccHis();
        record.setAddTime(o.getCreateTime());
        record.setCreateTime(new Date());
        record.setMsg(o.getMsg());
        record.setName(o.getName());
        record.setQueueId(o.getQueueId());
        record.setTel(o.getTel());
        record.setRemark("OK");

        Map<String, String> params = new HashMap<String, String>();
        params.put("req", req);
        params.put("pwd", pwd);
        params.put("sourceadd", sourceadd);
        params.put("phone", o.getTel());
        params.put("content", o.getMsg());
        String strSmsParam = null;
        try {
            strSmsParam = "reg=" + params.get("req") + "&pwd=" + params.get("pwd") + "&sourceadd="
                    + params.get("sourceadd") + "&phone=" + params.get("phone") + "&content="
                    + HxrtHttpKit.paraTo16(params.get("content"));
        } catch (UnsupportedEncodingException e) {
            this.logger.error(e);
        }
        String p = HxrtApi.requestPost(url, strSmsParam);
        this.logger.info(url);
        this.logger.info(p);
        record.setRemark(p);

        this.queueCmccHisMapper.batchInsert(record);
    }

    @Override
    public ResponseDTO<TaskCmcc> selectById(String id) {
        return new ResponseDTO(ResultCode.SUCCESS, "查询成功", this.taskCmccMapper.selectByPrimaryKey(id));
    }

    /**
     * 添加短信发送任务
     *
     * @param taskName  任务名称
     * @param message   发送给内容
     * @param mobileStr 目标号码 :nickName1,mobile1;nickName2,mobile2;
     * @return 发送结果
     */
    @Override
    public ResponseDTO addTaskCmcc(String taskName, String message, String mobileStr) {
        TaskCmcc o = new TaskCmcc();
        Map<String, Object> msg = new HashMap<>();
        msg.put("taskName", taskName);
        msg.put("msg", message);
        msg.put("tel", mobileStr);
        CommonBeanUtils.copyMap2Bean(o, msg);

        return insertTaskCmcc(o);
    }

    /**
     * 执行时间超过指定时间的回调方法
     *
     * @param rt
     */
    @Override
    public void timeOut(RunThread rt) {

    }

    @Override
    @Scheduled(fixedDelay = 5000)
    public void workFlowMsgTask() {
        logger.info("workFlowMsgTask");
    }

}
