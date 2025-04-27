package md.crudproject.fincontrol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID userId;
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private LocalDate createdAt;
}
