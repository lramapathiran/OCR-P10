package com.lavanya.api.configs;

import java.io.IOException;
import java.util.function.Function;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import com.lavanya.api.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

//public class JwtTokenFilter extends GenericFilterBean {
	
//	@Autowired
//    private UserService service;
//	
//	@Value("${security.jwt.token.secret-key:secret}")
//    private String secretKey = "secret";
//
//    private JwtTokenProvider jwtTokenProvider;
//
//    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//    
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//    
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }    
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//        throws IOException, ServletException {
//    	String userName = null;
//    	UserDetails userDetails = service.loadUserByUsername(userName);
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
//        final String username = extractUsername(token);
//        if (token != null && jwtTokenProvider.validateToken(token, userDetails)) {
//            Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token,username) : null;
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        filterChain.doFilter(req, res);
//    }
//}
