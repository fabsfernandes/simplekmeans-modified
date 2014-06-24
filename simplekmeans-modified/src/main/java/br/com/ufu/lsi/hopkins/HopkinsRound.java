package br.com.ufu.lsi.hopkins;

import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;


public class HopkinsRound {
    
    Instances originalInstances;
    
    List<Item> randomObjects;
    
    List<Item> databaseObjects;
    
    Double hopkins;
    
    int totalSamples;

    public HopkinsRound( Instances instances ){
        this.randomObjects = new ArrayList<Item>();
        this.databaseObjects = new ArrayList<Item>();
        this.originalInstances = instances;
        
        for( int i = 0; i < instances.numInstances(); i++ ){
            Item item = new Item();
            Item item2 = new Item();
            for( int j = 0; j < instances.numAttributes(); j++ ){
                Double d = instances.instance( i ).value( j );   
                item.getValues().add( d );
                item2.getValues().add( d );
            }
            randomObjects.add( item );
            databaseObjects.add( item2 );
        }
        
        // TODO check if rounding is correct
        this.totalSamples = (instances.numInstances() - (instances.numInstances()/10 ));
        
        handleRandomObjects();
        
        handleDatabaseObjects();
    }
    
    public void handleRandomObjects(){
        
        for( int i = 0; i < totalSamples; i++ ){
            int position = (int)(Math.random() * ((randomObjects.size()-1)));
            randomObjects.remove( position );
        }
        
        //System.out.println( "RandomObjects length: " + randomObjects.size() );
        //System.out.println( "RandomObjects atts: " + randomObjects.get(0).getValues().size() );
        
        // disturb values
        for( int i = 0; i < randomObjects.size(); i++ ){
            for( int j = 0; j < ( (Item) randomObjects.get(0) ).getValues().size(); j++ )
                randomObjects.get( i ).getValues().set(j, Math.random() );
        }
        
        System.out.println( "\n== Objetos aleatórios ==");
        System.out.println( randomObjects );
    }
    
    public void handleDatabaseObjects(){
        
        for( int i = 0; i < totalSamples; i++ ){
            int position = (int)(Math.random() * ((databaseObjects.size()-1)));
            databaseObjects.remove( position );
        }
        
        //System.out.println( "databaseObjects length: " + databaseObjects.size() );
        //System.out.println( "databaseObjects atts: " + databaseObjects.get(0).getValues().size() );
        System.out.println( "\n== Objetos do Banco ==");
        System.out.println( databaseObjects );
    }

    
    public Instances getOriginalInstances() {
    
        return originalInstances;
    }

    
    public void setOriginalInstances( Instances originalInstances ) {
    
        this.originalInstances = originalInstances;
    }

    
    public List< Item > getRandomObjects() {
    
        return randomObjects;
    }

    
    public void setRandomObjects( List< Item > randomObjects ) {
    
        this.randomObjects = randomObjects;
    }

    
    public List< Item > getDatabaseObjects() {
    
        return databaseObjects;
    }

    
    public void setDatabaseObjects( List< Item > databaseObjects ) {
    
        this.databaseObjects = databaseObjects;
    }

    
    public int getTotalSamples() {
    
        return totalSamples;
    }

    
    public void setTotalSamples( int totalSamples ) {
    
        this.totalSamples = totalSamples;
    }

    
    public Double getHopkins() {
    
        return hopkins;
    }

    
    public void setHopkins( Double hopkins ) {
    
        this.hopkins = hopkins;
    }
    
}
