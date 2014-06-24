package br.com.ufu.lsi;


import java.io.File;

import org.junit.Test;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.FilteredClusterer;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import br.com.ufu.lsi.kmeans.SimpleKMeansModified;


public class SimpleKMeansModifiedSSETest {
    
    private static final String PATH = "/home/fabiola/Desktop/Doutorado/DataMining/Trabalho-Cluster-2/water-treatment.arff";
//    private static final String PATH = "/home/fabiola/Downloads/k2.arff";

    @Test
    public void simpleTest() throws Exception {

        //for( int i =1; i<=100; i++ ){
        
        SimpleKMeansModified kmeans = new SimpleKMeansModified();
        
        // load file
        ArffLoader loader = new ArffLoader();
        loader.setSource(new File(PATH));
        Instances instances = loader.getDataSet();

        // replaces missing values
        ReplaceMissingValues replaceMissingValues = new ReplaceMissingValues();
        replaceMissingValues.setInputFormat( instances );
        Instances replaceMissingValuesData = Filter.useFilter(instances, replaceMissingValues);
        
        // normalize
        Normalize normalize = new Normalize();
        normalize.setInputFormat( replaceMissingValuesData );        
        Instances normalizedData = Filter.useFilter(replaceMissingValuesData, normalize);
        
        // setup kmeans
        kmeans.setNumClusters( 12 );
        
        // ignore string attributes
        FilteredClusterer fc = new FilteredClusterer(); //filtered clusterer to ignore attributes        
        String[] options = new String[2];
        options[0] = "-R"; // "range"
        options[1] = "1"; // we want to ignore the attribute that is in the position '1' - the class att
        Remove remove = new Remove(); // new instance of filter
        remove.setOptions(options); // set options
        remove.setInputFormat(normalizedData); // inform filter about dataset
        fc.setFilter(remove); //add filter to remove attributes
        fc.setClusterer(kmeans); //bind FilteredClusterer to original clusterer
        
        // run kmeans
        fc.buildClusterer( normalizedData );

        
        // show cluster distribution
        int[] assignments = ((SimpleKMeansModified) fc.getClusterer()).getAssignments();
        int i = 0;
        for ( int clusterNum : assignments ) {
            System.out.printf( "Instance %d -> Cluster %d\n", i+1, clusterNum );
            i++;
        }
        
        // show statistics
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(fc);
        eval.evaluateClusterer(normalizedData);

        System.out.println(eval.clusterResultsToString());
        
    }

}