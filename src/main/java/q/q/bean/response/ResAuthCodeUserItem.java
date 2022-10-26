package q.q.bean.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 类说明
 *
 * @author shawn
 * @since 2022/10/22 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResAuthCodeUserItem implements Serializable {
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("thirdUserId")
    @Expose
    private String thirdUserId;
    @SerializedName("headImg")
    @Expose
    private String headImg;
}
    