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

import org.grassius.grg.model.relationships.ExonGRGMRNARel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ExonNode extends GRGElement{

    public static final String NODE_TYPE = ExonNode.class.getCanonicalName();
    public static final String EXON_ID_INDEX = "exon_id_index";



    public ExonNode(Node n){
        super(n);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ExonNode){
            ExonNode other = (ExonNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public GRGMRNANode getGRGMRNA() {
        GRGMRNANode chr = null;
        Relationship rel = node.getSingleRelationship(new ExonGRGMRNARel(null), Direction.OUTGOING);
        if (rel != null) {
            chr = new GRGMRNANode(rel.getEndNode());
        }
        return chr;
    }

}

