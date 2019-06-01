package moe.perci.pie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name="meta")
public class Meta {
    @Id
    private int id;

    private String metaKey;
    private String metaValue;
}
