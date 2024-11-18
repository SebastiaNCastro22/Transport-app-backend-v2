package com.transport.app.platform.iotProcess.application.internal.outboundservices.hashing;

import org.springframework.stereotype.Service;


public interface HashingIotProcessService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
