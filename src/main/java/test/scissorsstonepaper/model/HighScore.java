package test.scissorsstonepaper.model;

import lombok.Data;

@Data
public class HighScore {
    private String name;
    private Integer winCount;
    private Integer lossCount;
    private Integer drawCount;

    public HighScore(String name, Integer winCount, Integer lossCount, Integer drawCount) {
        this.name = name;
        this.winCount = winCount;
        this.lossCount = lossCount;
        this.drawCount = drawCount;
    }
}
