package com.mangnaik.yogesh.client.views;

import com.mangnaik.yogesh.client.model.Query;

public interface View {


    public abstract void updateView(Query update);
    public abstract boolean getStatus();
    public abstract Query getData();
    public abstract void setStatus(boolean status);
}
