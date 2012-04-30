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

import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.grassius.grg.model.relationships.CisElementChromosomeRel;
import org.grassius.grg.model.relationships.CisElementPromoterRel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class CISElementNode extends BasicEntity{

    public static final String NODE_TYPE = CISElementNode.class.getCanonicalName();


    public CISElementNode(Node n){
        super(n);
    }


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CISElementNode){
            CISElementNode other = (CISElementNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public ChromosomeNode getChromosome() {
        ChromosomeNode chr = null;
        Relationship rel = node.getSingleRelationship(new CisElementChromosomeRel(null), Direction.OUTGOING);
        if (rel != null) {
            chr = new ChromosomeNode(rel.getEndNode());
        }
        return chr;
    }
    
    public List<PromoterNode> getPromoters() {
        List<PromoterNode> list = new LinkedList<PromoterNode>();
        Iterator<Relationship> iterator = node.getRelationships(new CisElementPromoterRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new PromoterNode(iterator.next().getEndNode()));
        }
        return list;
    }

}

