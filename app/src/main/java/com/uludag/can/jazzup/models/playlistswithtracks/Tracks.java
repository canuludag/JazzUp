
package com.uludag.can.jazzup.models.playlistswithtracks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("total")
    @Expose
    private Integer total;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
