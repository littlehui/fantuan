package com.littlehui.fantuan.zk.listbox.request;

/**
 * Created by littlehui on 2016/12/15.
 */
import com.littlehui.fantuan.zk.listbox.model.SortDirection;

import java.util.Collection;

/**
 *
 * @author cossaer.f
 */
public interface PagingModelRequest<T> {
    Collection<T> getContent(int activePage, int pageSize, String sortField, SortDirection sortDirection) throws CurrentPageExceedException;
    long getTotalSize();
}
