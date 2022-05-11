package hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email")})
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    private String name;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
