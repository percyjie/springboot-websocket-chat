package com.hehe;

/**
 * @Author percy.
 * @Date: 2020/10/8
 */

public class KeyWordBean implements Comparable<KeyWordBean> {
    public KeyWordBean() {
        super();
    }

    public KeyWordBean(float x, float y, int page, String text) {
        super();
        this.x = x;
        this.y = y;
        this.page = page;
        this.text = text;
    }

    private float x;
    private float y;
    //pdf的页面
    private int page;
    //当前页面中第几个
    private int num;
    private String text;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "KeyWordBean [x=" + x + ", y=" + y + ", page=" + page + ",num=" + num + "]";
    }

    @Override
    public int compareTo(KeyWordBean o) {
        int i = (int) (o.getY() - this.getY());// 先按照Y轴排序
        if (i == 0) {
            return (int) (this.x - o.getX());// 如果Y轴相等了再按X轴进行排序
        }
        return i;
    }
}
