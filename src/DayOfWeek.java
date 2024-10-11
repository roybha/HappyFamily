public enum DayOfWeek {
    MONDAY("Monday",0),
    TUESDAY("Tuesday",1),
    WEDNESDAY("Wednesday",2),
    THURSDAY("Thursday",3),
    FRIDAY("Friday",4),
    SATURDAY("Saturday",5),
    SUNDAY("Sunday",6);
    private String title;
    private  int number;
     DayOfWeek(String title,int number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
         this.title = title;
    }
    public int getNumber() {
         return number;
    }
    public void setNumber(int number) {
         this.number = number;
    }
}
