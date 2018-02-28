package com.littlehui.fantuan.controller.compbean;

import lombok.Data;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

/**
 * Created by littlehui on 2016/10/14 0014.
 */
@Data
public class IndexBean {

    private Window indexWin;

    private Include userPriceListInclude;

    private Include userPriceLogInclude;

    private Label loginUserName;

}
