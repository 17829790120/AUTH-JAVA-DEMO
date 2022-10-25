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

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespUserDetail implements Serializable {
    @SerializedName("thirdUserId")
    @Expose
    public String thirdUserId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("avatar")
    @Expose
    public String avatar;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("depIds")
    @Expose
    public List<String> depIds = null;
    @SerializedName("mainDepId")
    @Expose
    public String mainDepId;
    @SerializedName("memberDiyExtra")
    @Expose
    public Map<String, String> memberDiyExtra;
}