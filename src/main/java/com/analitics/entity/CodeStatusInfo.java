package com.analitics.entity;

public class CodeStatusInfo {
    private int length;
    private int found;
    private int match;
    private int http_code;
    private String http_msg;
    private double duration;
    private String infected;

    public CodeStatusInfo() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFound() {
        return found;
    }

    public void setFound(int found) {
        this.found = found;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getHttp_code() {
        return http_code;
    }

    public void setHttp_code(int http_code) {
        this.http_code = http_code;
    }

    public String getHttp_msg() {
        return http_msg;
    }

    public void setHttp_msg(String http_msg) {
        this.http_msg = http_msg;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getInfected() {
        return infected;
    }

    public void setInfected(String infected) {
        this.infected = infected;
    }

    @Override
    public String toString() {
        return "CodeStatusInfo{" +
                "length=" + length +
                ", found=" + found +
                ", match=" + match +
                ", http_code=" + http_code +
                ", http_msg='" + http_msg + '\'' +
                ", duration=" + duration +
                ", infected='" + infected + '\'' +
                '}';
    }
}
