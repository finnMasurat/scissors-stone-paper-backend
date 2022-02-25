package test.scissorsstonepaper.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatisticRequest implements Serializable {
    private String type;

    @Override
    public String toString() {
        return "UpdateStatisticRequest{" +
                "type='" + type + '\'' +
                '}';
    }
}
