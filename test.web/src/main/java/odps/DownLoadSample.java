package odps;

import com.aliyun.odps.Odps;
import com.aliyun.odps.PartitionSpec;
import com.aliyun.odps.TableSchema;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.RecordReader;
import com.aliyun.odps.data.RecordWriter;
import com.aliyun.odps.tunnel.TableTunnel;
import com.aliyun.odps.tunnel.TunnelException;

import java.io.IOException;

/**
 * Created by noodles on 16/4/19.
 */
public class DownLoadSample extends OdpsBaseDefine {

    private static String project = "uxin_test_odps_1";

    private static String table = "tunnel_sample_test";

    private static String partition = "pt=20150801,dt=hangzhou";


    public static void main(String[] args){
        Account account = new AliyunAccount(accessId, accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(odpsUrl);
        odps.setDefaultProject(project);
        try {
            TableTunnel tunnel = new TableTunnel(odps);
            tunnel.setEndpoint(tunnelUrl);
            PartitionSpec partitionSpec = new PartitionSpec(partition);

            TableTunnel.DownloadSession downloadSession = tunnel.createDownloadSession(project, table, partitionSpec);
            long count = downloadSession.getRecordCount();
            System.out.println(count);
            RecordReader recordReader = downloadSession.openRecordReader(0, count);

            Record record;

            while((record = recordReader.read()) != null){
                System.out.println(String.format("%s,%s,%s",record.getString(0),record.getString(1),record.getBigint(2)));
            }

            System.out.println("downlown success!");

        } catch (TunnelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
