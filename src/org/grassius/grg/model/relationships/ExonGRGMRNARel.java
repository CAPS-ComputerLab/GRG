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
package org.grassius.grg.model.relationships;

import com.era7.bioinfo.bioinfoneo4j.BasicRelationship;
import org.neo4j.graphdb.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ExonGRGMRNARel extends BasicRelationship{

    public static final String NAME = "EXON_GRGMRNA";

    public ExonGRGMRNARel(Relationship rel){
        super(rel);
    }

    @Override
    public String name() {
        return NAME;
    }

}

