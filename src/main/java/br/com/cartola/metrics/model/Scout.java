package br.com.cartola.metrics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Scout {

    private Integer RB;
    private Integer G;
    private Integer A;
    private Integer SG;
    private Integer FS;
    private Integer FF;
    private Integer FD;
    private Integer FT;
    private Integer DD;
    private Integer DP;
    private Integer GC;
    private Integer CV;
    private Integer CA;
    private Integer GS;
    private Integer PP;
    private Integer FC;
    private Integer I;
    private Integer PE;

    public Integer getRB() {
        return RB;
    }

    public void setRB(Integer RB) {
        this.RB = RB;
    }

    public Integer getG() {
        return G;
    }

    public void setG(Integer g) {
        G = g;
    }

    public Integer getA() {
        return A;
    }

    public void setA(Integer a) {
        A = a;
    }

    public Integer getSG() {
        return SG;
    }

    public void setSG(Integer SG) {
        this.SG = SG;
    }

    public Integer getFS() {
        return FS;
    }

    public void setFS(Integer FS) {
        this.FS = FS;
    }

    public Integer getFF() {
        return FF;
    }

    public void setFF(Integer FF) {
        this.FF = FF;
    }

    public Integer getFD() {
        return FD;
    }

    public void setFD(Integer FD) {
        this.FD = FD;
    }

    public Integer getFT() {
        return FT;
    }

    public void setFT(Integer FT) {
        this.FT = FT;
    }

    public Integer getDD() {
        return DD;
    }

    public void setDD(Integer DD) {
        this.DD = DD;
    }

    public Integer getDP() {
        return DP;
    }

    public void setDP(Integer DP) {
        this.DP = DP;
    }

    public Integer getGC() {
        return GC;
    }

    public void setGC(Integer GC) {
        this.GC = GC;
    }

    public Integer getCV() {
        return CV;
    }

    public void setCV(Integer CV) {
        this.CV = CV;
    }

    public Integer getCA() {
        return CA;
    }

    public void setCA(Integer CA) {
        this.CA = CA;
    }

    public Integer getGS() {
        return GS;
    }

    public void setGS(Integer GS) {
        this.GS = GS;
    }

    public Integer getPP() {
        return PP;
    }

    public void setPP(Integer PP) {
        this.PP = PP;
    }

    public Integer getFC() {
        return FC;
    }

    public void setFC(Integer FC) {
        this.FC = FC;
    }

    public Integer getI() {
        return I;
    }

    public void setI(Integer i) {
        I = i;
    }

    public Integer getPE() {
        return PE;
    }

    public void setPE(Integer PE) {
        this.PE = PE;
    }
}
