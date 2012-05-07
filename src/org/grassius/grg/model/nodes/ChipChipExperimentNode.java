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
public class ChipChipExperimentNode extends BasicEntity{

    public static final String NODE_TYPE = ChipChipExperimentNode.class.getCanonicalName();
    public static final String CHIP_CHIP_EXPERIMENT_ID_INDEX = "chip_chip_experiment_id_index";
    
    public static final String ID_PROPERTY = "id";


    public ChipChipExperimentNode(Node n){
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
        if(obj instanceof ChipChipExperimentNode){
            ChipChipExperimentNode other = (ChipChipExperimentNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    

}

