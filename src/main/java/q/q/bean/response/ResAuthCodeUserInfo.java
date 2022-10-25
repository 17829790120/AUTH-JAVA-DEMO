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
public class ResAuthCodeUserInfo implements Serializable {

    /**
     *
     */
    @SerializedName("hasUser")
    @Expose
    private String hasUser;

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("userInfo")
    @Expose
    private ResAuthCodeUserItem userInfo;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("companyId")
    @Expose
    private String companyId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
}
    