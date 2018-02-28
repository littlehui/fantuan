package com.littlehui.fantuan.zk.listbox.model;

/**
 * Created by littlehui on 2016/12/15.
 */

import com.littlehui.fantuan.zk.listbox.request.CurrentPageExceedException;
import com.littlehui.fantuan.zk.listbox.request.PagingModelRequest;

import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author cossaer.f
 */
public class PagingModel<T> {

    private String sortField;
    private PagingModelRequest pagingModelRequest;
    private SortDirection sortDirection;

    public PagingModel(PagingModelRequest request) {
        this(request, null, null);
    }

    public PagingModel(PagingModelRequest request, String sortField) {
        this(request, sortField, null);
    }

    public PagingModel(PagingModelRequest request, String sortField, SortDirection sortDirection) {
        Objects.requireNonNull(request, "PageModelRequest can't be null.");
        this.pagingModelRequest = request;
        this.sortField = sortField;
        this.sortDirection = sortDirection == null ? SortDirection.ASCENDING : sortDirection;
    }

    public Collection<T> getContent(int activePage, int pageSize) throws CurrentPageExceedException {
        return pagingModelRequest.getContent(activePage, pageSize, sortField, sortDirection);
    }

    public long getTotalSize() {
        return pagingModelRequest.getTotalSize();
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public PagingModelRequest getPagingModelRequest() {
        return pagingModelRequest;
    }

    public void setPagingModelRequest(PagingModelRequest request) {
        Objects.requireNonNull(request, "PageModelRequest can't be null.");
        this.pagingModelRequest = request;
    }
}
