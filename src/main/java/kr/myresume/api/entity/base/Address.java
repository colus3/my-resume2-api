package kr.myresume.api.entity.base;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String zipCode;
    private String address1;
    private String address2;

    public Address(String zipCode, String address1, String address2) {
        this.zipCode = zipCode;
        this.address1 = address1;
        this.address2 = address2;
    }

    public String toFullAddress() {
        List<String> addressList = Lists.newArrayList();
        if (!StringUtils.isEmpty(zipCode)) {
            addressList.add(addressList.size(), String.format("(%s)", zipCode));
        }
        if (!StringUtils.isEmpty(address1)) {
            addressList.add(addressList.size(), address1);
            if (!StringUtils.isEmpty(address2)) {
                addressList.add(addressList.size(), address2);
            }
        }
        return String.join(" ", addressList);
    }
}
