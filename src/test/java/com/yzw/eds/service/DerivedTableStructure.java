package com.yzw.eds.service;

import com.yzw.eds.dao.CitysDAO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DerivedTableStructure{

    @Autowired
    private CitysDAO citysDAO;

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 写入Excel
     * @param dataList      数据
     * @param cloumnCount   列数
     * @param finalXlsxPath
     */
    public static void writeExcel(List<Map> dataList, int cloumnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 0; i < rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j);
                // 得到要插入的每一条记录
                List list = (List) dataList.get(j);
                for (int k = 0; k <= columnNumCount; k++) {

                    for (int y = 0; y < cloumnCount;y++){
                        Cell cell = row.createCell(y);
                        if (list.size() < cloumnCount){
                            cell.setCellValue("未找到");
                        }else {
                            if (null == list.get(y)){
                                cell.setCellValue("");
                            }else {
                                cell.setCellValue(list.get(y).toString());
                            }
                        }
                    }
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 导出数据库表结构测试方法
     */
    @Test
    public void exportTable(){
        List<Map<String,String>> tables = citysDAO.getTables();
        List excelList = new ArrayList();

        excelList.add(getHeads());
        for (Map<String,String> table : tables){
            List<Map<String,String>> names = citysDAO.derivedTable(table.get("table_name"));
            for (Map<String,String> name : names){
                List dataRow = new ArrayList();
                dataRow.add(table.get("table_name"));
                dataRow.add(table.get("table_comment"));
                dataRow.add(name.get("列名").toUpperCase());
                dataRow.add(name.get("数据类型"));
                dataRow.add(name.get("字段类型"));
                dataRow.add(name.get("长度"));
                dataRow.add(name.get("是否为空"));
                dataRow.add(name.get("默认值"));
                dataRow.add(name.get("备注"));
                excelList.add(dataRow);
            }

        }

        writeExcel(excelList, ((List)excelList.get(0)).size(), "C:\\Users\\Administrator\\Desktop\\测试数据字段列表.xlsx");
    }

    private List getHeads(){
        List<String> list = new ArrayList<>();
        list.add("表名");
        list.add("表描述");
        list.add("列名");
        list.add("数据类型");
        list.add("字段类型");
        list.add("长度");
        list.add("是否为空");
        list.add("默认值");
        list.add("备注");
        return list;
    }
}
