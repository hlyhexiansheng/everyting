package odps;

import com.aliyun.odps.Instance;
import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.task.SQLTask;

import java.util.List;
/**
 * Created by noodles on 16/4/19.
 */
public class TestOdpsSql extends OdpsBaseDefine{

    private static String project = "uxin_test_odps_1";

    private static String select_sql = "SELECT * from tunnel_sample_test where pt = '20150802';";

    private static String add_partition = "ALTER TABLE tunnel_sample_test ADD IF NOT EXISTS PARTITION (pt='20150804',dt='hangzhou');";

    private static String create_table = "create table if not exists sale_detail(\n" +
            "\n" +
            "    shop_name     string,\n" +
            "\n" +
            "    customer_id   string,\n" +
            "\n" +
            "    total_price   double)\n" +
            "\n" +
            "    partitioned by (sale_date string,region string);";

    private static String delete_table = "DROP TABLE IF EXISTS sale_detail;";

    public static void main(String[] args){
        Account account = new AliyunAccount(accessId, accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(odpsUrl);
        odps.setDefaultProject(project);

//        querySql(odps);
//        addPartition(odps);
//        createTable(odps);
        deleteTable(odps);
    }

    private static void deleteTable(Odps odps) {
        Instance i;
        try {
            i = SQLTask.run(odps, delete_table);
            i.waitForSuccess();
            List<Record> recordList = SQLTask.getResult(i);
        } catch (OdpsException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Odps odps) {
        Instance i;
        try {
            i = SQLTask.run(odps, create_table);
            i.waitForSuccess();
            List<Record> recordList = SQLTask.getResult(i);
        } catch (OdpsException e) {
            e.printStackTrace();
        }
    }

    private static void addPartition(Odps odps) {
        Instance i;
        try {
            i = SQLTask.run(odps, add_partition);
            i.waitForSuccess();
            List<Record> records = SQLTask.getResult(i);
            for(Record r : records){
                System.out.println(r);
            }
        } catch (OdpsException e) {
            e.printStackTrace();
        }
    }

    public static void querySql(Odps odps){
        Instance i;
        try {
            i = SQLTask.run(odps, select_sql);
            i.waitForSuccess();
            List<Record> records = SQLTask.getResult(i);
            System.out.println(records.size());
            for(Record record : records){
                System.out.println(String.format("%s,%s,%s",record.getString(0),record.getString(1),record.get(2)));
            }
        } catch (OdpsException e) {
            e.printStackTrace();
        }
    }
}
