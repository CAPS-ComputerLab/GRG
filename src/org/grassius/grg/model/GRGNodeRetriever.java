/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grassius.grg.model;

import com.era7.bioinfo.bio4jmodel.util.NodeRetriever;
import org.grassius.grg.model.nodes.GRGCDSNode;
import org.grassius.grg.model.nodes.GRGGeneNode;
import org.grassius.grg.model.nodes.GRGMRNANode;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.IndexHits;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GRGNodeRetriever extends NodeRetriever{
    
    protected GRGManager grgManager;
    
    public GRGNodeRetriever(GRGManager manager){
        super(manager);
        grgManager = manager;
    }
    
    //-------------------------------------------------------------------
    //--------------------GRGGENE--------------------------------
    /**
     * 
     * @param grgGeneId 
     * @return GRGGeneNode with the id provided
     */
    public GRGGeneNode getGRGGene(String grgGeneId){
        
        IndexHits<Node> hits = grgManager.getGRGGeneIdIndex().get(GRGGeneNode.GRGGENE_ID_INDEX, grgGeneId);
        
        if(hits.hasNext()){
            return new GRGGeneNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    //-------------------------------------------------------------------
    //--------------------GRGMRNA--------------------------------
    /**
     * 
     * @param grgMRNAId 
     * @return GRGMRNANode with the id provided
     */
    public GRGMRNANode getGRGMRNA(String grgMRNAId){
        
        IndexHits<Node> hits = grgManager.getGRGMRNAIdIndex().get(GRGMRNANode.GRGMRNA_ID_INDEX, grgMRNAId);
        
        if(hits.hasNext()){
            return new GRGMRNANode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
    //-------------------------------------------------------------------
    //--------------------GRGCDS--------------------------------
    /**
     * 
     * @param grgMRNAId 
     * @return GRGMRNANode with the id provided
     */
    public GRGCDSNode getGRGCDS(String grgCDSId){
        
        IndexHits<Node> hits = grgManager.getGRGCDSIdIndex().get(GRGCDSNode.GRGCDS_ID_INDEX, grgCDSId);
        
        if(hits.hasNext()){
            return new GRGCDSNode(hits.getSingle());
        }else{
            return null;
        }        
    }
    
}
