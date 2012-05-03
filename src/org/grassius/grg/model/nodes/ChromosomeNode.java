/*
 * Copyright (C) 2012  "GRG"
 *
 * This file is part of GRG
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package org.grassius.grg.model.nodes;

import com.era7.bioinfo.bio4jmodel.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.grassius.grg.model.relationships.CisElementChromosomeRel;
import org.grassius.grg.model.relationships.GRGGeneChromosomeRel;
import org.grassius.grg.model.relationships.GRGMRNAChromosomeRel;
import org.grassius.grg.model.relationships.GenomeElementChromosomeRel;
import org.grassius.grg.model.relationships.PromoterChromosomeRel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ChromosomeNode extends BasicEntity{

    public static final String NODE_TYPE = ChromosomeNode.class.getCanonicalName();
    public static final String CHROMOSOME_ID_INDEX = "chromoshome_id_index";
    
    public static final String ID_PROPERTY = "id";


    public ChromosomeNode(Node n){
        super(n);
    }
    
    public String getId(){   return String.valueOf(node.getProperty(ID_PROPERTY));}
    
    public void setId(String value){ node.setProperty(ID_PROPERTY, value);}


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ChromosomeNode){
            ChromosomeNode other = (ChromosomeNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public List<GRGGeneNode> getGRGGenes(){
        List<GRGGeneNode> list = new LinkedList<GRGGeneNode>();
        Iterator<Relationship> iterator =  this.getNode().getRelationships(new GRGGeneChromosomeRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new GRGGeneNode(iterator.next().getStartNode()));
        }        
        return list;
    }
    public List<GRGMRNANode> getGRGMRNAs(){
        List<GRGMRNANode> list = new LinkedList<GRGMRNANode>();
        Iterator<Relationship> iterator =  this.getNode().getRelationships(new GRGMRNAChromosomeRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new GRGMRNANode(iterator.next().getStartNode()));
        }        
        return list;
    }
    public List<PromoterNode> getPromoters(){
        List<PromoterNode> list = new LinkedList<PromoterNode>();
        Iterator<Relationship> iterator =  this.getNode().getRelationships(new PromoterChromosomeRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new PromoterNode(iterator.next().getStartNode()));
        }        
        return list;
    }
    public List<CISElementNode> getCISElements(){
        List<CISElementNode> list = new LinkedList<CISElementNode>();
        Iterator<Relationship> iterator =  this.getNode().getRelationships(new CisElementChromosomeRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new CISElementNode(iterator.next().getStartNode()));
        }        
        return list;
    }
    public GenomeElementNode getGenomeElement(){
        GenomeElementNode genomeElementNode = null;
        Iterator<Relationship> iterator = this.getNode().getRelationships(new GenomeElementChromosomeRel(null), Direction.INCOMING).iterator();
        if(iterator.hasNext()){
            genomeElementNode = new GenomeElementNode(iterator.next().getStartNode());
        }
        return genomeElementNode;
    }

}

