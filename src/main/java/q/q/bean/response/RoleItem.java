package q.q.bean.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleItem implements Serializable {

    private static final long serialVersionUID = -8418583969301168757L;
    @SerializedName("companyId")
    private String companyId;

    @SerializedName("roleId")
    private int roleId;

    /**
     * 选人的角色的Id
     */
    @SerializedName("roleCustomId")
    private String roleCustomId;

    @SerializedName("roleName")
    private String roleName;

    @SerializedName("roleIcon")
    private String roleIcon;
}