import lombok.Data;

/**
 * Created by noodles on 16/11/22 下午7:21.
 */
@Data
public class MetricsLog {
    private String level;
    private long time;
    private String title;
    private String message;
    private String fromIp;

}
