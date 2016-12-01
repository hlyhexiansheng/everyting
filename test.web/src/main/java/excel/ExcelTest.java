package excel;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodles on 16/4/23.
 */
public class ExcelTest {

    public static void main(String[] args) throws IOException {
        List<List<String>> dateList = new ListSource().listSource();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("每日拨打");// 添加sheet

        // 表格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 指定单元格居中对齐
        // 在索引0的位置创建第一行

        for (int i = 0; i < dateList.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            List<String> list = dateList.get(i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(list.get(j));
                cell.setCellStyle(style);
            }
        }
        // 导出文件
        FileOutputStream fout = new FileOutputStream("//Users/noodles/a.xls");
        wb.write(fout);
        fout.close();
    }
}


class ListSource {

    public List<List<String>> listSource() {
        List<List<String>> totalList = new ArrayList<List<String>>();
        for (int i = 0; i < 10; i++) {
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < 5; j++) {
                String str = "";
                String source = getStr(j, str);
                list.add(source);
            }
            totalList.add(list);
        }
        return totalList;
    }

    private String getStr(int j, String str) {
        switch (j) {
            case 0:
                str = "姓名";
                break;
            case 1:
                str = "haha";
                break;
            case 2:
                str = "xixi";
                break;
            case 3:
                str = "电话";
                break;
            case 4:
                str = "爱好";
                break;
        }
        return str;
    }
}

