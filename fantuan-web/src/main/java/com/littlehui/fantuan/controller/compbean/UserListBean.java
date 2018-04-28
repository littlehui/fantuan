package com.littlehui.fantuan.controller.compbean;

import lombok.Getter;
import lombok.Setter;
import org.zkoss.zul.*;

/**
 * @author littlehui
 * @date 2018/4/26
 */
public class UserListBean {

    @Getter
    @Setter
    Window userListWin;

    @Getter
    @Setter
    Textbox searchBox;

    @Getter
    @Setter
    Button searchButton;

    @Getter
    @Setter
    Listbox userListBox;

    @Getter
    @Setter
    Listitem selectedItem;

}
