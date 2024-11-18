package com.transport.app.platform.profiles.domain.model.aggregates;

import com.transport.app.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.transport.app.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.transport.app.platform.profiles.domain.model.valueobjects.PersonName;
import com.transport.app.platform.profiles.domain.model.valueobjects.StreetAddress;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    private PersonName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))})
    private EmailAddress email;

    private Date birthday;
    private long dni;
    private String address;
    private String phone;

    public Profile(String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.birthday = birthday;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
    }

    public Profile() {
    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.birthday = command.birthday() ;
        this.dni = command.dni();
        this.address = command.address();
        this.phone = command.phone();
    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmailAddress() {
        return email.address();
    }

    public String getCity() { return address; }

    public Profile updateInformation(String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {
        this.name = new PersonName(firstName,lastName);
        this.email = new EmailAddress(email);
        this.birthday = birthday;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
        return this;
    }
}
