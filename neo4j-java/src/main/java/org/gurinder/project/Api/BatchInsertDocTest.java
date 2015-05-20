/*
package org.gurinder.project.Api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.io.fs.DefaultFileSystemAbstraction;
import org.neo4j.kernel.impl.nioneo.store.FileSystemAbstraction;
import org.neo4j.io.fs.FileUtils;

import org.neo4j.test.TestGraphDatabaseFactory;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserters;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BatchInsertDocTest {
    @Test
    public void insert() throws Exception {
        // Make sure our scratch directory is clean
        FileUtils.deleteRecursively(new File("target/batchinserter-example"));

        // START SNIPPET: insert
        BatchInserter inserter = null;
        try {
            inserter = BatchInserters.inserter(
                    new File("target/batchinserter-example").getAbsolutePath(),
                    fileSystem);

            Label personLabel = DynamicLabel.label("Person");
            inserter.createDeferredSchemaIndex(personLabel).on("name").create();

            Map<String, Object> properties = new HashMap<>();

            properties.put("name", "Mattias");
            long mattiasNode = inserter.createNode(properties, personLabel);

            properties.put("name", "Chris");
            long chrisNode = inserter.createNode(properties, personLabel);

            RelationshipType knows = DynamicRelationshipType.withName("KNOWS");
            inserter.createRelationship(mattiasNode, chrisNode, knows, null);
        } finally {
            if (inserter != null) {
                inserter.shutdown();
            }
        }
        // END SNIPPET: insert

        // try it out from a normal db
        TestGraphDatabaseFactory testGraphDatabaseFactory = new TestGraphDatabaseFactory();
        testGraphDatabaseFactory.setFileSystem(fileSystem);
        GraphDatabaseService db = testGraphDatabaseFactory.newImpermanentDatabase(
                new File("target/batchinserter-example").getAbsolutePath());
        try (Transaction tx = db.beginTx()) {
            Label personLabelForTesting = DynamicLabel.label("Person");
            Node mNode = db.findNode(personLabelForTesting, "name", "Mattias");
            Node cNode = mNode.getSingleRelationship(DynamicRelationshipType.withName("KNOWS"), Direction.OUTGOING).getEndNode();
            assertThat((String) cNode.getProperty("name"), is("Chris"));
            assertThat(db.schema()
                    .getIndexes(personLabelForTesting)
                    .iterator()
                    .hasNext(), is(true));
        } finally {
            db.shutdown();
        }
    }

    @Test
    public void insertWithConfig() throws Exception {
        // START SNIPPET: configuredInsert
        Map<String, String> config = new HashMap<>();
        config.put("dbms.pagecache.memory", "512m");
        BatchInserter inserter = BatchInserters.inserter(
                new File("target/batchinserter-example-config").getAbsolutePath(), fileSystem, config);
        // Insert data here ... and then shut down:
        inserter.shutdown();
        // END SNIPPET: configuredInsert
    }

    @Test
    public void insertWithConfigFile() throws IOException {
        try (Writer fw = fileSystem.openAsWriter(new File("target/docs/batchinsert-config").getAbsoluteFile(), "utf-8", false)) {
            fw.append("dbms.pagecache.memory=8m");
        }

        // START SNIPPET: configFileInsert
        try (InputStream input = fileSystem.openAsInputStream(new File("target/docs/batchinsert-config").getAbsoluteFile())) {
            Map<String, String> config = MapUtil.load(input);
            BatchInserter inserter = BatchInserters.inserter(
                    "target/docs/batchinserter-example-config", fileSystem, config);
            // Insert data here ... and then shut down:
            inserter.shutdown();
        }
        // END SNIPPET: configFileInsert
    }

    @Rule
    public DefaultFileSystemAbstraction fileSystemRule = new DefaultFileSystemAbstraction();
    private FileSystemAbstraction fileSystem;

    @Before
    public void before() throws Exception {
        fileSystem = fileSystemRule;
        fileSystem.mkdirs(new File("target"));
        fileSystem.mkdirs(new File("target/docs"));
    }
}*/