/*
 * Copyright (C) 2012  "GRG"
 *
 * This file is part of GRG
 *
 * GRG is free software: you can redistribute it and/or modify
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

import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.grassius.grg.model.relationships.ExonGRGMRNARel;
import org.grassius.grg.model.relationships.GRGMRNAChromosomeRel;
import org.grassius.grg.model.relationships.GRGMRNAGRGGeneRel;
import org.grassius.grg.model.relationships.IntronGRGMRNARel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GRGMRNANode extends GRGElement{

    public static final String NODE_TYPE = GRGMRNANode.class.getCanonicalName();
    public static final String GRGMRNA_ID_INDEX = "grg_mrna_id_index";

    public static final String NAME_PROPERTY = "name";


    public GRGMRNANode(Node n){
        super(n);
    }

    public String getName(){    return String.valueOf(node.getProperty(NAME_PROPERTY));}

    public void setName(String value){  node.setProperty(NAME_PROPERTY, value);}
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GRGMRNANode){
            GRGMRNANode other = (GRGMRNANode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public ChromosomeNode getChromosome() {
        ChromosomeNode chr = null;
        Relationship rel = node.getSingleRelationship(new GRGMRNAChromosomeRel(null), Direction.OUTGOING);
        if (rel != null) {
            chr = new ChromosomeNode(rel.getEndNode());
        }
        return chr;
    }
    
    public GRGGeneNode getGRGGene(){
        GRGGeneNode gene = null;
        Relationship rel = node.getSingleRelationship(new GRGMRNAGRGGeneRel(null), Direction.OUTGOING);
        if (rel != null) {
            gene = new GRGGeneNode(rel.getEndNode());
        }
        return gene;
    }
    
    public List<ExonNode> getExons(){
        List<ExonNode> list = new LinkedList<ExonNode>();
        Iterator<Relationship> iterator =  this.getNode().getRelationships(new ExonGRGMRNARel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new ExonNode(iterator.next().getStartNode()));
        }        
        return list;
    }
    public List<IntronNode> getIntrons(){
        List<IntronNode> list = new LinkedList<IntronNode>();
        Iterator<Relationship> iterator =  this.getNode().getRelationships(new IntronGRGMRNARel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new IntronNode(iterator.next().getStartNode()));
        }        
        return list;
    }

}

