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
@Table(name="user")
public class User {
    @Id
    private int id;

    private String nickname;
    private String username;
    private String password;
    private int role;
    private int status;
    private BigInteger createTime;
}
