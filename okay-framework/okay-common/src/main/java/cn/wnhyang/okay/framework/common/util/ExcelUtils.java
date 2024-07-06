package cn.wnhyang.okay.framework.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.longconverter.LongStringConverter;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author wnhyang
 * @date 2024/5/14
 **/
public class ExcelUtils {

    /**
     * 从Excel文件中读取数据。
     *
     * @param file 需要读取的MultipartFile文件对象，通常来自上传的文件。
     * @param head 数据实体的Class类型，用于解析Excel的头部信息并映射到对应的实体对象。
     * @return 返回一个包含读取到的所有数据实体的List集合。
     * @throws IOException 当读取文件发生错误时抛出IOException。
     */
    public static <T> List<T> read(MultipartFile file, Class<T> head) throws IOException {
        // 使用EasyExcel框架读取Excel文件内容
        return EasyExcel.read(file.getInputStream(), head, null)
                .autoCloseStream(Boolean.FALSE)
                .doReadAllSync();
    }

    /**
     * 将数据写入Excel文件。
     *
     * @param response  HttpServletResponse对象，用于将Excel文件写入响应。
     * @param fileName  Excel文件的名称，用于设置响应的Content-Disposition头信息。
     * @param sheetName Excel文件的sheet名称，用于设置Excel文件的sheet名称。
     * @param head      数据实体的Class类型，用于解析Excel的头部信息并映射到对应的实体对象。
     * @param data      需要写入的数据，类型为List<T>，其中T为数据实体的Class类型。
     */
    public static <T> void write(HttpServletResponse response, String fileName, String sheetName, Class<T> head, List<T> data) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
        // 输出 Excel
        EasyExcel.write(response.getOutputStream(), head)
                // 基于 column 长度，自动适配。最大 255 宽度
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                // 避免 Long 类型丢失精度
                .registerConverter(new LongStringConverter())
                .autoCloseStream(Boolean.FALSE)
                .sheet(sheetName).doWrite(data);
        // 设置 header 和 contentType。写在最后的原因是，避免报错时，响应 contentType 已经被修改了
    }
}
