package uz.pdp.appcodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String solution;


    @Column(nullable = false)
    private String hint;


    @Column(nullable = false)
    private String method;


    @Column(nullable = false)
    private String hasStar;

    @ManyToOne
    private Language language;
}
