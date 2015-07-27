

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageDemo {
	static List<File> list = new ArrayList<File>(); 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int yes;
        
		String path = "."; //"H://資料//Pixiv動態圖錄製，完成後在原圖下方會出現gif圖//Java//test";
		System.out.println("If you want to rename, input 123:");
		Scanner scanner = new Scanner(System.in);
		yes = scanner.nextInt();
		scanner.close();
		if( yes == 123 ){
			File f = new File(path);
			
			//ArrayList<String> fileList = new ArrayList<String>();
			
			if(f.isDirectory()){
				System.out.println("filename : "+f.getName()); 
				ReadAllFile(path);
				System.out.println("size : "+list.size());
				for(int i=1;i<list.size();i++)
	            {
					
					
					File file = new File(list.get(i).getAbsolutePath());
					
					boolean result = isImage(file);
					
					if(isImage(file)){
					
						System.out.println("result="+list.get(i).getName()+result);
				    
						String formatN = getExtension(file);

					
						System.out.println("真副檔名："+list.get(i).getAbsolutePath().substring(list.get(i).getAbsolutePath().lastIndexOf(".")+1) );
						if(formatN.equalsIgnoreCase(list.get(i).getAbsolutePath().substring(list.get(i).getAbsolutePath().lastIndexOf(".")+1))){
				    	
				    	}else{
				    		File toFileA = new File(list.get(i).getAbsolutePath().substring(0,list.get(i).getAbsolutePath().lastIndexOf("."))+"."+formatN   );
				    		list.get(i).renameTo(toFileA);
				    	}
				    }
						

					//fileList.add(s[i]);
					
	            }
				
				/*for(int i=0;i<fileList.size();i++)
		        {
		            System.out.println(fileList.get(i)); //印出資料夾內的檔名
		        }*/
		         
			}
			System.out.println("yes");
		}
		else
		{
			System.out.println("no");
		}
		


	}
	
	public static String getExtension(File file) {
		ImageInputStream iis = null;
		String result = null;
		try {
			iis = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis); 
			if(iter.hasNext()){
				String temp =iter.next().getFormatName();
				System.out.println("扩展名:"+temp);
				if(temp.equals("JPEG")){
					result="jpg";
				}else{
					result=temp;
				}
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(iis!=null){
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static boolean isImage(File resFile){
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(resFile);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis); 
			if (iter.hasNext()) {//文件不是图片 
			    return true;
			} 
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(iis!=null){
				try {
					iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static void ReadAllFile(String filePath) {  
        File f = null;  
        f = new File(filePath);  
        File[] files = f.listFiles(); // 得到f文件夹下面的所有文件。  
 
        for (File file : files) {  
            if(file.isDirectory()) {  
                //如何当前路劲是文件夹，则循环读取这个文件夹下的所有文件  
                ReadAllFile(file.getAbsolutePath());  
            } else {  
                list.add(file);  
            }  
        }  
        for(File file : files) {  
            System.out.println(file.getAbsolutePath());  
        }  
    }  
	
}
