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
import java.util.List;
import java.util.Map;

/**
 * 查询返回的用户信息
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
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("loginAccount")
    @Expose
    private String loginAccount;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("thirdUserId")
    @Expose
    private String thirdUserId;
    @SerializedName("headImg")
    @Expose
    private String headImg;
    @SerializedName("extraInfo")
    @Expose
    private Map<String, String> extraInfo;

    @Expose
    private List<DepItem> depItemList;

    @SerializedName("roleItemList")
    @Expose
    private List<RoleItem> roleItemList;
}
    