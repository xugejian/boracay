package com.hex.bigdata.udsp.rts.executor.model;

import java.util.Date;
import java.util.List;

/**
 * Created by junjiem on 2017-2-21.
 */
public class MessageData {
    private List<Column> datas;
    private Date time;

    public List<Column> getDatas() {
        return datas;
    }

    public void setDatas(List<Column> datas) {
        this.datas = datas;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
