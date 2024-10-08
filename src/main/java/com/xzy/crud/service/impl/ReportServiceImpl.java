package com.xzy.crud.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xzy.crud.pojo.User;
import com.xzy.crud.service.ReportService;
import com.xzy.crud.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private UserService userService;


    // 导出用户数据报表
    @Override
    public void exportBusinessData(HttpServletResponse response) throws IOException {
        // 1.获取全部用户的数据
        List<User> userList = userService.findAll();
        // 2.提供Excel模板文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/用户数据报表模板.xlsx");

        Workbook workbook;
        if (inputStream != null) {
            // 复制模板到新的工作簿
            workbook = new XSSFWorkbook(inputStream);
        } else {
            // 如果没有模板，创建一个新的工作簿
            workbook = new XSSFWorkbook();
        }

        // 3.填充数据
        Sheet sheet = workbook.createSheet("用户数据");
        int rownum = 0;
        Row row = sheet.createRow(rownum++);

        // 创建标题行
        String[] headers = {"ID", "姓名", "年龄", "状态", "性别", "地址", "电话", "创建时间", "更新时间"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 填充用户数据
        for (User user : userList) {
            row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getAge());
            row.createCell(3).setCellValue(user.getStatus().toString());
            row.createCell(4).setCellValue(user.getSex());
            row.createCell(5).setCellValue(user.getAddress());
            row.createCell(6).setCellValue(user.getPhone());
            row.createCell(7).setCellValue(user.getCreateTime() != null ? DateUtil.format(user.getCreateTime(), "yyyy-MM-dd HH:mm:ss") : "");
            row.createCell(8).setCellValue(user.getUpdateTime() != null ? DateUtil.format(user.getUpdateTime(), "yyyy-MM-dd HH:mm:ss") : "");
        }

        // 4.写入响应
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("用户数据报表模板.xlsx", StandardCharsets.UTF_8.name()));
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                workbook.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
