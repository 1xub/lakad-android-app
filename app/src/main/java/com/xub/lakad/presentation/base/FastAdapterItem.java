package com.xub.lakad.presentation.base;

import androidx.recyclerview.widget.RecyclerView;
import com.mikepenz.fastadapter.items.ModelAbstractItem;

/**
 * Generic Item for fast adapter
 * Created by Tenten Ponce on 8/26/2017.
 */

public abstract class FastAdapterItem<M, T extends ModelAbstractItem<?, ?, ?>, VH extends RecyclerView.ViewHolder> extends ModelAbstractItem<M, T, VH> {

    private long mHeaderId;
    private String mHeader;

    public FastAdapterItem(M m) {
        super(m);
    }

    public T withHeader(String header) {
        mHeader = header;
        return (T) this;
    }

    public T withHeaderId(long headerId) {
        mHeaderId = headerId;
        return (T) this;
    }

    public long getHeaderId() {
        return mHeaderId;
    }

    public String getHeader() {
        return mHeader;
    }
}
