package com.hex.bigdata.udsp.rc.service;

import com.hex.bigdata.udsp.common.api.model.Property;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.rc.alarm.Alarm;
import com.hex.bigdata.udsp.rc.alarm.model.Config;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 告警服务
 */
@Service
public class RcAlarmService extends BaseService {
    private static Logger logger = LogManager.getLogger (RcAlarmService.class);

    @Autowired
    private GFDictMapper gfDictMapper;
    @Autowired
    private ComPropertiesService comPropertiesService;

    /**
     * 发送警报信息
     *
     * @param rcUserService
     * @param message
     * @throws Exception
     */
    public void send(RcUserService rcUserService, String message) throws Exception {
        Alarm alarm = getAlarmImpl (rcUserService.getAlarmType ());
        if (alarm != null) {
            Config config = getAlarmConfig (comPropertiesService.selectList (rcUserService.getPkId ()));
            alarm.send (config, message);
        }
    }

    /**
     * 得到警报接口的实例
     *
     * @param alarmType
     * @return
     */
    private Alarm getAlarmImpl(String alarmType) {
        if ("NONE".equals (alarmType)) {
            return null;
        }
        GFDict gfDict = gfDictMapper.selectByPrimaryKey ("RC_IMPL_CLASS", alarmType);
        String implClass = gfDict.getDictName ();
        return (Alarm) ObjectUtil.newInstance (implClass);
    }

    private Config getAlarmConfig(List<ComProperties> comPropertiesList) {
        if (comPropertiesList == null || comPropertiesList.size () == 0) {
            return new Config ();
        }
        List<Property> comPropertyList = new ArrayList<> (comPropertiesList.size ());
        for (ComProperties properties : comPropertiesList) {
            Property property = new Property ();
            property.setName (properties.getName ());
            property.setValue (properties.getValue ());
            property.setDescribe (properties.getDescribe ());
            comPropertyList.add (property);
        }
        return new Config (comPropertyList);
    }
}
