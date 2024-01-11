package com.w83ll43.filter;

import cn.hutool.json.JSONUtil;
import com.w83ll43.common.Result;
import com.w83ll43.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    // 路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1、获取本次请求的 URI
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}", request.getRequestURI());

        // 不需要处理的请求路径
        String[] urls = new String[]{
                "/api/user/login"
        };

        // 2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        // 3、如果不需要处理则直接放行
        if (check) {
            log.info("本次请求 {} 不需要处理", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // 4、判断登录状态 如果已登录则放行 用户端
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户 {} 已登录！", request.getSession().getAttribute("user"));

            log.info("线程 id 为 {} ", Thread.currentThread().getId());
            // 设置线程副本的局部变量
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录！");
        // 5、如果未登录则返回未登录结果 通过输出流方式向客户端响应数据
        response.getWriter().write(JSONUtil.toJsonStr(Result.error("Please login first!")));
        // log.info("拦截到请求：{}", request.getRequestURI());
    }

    /**
     * 匹配路径
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }

}
