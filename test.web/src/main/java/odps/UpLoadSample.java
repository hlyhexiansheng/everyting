package odps;

import com.aliyun.odps.Odps;
import com.aliyun.odps.PartitionSpec;
import com.aliyun.odps.TableSchema;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.RecordWriter;
import com.aliyun.odps.tunnel.TableTunnel;
import com.aliyun.odps.tunnel.TableTunnel.UploadSession;
import com.aliyun.odps.tunnel.TunnelException;

import java.io.IOException;

/**
 * Created by noodles on 16/4/19.
 */
public class UpLoadSample extends OdpsBaseDefine {

    private static String project = "uxin_test_odps_1";

    private static String table = "tunnel_sample_test";

    private static String partition = "pt=20150801,dt=hangzhou";


    public static void main(String[] args) throws IOException {
        Account account = new AliyunAccount(accessId, accessKey);
        Odps odps = new Odps(account);
        odps.setEndpoint(odpsUrl);
        odps.setDefaultProject(project);
        try {
            TableTunnel tunnel = new TableTunnel(odps);
            tunnel.setEndpoint(tunnelUrl);
            PartitionSpec partitionSpec = new PartitionSpec(partition);
            UploadSession uploadSession = tunnel.createUploadSession(project,table, partitionSpec);

            System.out.println("Session Status is : "+ uploadSession.getStatus().toString());

            TableSchema schema = uploadSession.getSchema();
            RecordWriter recordWriter = uploadSession.openRecordWriter(0);

            for (int i = 0; i < 10; i++) {
                Record record = uploadSession.newRecord();
                record.set(0, String.valueOf(i));
                record.set(1, String.valueOf(i+"name"));
                record.set(2,Long.valueOf(i));
                recordWriter.write(record);
            }
            recordWriter.close();
            uploadSession.commit(new Long[]{0L});
            System.out.println("upload success!");

        } catch (TunnelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
