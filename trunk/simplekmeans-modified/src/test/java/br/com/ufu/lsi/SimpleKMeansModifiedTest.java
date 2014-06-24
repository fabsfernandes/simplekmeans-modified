package br.com.ufu.lsi;


import java.io.File;

import org.junit.Test;

import br.com.ufu.lsi.kmeans.SimpleKMeansModified;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.CSVLoader;



/**
 * Tests SimpleKMeans. Run from the command line with:
 * <p/>
 * java weka.clusterers.SimpleKMeansTest
 * 
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision: 1.1 $
 */
public class SimpleKMeansModifiedTest {
    
    private static final String PATH = "/home/fabiola/Desktop/Doutorado/DataMining/Trabalho-Cluster/points.csv";

    @Test
    public void simpleTest() throws Exception {

        SimpleKMeansModified kmeans = new SimpleKMeansModified();
        
        /*BufferedReader reader = new BufferedReader(new FileReader(PATH));
        Instances instances = new Instances(reader);
        reader.close();*/
        
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(PATH));
        Instances instances = loader.getDataSet();

        kmeans.setSeed( 10 );
        kmeans.setPreserveInstancesOrder( true );
        kmeans.setNumClusters( 3 );
        kmeans.buildClusterer( instances );


        // This array returns the cluster number (starting with 0) for each
        // instance
        // The array has as many elements as the number of instances
        int[] assignments = kmeans.getAssignments();

        int i = 0;
        for ( int clusterNum : assignments ) {
            System.out.printf( "Instance %d -> Cluster %d\n", i+1, clusterNum );
            i++;
        }
        
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(kmeans);
        eval.evaluateClusterer(instances);

        System.out.println(eval.clusterResultsToString());
    }

}