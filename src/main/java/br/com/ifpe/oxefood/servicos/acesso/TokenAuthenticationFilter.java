package br.com.ifpe.oxefood.servicos.acesso;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {

	String token = request.getHeader("Authorization");
	if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
	    token = null;
	}

	token = token.substring(7, token.length());

	filterChain.doFilter(request, response);
    }

}
