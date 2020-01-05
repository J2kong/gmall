package com.demo.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {


    @Test
    public void contextLoads() throws IOException, MyException {
      // 配置fdfs的全局链接地址
        String tracker  = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();
        ClientGlobal.init(tracker);

        TrackerClient trackerClient =new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        System.out.println(trackerServer);
        StorageClient storageClient = new StorageClient(trackerServer,null);

        String filename = "C:/StudyResource/111.jpg";
        String[] uploadInfos = storageClient.upload_file(filename, "jpg", null);
        String url = "http://106.15.248.81";

        for (String uploadInfo: uploadInfos) {
            url += "/"+uploadInfo;
        }

        System.out.println(url);

    }

}
