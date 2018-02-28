package com.littlehui.fantuan.zk.listbox.component;

import com.littlehui.fantuan.zk.listbox.model.PagingModel;
import com.littlehui.fantuan.zk.listbox.model.SortDirection;
import com.littlehui.fantuan.zk.listbox.request.CurrentPageExceedException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.annotation.ComponentAnnotation;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.SortEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import java.lang.reflect.Field;
import java.util.*;


/**
 *
 * @author littlehui
 */
@ComponentAnnotation({"selectedItems:@ZKBIND(ACCESS=both,SAVE_EVENT=onSelect)",
        "selectedItem:@ZKBIND(ACCESS=both,SAVE_EVENT=onSelect)"})
public class PagingListbox extends Idspace implements AfterCompose {

    private String pagingPosition = "top";
    private String emptyMessage = "";
    private String template = null;
    private boolean multiple = false;
    private boolean checkmark = false;
    private boolean detailed = true;
    private PagingModel pagingModel;
    private int pageSize = 20;
    private int activePage;
    private Component[] templateComponents;
    private Paging topPaging;
    private Paging bottomPaging;
    private final List<Paging> pagers = new ArrayList<>();
    private Listbox listbox;
    private Set selectedItems = new HashSet();
    private final EventListener onSelectListener = new EventListener<SelectEvent>() {

        @Override
        public void onEvent(SelectEvent event) throws Exception {
            selectedItems.clear();
            for (Object item : event.getSelectedItems()) {
                selectedItems.add(((Listitem) item).getValue());
            }
            Events.postEvent("onSelect", PagingListbox.this, selectedItems);
        }
    };

    private final EventListener onPagingListener = new EventListener<PagingEvent>() {

        @Override
        public void onEvent(PagingEvent event) throws Exception {
            if (pagingModel != null) {
                activePage = event.getPageable().getActivePage();
                refreshModel();
            }
        }
    };

    @Override
    public void afterCompose() {
        initComponents();
        changeTemplate();
        listbox.setModel(new ListModelList());
        refreshModel();
    }

    private void initComponents() {
        topPaging = new Paging();
        this.appendChild(topPaging);
        listbox = new Listbox();
        this.appendChild(listbox);
        listbox.addEventListener("onSelect", onSelectListener);
        bottomPaging = new Paging();
        this.appendChild(bottomPaging);
        pagers.add(topPaging);
        pagers.add(bottomPaging);
        for (Paging paging : pagers) {
            paging.addEventListener("onPaging", onPagingListener);
        }
    }

    private void changeTemplate() {
        if (listbox != null && template != null) {
            if (templateComponents != null) {
                for (Component comp : templateComponents) {
                    listbox.removeChild(comp);
                }
            }
            Template currentTemplate = this.getTemplate(template);
            if (currentTemplate != null) {
                templateComponents = currentTemplate.create(listbox, null, null, null);
            }
            Selectors.wireEventListeners(this, this);

            refreshModel();//because otherwise the template="model" will not change but selection is removed.
        }
    }

    public PagingModel getModel() {
        return pagingModel;
    }

    public void setModel(PagingModel pagingModel) {
        this.pagingModel = pagingModel;
        refreshModel();
    }

    private void refreshModel() {
        if (pagers != null && listbox != null && pagingModel != null) {
            setPagersVisible();
            listbox.setCheckmark(checkmark);
            Collection page = new ArrayList();
            try {
                page = pagingModel.getContent(activePage, pageSize);
            } catch (CurrentPageExceedException ex) {
                activePage = 0;
                for (Paging paging : pagers) {
                    paging.setActivePage(activePage);
                }
                try {
                    page = pagingModel.getContent(activePage, pageSize);
                } catch (CurrentPageExceedException ex1) {
                }
            }
            for (Paging paging : pagers) {
                paging.setDetailed(detailed);
                paging.setActivePage(activePage);
                paging.setTotalSize((int) pagingModel.getTotalSize());
                paging.setPageSize(pageSize);
            }
            ListModelList model = (ListModelList) listbox.getModel();
            model.setMultiple(multiple);
            model.clear();
            model.addAll(page);
            createSelection();
        }
    }

    @Listen("onSort = listheader")
    public void onSorting(SortEvent sortEvent) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Listheader header = (Listheader) sortEvent.getTarget();
        Field f = header.getClass().getDeclaredField("_sortAscNm");
        f.setAccessible(true);
        String sortAttribute = (String) f.get(header);
        if (sortAttribute != null && sortAttribute.startsWith("client(")) {
            sortAttribute = sortAttribute.substring(7);
            sortAttribute = sortAttribute.substring(0, sortAttribute.length() - 1);
            pagingModel.setSortField(sortAttribute);
        }
        String headerDirection = header.getSortDirection();
        SortDirection sortDirection = null;
        switch (headerDirection.toUpperCase()) {
            case "DESCENDING":
            case "NATURAL":
                sortDirection = SortDirection.ASCENDING;
                setSortDirection(header, sortDirection);
                break;
            case "ASCENDING":
                sortDirection = SortDirection.DESCENDING;
                setSortDirection(header, sortDirection);
                break;

        }
        pagingModel.setSortDirection(sortDirection);
        refreshModel();
    }

    private void setSortDirection(Listheader header, SortDirection direction) {
        for (Component comp : header.getParent().getChildren()) {
            if (comp instanceof Listheader) {
                ((Listheader) comp).setSortDirection("natural");
            }
        }
        header.setSortDirection(direction.getLongName().toLowerCase());
    }

    private void setPagersVisible() {
        topPaging.setVisible("top".equals(pagingPosition) || "both".equals(pagingPosition));
        bottomPaging.setVisible("bottom".equals(pagingPosition) || "both".equals(pagingPosition));
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        refreshModel();
    }

    public Object getSelectedItem() {
        return selectedItems.isEmpty() ? null : selectedItems.iterator().next();
    }

    public void setSelectedItem(Object selectedItem) {
        this.selectedItems.clear();
        if (selectedItems != null) {
            selectedItems.add(selectedItem);
        }
        createSelection();
    }

    public Set getSelectedItems() {
        return new HashSet(selectedItems);// new Hashset to break the reference to internal object.
    }

    public void setSelectedItems(Set selected) {
        selectedItems.clear();
        if (selected != null) {
            selectedItems.addAll(selected);
        }
        createSelection();
    }

    private void createSelection() {
        if (listbox != null) {
            ((ListModelList) listbox.getModel()).clearSelection();
            for (Object selection : selectedItems) {
                ((ListModelList) listbox.getModel()).addToSelection(selection);
            }
        }
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
        refreshModel();
    }

    public boolean isCheckmark() {
        return checkmark;
    }

    public void setCheckmark(boolean checkmark) {
        this.checkmark = checkmark;
        refreshModel();
    }

    public String getPagingPosition() {
        return pagingPosition;
    }

    public void setPagingPosition(String pagingPosition) {
        this.pagingPosition = pagingPosition;
    }

    public String getEmptyMessage() {
        return emptyMessage;
    }

    public void setEmptyMessage(String emptyMessage) {
        this.emptyMessage = emptyMessage;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
        changeTemplate();
    }

    public boolean isDetailed() {
        return detailed;
    }

    public void setDetailed(boolean detailed) {
        this.detailed = detailed;
    }
}
