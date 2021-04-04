package com.mmtax.common.utils.file;

import com.mmtax.common.config.Global;
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
 * 图片上传工具类
 * 
 * @author mmtax
 */
public class PictureUploadUtils
{
    /**
     * 移动端上传图片最大大小 5M
     */
    public static final long ID_CARD_MAX_SIZE = 5 * 1024 * 1024;
    /**
     * 后台营业执照最大大小 300kb
     */
    public static final long LICENSE_MAX_SIZE = 300 * 1024;
    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 图片上传的地址
     */
    private static String pictureDir = Global.getPictures();

    private static int counter = 0;

    public static void setPictureDir(String defaultBaseDir)
    {
        PictureUploadUtils.pictureDir = defaultBaseDir;
    }

    public static String getPictureDir()
    {
        return pictureDir;
    }

    /**
     * 身份证图片上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String uploadIdCardPicture(MultipartFile file) throws IOException
    {
        try
        {
            return uploadIdCardPicture(getPictureDir(), file, MimeTypeUtils.PICTURE_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据身份证图片路径上传
     *
     * @param pictureDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadIdCardPicture(String pictureDir, MultipartFile file) throws IOException
    {
        try
        {
            return uploadIdCardPicture(pictureDir, file, MimeTypeUtils.PICTURE_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 身份证图片上传
     *
     * @param pictureDir 相对应用的基目录
     * @param file 上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String uploadIdCardPicture(String pictureDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > PictureUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(PictureUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension,ID_CARD_MAX_SIZE);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(pictureDir, fileName);
        file.transferTo(desc);
        return fileName;
    }

    /**
     * 营业执照上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String uploadLicensePicture(MultipartFile file) throws IOException
    {
        try
        {
            return uploadLicensePicture(getPictureDir(), file, MimeTypeUtils.PICTURE_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据营业执照路径上传
     *
     * @param pictureDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadLicensePicture(String pictureDir, MultipartFile file) throws IOException
    {
        try
        {
            return uploadLicensePicture(pictureDir, file, MimeTypeUtils.PICTURE_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 营业执照上传
     *
     * @param pictureDir 相对应用的基目录
     * @param file 上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String uploadLicensePicture(String pictureDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > PictureUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(PictureUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension,LICENSE_MAX_SIZE);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(pictureDir, fileName);
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
     * @param file 上传的文件
     * @param allowedExtension 允许上传的格式
     * @param maxSize 文件最大大小
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension,Long maxSize)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        long size = file.getSize();
        if (maxSize != -1 && size > maxSize)
        {
            throw new FileSizeLimitExceededException(maxSize / 1024 / 1024);
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
            }else if (allowedExtension == MimeTypeUtils.PICTURE_ALLOWED_EXTENSION)
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
