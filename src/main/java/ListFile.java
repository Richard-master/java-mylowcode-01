import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Classname
 * @Description
 * @Date 2022/5/13 16:47
 * @Created by wind
 */
public class ListFile {
    public static void main(String[] args) {
//        传入一个路径
        getFiles("D:\\IDEAProject\\lowcode\\myLowCode");

    }
    public static void getFiles(String path) {
//        1、创建File对象
        File file = new File(path);
//        2、遍历该目录下的所有文件
        for(File f:file.listFiles()) {
//            3、判断如果有文件是目录
            if(f.isDirectory()) {
//                4、递归调用
                getFiles(f.getAbsolutePath());
            }else {
//                如果不是目录，输出文件
                System.out.println(f.getAbsolutePath());
                String[] split = f.getAbsolutePath().split(".java");
                System.out.println(split.length);
                String name = "";
                for (int i = 0;i< split.length;i++){
                    name+=split[i];
                }
                System.out.println(name);
            }
        }
    }
}