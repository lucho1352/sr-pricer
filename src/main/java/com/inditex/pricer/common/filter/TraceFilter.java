package com.inditex.pricer.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * This class will be logging rest interactions and time taken to response (Latency)
 * Also adds a transactionId for traceability purposes
 */
@Slf4j
@Component
public class TraceFilter implements Filter {

    public static final String TRANSACTION_ID = "transactionId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();
        String transactionId = UUID.randomUUID().toString();
        MDC.put(TRANSACTION_ID,transactionId);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader(TRANSACTION_ID, transactionId);

        //Executing method requested
        chain.doFilter(request, response);
        long endTime = System.currentTimeMillis();
        log.info("Audit {} {} - Time taken {}ms", req.getMethod(), req.getRequestURI(), (endTime - startTime));
    }
}
