package com.uralkeser.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @SequenceGenerator(name = "generator")
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "e_mail", nullable = false, length = 50)
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
