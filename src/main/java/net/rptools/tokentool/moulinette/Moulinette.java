package net.rptools.tokentool.moulinette;

import java.util.List;

public class Moulinette {

    private String name;
    private String type;
    private List<Token> list;

    public Moulinette(String name, String type, List<Token> list) {
        this.name = name;
        this.type = type;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Token> getList() {
        return list;
    }

    public void setList(List<Token> list) {
        this.list = list;
    }
}
