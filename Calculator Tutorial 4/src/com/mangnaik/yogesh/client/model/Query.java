package com.mangnaik.yogesh.client.model;

public class Query {
    private String query;
    private String answer;

    public Query(String query, String answer) {
        this.query = query;
        this.answer = answer;
    }

    public Query(String query, double answer){
        this.query = query;
        this.answer = String.valueOf(answer);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setAnswer(double answer){
        this.answer = String.valueOf(answer);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
