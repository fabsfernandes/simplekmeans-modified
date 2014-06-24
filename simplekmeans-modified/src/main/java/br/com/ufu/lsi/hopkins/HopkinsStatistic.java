package br.com.ufu.lsi.hopkins;


import java.io.File;
import java.util.List;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;


public class HopkinsStatistic {

    private static final String PATH = "/home/fabiola/Desktop/Doutorado/DataMining/Trabalho-Cluster-2/water-treatment.arff";


    public static void main( String... args ) throws Exception {

        // load file
        ArffLoader loader = new ArffLoader();
        loader.setSource( new File( PATH ) );
        Instances instances = loader.getDataSet();

        // replaces missing values
        ReplaceMissingValues replaceMissingValues = new ReplaceMissingValues();
        replaceMissingValues.setInputFormat( instances );
        Instances replaceMissingValuesData = Filter.useFilter( instances, replaceMissingValues );

        // normalize
        Normalize normalize = new Normalize();
        normalize.setInputFormat( replaceMissingValuesData );
        Instances normalizedData = Filter.useFilter( replaceMissingValuesData, normalize );

        calculateStatistic( normalizedData );
    }


    public static void calculateStatistic( Instances instances ) {
        
        Double finalHopkinsMean = 0.0;

        for ( int count = 0; count < 100; count++ ) {
            // build
            HopkinsRound round = new HopkinsRound( instances );

            // calculate uis
            Double sumUis = 0.0;
            List< Item > itens = round.getRandomObjects();
            for ( Item item : itens ) {
                item.setUi( calculateItemDistance( item, instances ) );
                sumUis += item.getUi();
                // System.out.println( "Item UI: " + item.getUi() );

            }

            // calculate wis
            Double sumWis = 0.0;
            List< Item > itensWi = round.getDatabaseObjects();
            for ( Item item : itensWi ) {
                item.setWi( calculateItemDistance( item, instances ) );
                sumWis += item.getWi();
                // System.out.println( "Item WI: " + item.getWi() );
            }

            // calculate round hopkins
            round.setHopkins( sumUis / ( sumUis + sumWis ) );
            finalHopkinsMean += round.getHopkins();
            System.out.println( "\n==> Estatística de Hopkins = " + round.getHopkins() );
        }
        
        finalHopkinsMean = (finalHopkinsMean / 100);
        System.out.println( "FINAL = " + finalHopkinsMean );
    }


    public static Double calculateItemDistance( Item item, Instances instances ) {

        Double minDist = 100000.0;
        for ( int i = 0; i < instances.numInstances(); i++ ) {
            Instance instance = instances.instance( i );
            Double dist = euclidianDistance( instance, item.getValues() );

            if ( dist != 0.0 && dist < minDist ) {
                minDist = dist;
            }
        }
        return minDist;
    }


    public static Double euclidianDistance( Instance instance, List< Double > values ) {

        double soma = 0.0;
        for ( int j = 1; j < instance.numAttributes(); j++ ) {
            soma += Math.pow( ( values.get( j ) - instance.value( j ) ), 2 );
        }

        return soma;
    }

}
