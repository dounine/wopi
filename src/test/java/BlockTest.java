import com.dounine.wopi.core.RSACoder;
import com.dounine.wopi.core.impl.BlockBuildImpl;
import com.dounine.wopi.core.impl.BlockInfoImpl;
import com.dounine.wopi.core.impl.DiskStorage;
import com.dounine.wopi.core.impl.ElementImpl;
import com.dounine.wopi.exception.BlockException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by lake on 17-4-21.
 */
public class BlockTest {

    private String publicStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQ1IqbQGlOXWBVIu+LW6wtX3PAreebE2vpsGPzoU2R7R5eckf17mlVvbfuuw3zaSG+YsKe4CniUCd4Dr2L6bPgqlf48QOWB9XTjW3VDWfGW6DNXkPMzmNXq3jEUOgXEJViSrkDoEo8TDYVKC76fzhu3jAShk/jJagtQ1CQaFc9iQIDAQAB";
    private String privateStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJDUiptAaU5dYFUi74tbrC1fc8Ct55sTa+mwY/OhTZHtHl5yR/XuaVW9t+67DfNpIb5iwp7gKeJQJ3gOvYvps+CqV/jxA5YH1dONbdUNZ8ZboM1eQ8zOY1ereMRQ6BcQlWJKuQOgSjxMNhUoLvp/OG7eMBKGT+MlqC1DUJBoVz2JAgMBAAECgYAJkaFexJCwlsbImThOcs4baVUSg+rregsFM5xG+n9PhuUcxFwwyYG4IoLGiSFQ1b9e/tNw/8qEo1sYOZEYb2Zs9cggn920yKzqbLIGfcY8gbafvGg4iTOkO+OdFyCZwxjGLJEDtZgQk1vBI8jFQPr6N3GxRMxm5pyIGG6uaHmSKQJBAONrjG+Jt2B5785gBpohYaPhjG7RHm20lRgEAy76ZI3iq5ZficPllHTRuM0KMLlaQVz8cP+916ST7NgQc8danhsCQQCjB/NsaK+R2yybrBDqbDcUB4imI4UGsBgYQQYGg5RB/IqjXsfd01Ke+EFsaancjqV/jTfnbrUgJuR8WZpfev0rAkBcsJ3pa/DgknJJ49ttAIQCPlB1bif/wKUvx5rgj89iAOu8ey30OOMcBcpTT37yXLXdR0prAZ4dhuj2WyZFL0rXAkBkMFetEKyMsye3x0dh+PzkJzkX+I4grQkxpowgZq4u2xcMDQxAy0RkA2aZTFoh+9vDBjML2+IdjyktLheLg9afAkEAw0dWPydTMzid8Q8kekaL02grlkomqxwf+K9R+oGk/hlNcZSV9TAYs5EA9kQVzZ4eLh55egeW3pf55QV85efO1w==";

    /**
     * 测试保存
     */
//    @Test
    public void testCreate() throws BlockException {
        BlockInfoImpl blockInfo = new BlockInfoImpl();
        blockInfo.setAuthor("lake");
        blockInfo.setCreateTime(LocalDateTime.now());
        blockInfo.setHash(Hex.encodeHexString(DigestUtils.sha1("lake2")));

        BlockBuildImpl blockBuild = new BlockBuildImpl();
        blockBuild.setStorage(new DiskStorage());
        blockBuild.create(blockInfo);
    }

//    @Test
    public void testPush() throws BlockException {
        BlockBuildImpl blockBuild = new BlockBuildImpl();
        blockBuild.setStorage(new DiskStorage());

        ElementImpl element = new ElementImpl();
        element.setAuthor("lake");
        element.setContent("这是我的密码");
        element.setCreateTime(LocalDateTime.now());

        blockBuild.push("41be905ed2febef70dcc1bac29f7eb97d7e5746d",element);
    }
//    @Test
    public void testPushRsa() throws Exception {
        BlockBuildImpl blockBuild = new BlockBuildImpl();
        blockBuild.setStorage(new DiskStorage());

        ElementImpl element = new ElementImpl();
        element.setAuthor("lake");
        byte[] bb = RSACoder.encryptByPublicKey("lake",publicStr);
        element.setContent(Base64.encodeBase64String(bb));
        element.setCreateTime(LocalDateTime.now());

        blockBuild.push("41be905ed2febef70dcc1bac29f7eb97d7e5746d",element);
    }

}
