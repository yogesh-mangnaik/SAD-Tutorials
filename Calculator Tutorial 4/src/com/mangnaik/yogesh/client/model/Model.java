package com.mangnaik.yogesh.client.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Query> list;

    public Model(){
        list = new ArrayList<>();
    }

    public void update(Query query){
        list.add(query);
    }

    public String getHistory(){
        //TODO: Return history as string
        return "History";
    }
}
