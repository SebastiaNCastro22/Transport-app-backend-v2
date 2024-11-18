package com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.tokens.jwt.services;
;
import com.transport.app.platform.iotProcess.application.internal.outboundservices.tokens.TokenIotProcessService;
import com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.tokens.jwt.BearerTokenIotProcessService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenIotProcessServiceImpl implements TokenIotProcessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenIotProcessServiceImpl.class);

    @Value("${iot.token.secret}")
    private String secret;

    @Value("${iot.token.expiration.minutes}")
    private int expirationMinutes;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(String nameIotDevice) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMinutes * 60 * 1000);

        return Jwts.builder()
                .setSubject(nameIotDevice)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("Invalid or expired token: {}", e.getMessage());
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    @Override
    public String getNameIotDeviceFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
