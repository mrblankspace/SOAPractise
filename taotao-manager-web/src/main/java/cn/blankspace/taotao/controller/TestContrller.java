package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestContrller {
    @Autowired
     private TestService testService;


    @RequestMapping("/test/queryNow")
    @ResponseBody
    public String queryNow(){
        System.out.println("laile");
        return testService.queryNow();
    }

//    @Test
//    public void testFtp() throws IOException {
//        FTPClient ftpClient = new FTPClient();
//        ftpClient.connect("149.28.25.213");
//        ftpClient.login("ftpuser","1042079408");
//        FileInputStream inputStream = new FileInputStream("aaa.txt");
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//        ftpClient.storeFile("123",inputStream);
//        inputStream.close();
//        ftpClient.logout();
//    }
}
