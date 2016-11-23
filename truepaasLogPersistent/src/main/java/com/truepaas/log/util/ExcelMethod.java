package com.truepaas.log.util;

import com.truepaas.log.vo.AppLog;
import com.truepaas.log.vo.CountLog;
import com.truepaas.log.vo.HttpLog;
import com.truepaas.log.vo.OperLog;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by mas on 2016/11/11.
 */
@ConfigurationProperties(prefix = "filepath" ,locations = "classpath:config.properties")
public class ExcelMethod {

    private  String windowsPath;

    public String getWindowsPath() {
        return windowsPath;
    }

    public void setWindowsPath(String windowsPath) {
        this.windowsPath = windowsPath;
    }

    public String getLinuxPath() {
        return linuxPath;
    }

    public void setLinuxPath(String linuxPath) {
        this.linuxPath = linuxPath;
    }

    private  String linuxPath;

    private  String getPath() throws Exception{
        if( System.getProperty("os.name").toLowerCase().contains("windows")){
            File file =new File(windowsPath);
            if  (!file .exists()  && !file .isDirectory())
            {
                file.mkdirs();
            }
            return windowsPath;
        }else{
            File file =new File(linuxPath);
            if  (!file .exists()  && !file .isDirectory())
            {
                file.setWritable(true,false);
                file.mkdirs();
            }
            return linuxPath;
        }
    }

