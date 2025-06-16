package com.pl.premiere_zone.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="premiere_stats")
public class Player {
    public Player() {

    }
    @Id
    @Column(name ="Player",unique = true)
    private String player;
    
    private String nation;
    private String pos;
    private Integer age;
    private Integer mp;
    private Integer starts;
    private Double min;
    private Double gls;
    private Double ast;
    private Double pk;
    private Integer crdY;
    private Integer crdR;
    private Double xG;
    private Double xAG;
    private String team;

    public Player(String player, String nation, String pos, Integer age, Integer mp, Integer starts, Double min, Double gls, Double ast, Double pk, Integer crdY, Integer crdR, Double xG, Double xAG, String team) {
        this.player = player;
        this.nation = nation;
        this.pos = pos;
        this.age = age;
        this.mp = mp;
        this.starts = starts;
        this.min = min;
        this.gls = gls;
        this.ast = ast;
        this.pk = pk;
        this.crdY = crdY;
        this.crdR = crdR;
        this.xG = xG;
        this.xAG = xAG;
        this.team = team;

    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getStarts() {
        return starts;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getGls() {
        return gls;
    }

    public void setGls(Double gls) {
        this.gls = gls;
    }

    public Double getAst() {
        return ast;
    }

    public void setAst(Double ast) {
        this.ast = ast;
    }

    public Double getPk() {
        return pk;
    }

    public void setPk(Double pk) {
        this.pk = pk;
    }

    public Integer getCrdY() {
        return crdY;
    }

    public void setCrdY(Integer crdY) {
        this.crdY = crdY;
    }

    public Integer getCrdR() {
        return crdR;
    }

    public void setCrdR(Integer crdR) {
        this.crdR = crdR;
    }

    public Double getxG() {
        return xG;
    }

    public void setxG(Double xG) {
        this.xG = xG;
    }

    public Double getxAG() {
        return xAG;
    }

    public void setxAG(Double xAG) {
        this.xAG = xAG;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
