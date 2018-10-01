package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.pojo.PictureResult;
import cn.blankspace.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pic")
public class PictureConroller {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    public PictureResult upload(MultipartFile uploadFile) {
        System.out.println(uploadFile.getOriginalFilename());
        PictureResult pictureResult = pictureService.uplodFile(uploadFile);
        return pictureResult;
    }
}
