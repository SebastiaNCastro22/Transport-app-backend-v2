package com.transport.app.platform.check.domain.model.commands;

import java.util.Date;

public record CreateTransporterCommand(String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {

}
