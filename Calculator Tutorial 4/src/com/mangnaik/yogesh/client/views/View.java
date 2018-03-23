package com.mangnaik.yogesh.client.views;

import com.mangnaik.yogesh.client.model.Query;

public abstract class View {

    protected boolean status;
    protected Query data;

    public abstract void updateView(Query update);
    public abstract boolean getStatus();
    public abstract Query getData();
    public abstract void setStatus(boolean status);
}
