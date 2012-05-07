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
public class TFomeElementNode extends BasicEntity{

    public static final String NODE_TYPE = TFomeElementNode.class.getCanonicalName();
    
    public static final String VECTOR_PROPERTY = "vector";
    public static final String INSERT_PROPERTY = "insert";
    public static final String FIVE_PRIMER_NAME_PROPERTY = "five_primer_name";
    public static final String THREE_PRIMER_NAME_PROPERTY = "three_primer_name";
    public static final String FIVE_PRIMER_SEQUENCE_PROPERTY = "five_primer_sequence";
    public static final String THREE_PRIMER_SEQUENCE_PROPERTY = "three_primfive_primer_sequenceer_sequence";
    public static final String FIVE_PRIMER_TM_PROPERTY = "five_primer_tm";
    public static final String THREE_PRIMER_TM_PROPERTY = "three_primer_tm";
    public static final String CLONED_BY_PROPERTY = "cloned_by";
    public static final String PCR_CONDITION_PROPERTY = "pcr_condition";
    public static final String NUCLEOTIDE_SEQUENCE_PROPERTY = "nucleotide_sequence";
    public static final String TRANSLATION_PROPERTY = "translation";
    public static final String AD_GENE_URL_PROPERTY = "ad_gene_url";
    public static final String GENE_BANK_ID_PROPERTY = "gene_bank_id";
    public static final String PLASMID_MAP_PROPERTY = "plasmid_map";


    public TFomeElementNode(Node n){
        super(n);
    }
    
    public String getVector(){   return String.valueOf(node.getProperty(VECTOR_PROPERTY));}
    public String getInsert(){   return String.valueOf(node.getProperty(INSERT_PROPERTY));}
    public String getFivePrimerName(){   return String.valueOf(node.getProperty(FIVE_PRIMER_NAME_PROPERTY));}
    public String getThreePrimerName(){   return String.valueOf(node.getProperty(THREE_PRIMER_NAME_PROPERTY));}
    public String getFivePrimerSequence(){   return String.valueOf(node.getProperty(FIVE_PRIMER_SEQUENCE_PROPERTY));}
    public String getThreePrimerSequence(){   return String.valueOf(node.getProperty(THREE_PRIMER_SEQUENCE_PROPERTY));}
    public float getThreePrimerTm(){   return Float.parseFloat(String.valueOf(node.getProperty(THREE_PRIMER_TM_PROPERTY)));}
    public float getFivePrimerTm(){   return Float.parseFloat(String.valueOf(node.getProperty(FIVE_PRIMER_TM_PROPERTY)));}
    public String getClonedBy(){    return String.valueOf(node.getProperty(CLONED_BY_PROPERTY));}
    public String getPCRCondition(){    return String.valueOf(node.getProperty(PCR_CONDITION_PROPERTY));}
    public String getNucleotideSequence(){  return String.valueOf(node.getProperty(NUCLEOTIDE_SEQUENCE_PROPERTY));}
    public String getTranslation(){ return String.valueOf(node.getProperty(TRANSLATION_PROPERTY));}
    public String getAdGeneUrl(){   return String.valueOf(node.getProperty(AD_GENE_URL_PROPERTY));}
    public String getGeneBankId(){  return String.valueOf(node.getProperty(GENE_BANK_ID_PROPERTY));}
    public String getPlasmidMap(){  return String.valueOf(node.getProperty(PLASMID_MAP_PROPERTY));}
    
    public void setVector(String value){ node.setProperty(VECTOR_PROPERTY, value);}
    public void setInsert(String value){ node.setProperty(INSERT_PROPERTY, value);}
    public void setFivePrimerName(String value){    node.setProperty(FIVE_PRIMER_NAME_PROPERTY, value);}
    public void setThreePrimerName(String value){    node.setProperty(THREE_PRIMER_NAME_PROPERTY, value);}
    public void setFivePrimerSequence(String value){    node.setProperty(FIVE_PRIMER_SEQUENCE_PROPERTY, value);}
    public void setThreePrimerSequence(String value){    node.setProperty(THREE_PRIMER_SEQUENCE_PROPERTY, value);}
    public void setFivePrimerTm(float value){    node.setProperty(FIVE_PRIMER_TM_PROPERTY, value);}
    public void setThreePrimerTm(float value){    node.setProperty(THREE_PRIMER_TM_PROPERTY, value);}
    public void setClonedBy(String value){  node.setProperty(CLONED_BY_PROPERTY, value);}
    public void setPCRCondition(String value){   node.setProperty(PCR_CONDITION_PROPERTY, value);}
    public void setNucleotideSequence(String value){    node.setProperty(NUCLEOTIDE_SEQUENCE_PROPERTY, value);}
    public void setTranslation(String value){    node.setProperty(TRANSLATION_PROPERTY, value);}
    public void setAdGeneUrl(String value){ node.setProperty(AD_GENE_URL_PROPERTY, value);}
    public void setGeneBankId(String value){    node.setProperty(GENE_BANK_ID_PROPERTY, value);}
    public void setPlasmidMap(String value){    node.setProperty(PLASMID_MAP_PROPERTY, value);}


    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TFomeElementNode){
            TFomeElementNode other = (TFomeElementNode) obj;
            return this.node.equals(other.node);
        }else{
            return false;
        }
    }
    

}

