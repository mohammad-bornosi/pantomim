package com.e.adabazi;

public class Group {
    private int name;
    private int  point;

    public Group(int name, int point) {
        this.name = name;
        this.point = point;
    }

    public int getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
