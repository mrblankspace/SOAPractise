package cn.blankspace.taotao.service;

import cn.blankspace.taotao.pojo.PictureResult;
import cn.blankspace.util.FtpUtil;
import cn.blankspace.util.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 学习基于spring的单元测试
 */
@Service
public class PictureServiceImpl implements PictureService{
    @Value("${IMAGE_BASE_URL}")      //
    private String IMAGE_BASE_URL;

    @Value("${FILE_UPLOAD_PATH}")
    private String FILE_UPLOAD_PATH;     //?home/ftpuser

    @Value("${FTP_SERVER_IP}")
    private String FTP_SERVER_IP;

    @Value("${FTP_SERVER_PORT}")
    private Integer FTP_SERVER_PORT;

    @Value("${FTP_SERVER_USERNAME}")
    private String FTP_SERVER_USERNAME;

    @Value("${FTP_SERVER_PASSWORD}")
    private  String FTP_SERVER_PASSWORD;

    @Override
    public PictureResult uplodFile(MultipartFile uploadFile) {
        System.out.println("inserve");
        String path = savePicture(uploadFile);
        PictureResult result = new PictureResult(0, IMAGE_BASE_URL+path);
        return result;
    }

    private String savePicture(MultipartFile uploadFile){
        String result = null;
        if (uploadFile.isEmpty()){
            return null;
        }
        try {
            String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date())+ "/"+
                    new SimpleDateFormat("MM").format(new Date())+"/"+
                    new SimpleDateFormat("dd").format(new Date());
            String originalFilename = uploadFile.getOriginalFilename();
            String newFileName = IDUtils.genImageName()+originalFilename.substring(originalFilename.lastIndexOf("."));
            FtpUtil.uploadFile(FTP_SERVER_IP,FTP_SERVER_PORT,FTP_SERVER_USERNAME,FTP_SERVER_PASSWORD,FILE_UPLOAD_PATH,filePath,newFileName,uploadFile.getInputStream());
            result = filePath + "/" +newFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }
}
