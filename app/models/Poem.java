package models;

public class Poem {

    private String poem_name;
    private String poem_text;
    private String poem_date;
    private int poem_views;

    public Poem(String poem_name, String poem_text, String poem_date){
        this.poem_name = poem_name;
        this.poem_text = poem_text;
        this.poem_date = poem_date;
    }


    public String getPoem_name() {
        return poem_name;
    }

    public void setPoem_name(String poem_name) {
        this.poem_name = poem_name;
    }

    public String getPoem_text() {
        return poem_text;
    }

    public void setPoem_text(String poem_text) {
        this.poem_text = poem_text;
    }

    public String getPoem_date() {
        return poem_date;
    }

    public void setPoem_date(String poem_date) {
        this.poem_date = poem_date;
    }

}
