package com.mangnaik.yogesh.client.controller;

import com.mangnaik.yogesh.calculator.Calculator;
import com.mangnaik.yogesh.client.model.Model;
import com.mangnaik.yogesh.client.model.Query;
import com.mangnaik.yogesh.client.views.View;

import java.util.List;

public class Controller {
    Calculator calculator;
    private List<View> view;
    private Model model;

    public Controller(List<View> view, Model model){
        System.out.println("Controller constructor");
        this.model = model;
        this.view = view;
        run();
    }

    public void run(){
        System.out.println("Polling Views");
        while(true){
            pollViews();
        }
    }

    public void updateModel(Query query){
        model.update(query);
    }

    public void pollViews(){
        for(int i=0; i<view.size(); i++){
            if(view.get(i).getStatus()){
                System.out.println("Query L " + view.get(i).getData().getQuery());
                Query query = view.get(i).getData();
                double answer = calculator.evaluate(query.getQuery());
                query.setAnswer(answer);
                updateModel(query);
                notifyViews(query);
                view.get(i).setStatus(false);
            }
        }
    }

    private void notifyViews(Query query) {
        for(int i=0; i<view.size(); i++){
            view.get(i).updateView(query);
        }
    }
}
