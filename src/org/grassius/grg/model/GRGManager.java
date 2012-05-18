/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grassius.grg.model;

import com.era7.bioinfo.bio4jmodel.util.Bio4jManager;
import java.util.HashMap;
import java.util.Map;
import org.grassius.grg.model.nodes.GRGCDSNode;
import org.grassius.grg.model.nodes.GRGGeneNode;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GRGManager extends Bio4jManager {
    
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
        
    //-----------------node indexes-----------------------
    private Index<Node> gRGGeneIdIndex = null;
    private Index<Node> grgCDSIdIndex = null;
    private Index<Node> gRGMRNAIdIndex = null;
    private Index<Node> exonIdIndex = null;
    private Index<Node> intronIdIndex = null;
    private Index<Node> cISElementIdIndex = null;
    private Index<Node> promoterIdIndex = null;
    private Index<Node> chromosomeIdIndex = null;
    
    
    /**
     * Constructor
     * @param dbFolder
     */
    public GRGManager(String dbFolder) {
        super(dbFolder);

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Constructor
     * @param dbFolder
     */
    public GRGManager(String dbFolder, String configFile, boolean readOnlyMode) {
        super(dbFolder, configFile, readOnlyMode);       

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    /**
     * Constructor
     * @param dbFolder
     */
    public GRGManager(String dbFolder, boolean createUnderlyingService, boolean readOnlyMode) {
        
        super(dbFolder, createUnderlyingService, readOnlyMode);      

        initializeIndexes(getIndexProps(), getIndexFullTextProps());
        
        System.out.println("graphservice hashcode: " + graphService.hashCode());

    }
    
    private void initializeIndexes(Map<String, String> indexProps, Map<String, String> indexFullTextProps) {
        //----------node indexes-----------
        gRGGeneIdIndex = graphService.index().forNodes(GRGGeneNode.GRGGENE_ID_INDEX);
        grgCDSIdIndex = graphService.index().forNodes(GRGCDSNode.GRGCDS_ID_INDEX);
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
    
    public Index<Node> getGRGGeneIdIndex() {
        return gRGGeneIdIndex;
    }
    public Index<Node> getGRGCDSIdIndex() {
        return grgCDSIdIndex;
    }
    public Index<Node> getGRGMRNAIdIndex() {
        return gRGMRNAIdIndex;
    }
    public Index<Node> getExonIdIndex() {
        return exonIdIndex;
    }
    public Index<Node> getIntronIdIndex() {
        return intronIdIndex;
    }
    public Index<Node> getCISElementIdIndex(){
        return cISElementIdIndex;
    }
    public Index<Node> getPromoterIdIndex(){
        return promoterIdIndex;
    }
    public Index<Node> getChrosomeIdIndex(){
        return chromosomeIdIndex;
    }
}
