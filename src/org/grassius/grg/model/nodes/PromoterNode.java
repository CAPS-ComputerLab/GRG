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
import org.grassius.grg.model.relationships.PromoterChromosomeRel;
import org.grassius.grg.model.relationships.PromoterGRGGeneRel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class PromoterNode extends BasicEntity{

    public static final String NODE_TYPE = PromoterNode.class.getCanonicalName();
    public static final String PROMOTER_ID_INDEX = "promoter_id_index";
    
    public static final String ID_PROPERTY = "id";


    public PromoterNode(Node n){
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
        if(obj instanceof PromoterNode){
            PromoterNode other = (PromoterNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    
    public ChromosomeNode getChromosome() {
        ChromosomeNode chr = null;
        Relationship rel = node.getSingleRelationship(new PromoterChromosomeRel(null), Direction.OUTGOING);
        if (rel != null) {
            chr = new ChromosomeNode(rel.getEndNode());
        }
        return chr;
    }
    
    public List<GRGGeneNode> getGRGGenes(){
        List<GRGGeneNode> list = new LinkedList<GRGGeneNode>();
        Iterator<Relationship> iterator = node.getRelationships(new PromoterGRGGeneRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new GRGGeneNode(iterator.next().getEndNode()));
        }
        return list;
    }
}
