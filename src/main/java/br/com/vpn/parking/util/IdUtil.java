package br.com.vpn.parking.util;

import java.util.UUID;

public abstract class IdUtil {
    public static String getUUID() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
