package com.fw.service.board.service.impl;

import com.fw.entity.board.DeviceParam;
import com.fw.domain.Result;
import com.fw.entity.board.ProductParam;
import com.fw.service.board.service.BoradService;
import com.fw.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @Description:    看板管理
* @Author:         gchen
* @CreateDate:     2020/10/19 16:14
*/
@Service
@Slf4j
public class BoradServiceImpl implements BoradService {


    @Override
    public Result overView(String codes) {
        if(codes == null){
            return ResultUtils.error("参数错误");
        }
        Map map = new HashMap<>();
        String[] codeArray = codes.split(",");
        int load = 0; //正在生产
        int standBy = 0; //待机
        int closeDown = 0; //正常停机
        int onlineMaintain = 0; //在线保养
        int maintain = 0; //维修
        int downMaintain = 0; //停机保养
        int aberrant = 0; //异常待处理
        for (String code:codeArray) {
            DeviceParam deviceParam = new DeviceParam();
            ProductParam productParam = new ProductParam("--","--","--","--","--","--","--","--","--","--");
            deviceParam.setProductParam(productParam);//设置机器生产参数
            deviceParam.setRunStatus((int) (Math.random()*7));//设置设备运行状态
            switch (deviceParam.getRunStatus()){
                case 0:
                    load++;
                    break;
                case 1:
                    standBy++;
                    break;
                case 2:
                    closeDown++;
                    break;
                case 3:
                    onlineMaintain++;
                    break;
                case 4:
                    maintain++;
                    break;
                case 5:
                    downMaintain++;
                    break;
                case 6:
                    aberrant++;
                    break;
                case 7:
                    log.error("设备:{},状态为:{},状态错误",code,deviceParam.getRunStatus());
                    break;
            }
            map.put(code,deviceParam);
        }
        map.put("load",load);
        map.put("standBy",standBy);
        map.put("closeDown",closeDown);
        map.put("onlineMaintain",onlineMaintain);
        map.put("maintain",maintain);
        map.put("downMaintain",downMaintain);
        map.put("aberrant",aberrant);
        return ResultUtils.success(map);
    }
}
