package com.mangnaik.yogesh.client;

import com.mangnaik.yogesh.client.controller.Controller;
import com.mangnaik.yogesh.client.model.Model;
import com.mangnaik.yogesh.client.views.ConsoleView;
import com.mangnaik.yogesh.client.views.View;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<View> viewList = new ArrayList<>();
    private Model model;
    private Controller controller;

    public Main(){
        viewList.add(new ConsoleView());
        viewList.add(new GUIView());
        model = new Model();
        controller = new Controller(viewList, model);
    }

    public static void main(String[] args){
        new Main();
    }
}
