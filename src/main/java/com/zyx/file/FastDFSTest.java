package com.zyx.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;


/**
 * 
 * @title FastDFSTest.java
 * @package com.file
 * @description TODO
 * @author ZhangHuaRong   
 * @update 2016年5月27日 上午10:28:24
 */
public class FastDFSTest {
	
	/**
	 * 上传测试.
	 * @throws Exception
	 */
	public static void upload() throws Exception {
		//String filePath = "D:/img/2.jpg";
		String filePath = "/home/Rainbow/desk/1.jpg";
		File file = new File(filePath);
		String fileId = FastDFSClient.uploadFile(file, filePath);
		System.out.println("Upload local file " + filePath + " ok, fileid=" + fileId);
		// fileId:	group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg
		//group1/M00/00/00/cEpOSlc4mV2AM799AAA-dTeNr1g816.jpg

		// url:	http://slave2:8888/group1/M00/00/00/cEpOSlc4lfGAH32UAAAqFU2nbQg763.jpg
		//http://slave2:8888/group1/M00/00/00/cEpOSldHsr2Aa_chAABi02IYZxE441.jpg

	}
	
	/**
	 * 下载测试.
	 * @throws Exception
	 */
	public static void download() throws Exception {
		String fileId = "group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg";
		InputStream inputStream = FastDFSClient.downloadFile(fileId);
		File destFile = new File("E:/WorkSpaceSpr10.6/edu-demo-fdfs/TestFile/DownloadTest.jpg");
		FileUtils.copyInputStreamToFile(inputStream, destFile);
	}

	/**
	 * 删除测试
	 * @throws Exception
	 */
	public static void delete() throws Exception {
		String fileId = "group1/M00/00/00/wKgEfVUYPieAd6a0AAP3btxj__E335.jpg";
		int result = FastDFSClient.deleteFile(fileId);
		System.out.println(result == 0 ? "删除成功" : "删除失败:" + result);
	}


	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		String [] strings = new String[]{"png","gif","jpeg","jpeg","jpg","bmp"};
//		System.out.println(Arrays.binarySearch(strings, "aaaa")!=-1);
//		System.out.println(FastDFSClient.class.getResource("/fdfs_client.conf").getFile());
		upload();
		//download();
	//	delete();

	}

}
