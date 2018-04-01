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
        calculator = new Calculator();
        this.model = model;
        this.view = view;
        run();
    }

    private void run(){
        while(true){
            pollViews();
        }
    }

    private void updateModel(Query query){
        model.update(query);
    }

    private void pollViews(){
        for(int i=0; i<view.size(); i++){
            if(view.get(i).getStatus()){
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
