package com.mmtax.common.utils.file;

import cn.hutool.db.ds.pooled.PooledDSFactory;
import com.mmtax.common.config.Global;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.file.FileNameLengthLimitExceededException;
import com.mmtax.common.exception.file.FileSizeLimitExceededException;
import com.mmtax.common.exception.file.InvalidExtensionException;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.security.Md5Utils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具类
 * 
 * @author mmtax
 */
public class FileUploadUtils
{
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 3M
     */
    public static final long THREE_M = 3 * 1024 * 1024;
    /**
     * 200KB
     */
    public static final long TWO_HUNDRED_K = 200 * 1024 ;
    /**
     * 30M
     */
    public static final long THIRETY_M = 30 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    public static final  String PDF = ".pdf";
    public static final  String JPG = ".jpg";
    public static final  String PNG = ".png";
    public static final  String JPEG = ".jpeg";
    public static final  String MP4 = ".mp4";
    public static final  String MOV = ".mov";

    private static final String[] IMGS = {".jpg",".png","jpeg","HEIC"};
    private static final String[] VIDEOS = {".mp4",".mov",".avi",".wmv",".asf",".mp",".navi"};




    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = Global.getProfile();

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }



    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadWithWorkOrder(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return uploadWithWorkOrder(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        return fileName;
    }


    public static final String uploadWithWorkOrder(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowedWithWorkOrder(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        return fileName;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file)
    {
        String filename = file.getOriginalFilename();
        String extension = getExtension(file);
        filename = DateUtils.datePath() + "/" + encodingFilename(filename) + "." + extension;
        return filename;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException
    {
        File desc = new File(uploadDir + File.separator + filename);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String filename)
    {
        filename = filename.replace("_", " ");
        filename = Md5Utils.hash(filename + System.nanoTime() + counter++);
        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String filename = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension))
        {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        filename);
            }
            else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        filename);
            }
            else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        filename);
            }
            else
            {
                throw new InvalidExtensionException(allowedExtension, extension, filename);
            }
        }

    }



    public static final void assertAllowedWithWorkOrder(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        long size = file.getSize();

        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        if(!PDF.equals(suffixName) && !equalsImg(suffixName) && !equalsVideo(suffixName)){
            throw new BusinessException(getErrorMessage());
        }
        if(PDF.equals(suffixName) && size > THREE_M){
            throw new BusinessException("PDF允许最大文件大小为2M");
        }
        if(equalsImg(suffixName) && size > TWO_HUNDRED_K ){
            throw new BusinessException("图片允许最大文件大小为200KB");
        }
        if(equalsVideo(suffixName) && size > THIRETY_M ){
            throw new BusinessException("视频允许最大文件大小为30M");
        }



    }

    public static boolean equalsImg(String extention){
        for (String s : IMGS){
            if(s.equalsIgnoreCase(extention)){
                return true;
            }
        }
        return  false;
    }

    public static boolean equalsVideo(String extention){

        for(String s : VIDEOS){
            if(s.equalsIgnoreCase(extention)){
                return true;
            }
        }
        return  false;
    }

    public static String getErrorMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("不支持此格式文件,只支持pdf,图片:");
        for (String s : IMGS){
            sb.append(s).append(",");
        }
        sb.append("视频:");
        for(String s : VIDEOS){
            sb.append(s).append(",");
        }

        return sb.toString().substring(0,sb.toString().length() - 1);

    }


    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension)
    {
        for (String str : allowedExtension)
        {
            if (str.equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     * 
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

}
