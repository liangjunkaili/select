package com.course.selection.lj;

public class HelloService {
    private String msg;
    private String sayHello(){
        return "Hello"+msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
