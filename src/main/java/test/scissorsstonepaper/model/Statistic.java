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
    @Column
    private Integer winCount;
    @Column
    private Integer lossCount;
    @Column
    private Integer drawCount;
//    @OneToOne(fetch=FetchType.LAZY, mappedBy = "statistic")
//    private Player player;

    public Statistic(int win, int loss, int draw) {
        this.winCount = win;
        this.lossCount = loss;
        this.drawCount = draw;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", winCount='" + winCount + '\'' +
                ", lossCount='" + lossCount + '\'' +
                ", drawCount='" + drawCount + '\'' +
                '}';
    }
}
