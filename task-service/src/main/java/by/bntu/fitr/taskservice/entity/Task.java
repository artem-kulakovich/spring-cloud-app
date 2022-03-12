package by.bntu.fitr.taskservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "task")
public class Task {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @JsonProperty(value = "name")
    @Column(name = "name")
    private String name;

    @JsonProperty(value = "description")
    @Column(name = "description")
    private String description;

    @JsonProperty(value = "estimatedTime")
    @Column(name = "estimated_time")
    private Date estimatedTime;

    @JsonProperty(value = "remainingTime")
    @Column(name = "remaining_time")
    private Date remainingTime;

    @JsonProperty(value = "spent_time")
    @Column(name = "spent_time")
    private Date spentTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_id")
    private Status status;


    public Task(String name, String description, Date estimatedTime, Date remainingTime, Date spentTime) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.remainingTime = remainingTime;
        this.spentTime = spentTime;
    }

}
