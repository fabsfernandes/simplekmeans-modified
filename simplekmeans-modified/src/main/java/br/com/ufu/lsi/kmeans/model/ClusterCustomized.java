package br.com.ufu.lsi.kmeans.model;

import java.util.ArrayList;
import java.util.List;


public class ClusterCustomized {
    
    private List<Item> items;
    
    private double clusterSilhouette;

    public ClusterCustomized(){
        this.items = new ArrayList<Item>();
    }
    
    public List< Item > getItems() {
    
        return items;
    }

    
    public void setItems( List< Item > items ) {
    
        this.items = items;
    }

    
    public double getClusterSilhouette() {
    
        return clusterSilhouette;
    }

    
    public void setClusterSilhouette( double clusterSilhouette ) {
    
        this.clusterSilhouette = clusterSilhouette;
    }

    @Override
    public String toString() {

        return "ClusterCustomized [items=" + items + ", clusterSilhouette=" + clusterSilhouette + "]";
    }
    

}
