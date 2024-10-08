package com.xzy.crud.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ReportService {

    void exportBusinessData(HttpServletResponse response) throws IOException;
}
