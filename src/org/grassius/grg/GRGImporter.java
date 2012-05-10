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
package org.grassius.grg;

import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.grassius.grg.model.GRGManager;
import org.grassius.grg.model.nodes.ChromosomeNode;
import org.neo4j.graphdb.index.BatchInserterIndex;
import org.neo4j.graphdb.index.BatchInserterIndexProvider;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.impl.lucene.LuceneBatchInserterIndexProvider;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 * Imports GRG DB and integrates it with Bio4j DB
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class GRGImporter implements Executable {

    private static final Logger logger = Logger.getLogger("GRGImporter");
    private static FileHandler fh;
    
    //--------indexing API constans-----
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
    //-----------------------------------

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("This program expects the following parameters: \n"
                    + "1. Bio4j DB folder" + "\n"
                    + "2. GFF file ");
        } else {


            GRGManager gRGManager = null;

            String tempLine = "";

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("GRGImporter.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);
                
                String bio4jdbFolder = args[0];
                String gffFileSt = args[1];
                
                System.out.println("Creating manager....");

                gRGManager = new GRGManager(bio4jdbFolder);
                
                
                File gffFile = new File(gffFileSt);
                
                BufferedReader reader = new BufferedReader(new FileReader(gffFile));
                String line = null;
                
                System.out.println("Reading gff file....");
                
                //----skip header---
                reader.readLine();
                
                while((line = reader.readLine()) != null){

                    tempLine = line;
                    String[]  columns = line.split("\t");
                                        
                    String chromosomeSt = columns[0];
                    String elementTypeSt = columns[2];
                    int startPosition = Integer.parseInt(columns[3]);
                    int endPosition = Integer.parseInt(columns[4]);
                    String strandSt = columns[6];
                    String descriptionSt = columns[8];
                    
                    String[] descriptionColumns = descriptionSt.split(";"); 
                    
                    
                    //----------------------GENE--------------------------
                    if(elementTypeSt.equals("gene")){
                        
                        String geneID = descriptionColumns[0].split("ID=")[1];
                        System.out.println("geneID = " + geneID);
                        
                    }
                    //----------------------CDS--------------------------
                    else if(elementTypeSt.equals("CDS")){
                        
                        String elementId = descriptionColumns[0].split("ID=PAC")[1].substring(1);
                        //System.out.println("CDS: elementId = " + elementId);
                        
                    }
                    //----------------------mRNA--------------------------
                    else if(elementTypeSt.equals("mRNA")){
                        
                        String elementId = descriptionColumns[0].split("ID=PAC")[1].substring(1);
                        //System.out.println("mRNA: elementId = " + elementId);
                        
                    }
                    //----------------------EXON--------------------------
                    else if (elementTypeSt.equals("exon")){
                        String elementId = descriptionColumns[0].split("ID=PAC")[1].substring(1);
                        //System.out.println("exon: elementId = " + elementId);
                    }
                    //----------------------FIVE PRIME UTR--------------------------
                    else if (elementTypeSt.equals("five_prime_UTR")){
                        String elementId = descriptionColumns[0].split("ID=PAC")[1].substring(1);
                        //System.out.println("five_prime_UTR: elementId = " + elementId);
                    }
                    //----------------------THREE PRIME UTR--------------------------
                    else if(elementTypeSt.equals("three_prime_UTR")){
                        String elementId = descriptionColumns[0].split("ID=PAC")[1].substring(1);
                        //System.out.println("three_prime_UTR: elementId = " + elementId);
                    }
                    
                    
                }
                
                
                reader.close();

                

            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage());
                StackTraceElement[] trace = ex.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
                
                System.out.println("tempLine: " + tempLine);
            } finally {
                try {
                    //closing logger file handler
                    fh.close();
                    //closing managers
                    gRGManager.shutDown();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                    StackTraceElement[] trace = e.getStackTrace();
                    for (StackTraceElement stackTraceElement : trace) {
                        logger.log(Level.SEVERE, stackTraceElement.toString());
                    }
                }

            }

            System.out.println("GRG importing process was finished!! :D");


        }
    }
    
    //private static void importMaize

}
