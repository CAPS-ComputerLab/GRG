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
import org.neo4j.graphdb.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GRGIntergenicRegionNode extends BasicEntity{

    public static final String SEQUENCE_PROPERTY = "sequence";
    
    public GRGIntergenicRegionNode(Node n){
        super(n);
    }
    
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GRGIntergenicRegionNode){
            GRGIntergenicRegionNode other = (GRGIntergenicRegionNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    
    
    public GRGGeneNode getPreviousGRGGene(){
        GRGGeneNode gene = null;
        return gene;
    }
    public GRGGeneNode getNexGGeneNode(){
        GRGGeneNode gene = null;
        return gene;
    }
    
}
