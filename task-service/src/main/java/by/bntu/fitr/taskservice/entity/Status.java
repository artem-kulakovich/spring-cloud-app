package by.bntu.fitr.taskservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "status")
public class Status {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq")
    @SequenceGenerator(name = "status_seq", sequenceName = "status_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
