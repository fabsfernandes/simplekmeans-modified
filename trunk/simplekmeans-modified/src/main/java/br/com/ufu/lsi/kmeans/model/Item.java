package br.com.ufu.lsi.kmeans.model;

import weka.core.Instance;


public class Item {
    
    private double at;
    private double bt;
    private double silhouette;
    private Instance instance;
    
    public Item( Instance instance ){
        this.instance = instance;
    }
    
    public double getAt() {
    
        return at;
    }
    
    public void setAt( double at ) {
    
        this.at = at;
    }
    
    public double getBt() {
    
        return bt;
    }
    
    public void setBt( double bt ) {
    
        this.bt = bt;
    }
    
    public double getSilhouette() {
    
        return silhouette;
    }
    
    public void setSilhouette( double silhouette ) {
    
        this.silhouette = silhouette;
    }

    
    public Instance getInstance() {
    
        return instance;
    }

    
    public void setInstance( Instance instance ) {
    
        this.instance = instance;
    }

    @Override
    public String toString() {

        return "Item [at=" + at + ", bt=" + bt + ", silhouette=" + silhouette + ", instance=" + instance + "]";
    }

    
}
