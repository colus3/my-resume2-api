package kr.myresume.api.entity.base;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AddressTest {

    @Test
    public void testAddress_값이_모두_있을_경우() {
        Address address = new Address("12345", "addr1", "addr2");
        String fullAddress = address.toFullAddress();

        assertThat(fullAddress).isEqualTo("(12345) addr1 addr2");
    }

    @Test
    public void testAddress_값이_모두_없을_경우() {
        Address address = new Address("", "", "");
        String fullAddress = address.toFullAddress();

        assertThat(fullAddress).isEqualTo("");
    }

    @Test
    public void testAddress_값이_우편번호가_없을_경우() {
        Address address = new Address("", "addr1", "addr2");
        String fullAddress = address.toFullAddress();

        assertThat(fullAddress).isEqualTo("addr1 addr2");
    }

    @Test
    public void testAddress_값이_주소1이_없을_경우() {
        Address address = new Address("12345", "", "addr2");
        String fullAddress = address.toFullAddress();

        assertThat(fullAddress).isEqualTo("(12345)");
    }

    @Test
    public void testAddress_값이_주소2이_없을_경우() {
        Address address = new Address("12345", "addr1", "");
        String fullAddress = address.toFullAddress();

        assertThat(fullAddress).isEqualTo("(12345) addr1");
    }

}