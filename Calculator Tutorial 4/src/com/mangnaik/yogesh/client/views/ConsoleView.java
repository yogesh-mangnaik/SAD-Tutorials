package com.mangnaik.yogesh.client.views;

import com.mangnaik.yogesh.client.model.Query;
import java.util.Scanner;

public class ConsoleView implements View {

    private Scanner scan;
    boolean status;
    Query data;

    public ConsoleView(){
        scan = new Scanner(System.in);
        run();
    }

    private void run() {
        Thread t = new Thread(() -> {
            while(true){
                String input;
                input = scan.nextLine();
                data = new Query(input, 0);
                status = true;
                System.out.println("Data : " + input);
            }
        });
        System.out.println("Thread Running");
        t.start();
        System.out.println("Thread Running");
    }

    @Override
    public void updateView(Query query) {
        System.out.println(query.getQuery());
        System.out.println("Answer : " + query.getAnswer());
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public Query getData() {
        System.out.println(data.getQuery());
        return data;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }
}
