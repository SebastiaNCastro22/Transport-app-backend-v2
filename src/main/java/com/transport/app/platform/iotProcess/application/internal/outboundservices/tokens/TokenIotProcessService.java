package com.transport.app.platform.iotProcess.application.internal.outboundservices.tokens;



public interface TokenIotProcessService {
    String generateToken(String nameIotDevice);
    boolean validateToken(String token);
    String getNameIotDeviceFromToken(String token);
}
