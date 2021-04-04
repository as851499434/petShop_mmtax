package com.mmtax.common.utils;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.mmtax.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;

/**
 * 文件压缩解压工具
 */
@Slf4j
public class DeCompressUtil {

    public static final Integer RAR = 1;
    public static final Integer ZIP = 2;

    public static void unzipOrRar(String source,String target,Integer type){
        try {
            switch (type){
                case 2:
                    unzip(new File(source), target);
                    break;
                case 1:
                    unRar(source, target);
                    break;
                default:
            }
        }catch (Exception e){
            log.error("解压zip={}报错",source,e);
        }
    }

    private static void unzip(File source,String target) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File targetFile = new File(target);
        if(!targetFile.exists()){
            targetFile.mkdir();
        }
        //读取成zip文件
        ZipFile zipFile = new ZipFile(source);
        Enumeration entries = zipFile.getEntries();
        while(entries.hasMoreElements()){
            //读取zip内部文件
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            //文件名读取进stream流
            String entryName = zipEntry.getName();
            inputStream = zipFile.getInputStream(zipEntry);
            String outPath = (target+"/"+entryName).replaceAll("\\*","/");
            File file = new File(outPath.substring(0,outPath.lastIndexOf("/")));
            if(!file.exists()){
                file.mkdir();
            }
            //略过内部文件目录的解压读取
            if(new File(outPath).isDirectory()){
                continue;
            }
            outputStream = new FileOutputStream(outPath);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes))>0){
                outputStream.write(bytes,0,length);
            }
        }
        assert outputStream != null;
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        zipFile.close();
    }

    private static void unRar(String source,String target) throws Exception{
        File targetFile = new File(target);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        Archive archive = new Archive(new File(source));
        FileHeader header = archive.nextFileHeader();
        while(header != null){
            if(header.isDirectory()){
                File file = new File(target+ File.separator+header.getFileNameString());
            }else{
                File file = new File(target+File.separator+header.getFileNameString());
                try{
                    if(!file.exists()){
                        if(!file.getParentFile().exists()){
                            file.getParentFile().mkdirs();
                        }
                        file.createNewFile();
                    }
                    FileOutputStream outputStream = new FileOutputStream(file);
                    archive.extractFile(header,outputStream);
                    outputStream.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            header = archive.nextFileHeader();
        }
        archive.close();
    }
}
