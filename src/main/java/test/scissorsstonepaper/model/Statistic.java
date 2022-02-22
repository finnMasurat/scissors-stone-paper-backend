package test.scissorsstonepaper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private Long playerId;
    @Column
    private Integer winCount;
    @Column
    private Integer lossCount;
    @Column
    private Integer drawCount;

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", playerId='" + playerId + '\'' +
                ", winCount='" + winCount + '\'' +
                ", lossCount='" + lossCount + '\'' +
                ", drawCount='" + drawCount + '\'' +
                '}';
    }
}
