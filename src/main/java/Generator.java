import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname
 * @Description
 * @Date 2022/5/13 15:26
 * @Created by wind
 */
public class Generator {
    public static void main(String[] args) throws IOException {

        //1.将模版以文件的形式读入

        //获取文件地址
        String path = ClassLoader.getSystemResource("pojoTemplate/user.txt").getPath();

        //根据文件地址找到文件
        File file = new File(path);

        //获取文件绝对路径
        Path absolutePath = Paths.get(file.getAbsolutePath());


        //2.将读入的文件转为string 字符串
        //读取其内容
        String data = new String(Files.readAllBytes(absolutePath), "UTF8");


        //3.将转为的string字符串 中的占位符 替换成自己想要的内容

        //定义要替换的内容参数 map的key为和模版中${}内的值一致 value为要替换的结果
        Map<String, Object> param = new HashMap<>();

        String[] strs = {"name","age","gender"};

        param.put("params", strs);
        param.put("table","user");

        //创建模版引起上下文 并传入要替换的参数
        VelocityContext vc = new VelocityContext(param);
        //创建StringWriter对象 其内部是对StringBuffer进行的操作
        StringWriter writer = new StringWriter();
        //模版引起开始替换模版内容
        Velocity.evaluate(vc, writer, "code_gen", data);
        //替换之后的字符串
        String sqlmapResult = writer.getBuffer().toString();

        //4.将新的字符串输出到文件

        //新文件的路径+文件名+后缀
        File sqlmapOutFile = new File("D:\\IDEAProject\\lowcode\\myLowCode\\src\\main\\java" + "\\User.java");
        if (!sqlmapOutFile.exists()) {
            sqlmapOutFile.createNewFile();
        }
        Path outPath = Paths.get(sqlmapOutFile.getAbsolutePath());
        //将文件写入
        try (BufferedWriter out = Files.newBufferedWriter(outPath)) {
            out.write(sqlmapResult);
        }

    }

}
