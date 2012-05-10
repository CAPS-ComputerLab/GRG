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
public abstract class GRGElement extends BasicEntity{

    public static final String ID_PROPERTY = "id";
    public static final String START_POSITION_PROPERTY = "start_position";
    public static final String END_POSITION_PROPERTY = "end_position";
    public static final String STRAND_PROPERTY = "strand";


    public GRGElement(Node n){
        super(n);
    }


    public String getId(){   return String.valueOf(node.getProperty(ID_PROPERTY));}
    public String getStrand(){   return String.valueOf(node.getProperty(STRAND_PROPERTY));}
    public int getStartPosition(){  return Integer.parseInt(String.valueOf(node.getProperty(START_POSITION_PROPERTY)));}
    public int getEndPosition(){  return Integer.parseInt(String.valueOf(node.getProperty(END_POSITION_PROPERTY)));}
    


    public void setId(String value){ node.setProperty(ID_PROPERTY, value);}
    public void setStrand(String value){ node.setProperty(STRAND_PROPERTY, value);}
    public void setStartPosition(int value){ node.setProperty(START_POSITION_PROPERTY, value);}
    public void setEndPosition(int value){ node.setProperty(END_POSITION_PROPERTY, value);}

}

