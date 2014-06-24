package br.com.ufu.lsi.hopkins;

import java.util.ArrayList;
import java.util.List;


public class Item {
    
    List<Double> values;
    
    Double ui;
    
    Double wi;
    
    public Item() {
        this.values = new ArrayList<Double>();
    }

    
    public List< Double > getValues() {
    
        return values;
    }

    
    public void setValues( List< Double > values ) {
    
        this.values = values;
    }


    @Override
    public String toString() {

        return "\nItem [values=" + values + "]";
    }


    
    public Double getUi() {
    
        return ui;
    }


    
    public void setUi( Double ui ) {
    
        this.ui = ui;
    }


    
    public Double getWi() {
    
        return wi;
    }


    
    public void setWi( Double wi ) {
    
        this.wi = wi;
    }

}