    public  void exportAppExcel(List<AppLog> list,HttpServletResponse response){
        try{
            String path=getPath()+"/"+UtilMethod.getTimeString()+"_appLog.xls";
            exportAppExcel(path,list);
            clientDownLoad(path,response);
        }catch(Exception e){

        }
    }
    private  void exportAppExcel(String fileName, List<AppLog> list){
        WritableWorkbook wwb;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet("appLog", 10);        // 创建一个工作表

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            ws.setRowView(1, 500);

            ws.addCell(new Label(1, 0, "cf_app_id", wcf));
            ws.addCell(new Label(2, 0, "cf_app_name", wcf));
            ws.addCell(new Label(3, 0, "cf_org_id", wcf));
            ws.addCell(new Label(4, 0, "cf_org_name", wcf));
            ws.addCell(new Label(5, 0, "cf_space_id", wcf));
            ws.addCell(new Label(6, 0, "cf_space_name", wcf));
            ws.addCell(new Label(7, 0, "cpu_percentage", wcf));
            ws.addCell(new Label(8, 0, "disk_bytes", wcf));
            ws.addCell(new Label(9, 0, "disk_bytes_quota", wcf));
            ws.addCell(new Label(10, 0, "memory_bytes", wcf));
            ws.addCell(new Label(11, 0, "memory_bytes_quota", wcf));
            ws.addCell(new Label(12, 0, "time", wcf));
            //    填充数据的内容
            for (int i = 0; i < list.size(); i++){
                wcf = new WritableCellFormat();
                ws.addCell(new Label(1, i + 1, list.get(i).getCf_app_id(), wcf));
                ws.addCell(new Label(2, i + 1, list.get(i).getCf_app_name(), wcf));
                ws.addCell(new Label(3, i + 1, list.get(i).getCf_org_id(), wcf));
                ws.addCell(new Label(4, i + 1, list.get(i).getCf_org_name(), wcf));
                ws.addCell(new Label(5, i + 1, list.get(i).getCf_space_id(), wcf));
                ws.addCell(new Label(6, i + 1, list.get(i).getCf_space_name(), wcf));
                ws.addCell(new Label(7, i + 1, list.get(i).getCpu_percentage(), wcf));
                ws.addCell(new Label(8, i + 1, list.get(i).getDisk_bytes(), wcf));
                ws.addCell(new Label(9, i + 1, list.get(i).getDisk_bytes_quota(), wcf));
                ws.addCell(new Label(10, i + 1, list.get(i).getMemory_bytes(), wcf));
                ws.addCell(new Label(11, i + 1, list.get(i).getMemory_bytes_quota(), wcf));
                ws.addCell(new Label(12, i + 1, list.get(i).getTime(), wcf));
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
    }

    public  void exportHttpExcel(List<HttpLog> list,HttpServletResponse response){
        try{
            String path=getPath()+"/"+UtilMethod.getTimeString()+"_httpLog.xls";
            exportHttpExcel(path,list);
            clientDownLoad(path,response);
        }catch(Exception e){

        }
    }

    private  void exportHttpExcel(String fileName, List<HttpLog> list){
        WritableWorkbook wwb;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet("httpLog", 10);        // 创建一个工作表

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            ws.setRowView(1, 500);

            ws.addCell(new Label(1, 0, "cf_app_id", wcf));
            ws.addCell(new Label(2, 0, "cf_app_name", wcf));
            ws.addCell(new Label(3, 0, "cf_org_id", wcf));
            ws.addCell(new Label(4, 0, "cf_org_name", wcf));
            ws.addCell(new Label(5, 0, "cf_space_id", wcf));
            ws.addCell(new Label(6, 0, "cf_space_name", wcf));
            ws.addCell(new Label(7, 0, "uri", wcf));
            ws.addCell(new Label(8, 0, "instance_id", wcf));
            ws.addCell(new Label(9, 0, "instance_index", wcf));
            ws.addCell(new Label(10, 0, "ip", wcf));
            ws.addCell(new Label(11, 0, "user_agent", wcf));
            ws.addCell(new Label(12, 0, "start_timestamp", wcf));
            ws.addCell(new Label(13, 0, "stop_timestamp", wcf));
            ws.addCell(new Label(14, 0, "time", wcf));
            ws.addCell(new Label(15, 0, "status_code", wcf));

            //    填充数据的内容
            for (int i = 0; i < list.size(); i++){
                wcf = new WritableCellFormat();
                ws.addCell(new Label(1, i + 1, list.get(i).getCf_app_id(), wcf));
                ws.addCell(new Label(2, i + 1, list.get(i).getCf_app_name(), wcf));
                ws.addCell(new Label(3, i + 1, list.get(i).getCf_org_id(), wcf));
                ws.addCell(new Label(4, i + 1, list.get(i).getCf_org_name(), wcf));
                ws.addCell(new Label(5, i + 1, list.get(i).getCf_space_id(), wcf));
                ws.addCell(new Label(6, i + 1, list.get(i).getCf_space_name(), wcf));
                ws.addCell(new Label(7, i + 1, list.get(i).getUri(), wcf));
                ws.addCell(new Label(8, i + 1, list.get(i).getInstance_id(), wcf));
                ws.addCell(new Label(9, i + 1, list.get(i).getInstance_index(), wcf));
                ws.addCell(new Label(10, i + 1, list.get(i).getIp(), wcf));
                ws.addCell(new Label(11, i + 1, list.get(i).getUser_agent(), wcf));
                ws.addCell(new Label(12, i + 1, list.get(i).getStart_timestamp(), wcf));
                ws.addCell(new Label(13, i + 1, list.get(i).getStop_timestamp(), wcf));
                ws.addCell(new Label(14, i + 1, list.get(i).getTime(), wcf));
                ws.addCell(new Label(15, i + 1, list.get(i).getStatus_code(), wcf));
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
    }


    public  void exportOperExcel(List<OperLog> list,HttpServletResponse response){
        try{
            String path=getPath()+"/"+UtilMethod.getTimeString()+"_operLog.xls";
            exportOperExcel(path,list);
            clientDownLoad(path,response);
        }catch(Exception e){

        }
    }

    private  void exportOperExcel(String fileName, List<OperLog> list){
        WritableWorkbook wwb;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet("operLog", 10);        // 创建一个工作表

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            ws.setRowView(1, 500);

            ws.addCell(new Label(1, 0, "cf_app_id", wcf));
            ws.addCell(new Label(2, 0, "cf_app_name", wcf));
            ws.addCell(new Label(3, 0, "cf_org_id", wcf));
            ws.addCell(new Label(4, 0, "cf_org_name", wcf));
            ws.addCell(new Label(5, 0, "cf_space_id", wcf));
            ws.addCell(new Label(6, 0, "cf_space_name", wcf));
            ws.addCell(new Label(7, 0, "msg", wcf));
            ws.addCell(new Label(8, 0, "time", wcf));

            //    填充数据的内容
            for (int i = 0; i < list.size(); i++){
                wcf = new WritableCellFormat();
                ws.addCell(new Label(1, i + 1, list.get(i).getCf_app_id(), wcf));
                ws.addCell(new Label(2, i + 1, list.get(i).getCf_app_name(), wcf));
                ws.addCell(new Label(3, i + 1, list.get(i).getCf_org_id(), wcf));
                ws.addCell(new Label(4, i + 1, list.get(i).getCf_org_name(), wcf));
                ws.addCell(new Label(5, i + 1, list.get(i).getCf_space_id(), wcf));
                ws.addCell(new Label(6, i + 1, list.get(i).getCf_space_name(), wcf));
                ws.addCell(new Label(7, i + 1, list.get(i).getMsg(), wcf));
                ws.addCell(new Label(8, i + 1, list.get(i).getTime(), wcf));
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
    }


    public  void exportCountExcel(List<CountLog> list,HttpServletResponse response){
        try{
            String path=getPath()+"/"+UtilMethod.getTimeString()+"_countLog.xls";
            exportCountExcel(path,list);
            clientDownLoad(path,response);
        }catch(Exception e){

        }
    }

    private  void exportCountExcel(String fileName, List<CountLog> list){
        WritableWorkbook wwb;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            wwb = Workbook.createWorkbook(fos);
            WritableSheet ws = wwb.createSheet("countLog", 10);        // 创建一个工作表

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            ws.setRowView(1, 500);

            ws.addCell(new Label(1, 0, "cf_app_id", wcf));
            ws.addCell(new Label(2, 0, "cf_app_name", wcf));
            ws.addCell(new Label(3, 0, "cf_org_id", wcf));
            ws.addCell(new Label(4, 0, "cf_space_id", wcf));
            ws.addCell(new Label(5, 0, "indicator", wcf));
            ws.addCell(new Label(6, 0, "indicator_value", wcf));
            ws.addCell(new Label(7, 0, "indicator_desc", wcf));
            ws.addCell(new Label(8, 0, "time", wcf));

            //    填充数据的内容
            for (int i = 0; i < list.size(); i++){
                wcf = new WritableCellFormat();
                ws.addCell(new Label(1, i + 1, list.get(i).getCf_app_id(), wcf));
                ws.addCell(new Label(2, i + 1, list.get(i).getCf_app_name(), wcf));
                ws.addCell(new Label(3, i + 1, list.get(i).getCf_org_id(), wcf));
                ws.addCell(new Label(4, i + 1, list.get(i).getCf_space_id(), wcf));
                ws.addCell(new Label(5, i + 1, list.get(i).getIndicator(), wcf));
                ws.addCell(new Label(6, i + 1, list.get(i).getIndicator_value(), wcf));
                ws.addCell(new Label(7, i + 1, list.get(i).getIndicator_desc(), wcf));
                ws.addCell(new Label(8, i + 1, list.get(i).getTime(), wcf));
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
    }

    private void clientDownLoad(String path, HttpServletResponse response) throws Exception{
        //客户端下载
        File downFile = new File(path);
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment; filename=" +downFile.getName());
            response.addHeader("Cache-Control", "no-cache");
            InputStream blobStream = new FileInputStream(downFile);
            ServletOutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[10 * 1024];
            int nbytes;
            while ( (nbytes = blobStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, nbytes);
            }
            outStream.flush();
            outStream.close();
            blobStream.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            File dir=new File(getPath());
            File[] files=dir.listFiles();
            for(int i=0;i<files.length;i++){
                files[i].delete();
            }
        }
    }
}
