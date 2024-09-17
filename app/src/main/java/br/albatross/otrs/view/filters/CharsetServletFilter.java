package br.albatross.otrs.view.filters;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharsetServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.ISO_8859_1.name());
        chain.doFilter(request, response);
    }

}
