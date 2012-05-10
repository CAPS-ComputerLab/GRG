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

import org.grassius.grg.model.relationships.ExonFivePrimeUTRRel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class FivePrimeUTR extends GRGElement{
    
    public static final String NODE_TYPE = FivePrimeUTR.class.getCanonicalName();
    
    public FivePrimeUTR(Node n){
        super(n);
    }
    
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FivePrimeUTR){
            FivePrimeUTR other = (FivePrimeUTR) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public ExonNode getExon() {
        ExonNode exon = null;
        Relationship rel = node.getSingleRelationship(new ExonFivePrimeUTRRel(null), Direction.INCOMING);
        if (rel != null) {
            exon = new ExonNode(rel.getStartNode());
        }
        return exon;
    }
    
}
