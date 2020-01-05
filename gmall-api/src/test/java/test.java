import com.demo.gmall.bean.PmsBaseAttrInfo;
import com.demo.gmall.bean.PmsBaseAttrValue;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/10/20 14:18
 **/
public class test {
    public static void main(String[] args) {
        PmsBaseAttrInfo baseAttrInfo = new PmsBaseAttrInfo();
        baseAttrInfo.setId("1");
        baseAttrInfo.setAttrName("abvc");
        baseAttrInfo.setCatalog3Id(4+"");
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        BeanUtils.copyProperties(baseAttrInfo,pmsBaseAttrValue);
        System.out.println(pmsBaseAttrValue.toString());

    }
}
