import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by noodles on 2017/5/16 16:41.
 */
public class UnGzTest {

    public static void unGzipFile(String sourcedir, String outdir) {
        try {
            //建立gzip压缩文件输入流
            FileInputStream fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            String ouputfile = sourcedir.substring(0, sourcedir.lastIndexOf('.')) + ".log";
            ouputfile = ouputfile.substring(ouputfile.lastIndexOf('/') + 1, ouputfile.length());

            FileOutputStream fout = new FileOutputStream(outdir + "/" + ouputfile);

            int num;
            byte[] buf = new byte[1024];

            while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                fout.write(buf, 0, num);
            }

            gzin.close();
            fout.close();
            fin.close();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        unGzipFile("/Users/noodles/Desktop/ar_zaful_akamai_541905.inextv_S.201705080000-2400-0.gz", "/Users/noodles/soft");
    }

}
