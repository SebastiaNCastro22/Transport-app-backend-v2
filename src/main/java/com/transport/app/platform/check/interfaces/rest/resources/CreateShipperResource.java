package com.transport.app.platform.check.interfaces.rest.resources;

import java.util.Date;

public record CreateShipperResource(String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {
}
