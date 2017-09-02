package com.hex.bigdata.udsp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by PC on 2017/7/4.
 */
public class EleployeeUpdateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        if(httpRequest.getRequestURI().indexOf("goframe/p/goframe.employee.list") > 0){
            httpRequest.getRequestDispatcher("/goframe/p/goframe.employeeUpdate.list").forward(servletRequest,servletResponse);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
