import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by noodles on 16/11/1 上午10:22.
 */
public class HBaseTest {

    public static void main(String[] args) throws IOException {
//        getTest();
//        putTest();
        deleteTest();
    }

    public static void putTest() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.clear();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.56.101");

        HTable table = new HTable(conf,"mutipl_colunm".getBytes());

        Put put = new Put(UUID.randomUUID().toString().getBytes());
        put.addColumn("cl1".getBytes(), "a1".getBytes(), "value-a3".getBytes());
        put.addColumn("cl2".getBytes(), "a2".getBytes(), "value-a2".getBytes());
        put.addColumn("cl3".getBytes(), "a3".getBytes(), "value-a3".getBytes());


        table.put(put);
    }


    public static void getTest() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.clear();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.56.101");

        HTable table = new HTable(conf,"test".getBytes());

        Get get = new Get("row1".getBytes());
        get.addColumn("cf".getBytes(),"a".getBytes());

        final Result result = table.get(get);
        final List<Cell> columnCells = result.getColumnCells("cf".getBytes(), "a".getBytes());
        System.out.println(columnCells.size());

        final byte[] value = result.getValue("cf".getBytes(), "a".getBytes());
        System.out.println(new String(value));


    }

    public static void deleteTest() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.clear();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.56.101");

        HTable table = new HTable(conf,"mutipl_colunm".getBytes());

        Delete delete = new Delete("66e42725-5e92-4ad2-ad81-8281a77baf98".getBytes());
//        delete.addColumn("cl1".getBytes(),"a1".getBytes());
        table.delete(delete);

        table.flushCommits();
    }

    public static void firstTest() throws IOException{
        TableName tableName = TableName.valueOf("stock-prices");

        Configuration conf = HBaseConfiguration.create();
        conf.clear();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "192.168.56.101");
//        conf.set("zookeeper.znode.parent", "/hbase-unsecure");
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
        if (!admin.tableExists(tableName)) {
            System.out.println("-------------");
            admin.createTable(new HTableDescriptor(tableName).addFamily(new HColumnDescriptor("cf")));
        }

        Table table = conn.getTable(tableName);
        Put p = new Put(Bytes.toBytes("AAPL10232015"));
        p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("close"), Bytes.toBytes(119));
        table.put(p);

        Result r = table.get(new Get(Bytes.toBytes("AAPL10232015")));
        System.out.println(r);
    }

}
