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
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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

        if (args.length != 3) {
            System.out.println("This program expects four parameters: \n"
                    + "1. Bio4j DB folder" + "\n"
                    + "2. BatchInserter properties file");
        } else {


            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;


            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("GRGImporter.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);
                
                String bio4jdbFolder = args[0];
                String batchInserterPropsFile = args[1];

                // create the batch inserter
                inserter = new BatchInserterImpl(bio4jdbFolder, BatchInserterImpl.loadProperties(batchInserterPropsFile));

                // create the batch index service
                indexProvider = new LuceneBatchInserterIndexProvider(inserter);

                //------------------indexes creation----------------------------------
                //BatchInserterIndex proteinAccessionIndex = indexProvider.nodeIndex(ProteinNode.PROTEIN_ACCESSION_INDEX,
                //        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                //--------------------------------------------------------------------

                

            } catch (Exception ex) {
                logger.log(Level.SEVERE, ex.getMessage());
                StackTraceElement[] trace = ex.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {
                try {
                    //closing logger file handler
                    fh.close();
                    //closing no4j managers
                    indexProvider.shutdown();
                    inserter.shutdown();
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

}
