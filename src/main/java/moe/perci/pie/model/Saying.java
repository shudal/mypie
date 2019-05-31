package moe.perci.pie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@Entity
@Table(name="saying")
public class Saying {
    @Id
    private int id;

    private String content;
    private int speed;
    private int vol;
    private int per;
    private int pit;

    private int status;
    private BigInteger createTime;
}
