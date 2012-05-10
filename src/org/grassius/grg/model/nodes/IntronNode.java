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
import org.grassius.grg.model.relationships.IntronGRGMRNARel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class IntronNode extends GRGElement{

    public static final String NODE_TYPE = IntronNode.class.getCanonicalName();
    public static final String INTRON_ID_INDEX = "intron_id_index";


    public IntronNode(Node n){
        super(n);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof IntronNode){
            IntronNode other = (IntronNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    public GRGMRNANode getGRGMRNA() {
        GRGMRNANode mrna = null;
        Relationship rel = node.getSingleRelationship(new IntronGRGMRNARel(null), Direction.OUTGOING);
        if (rel != null) {
            mrna = new GRGMRNANode(rel.getEndNode());
        }
        return mrna;
    }

}

