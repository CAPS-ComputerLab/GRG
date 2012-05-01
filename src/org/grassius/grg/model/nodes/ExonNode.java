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
import org.grassius.grg.model.relationships.ExonChromosomeRel;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ExonNode extends BasicEntity{

    public static final String NODE_TYPE = ExonNode.class.getCanonicalName();
    public static final String EXON_ID_INDEX = "exon_id_index";

    public static final String ID_PROPERTY = "id";
    public static final String START_POSITION_PROPERTY = "start_position";
    public static final String END_POSITION_PROPERTY = "end_position";


    public ExonNode(Node n){
        super(n);
    }


    public String getId(){   return String.valueOf(node.getProperty(ID_PROPERTY));}
    public int getStartPosition(){  return Integer.parseInt(String.valueOf(node.getProperty(START_POSITION_PROPERTY)));}
    public int getEndPosition(){  return Integer.parseInt(String.valueOf(node.getProperty(END_POSITION_PROPERTY)));}


    public void setId(String value){ node.setProperty(ID_PROPERTY, value);}
    public void setStartPosition(int value){ node.setProperty(START_POSITION_PROPERTY, value);}
    public void setEndPosition(int value){ node.setProperty(END_POSITION_PROPERTY, value);}


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
    
    public ChromosomeNode getChromosome() {
        ChromosomeNode chr = null;
        Relationship rel = node.getSingleRelationship(new ExonChromosomeRel(null), Direction.OUTGOING);
        if (rel != null) {
            chr = new ChromosomeNode(rel.getEndNode());
        }
        return chr;
    }

}

