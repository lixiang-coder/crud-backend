package com.xzy.crud.controller;

import com.xzy.crud.annotation.Log;
import com.xzy.crud.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@Tag(name = "导入、导出报表")
public class ReportController {
    @Resource
    private ReportService reportService;


    @GetMapping("/export")
    @Operation(summary = "导出用户数据报表")
    @Log
    public void export(HttpServletResponse response) throws IOException {
        reportService.exportBusinessData(response);
    }
}
