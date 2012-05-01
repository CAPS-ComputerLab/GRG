/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grassius.grg.model;

import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import java.util.HashMap;
import java.util.Map;
import org.grassius.grg.model.nodes.GRGGeneNode;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GRGManager extends Neo4jManager {
    
    private static boolean alreadyCreated = false;
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
    
    public static final String NODE_TYPE_INDEX_NAME = "node_type_index";
    
    //-----------------node indexes-----------------------
    private Index<Node> nodeTypeIndex = null;
    private Index<Node> gRGGeneIdIndex = null;
    private Index<Node> cDSIdIndex = null;
    private Index<Node> gRGMRNAIdIndex = null;
    private Index<Node> exonIdIndex = null;
    private Index<Node> intronIdIndex = null;
    private Index<Node> cISElementIdIndex = null;
    private Index<Node> promoterIdIndex = null;
    
    
    /**
     * Constructor
     * @param dbFolder
     */
    public GRGManager(String dbFolder) {
        super(dbFolder, firstTimeCalled(), false, null);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Constructor
     * @param dbFolder
     */
    public GRGManager(String dbFolder, String configFile, boolean readOnlyMode) {
        super(dbFolder, firstTimeCalled(), readOnlyMode, configFile);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Constructor
     * @param dbFolder
     */
    public GRGManager(String dbFolder, boolean createUnderlyingService, boolean readOnlyMode) {
        
        super(dbFolder, createUnderlyingService, readOnlyMode, null);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    private void initializeIndexes(Map<String, String> indexProps, Map<String, String> indexFullTextProps) {
        //----------node indexes-----------
        nodeTypeIndex = graphService.index().forNodes(NODE_TYPE_INDEX_NAME, indexProps);
        //gRGGeneIdIndex = graphService.index().forNodes(GRGGeneNode.)
    }
    
     private static synchronized boolean firstTimeCalled() {
        if (!alreadyCreated) {
            alreadyCreated = true;
            return true;
        } else {
            return false;
        }
    }
    
    private Map<String, String> getIndexProps(){
        
        Map<String, String> indexProps = new HashMap<String, String>();        
        indexProps.put(PROVIDER_ST, LUCENE_ST);
        indexProps.put(TYPE_ST, EXACT_ST);
        
        return indexProps;
    }
    
    private Map<String, String> getIndexFullTextProps(){
        
        Map<String, String> indexFullTextProps = new HashMap<String, String>();
        indexFullTextProps.put(PROVIDER_ST, LUCENE_ST);
        indexFullTextProps.put(TYPE_ST, FULL_TEXT_ST);
        
        return indexFullTextProps;
    }
}
