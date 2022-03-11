package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    String nickname;
    String email;
    List<String> roles;
    String password;
    List<String>  source = new ArrayList<>();
    List<String>  target =  new ArrayList<>();
    int status = 1;
    int eval = 1;
    List<Integer> groups = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String nickname, String email, List<String> roles, String password, List<String> source, List<String> target, int status, int eval, List<Integer> groups) {
        this.nickname = nickname;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.source = source;
        this.target = target;
        this.status = status;
        this.eval = eval;
        this.groups = groups;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getTarget() {
        return target;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEval() {
        return eval;
    }

    public void setEval(int eval) {
        this.eval = eval;
    }

    public List<Integer> getGroups() {
        return groups;
    }

    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }
}
