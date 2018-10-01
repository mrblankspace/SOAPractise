package cn.blankspace.taotao.service;

import cn.blankspace.taotao.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    public PictureResult uplodFile(MultipartFile uploadFile);
}
